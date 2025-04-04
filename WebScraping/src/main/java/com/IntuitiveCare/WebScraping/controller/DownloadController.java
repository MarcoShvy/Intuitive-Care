package com.IntuitiveCare.WebScraping.controller;

import com.IntuitiveCare.WebScraping.services.PdfDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api")
public class DownloadController {

    @Autowired
    private PdfDownloadService pdfDownloadService;

    @GetMapping("/download-and-zip/get")
    public ResponseEntity<String> downloadAndZipFiles() {
        try {
            pdfDownloadService.executeDownloadAndZip();
            return ResponseEntity.ok("Download e compactação finalizado! Disponivel em: "+ pdfDownloadService.getZipFilePath());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao processar" + e.getMessage());
        }
    }
}

