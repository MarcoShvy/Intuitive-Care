package com.IntuitiveCare.WebScraping;

import com.IntuitiveCare.WebScraping.config.AppConfig;
import com.IntuitiveCare.WebScraping.services.PdfDownloadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class WebScrapingApplicationTests {

	@Autowired
	private PdfDownloadService pdfDownloaderService;

	@Mock
	private AppConfig appConfig;

	@BeforeEach
	public void setup(){
		when(appConfig.getOutputDir()).thenReturn("downloads/");
		when(appConfig.getZipFilePath()).thenReturn("${user.home}/Downloads/Anexos.zip");
		when(appConfig.getWebsiteURL()).thenReturn("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos");
	}

	@Test
	public void testFetchPdfLinks() throws IOException {
		List<String> pdfLinks = pdfDownloaderService.findPDFLink();
		assertNotNull(pdfLinks);
	}

	@Test
	public void testDownloadPdf() throws IOException {
		List<String> testPdfLink = Collections.singletonList("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");
		List<File> downloadedFile = pdfDownloaderService.downloadPdfs(testPdfLink);
		assertNotNull(downloadedFile);
	}

	@Test
	public void testZipFiles() throws Exception {
		List<File> files = new ArrayList<>();
		File file = new File("downloads/teste.pdf");
		pdfDownloaderService.zipFiles(files, "downloads/Anexos.zip");
		assertTrue(new File("Downloads/Anexos.zip").exists());
	}
}

