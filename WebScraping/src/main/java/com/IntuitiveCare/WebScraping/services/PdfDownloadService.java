package com.IntuitiveCare.WebScraping.services;

import com.IntuitiveCare.WebScraping.config.AppConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfDownloadService {

    private final String websiteURL;
    private static final String ZIP_FILE = "download/file.zip";

    public PdfDownloadService(AppConfig config) {
        this.websiteURL = config.getWebsiteURL();
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
}
