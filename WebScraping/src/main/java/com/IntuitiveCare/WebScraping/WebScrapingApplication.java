package com.IntuitiveCare.WebScraping;

import com.IntuitiveCare.WebScraping.services.PdfDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebScrapingApplication implements CommandLineRunner {

	private final PdfDownloadService pdfDownloadService;

    public WebScrapingApplication(PdfDownloadService pdfDownloadService) {
        this.pdfDownloadService = pdfDownloadService;
    }

    public static void main(String[] args) {
		SpringApplication.run(WebScrapingApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		pdfDownloadService.executeTest();
	}
}
