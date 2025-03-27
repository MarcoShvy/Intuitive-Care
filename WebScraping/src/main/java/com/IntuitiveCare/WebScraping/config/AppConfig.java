package com.IntuitiveCare.WebScraping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${website.url}")
    private String websiteURL;

    public String getWebsiteURL(){
        return websiteURL;
    }
}
