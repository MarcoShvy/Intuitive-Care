package com.IntuitiveCare.WebScraping.services;

import com.IntuitiveCare.WebScraping.config.AppConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Service
public class PdfDownloadService {

    private final String websiteURL;
    private final String outputDirectory;
    private final String zipFilePath;

    public PdfDownloadService(AppConfig config) {
        this.websiteURL = config.getWebsiteURL();
        this.outputDirectory = config.getOutputDir();
        this.zipFilePath = config.getZipFilePath();
    }

    public List<String> findPDFLink() throws IOException {
        List<String> pdfLinks = new ArrayList<>();
        Document doc = Jsoup.connect(websiteURL).get();
        Elements links = doc.select("a[href$=.pdf]");

        for (Element link : links) {
            String pdfUrl = link.absUrl("href");
            if (pdfUrl.contains("Anexo_I") || pdfUrl.contains("Anexo_II"))
                pdfLinks.add(pdfUrl);
        }
        return pdfLinks;
    }


    public List<File> downloadPdfs(String pdfLink) throws IOException {
        try {
            String fileName = pdfLink.substring(pdfLink.lastIndexOf("/") + 1);
            File file = new File(outputDirectory + fileName);
            try (InputStream in = new URL(pdfLink).openStream()) {
                Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return (List<File>) file;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<File> downloadPdfsInParallel(List<String> pdfLinks) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<File>> tasks = new ArrayList<>();

        for (String pdfLink : pdfLinks) {
            tasks.add(() -> (File) downloadPdfs(pdfLinks.toString()));
        }

        // Inicia os downloads em paralelo
        List<Future<File>> futures = executorService.invokeAll(tasks);
        List<File> downloadedFiles = new ArrayList<>();

        for (Future<File> future : futures) {
            downloadedFiles.add(future.get()); // Obtém o arquivo baixado
        }

        executorService.shutdown();
        return downloadedFiles;
    }

    private void zipFiles(List<File> files, String zipFileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            for (File file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOut.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                }
            }
            System.out.println("Arquivos compactados em: " + zipFileName);
        }
    }
    public void executeDownloadAndZip() throws IOException, ExecutionException, InterruptedException {
        List<File> downloadedFiles = downloadPdfsInParallel(findPDFLink());
        zipFiles(downloadedFiles, zipFilePath);
    }

    public String getZipFilePath() {
        return zipFilePath;
    }
}