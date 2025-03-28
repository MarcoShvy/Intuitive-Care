package com.IntuitiveCare.WebScraping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${website.url}")
    private String websiteURL;

    @Value("${download.directory}")
    private String outputDir;

    @Value("${zip.file}")
    private String zipFilePath;

    public String getWebsiteURL(){
        return websiteURL;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getZipFilePath() {
        return zipFilePath;
    }
}
