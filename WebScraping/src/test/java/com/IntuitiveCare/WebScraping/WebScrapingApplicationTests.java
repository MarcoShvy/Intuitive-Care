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
		String testPdfLink = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
		File downloadedFile = (File) pdfDownloaderService.downloadPdfs(testPdfLink);
		assertNotNull(downloadedFile);
	}

	@Test
	public void testZipFiles() throws Exception {
		List<File> files = new ArrayList<>();
		files.add(new File("${user.home}/Downloads/teste.pdf"));
		Method zipFilesMethod = PdfDownloadService.class.getDeclaredMethod("zipFiles", List.class, String.class);
		zipFilesMethod.setAccessible(true);
		zipFilesMethod.invoke(pdfDownloaderService, files, "downloads/test.zip");
		assertTrue(new File("${user.home}/Downloads/teste.zip").exists());
	}
}

