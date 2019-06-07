package com.drydock.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drydock.entity.Job;
import com.drydock.entity.Project;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.commons.configuration2.DatabaseConfiguration;

@Component
public class PDFGenerator {
	@Autowired
	private DatabaseConfiguration databaseConfiguration;
	
	public static ByteArrayInputStream customerPDFReport(Project project)throws Exception {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
            // Add Text to PDF file ->
        	Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        	Paragraph para = new Paragraph( "Customer Table", font);
        	para.setAlignment(Element.ALIGN_CENTER);
        	document.add(para);
        	document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(3);
        	// Add PDF Table Header ->
            Stream.of("Job No", "Description", "Location")
	            .forEach(headerTitle -> {
		              PdfPCell header = new PdfPCell();
		              Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		              header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		              header.setHorizontalAlignment(Element.ALIGN_CENTER);
		              header.setBorderWidth(2);
		              header.setPhrase(new Phrase(headerTitle, headFont));
		              table.addCell(header);
	            });
            
            for (Job job : project.getProjectJobList()) {
            	PdfPCell idCell = new PdfPCell(new Phrase(job.getJobno().toString()));
            	idCell.setPaddingLeft(4);
            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);
 
                PdfPCell firstNameCell = new PdfPCell(new Phrase(job.getDescription()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstNameCell);
 
                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(job.getLocation())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	throw e;
        }
        
        OutputStream outStream = null;  
        ByteArrayOutputStream byteOutStream = null; 
        outStream = new FileOutputStream("D:/Development/DryDock/aaa.pdf");  
        byteOutStream = new ByteArrayOutputStream();  
        // writing bytes in to byte output stream  
        byteOutStream.write(out.toByteArray()); //data  
        byteOutStream.writeTo(outStream);  
        
        List<InputStream> list = new ArrayList<InputStream>();

		InputStream inputStreamOne = new FileInputStream(new File("D:/Development/DryDock/aaa.pdf"));
		list.add(inputStreamOne);
		InputStream inputStreamTwo = new FileInputStream(new File("D:/Development/DryDock/bbb.pdf"));
		list.add(inputStreamTwo);

		OutputStream outputStream = new FileOutputStream(new File("D:/Development/DryDock/ccc.pdf"));
		mergePdf(list, outputStream);
        
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private static void mergePdf(List<InputStream> list, OutputStream outputStream) throws DocumentException, IOException
    {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

        for (InputStream inputStream : list)
        {
            PdfReader pdfReader = new PdfReader(inputStream);
            for (int i = 1; i <= pdfReader.getNumberOfPages(); i++)
            {
                document.newPage();
                PdfImportedPage page = pdfWriter.getImportedPage(pdfReader, i);
                pdfContentByte.addTemplate(page, 0, 0);
            }
        }
        
        outputStream.flush();
        document.close();
        outputStream.close();
    }

}
