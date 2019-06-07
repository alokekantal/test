package com.drydock.util;

import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {
	String vesselName, projectDesc;
	
	public HeaderFooterPageEvent(String vesselName, String projectDesc){
		this.vesselName = vesselName;
		this.projectDesc = projectDesc;
	}

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
//		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Left"), 30, 800, 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(vesselName), 290, 810, 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(projectDesc), 290, 790, 0);
//		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 550, 800, 0);

		try {
			Image img = Image.getInstance("D:/Development/DryDock/report/image/ferry-boat-123059__340.jpg");
			img.scaleAbsolute(50f, 50f);
			img.setAbsolutePosition(20f, 785f);
			document.add(img);
			
			img = Image.getInstance("D:/Development/DryDock/report/image/ferry-boat-123059__340.jpg");
			img.scaleAbsolute(50f, 50f);
			img.setAbsolutePosition(520f, 785f);
			document.add(img);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	} 

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Report generation time"), 90, 40, 0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(DrydockWebUtil.milisToDateAsStringOrgSpec(new Date().getTime()+"", "dd-MMM-YYYY", "HH:mm")), 90, 20, 0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("page " + document.getPageNumber()), 550, 30, 0);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}