package com.IntuitiveCare.WebScraping;

import com.IntuitiveCare.WebScraping.services.PdfDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebScrapingApplication {
    public static void main(String[] args) {
		SpringApplication.run(WebScrapingApplication.class, args);
	}
}
