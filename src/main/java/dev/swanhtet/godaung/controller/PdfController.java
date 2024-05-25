package dev.swanhtet.godaung.controller;

import dev.swanhtet.godaung.services.impl.PdfServiceImpl;
import dev.swanhtet.godaung.services.interfaces.PdfServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {
	private final PdfServiceImpl pdfService;
	@GetMapping("/")
	@Tag(name = "PDF Generation")
	public ResponseEntity<?> generatePdf() throws IOException  {

		ByteArrayInputStream inputStream = pdfService.generateSystemUserPDFFIle();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=users.pdf");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(inputStream));
	}
}
