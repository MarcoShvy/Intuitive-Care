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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
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

    public void executeDownloadAndZip() throws Exception {
        List<File> downloadedFiles = downloadPdfs(findPDFLink());
        zipFiles(downloadedFiles, zipFilePath);
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


    public List<File> downloadPdfs(List<String> pdfLinks) throws IOException {
        List<File> downloadedFiles = new ArrayList<>();
        Files.createDirectories(Paths.get(outputDirectory));

        for (String pdfLink : pdfLinks) {
            String fileName = pdfLink.substring(pdfLink.lastIndexOf("/") + 1);
            File file = new File(outputDirectory + fileName);
            try (InputStream in = new URL(pdfLink).openStream()) {
                Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                downloadedFiles.add(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return downloadedFiles;
    }


    public void zipFiles(List<File> files, String zipFileName) throws IOException {
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

    public String getZipFilePath() {
        return zipFilePath;
    }
}