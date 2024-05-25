package dev.swanhtet.godaung.services.impl;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import dev.swanhtet.godaung.Config.ApplicationConfig;
import dev.swanhtet.godaung.model.Product;
import dev.swanhtet.godaung.repo.ProductRepository;
import dev.swanhtet.godaung.repo.UserRepository;
import dev.swanhtet.godaung.services.interfaces.PdfServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PdfServiceImpl implements PdfServices {

	private final ProductRepository productRepository;
	@Override
	public ByteArrayInputStream generateSystemUserPDFFIle() throws IOException {
	/*	String filePath = ApplicationConfig.pdfFilePath;


		File file = new File(filePath);
		File parent = file.getParentFile();

		if(parent == null || !parent.exists())
			parent.mkdirs();

*/		List<Product> productList = productRepository.findAll();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try{
			PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
			PdfDocument document = new PdfDocument(pdfWriter);
			Document document1 = new Document(document);

			document1.add(new Paragraph("Product in System"));

			Table table = new Table(7);
			table.addCell(new Cell().add(new Paragraph("id")));
			table.addCell(new Cell().add(new Paragraph("name")));
			table.addCell(new Cell().add(new Paragraph("created_at")));
			table.addCell(new Cell().add(new Paragraph("updated_at")));
			table.addCell(new Cell().add(new Paragraph("category")));
			table.addCell(new Cell().add(new Paragraph("description")));
			table.addCell(new Cell().add(new Paragraph("inventory_id")));


			for(Product product: productList){
				table.addCell(new Cell().add(new Paragraph(product.getId().toString())));
				table.addCell(new Cell().add(new Paragraph(product.getName())));
				table.addCell(new Cell().add(new Paragraph(product.getCreatedAt().toString())));
				table.addCell(new Cell().add(new Paragraph(product.getUpdatedAt().toString())));
				table.addCell(new Cell().add(new Paragraph(product.getCategory().toString())));
				table.addCell(new Cell().add(new Paragraph(product.getDescription())));
				table.addCell(new Cell()
						.add(new Paragraph(String.valueOf(product.getInventory_id()))));
			}
			document1.add(table);
			document1.close();
		}catch (Exception e ){
			log.error(e.getMessage());
			throw  new IOException("Failed to generate the PDF");
		}
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}

}
