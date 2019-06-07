package com.drydock.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.entity.CurrencyMaster;
import com.drydock.entity.Job;
import com.drydock.entity.JobAttachment;
import com.drydock.entity.JobComment;
import com.drydock.entity.JobCreationCheckboxes;
import com.drydock.entity.JobMaterialDetails;
import com.drydock.entity.JobProgressReport;
import com.drydock.entity.Project;
import com.drydock.entity.ProjectAttachment;
import com.drydock.entity.ProjectCurrencyConversion;
import com.drydock.entity.ProjectDockyard;
import com.drydock.entity.ReportDTO;
import com.drydock.entity.Shipmaster;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
public class ItextPdfUtil {
	
	public static final String  PDF_FONT_NAME="Tahoma";
	public static final float  PDF_FONT_SIZE_DATA=10;
	public static final float  PDF_FONT_SIZE_SECTION=12;
	public static final float  PDF_FONT_SIZE_LABEL=10;

	public static final String  PDF_FONT_DATA="data";
	public static final String  PDF_FONT_LABEL="label";
	public static final String  PDF_FONT_HYPERLINK="hyperlink";
	public static final String PDF_FONT_SECTION_HEADER="sectionHeader";

	public static final String  BCG_COLOR_SECTION_HEADER="darkGray";
	public static final String  BCG_COLOR_LABEL="gray";


	private static Font getFont(String fontType,float size){

		Font font=null;
		switch (fontType) {

		case PDF_FONT_LABEL:
			font = FontFactory.getFont(PDF_FONT_NAME, "Cp1253", true);
			font.setColor(BaseColor.BLACK);

			break;

		case PDF_FONT_HYPERLINK:
			font = FontFactory.getFont(PDF_FONT_NAME, "Cp1253", true);
			font.setColor(BaseColor.BLUE);

			break;

		case PDF_FONT_DATA:
			font = FontFactory.getFont(PDF_FONT_NAME, "Cp1253", true);
			font.setColor(BaseColor.BLACK);

			break;

		case PDF_FONT_SECTION_HEADER:
			font = FontFactory.getFont(PDF_FONT_NAME, "Cp1253", true);
			font.setColor(BaseColor.BLACK);

			break;

		default:
			font = FontFactory.getFont(PDF_FONT_NAME, "Cp1253", true);
			font.setColor(BaseColor.BLACK);

		}

		font.setSize(size);
		return font;

	}

	public static PdfPCell getCell(int dataTypeId,String cellValue, float fontSize, int colspan, int rowspan) 
			throws Exception{

		PdfPCell cell= null;

		switch (dataTypeId) {

		case DrydockConstant.DATA_TYPE_DATE:
			cell=new PdfPCell(new Paragraph(DrydockWebUtil.milisToDateAsStringOrgSpec(cellValue, "dd-MM-yyyy", "HH:MM")));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			break;

		case DrydockConstant.DATA_TYPE_DECIMAL:
			cell= new PdfPCell(new Paragraph(cellValue,getFont(PDF_FONT_SECTION_HEADER,PDF_FONT_SIZE_DATA)));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			break;

		case DrydockConstant.DATA_TYPE_FILE:
			cell= new PdfPCell(new Paragraph(cellValue,getFont(PDF_FONT_SECTION_HEADER,PDF_FONT_SIZE_DATA)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			break;

		case DrydockConstant.DATA_TYPE_INTEGER:
			cell= new PdfPCell(new Paragraph(cellValue,getFont(PDF_FONT_SECTION_HEADER,PDF_FONT_SIZE_DATA)));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

			break;
		case DrydockConstant.DATA_TYPE_STRING:
			cell= new PdfPCell(new Paragraph(cellValue,getFont(PDF_FONT_SECTION_HEADER,PDF_FONT_SIZE_DATA)));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			break;

		default:
			cell= new PdfPCell(new Paragraph(cellValue,getFont(PDF_FONT_SECTION_HEADER,PDF_FONT_SIZE_DATA)));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			break;
		}
		cell.setPaddingLeft(10);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingBottom(5);
		cell.setBorderWidth(0.1f);
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);

		return cell;

	}


	private static PdfPCell getHyperLinkCell(String cellValue, int colspan, int rowspan){

		Chunk underline = new Chunk("View Attachment",getFont(PDF_FONT_HYPERLINK,PDF_FONT_SIZE_DATA));
		underline.setUnderline(0.1f, -2f);
		Anchor hyperLink = new Anchor(underline);
		hyperLink.setReference(cellValue);

		Paragraph file=new Paragraph();
		file.add(hyperLink);
		PdfPCell cell= new PdfPCell(file);
		cell.setPaddingLeft(10);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);
		return cell;

	}

	private static Color getColor(String colorName){

		Color color=null;

		switch (colorName) {

		case BCG_COLOR_SECTION_HEADER:
			color=new Color(118, 127, 142);
			break;

		case BCG_COLOR_LABEL:
			color=new Color(208, 219, 237);
			break;
		}

		return color;
	}


	public static void createSectionHeader(Document document,String sectionName, int colspan, int rowspan) 
			throws Exception{
		try{

			PdfPTable sectionHeader=new PdfPTable(1);
			sectionHeader.setWidthPercentage(100); 
			sectionHeader.setSpacingAfter(10f);
			PdfPCell cell= getCell(DrydockConstant.DATA_TYPE_STRING, sectionName, PDF_FONT_SIZE_SECTION, colspan, rowspan);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			sectionHeader.addCell(cell);
			sectionHeader.setSpacingAfter(10f); 
			document.add(sectionHeader);
		}
		catch(Exception exp){
			throw exp;
		}


	}


	public static void  createCell(Document document, PdfPTable table, String value, boolean isAttachment, boolean headerFlag, int colspan, int rowspan) 
			throws Exception{

		try{
			if(headerFlag){
				PdfPCell cellHeader= getCell(DrydockConstant.DATA_TYPE_STRING, value, PDF_FONT_SIZE_LABEL, colspan, rowspan);
				cellHeader.setBackgroundColor(BaseColor.GRAY);
				table.addCell(cellHeader);
			}else{
				if(isAttachment && null!=value && value.length()>0){
					PdfPCell cell=getHyperLinkCell(value, colspan, rowspan);
					table.addCell(cell);
				}else{
					PdfPCell cellData= getCell(DrydockConstant.DATA_TYPE_STRING, value, PDF_FONT_SIZE_DATA, colspan, rowspan);
					table.addCell(cellData);

				}
			}
		}
		catch(Exception exp){
			throw exp;
		}
	}
	
	public static void margeAttachment(String filePath, Document document, PdfWriter writer) throws IOException{
		InputStream inputStream = new FileInputStream(new File(filePath));
		PdfReader pdfReader = new PdfReader(inputStream);
		PdfContentByte pdfContentByte = writer.getDirectContent();
        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++)
        {
            document.newPage();
            PdfImportedPage page = writer.getImportedPage(pdfReader, i);
            pdfContentByte.addTemplate(page, 0, 0);
        }
	}

	public static void createProjectReport(Document document, Project project, BasicInfoDTO info, String basePath, PdfWriter writer, List<JobCreationCheckboxes> checkBoxList, ReportDTO reportFields) throws Exception{
		PdfPTable table=null;
		List<SimpleEntry<String, String>> toc = new ArrayList();
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_CENTER);
		Chunk chunk = new Chunk(
				"Dry Docking");
		paragraph.add(chunk);

		table = new PdfPTable(1);
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell();
		cell.setMinimumHeight(90);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.addElement(paragraph);
		table.addCell(cell);
		document.add(table);
		//add ship image to 1st page
		boolean shipImagePresent = false;
		if(null!=project.getProjectAttachmentList() && !project.getProjectAttachmentList().isEmpty()){
			for (ProjectAttachment projectAttachment : project.getProjectAttachmentList()) {
				String fileExt = projectAttachment.getAttachmentName().substring(projectAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
				if((fileExt.equals("jpg") || fileExt.equals("jpeg") || fileExt.equals("png")) && !shipImagePresent){
					Image img = Image.getInstance(basePath+"/"+info.getOrgId()+"/"+project.getShipid()+"/"+"project_attachment/"+project.getId()+"/"+projectAttachment.getRelativepath());
					img.scaleAbsolute(250f, 250f);
					img.setAbsolutePosition(150f, 400f);
					document.add(img);
					shipImagePresent = true;
				}
			}
		}
		//if no image present add a default image 
		if(!shipImagePresent){
			Image imgDefault = Image.getInstance("D:/Development/DryDock/report/image/ferry-boat-123059__340.jpg");
			imgDefault.scaleAbsolute(250f, 250f);
			imgDefault.setAbsolutePosition(150f, 400f);
	        document.add(imgDefault);
	        shipImagePresent = true;
		}

		
        
        for(int i=0;i<=21;i++){
        	document.add(new Paragraph(" "));
        }

        paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_CENTER);
		chunk = new Chunk("Project Description");
		paragraph.add(chunk);

		table = new PdfPTable(1);
		table.setWidthPercentage(100);
		cell = new PdfPCell();
		cell.setMinimumHeight(90);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.addElement(paragraph);
		table.addCell(cell);
		document.add(table);
		
		
		document.newPage();

		Shipmaster ship = project.getShipDetails();
		if(null!=ship && reportFields.isVesselParticulars()){
			toc.add(new SimpleEntry("Vessel Particulars", writer.getPageNumber()));
			createSectionHeader(document, "Vessel Particulars", 1, 1);
			table=new PdfPTable(4);
			table.setWidthPercentage(100); 
			table.setSpacingAfter(10f); 
			if(0 != ship.getV_imo_no() && reportFields.isV_imo_no()){
				createCell(document, table, "IMO Number", false, true, 1, 1);
				createCell(document, table, ship.getV_imo_no()+"", false, false, 1, 1);
			}
			
			if(ship.getName() != null && reportFields.isName()){
				createCell(document, table, "Vessel Name", false, true, 1, 1);
				createCell(document, table, ship.getName(), false, false, 1, 1);
			}
			
			if(ship.getDescription() != null && reportFields.isDescription()){
				createCell(document, table, "Vessel Description", false, true, 1, 1);
				createCell(document, table, ship.getDescription(), false, false, 1, 1);
			}
			
			if(ship.getMmsi_no() != 0 && reportFields.isMmsi_no()){
				createCell(document, table, "MMSI Number", false, true, 1, 1);
				createCell(document, table, ship.getMmsi_no()+"", false, false, 1, 1);
			}
			
			if(ship.getCall_sign() != null && reportFields.isCall_sign()){
				createCell(document, table, "Call Sign", false, true, 1, 1);
				createCell(document, table, ship.getCall_sign()+"", false, false, 1, 1);
			}
			
			if(ship.getOfficial_no() != null && reportFields.isOfficial_no()){
				createCell(document, table, "Official Number", false, true, 1, 1);
				createCell(document, table, ship.getOfficial_no()+"", false, false, 1, 1);
			}
			
			if(ship.getVesselTypeDesc() != null && reportFields.isV_type()){
				createCell(document, table, "Vessel Type", false, true, 1, 1);
				createCell(document, table, ship.getVesselTypeDesc()+"", false, false, 1, 1);
			}
			
			if(ship.getOwner_imo_no() != 0 && reportFields.isOwner_imo_no()){
				createCell(document, table, "Registered Owner's IMO number", false, true, 1, 1);
				createCell(document, table, ship.getOwner_imo_no()+"", false, false, 1, 1);
			}
			
			if(ship.getOwner_name() != null && reportFields.isOwner_name()){
				createCell(document, table, "Register Owner Name", false, true, 1, 1);
				createCell(document, table, ship.getOwner_name()+"", false, false, 1, 1);
			}
			
			if(ship.getSat_f_77() != null && reportFields.isSat_f_77()){
				createCell(document, table, "Sat F 77", false, true, 1, 1);
				createCell(document, table, ship.getSat_f_77()+"", false, false, 1, 1);
			}
			
			if(ship.getSat_c() != null && reportFields.isSat_c()){
				createCell(document, table, "Sat C", false, true, 1, 1);
				createCell(document, table, ship.getSat_c()+"", false, false, 1, 1);
			}
			
			if(ship.getFleet_broadband() != null && reportFields.isFleet_broadband()){
				createCell(document, table, "Fleet Broadband", false, true, 1, 1);
				createCell(document, table, ship.getFleet_broadband()+"", false, false, 1, 1);
			}
			
			if(ship.getSat_c_emailID() != null && reportFields.isSat_c_emailID()){
				createCell(document, table, "Sat C Mail ID", false, true, 1, 1);
				createCell(document, table, ship.getSat_c_emailID()+"", false, false, 1, 1);
			}
			
			if(ship.getEmailID() != null && reportFields.isEmailID()){
				createCell(document, table, "Mail ID", false, true, 1, 1);
				createCell(document, table, ship.getEmailID()+"", false, false, 1, 1);
			}
			
			if(ship.getClass() != null && reportFields.isClass_notations()){
				createCell(document, table, "Class", false, true, 1, 1);
				createCell(document, table, ship.getClass()+"", false, false, 1, 1);
			}
			
			if(ship.getClass_notations() != null && reportFields.isClass_notations()){
				createCell(document, table, "Class Notations", false, true, 1, 1);
				createCell(document, table, ship.getClass_notations()+"", false, false, 1, 1);
			}
			
			if(ship.getClassi_Id_No() != null && reportFields.isClassi_Id_No()){
				createCell(document, table, "Classification ID.No", false, true, 1, 1);
				createCell(document, table, ship.getClassi_Id_No()+"", false, false, 1, 1);
			}
			
			if(ship.getFlag() != null && reportFields.isFlag()){
				createCell(document, table, "Flag", false, true, 1, 1);
				createCell(document, table, ship.getFlag()+"", false, false, 1, 1);
			}
			
			if(ship.getPort_of_registry() != null && reportFields.isPort_of_registry()){
				createCell(document, table, "Port Of Registry", false, true, 1, 1);
				createCell(document, table, ship.getPort_of_registry()+"", false, false, 1, 1);
			}
			
			if(ship.getYear_built() != null && reportFields.isYear_built()){
				createCell(document, table, "Year Built", false, true, 1, 1);
				createCell(document, table, ship.getYear_built()+"", false, false, 1, 1);
			}
			
			if(ship.getKeel_laying_date() != null && reportFields.isKeel_laying_date()){
				createCell(document, table, "Keel Laying Date", false, true, 1, 1);
				createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(ship.getKeel_laying_date()+"", "dd-MM-yyyy", "HH:MM")+"", false, false, 1, 1);
			}
			
			if(ship.getVessel_delivery_date() != null && reportFields.isVessel_delivery_date()){
				createCell(document, table, "Vessel Delivery Date", false, true, 1, 1);
				createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(ship.getVessel_delivery_date()+"", "dd-MM-yyyy", "HH:MM")+"", false, false, 1, 1);
			}
			
			if(ship.getHull_type() != null && reportFields.isHull_type()){
				createCell(document, table, "Hull Type(SingleHull/Double Sided/Double Bottom/Double Hull)", false, true, 1, 1);
				createCell(document, table, ship.getHull_type()+"", false, false, 1, 1);
			}
			
			if(ship.getLength_overall() != null && reportFields.isLength_overall()){
				createCell(document, table, "Length Overall", false, true, 1, 1);
				createCell(document, table, ship.getLength_overall()+"", false, false, 1, 1);
			}
			
			if(ship.getBreadth_MLD() != null && reportFields.isBreadth_MLD()){
				createCell(document, table, "Breadth MLD", false, true, 1, 1);
				createCell(document, table, ship.getBreadth_MLD()+"", false, false, 1, 1);
			}
			
			if(ship.getDepth() != null && reportFields.isDepth()){
				createCell(document, table, "Depth", false, true, 1, 1);
				createCell(document, table, ship.getDepth()+"", false, false, 1, 1);
			}
			
			if(ship.getSummer_draft_M() != null && reportFields.isSummer_draft_M()){
				createCell(document, table, "Summer Draft (M)", false, true, 1, 1);
				createCell(document, table, ship.getSummer_draft_M()+"", false, false, 1, 1);
			}
			
			if(ship.getSummer_DWT_MT() != null && reportFields.isSummer_DWT_MT()){
				createCell(document, table, "Summer DWT (MT)", false, true, 1, 1);
				createCell(document, table, ship.getSummer_DWT_MT()+"", false, false, 1, 1);
			}
			
			if(ship.getInternational_GRT() != null && reportFields.isInternational_GRT()){
				createCell(document, table, "International GRT", false, true, 1, 1);
				createCell(document, table, ship.getInternational_GRT()+"", false, false, 1, 1);
			}
			
			if(ship.getInternational_NRT() != null && reportFields.isInternational_NRT()){
				createCell(document, table, "International NRT", false, true, 1, 1);
				createCell(document, table, ship.getInternational_NRT()+"", false, false, 1, 1);
			}
			
			if(ship.getLife_boat_cap() != 0 && reportFields.isLife_boat_cap()){
				createCell(document, table, "Life Boat Capacity", false, true, 1, 1);
				createCell(document, table, ship.getLife_boat_cap()+"", false, false, 1, 1);
			}
			
			if(ship.getV_short_name() != null && reportFields.isV_short_name()){
				createCell(document, table, "Vessel Short Name", false, true, 1, 1);
				createCell(document, table, ship.getV_short_name()+"", false, false, 1, 1);
			}
			
			if(ship.getAccount_code_old() != null && reportFields.isAccount_code_old()){
				createCell(document, table, "Account Code Old", false, true, 1, 1);
				createCell(document, table, ship.getAccount_code_old()+"", false, false, 1, 1);
			}
			
			if(ship.getAccount_code_new() != null && reportFields.isAccount_code_new()){
				createCell(document, table, "Account Code New", false, true, 1, 1);
				createCell(document, table, ship.getAccount_code_new()+"", false, false, 1, 1);
			}
			
			if(ship.getTel_fac_code() != null && reportFields.isTel_fac_code()){
				createCell(document, table, "Tel FAC Code", false, true, 1, 1);
				createCell(document, table, ship.getTel_fac_code()+"", false, false, 1, 1);
			}
			
			if(ship.getMaxEmailSizeInMB() != null && reportFields.isMaxEmailSizeInMB()){
				createCell(document, table, "Max Email Size (MB)", false, true, 1, 1);
				createCell(document, table, ship.getMaxEmailSizeInMB()+"", false, false, 1, 1);
			}
			
			if(ship.getDailyDataLimitInMB() != null && reportFields.isDailyDataLimitInMB()){
				createCell(document, table, "Daily Data Limit (MB)", false, true, 1, 1);
				createCell(document, table, ship.getDailyDataLimitInMB()+"", false, false, 1, 1);
			}
			
			if(ship.getEmail1() != null && reportFields.isEmail1()){
				createCell(document, table, "Email 1", false, true, 1, 1);
				createCell(document, table, ship.getEmail1()+"", false, false, 1, 1);
			}
			
			if(ship.getEmail2() != null && reportFields.isEmail2()){
				createCell(document, table, "Email 2", false, true, 1, 1);
				createCell(document, table, ship.getEmail2()+"", false, false, 1, 1);
			}
			
			if(ship.getPhoneNo() != null && reportFields.isPhoneNo()){
				createCell(document, table, "Phone No", false, true, 1, 1);
				createCell(document, table, ship.getPhoneNo()+"", false, false, 1, 1);
			}
			
			if(ship.getPhoneNo1() != null && reportFields.isPhoneNo1()){
				createCell(document, table, "Phone No 1", false, true, 1, 1);
				createCell(document, table, ship.getPhoneNo1()+"", false, false, 1, 1);
			}
			
			if(ship.getPhoneNo2() != null && reportFields.isPhoneNo2()){
				createCell(document, table, "Phone No 2", false, true, 1, 1);
				createCell(document, table, ship.getPhoneNo2()+"", false, false, 1, 1);
			}		
			document.add(table);
			document.newPage(); 
		}
		if(reportFields.isDdParameter()){
			toc.add(new SimpleEntry("DD Parameters", writer.getPageNumber()));
			createSectionHeader(document, "DD Parameters", 1, 1);
			
			table=new PdfPTable(4);
			table.setWidthPercentage(100); 
			table.setSpacingAfter(10f); 
			if(0 != project.getEstimatedStart()){
				createCell(document, table, "Estimated Start", false, true, 1, 1);
				createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(project.getEstimatedStart()+"", "dd-MM-yyyy", "HH:MM"), false, false, 1, 1);
			}
			if(0 != project.getActualStart()){
				createCell(document, table, "Actual Start", false, true, 1, 1);
				createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(project.getActualStart()+"", "dd-MM-yyyy", "HH:MM"), false, false, 1, 1);
			}
			if(0 != project.getEstimatedfinish()){
				createCell(document, table, "Estimated finish", false, true, 1, 1);
				createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(project.getEstimatedfinish()+"", "dd-MM-yyyy", "HH:MM"), false, false, 1, 1);
			}
			if(0 != project.getActualfinish()){
				createCell(document, table, "Actual Finish", false, true, 1, 1);
				createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(project.getActualfinish()+"", "dd-MM-yyyy", "HH:MM"), false, false, 1, 1);
			}
			if(0 != project.getCurrencyMasterId()){
				createCell(document, table, "Project Currency", false, true, 1, 1);
				createCell(document, table, project.getCurrencyMasterDesc(), false, false, 3, 1);
			}					
			document.add(table);
			
			if(project.getProjectDockyardList() != null && !project.getProjectDockyardList().isEmpty()){
				table=new PdfPTable(4);
				table.setWidthPercentage(100); 
				table.setSpacingAfter(10f); 
				
				createCell(document, table, "Yard", false, true, 1, 1);
				createCell(document, table, "Conatct", false, true, 1, 1);
				createCell(document, table, "Remark", false, true, 1, 1);
				createCell(document, table, "Default Currency", false, true, 1, 1);
				for(ProjectDockyard projectDockyard : project.getProjectDockyardList()){
					createCell(document, table, projectDockyard.getDockyardDesc(), false, false, 1, 1);
					createCell(document, table, projectDockyard.getContactDetails(), false, false, 1, 1);
					createCell(document, table, projectDockyard.getRemarks(), false, false, 1, 1);
					createCell(document, table, projectDockyard.getDefaultCurrencyDesc(), false, false, 1, 1);			
				}				
				document.add(table);				
			}
			
			
			if(project.getProjectCurrencyConversionList() != null && !project.getProjectCurrencyConversionList().isEmpty()){
				table=new PdfPTable(3);
				table.setWidthPercentage(100); 
				table.setSpacingAfter(10f); 
				
				createCell(document, table, "From Currency", false, true, 1, 1);
				createCell(document, table, "To Currency", false, true, 1, 1);
				createCell(document, table, "Conversion Rate", false, true, 1, 1);
				for(ProjectCurrencyConversion projectCurrencyConversion : project.getProjectCurrencyConversionList()){
					if(projectCurrencyConversion.getConversionRate() != 0.0f && projectCurrencyConversion.getConversionRate() != null){
						createCell(document, table, projectCurrencyConversion.getFromcurrencyDesc(), false, false, 1, 1);
						createCell(document, table, projectCurrencyConversion.getTocurrencyDesc(), false, false, 1, 1);
						createCell(document, table, projectCurrencyConversion.getConversionRate() + "", false, false, 1, 1);	
					}							
				}				
				document.add(table);				
			}
		}
		if(reportFields.isProjectDetail()){
			if(reportFields.isPreamble()){
				toc.add(new SimpleEntry("Preamble", writer.getPageNumber()));
				createSectionHeader(document, "Preamble", 1, 1);			
				// add Preamble Editor data to pdf 
				HTMLWorker htmlWorker1 = new HTMLWorker(document);
			    htmlWorker1.parse(new StringReader(project.getPreamble()));
				document.newPage();
			}
			
			if(null!=project.getProjectAttachmentList() && !project.getProjectAttachmentList().isEmpty() && reportFields.isProjectAttachments()){
				//add project image file 
				table=new PdfPTable(2);
				table.setWidthPercentage(100); 
				int cellCount=0;
				String lastAttachmentDesc=null;
				for (ProjectAttachment projectAttachment : project.getProjectAttachmentList()) {
					String fileExt = projectAttachment.getAttachmentName().substring(projectAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
					if(fileExt.equals("jpg") || fileExt.equals("jpeg") || fileExt.equals("png")){
						Image image = Image.getInstance(basePath+"/"+info.getOrgId()+"/"+project.getShipid()+"/"+"project_attachment/"+project.getId()+"/"+projectAttachment.getRelativepath());
						image.scaleAbsolute(150f, 150f);
						image.setAbsolutePosition(150f, 400f);		        
						PdfPCell imageCell = new PdfPCell(image);	
						imageCell.setPaddingTop(20);
						imageCell.setPaddingRight(30);
						imageCell.setPaddingBottom(20);
						imageCell.setPaddingLeft(30);
						table.addCell(imageCell);
						cellCount++;
						if(cellCount%2==0){
							createCell(document, table, lastAttachmentDesc, false, false, 1, 1);
							createCell(document, table, projectAttachment.getAttachmentDescription(), false, false, 1, 1);
						}
						lastAttachmentDesc=projectAttachment.getAttachmentDescription();
					}
				}
				if(cellCount%2!=0){
					createCell(document, table, "", false, false, 1, 1);
					createCell(document, table, lastAttachmentDesc, false, false, 1, 1);
					createCell(document, table, "", false, false, 1, 1);
				}
				document.add(table);
				//project file	
				document.newPage();
				table=new PdfPTable(2);
				table.setWidthPercentage(100); 
				table.setSpacingAfter(10f); 
				createCell(document, table, "File Name", false, true, 1, 1);
				createCell(document, table, "View File", false, true, 1, 1);
				for (ProjectAttachment projectAttachment : project.getProjectAttachmentList()) {
					String fileExt = projectAttachment.getAttachmentName().substring(projectAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
					if(!fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png") && !fileExt.equals("pdf")){
						createCell(document, table, projectAttachment.getAttachmentName(), false, false, 1, 1);
						createCell(document, table, info.getOrgId()+"/"+project.getShipid()+"/"+"project_attachment/"+project.getId()+"/"+projectAttachment.getRelativepath(), true, false, 1, 1);
					}			
				}

				document.add(table);
				
				for (ProjectAttachment projectAttachment : project.getProjectAttachmentList()) {
					String fileExt = projectAttachment.getAttachmentName().substring(projectAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
					if(!fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png") && fileExt.equals("pdf")){
						margeAttachment(basePath + File.separator + info.getOrgId()+"/"+project.getShipid()+"/"+"project_attachment/"+project.getId()+"/"+projectAttachment.getRelativepath(), document, writer);
					}			
				}
				document.newPage();
			}
		}		
		
		
/*<<<<<<< HEAD
        if(cellCount%2!=0){
        	createCell(document, table, "", false, false, 1, 1);
        	createCell(document, table, lastAttachmentDesc, false, false, 1, 1);
        	createCell(document, table, "", false, false, 1, 1);
        }
		document.add(table);
		
		//project file	
		document.newPage();
		table=new PdfPTable(2);
		table.setWidthPercentage(100); 
		table.setSpacingAfter(10f); 
		createCell(document, table, "File Name", false, true, 1, 1);
		createCell(document, table, "View File", false, true, 1, 1);
		for (ProjectAttachment projectAttachment : project.getProjectAttachmentList()) {
			String fileExt = projectAttachment.getAttachmentName().substring(projectAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
			if(!fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png")){
				createCell(document, table, projectAttachment.getAttachmentName(), false, false, 1, 1);
				createCell(document, table, info.getOrgId()+"/"+project.getShipid()+"/"+"project_attachment/"+project.getId()+"/"+projectAttachment.getRelativepath(), true, false, 1, 1);
			}			
		}		
		document.add(table);
		
		
		document.newPage();
=======
>>>>>>> b7ff12e34a33831a40dd2ad1c4ad0dc8134f4841*/
		
		if(null!=project.getProjectJobList() &&!project.getProjectJobList().isEmpty() && reportFields.isSpecificationOfRepairs()){
			List<Job> jobList = project.getProjectJobList();
			for (Job job : jobList) {
				ReportDTO currentJob = reportFields.getJobList().stream().filter(selectedJob -> (job.getJobno().equals(selectedJob.getSelectedJobNo())  && selectedJob.isSpecificationOfRepairs())).findAny().orElse(null);
				
				
				
				if(currentJob != null){
					toc.add(new SimpleEntry(job.getJobno(), writer.getPageNumber()));
					createSectionHeader(document, "Specification of Repairs", 1, 1);
					
					if(currentJob.isJobGeneralInfo()){
						table=new PdfPTable(4);
						table.setWidthPercentage(100); 
						table.setSpacingAfter(10f); 
						if(currentJob.isJobno()){
							createCell(document, table, "Job #", false, true, 1, 1);
							createCell(document, table, job.getJobno(), false, false, 1, 1);
						}
						
						if(currentJob.isMake()){
							createCell(document, table, "Make", false, true, 1, 1);
							createCell(document, table, job.getMake(), false, false, 1, 1);
						}
						

						if(currentJob.isComponent()){
							createCell(document, table, "Component Data (Make and Number)", false, true, 1, 1);
							createCell(document, table, job.getOrgComponent().getCode() +" - "+ job.getOrgComponent().getDescription(), false, false, 1, 1);
						}
						
						if(currentJob.isModel()){
							createCell(document, table, "Model", false, true, 1, 1);
							createCell(document, table, job.getModel(), false, false, 1, 1);
						}
						
						if(currentJob.isMakeModelDescription()){
							createCell(document, table, "Description", false, true, 1, 1);
							createCell(document, table, job.getMakeModelDescription(), false, false, 1, 1);
						}

						if(currentJob.isLocation()){
							createCell(document, table, "Location", false, true, 1, 1);
							createCell(document, table, job.getLocation(), false, false, 1, 1);
						}
						
						if(currentJob.isEquipment()){
							createCell(document, table, "Equipment Description", false, true, 1, 1);
							createCell(document, table, job.getEquipment(), false, false, 1, 1);
						}
						
						if(currentJob.isJobDescription()){
							createCell(document, table, "Job Short Description", false, true, 1, 1);
							createCell(document, table, job.getDescription(), false, false, 3, 1);
						}
						
						if(currentJob.isJobDate()){
							createCell(document, table, "Job Date", false, true, 1, 1);
							createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(job.getJobdate()+"", "dd-MM-yyyy", "HH:MM") , false, false, 3, 1);
						}
						
						if(currentJob.isExternalReference()){
							createCell(document, table, "External Reference", false, true, 1, 1);
							createCell(document, table, job.getExternalReference(), false, false, 3, 1);
						}
						
						document.add(table);
						
						//check box
						if(job.getCheckboxes() != null && currentJob.isCheckBox()){
							document.newPage();
							List<String> chkBoxList = Arrays.asList(job.getCheckboxes().split(","));
							if(currentJob.isToBeInclude()){
								table=new PdfPTable(4);
								table.setWidthPercentage(100); 
								table.setSpacingAfter(10f); 
								createCell(document, table, "To Be Include", false, true, 5, 1);					
								for(JobCreationCheckboxes checkBox: checkBoxList){
									if(checkBox.getKey().equals("To be include")){
										Font checkFont = new Font(Font.FontFamily.ZAPFDINGBATS, 12);
										Paragraph p = new Paragraph();
										if(chkBoxList.contains(checkBox.getId()+"")){
											p.add(new Chunk("4", checkFont));
										} else {
											p.add(new Chunk("6", checkFont));
										}
										p.add(checkBox.getDescription());
										PdfPCell cellBox= new PdfPCell(p);
										cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
										table.addCell(cellBox);					
									}
								}
								table.addCell(new PdfPCell());
								document.add(table);
							}
								
							if(currentJob.isMaterials()){
								table=new PdfPTable(4);
								table.setWidthPercentage(100); 
								table.setSpacingAfter(10f); 
								createCell(document, table, "Materials", false, true, 5, 1);
								for(JobCreationCheckboxes checkBox: checkBoxList){
									if(checkBox.getKey().equals("Materials")){
										Font checkFont = new Font(Font.FontFamily.ZAPFDINGBATS, 12);
										Paragraph p = new Paragraph();
										if(chkBoxList.contains(checkBox.getId()+"")){
											p.add(new Chunk("4", checkFont));
										} else {
											p.add(new Chunk("6", checkFont));
										}
										p.add(checkBox.getDescription());
										PdfPCell cellBox= new PdfPCell(p);
										cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
										table.addCell(cellBox);							
									}
								}
								table.addCell(new PdfPCell());
								table.addCell(new PdfPCell());
								document.add(table);
							}
							
							if(currentJob.isTheWorkToBeSurveyedAlsoBy()){
								table=new PdfPTable(4);
								table.setWidthPercentage(100); 
								table.setSpacingAfter(10f); 
								createCell(document, table, "The work to be surveyed also by", false, true, 5, 1);
								for(JobCreationCheckboxes checkBox: checkBoxList){
									if(checkBox.getKey().equals("The work to be surveyed also by")){
										Font checkFont = new Font(Font.FontFamily.ZAPFDINGBATS, 12);
										Paragraph p = new Paragraph();
										if(chkBoxList.contains(checkBox.getId()+"")){
											p.add(new Chunk("4", checkFont));
										} else {
											p.add(new Chunk("6", checkFont));
										}
										p.add(checkBox.getDescription());
										PdfPCell cellBox= new PdfPCell(p);
										cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
										table.addCell(cellBox);					
									}
								}
								document.add(table);
							}
						}
					}
					
					if(currentJob.isJobSpecificationDetail()){
						document.newPage();
						createSectionHeader(document, "Job Specification Details", 1, 1);				
						// add job specification data to pdf 
						HTMLWorker jobSpecification = new HTMLWorker(document);
						jobSpecification.parse(new StringReader(job.getDetailedSpecification()));
					}	
					
					if(job.getJobAttachmentList() != null && currentJob.isJobSpecificationAttachment()){
						document.newPage();
						createSectionHeader(document, "Job Specification Attachment", 1, 1);	
						//add job image file 						
						table=new PdfPTable(2);
						table.setWidthPercentage(100);
						table.setSpacingAfter(10f);
						int cellCountJob=0;
						String lastAttachmentDescJob=null;
						for (JobAttachment jobAttachment : job.getJobAttachmentList()) {
							String fileExt = jobAttachment.getAttachmentName().substring(jobAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
							if((fileExt.equals("jpg") || fileExt.equals("jpeg") || fileExt.equals("png")) && jobAttachment.getAttachmentType().equals("SPEC")){				
								Image image = Image.getInstance(basePath+"/"+info.getOrgId()+"/"+project.getShipid()+"/"+project.getId()+"/"+job.getId()+"/"+jobAttachment.getRelativepath());
								image.scaleAbsolute(150f, 150f);
								image.setAbsolutePosition(150f, 400f);		        
						        PdfPCell imageCell = new PdfPCell(image);	
						        imageCell.setPaddingTop(20);
						        imageCell.setPaddingRight(30);
						        imageCell.setPaddingBottom(20);
						        imageCell.setPaddingLeft(30);
						        table.addCell(imageCell);
						        cellCountJob++;
							}
						}
				        if(cellCountJob%2!=0){
				        	createCell(document, table, "", false, false, 1, 1);
				        }
						document.add(table);
						
						//job attachment list
						table=new PdfPTable(2);
						table.setWidthPercentage(100); 
						table.setSpacingAfter(10f); 
						createCell(document, table, "File Name", false, true, 1, 1);
						createCell(document, table, "View File", false, true, 1, 1);
						for (JobAttachment jobAttachment : job.getJobAttachmentList()) {
							String fileExt = jobAttachment.getAttachmentName().substring(jobAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
							if(!fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png") && jobAttachment.getAttachmentType().equals("SPEC")){
								createCell(document, table, jobAttachment.getAttachmentName(), false, false, 1, 1);
								createCell(document, table, info.getOrgId()+"/"+project.getShipid()+"/"+project.getId()+"/"+job.getId()+"/"+jobAttachment.getRelativepath(), true, false, 1, 1);
							}
						}					
						document.add(table);	
						document.newPage();
					}
					
					//meterial detail
					if(null!=job.getJobMaterialDetailsList() && !job.getJobMaterialDetailsList().isEmpty() && currentJob.isMeterialDetail()){
						createSectionHeader(document, "Meterial Detail", 1, 1);
						int meterialDetailColumnCount = getMeterialDetailColumnCount(currentJob);
						table=new PdfPTable(meterialDetailColumnCount);
						table.setWidthPercentage(100); 
						table.setSpacingAfter(10f); 
						if(currentJob.isMeterialDetailMake()){
							createCell(document, table, "Make", false, true, 1, 1);						
						}
						if(currentJob.isMeterialDetailModel()){
							createCell(document, table, "Model", false, true, 1, 1);
						}
						if(currentJob.isMeterialDetailPartNo()){
							createCell(document, table, "Part Number", false, true, 1, 1);
						}
						if(currentJob.isMeterialDetailPartName()){
							createCell(document, table, "Part Name", false, true, 1, 1);
						}
						if(currentJob.isMeterialDetailQuantity()){
							createCell(document, table, "Quantity", false, true, 1, 1);
						}
						if(currentJob.isMeterialDetailUOM()){
							createCell(document, table, "UOM", false, true, 1, 1);
						}
						
						if(currentJob.isMeterialDetailDrawingNo()){
							createCell(document, table, "Drawing No.", false, true, 1, 1);
						}
						if(currentJob.isMeterialDetailRemark()){
							createCell(document, table, "Remark", false, true, 1, 1);
						}
						

						for (JobMaterialDetails jobMaterialDetails : job.getJobMaterialDetailsList()) {
							if(currentJob.isMeterialDetailMake()){
								createCell(document, table, jobMaterialDetails.getMake(), false, false, 1, 1);
							}
							if(currentJob.isMeterialDetailModel()){
								createCell(document, table, jobMaterialDetails.getModel(), false, false, 1, 1);
							}
							if(currentJob.isMeterialDetailPartNo()){
								createCell(document, table, jobMaterialDetails.getPartNo(), false, false, 1, 1);
							}
							if(currentJob.isMeterialDetailPartName()){
								createCell(document, table, jobMaterialDetails.getPartName(), false, false, 1, 1);
							}
							if(currentJob.isMeterialDetailQuantity()){
								createCell(document, table, jobMaterialDetails.getQuantity()+"", false, false, 1, 1);
							}
							if(currentJob.isMeterialDetailUOM()){
								createCell(document, table, jobMaterialDetails.getUom(), false, false, 1, 1);
							}
							if(currentJob.isMeterialDetailDrawingNo()){
								createCell(document, table, jobMaterialDetails.getDrawingNo(), false, false, 1, 1);
							}
							if(currentJob.isMeterialDetailRemark()){
								createCell(document, table, jobMaterialDetails.getRemarks(), false, false, 1, 1);
							}						
						}					
						document.add(table);
					}

					if(null!=job.getJobProgressReportList() && !job.getJobProgressReportList().isEmpty() && currentJob.isProgressReport()){
						document.newPage();
						createSectionHeader(document, "Progress Report", 1, 1);
						int ProgressReportColumnCount = getProgressReportColumnCount(currentJob);
						table=new PdfPTable(ProgressReportColumnCount);
						table.setWidthPercentage(100); 
						table.setSpacingAfter(10f);
						if(currentJob.isProgressReportReportingDate()){
							createCell(document, table, "Reporting Date", false, true, 1, 1);
						}					
						if(currentJob.isProgressReportExecutionDate()){
							createCell(document, table, "Execution Date", false, true, 1, 1);
						}
						if(currentJob.isProgressReportWorkDoneForTheDay()){
							createCell(document, table, "Work Done For The Day", false, true, 1, 1);
						}
						if(currentJob.isProgressReportAttachments()){
							createCell(document, table, "Attachments", false, true, 1, 1);
						}
						

						for (JobProgressReport progressReport : job.getJobProgressReportList()) {
							if(currentJob.isProgressReportReportingDate()){
								createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(progressReport.getReportingDate()+"", "dd-MM-yyyy", "HH:MM") , false, false, 1, 1);
							}
							if(currentJob.isProgressReportExecutionDate()){
								createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(progressReport.getExecutionDate()+"", "dd-MM-yyyy", "HH:MM"), false, false, 1, 1);
							}
							if(currentJob.isProgressReportWorkDoneForTheDay()){
								createCell(document, table, progressReport.getWorkDone(), false, false, 1, 1);
							}
							if(job.getJobAttachmentList() != null && currentJob.isProgressReportAttachments()){
								Paragraph file=new Paragraph();
								for(JobAttachment progressReportAttachment: progressReport.getJobAttachmentList()){
									Chunk underline = new Chunk("View Attachment",getFont(PDF_FONT_HYPERLINK,PDF_FONT_SIZE_DATA));
									underline.setUnderline(0.1f, -2f);
									Anchor hyperLink = new Anchor(underline);
									hyperLink.setReference(info.getOrgId()+"/"+project.getShipid()+"/"+project.getId()+"/"+job.getId()+"/"+progressReportAttachment.getRelativepath());							
									file.add(hyperLink);
									file.add("  ");
								}
								PdfPCell cellProgressReportAttachment = new PdfPCell(file);
								cellProgressReportAttachment.setPaddingLeft(10);
								cellProgressReportAttachment.setHorizontalAlignment(Element.ALIGN_LEFT);
								cellProgressReportAttachment.setVerticalAlignment(Element.ALIGN_MIDDLE);
								table.addCell(cellProgressReportAttachment);
							}
						}
						document.add(table);
					}
					
					if(job.getJobAttachmentList() != null && currentJob.isExecPhotosReps()){
						document.newPage();
						createSectionHeader(document, "Exec Photos & Reps", 1, 1);
						//add job image file 						
						table=new PdfPTable(2);
						table.setSpacingAfter(10f);
						table.setWidthPercentage(100);
						int cellCountJob=0;
						String lastAttachmentDescJob=null;
						for (JobAttachment jobAttachment : job.getJobAttachmentList()) {
							String fileExt = jobAttachment.getAttachmentName().substring(jobAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
							if((fileExt.equals("jpg") || fileExt.equals("jpeg") || fileExt.equals("png")) && jobAttachment.getAttachmentType().equals("JER")){				
								Image image = Image.getInstance(basePath+"/"+info.getOrgId()+"/"+project.getShipid()+"/"+project.getId()+"/"+job.getId()+"/"+jobAttachment.getRelativepath());
								image.scaleAbsolute(150f, 150f);
								image.setAbsolutePosition(150f, 400f);		        
						        PdfPCell imageCell = new PdfPCell(image);	
						        imageCell.setPaddingTop(20);
						        imageCell.setPaddingRight(30);
						        imageCell.setPaddingBottom(20);
						        imageCell.setPaddingLeft(30);
						        table.addCell(imageCell);
						        cellCountJob++;
							}
						}
				        if(cellCountJob%2!=0){
				        	createCell(document, table, "", false, false, 1, 1);
				        }
						document.add(table);
						
						//job attachment list
						table=new PdfPTable(2);
						table.setWidthPercentage(100); 
						table.setSpacingAfter(10f); 
						createCell(document, table, "File Name", false, true, 1, 1);
						createCell(document, table, "View File", false, true, 1, 1);
						for (JobAttachment jobAttachment : job.getJobAttachmentList()) {
							String fileExt = jobAttachment.getAttachmentName().substring(jobAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
							if(!fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png") && jobAttachment.getAttachmentType().equals("JER")){
								createCell(document, table, jobAttachment.getAttachmentName(), false, false, 1, 1);
								createCell(document, table, info.getOrgId()+"/"+project.getShipid()+"/"+project.getId()+"/"+job.getId()+"/"+jobAttachment.getRelativepath(), true, false, 1, 1);
							}
						}					
						document.add(table);
						document.newPage();
					}
					//job comment
					if(job.getJobAttachmentList() != null && currentJob.isJobComment()){
						createSectionHeader(document, "Job Comment", 1, 1);						
						//job attachment list
						table=new PdfPTable(3);
						table.setWidthPercentage(100); 
						table.setSpacingAfter(10f); 
						createCell(document, table, "Comment", false, true, 1, 1);
						createCell(document, table, "User Name", false, true, 1, 1);
						createCell(document, table, "Time", false, true, 1, 1);
						for (JobComment jobComment : job.getJobCommentList()) {
							createCell(document, table, jobComment.getJobComment(), false, false, 1, 1);
							createCell(document, table, jobComment.getUpdatedBy(), false, false, 1, 1);
							createCell(document, table, DrydockWebUtil.milisToDateAsStringOrgSpec(jobComment.getUpdatedate()+"", "dd-MM-yyyy", "HH:MM"), false, false, 1, 1);
						}					
						document.add(table);
						//document.newPage();
					}
				}
				
				//marge job specification attachment
				for (JobAttachment jobAttachment : job.getJobAttachmentList()) {
					String fileExt = jobAttachment.getAttachmentName().substring(jobAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
					if(!fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png") && jobAttachment.getAttachmentType().equals("SPEC") && fileExt.equals("pdf")){
						margeAttachment(basePath + File.separator + info.getOrgId()+"/"+project.getShipid()+"/"+project.getId()+"/"+job.getId()+"/"+jobAttachment.getRelativepath(), document, writer);
					}
				}

				//Marge Exec Photos & Reps attachment
				for (JobAttachment jobAttachment : job.getJobAttachmentList()) {
					String fileExt = jobAttachment.getAttachmentName().substring(jobAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
					if(!fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png") && jobAttachment.getAttachmentType().equals("JER") && fileExt.equals("pdf")){
						margeAttachment(basePath + File.separator + info.getOrgId()+"/"+project.getShipid()+"/"+project.getId()+"/"+job.getId()+"/"+jobAttachment.getRelativepath(), document, writer);
					}
				}
				
				
				for (JobProgressReport progressReport : job.getJobProgressReportList()) {
					if(job.getJobAttachmentList() != null && currentJob.isProgressReportAttachments()){
						for(JobAttachment progressReportAttachment: progressReport.getJobAttachmentList()){
							String fileExt = progressReportAttachment.getAttachmentName().substring(progressReportAttachment.getAttachmentName().lastIndexOf('.')+1).toLowerCase();
							if(!fileExt.equals("jpg") && !fileExt.equals("jpeg") && !fileExt.equals("png") && progressReportAttachment.getAttachmentType().equals("JER") && fileExt.equals("pdf")){
								margeAttachment(basePath + File.separator + info.getOrgId()+"/"+project.getShipid()+"/"+project.getId()+"/"+job.getId()+"/"+progressReportAttachment.getRelativepath(), document, writer);
							}
						}
					}
				}
								
			}
		}
		
		document.add(new Paragraph("Table of Contents"));

        Chunk dottedLine = new Chunk(new DottedLineSeparator());


        Paragraph p;

        for (SimpleEntry<String, String> entry : toc) {

            p = new Paragraph(entry.getKey());

            p.add(dottedLine);

            p.add(String.valueOf(entry.getValue()));

            document.add(p);

        }

	}
	
	private static int getProgressReportColumnCount(ReportDTO reportFields){
		int count = 0;
		if(reportFields.isProgressReportReportingDate()){
			count++;
		}					
		if(reportFields.isProgressReportExecutionDate()){
			count++;
		}
		if(reportFields.isProgressReportWorkDoneForTheDay()){
			count++;
		}
		if(reportFields.isProgressReportAttachments()){
			count++;
		}
		return count;
	}
	
	private static int getMeterialDetailColumnCount(ReportDTO reportFields) {
		int count = 0;
		if(reportFields.isMeterialDetailMake()){
			count++;					
		}
		if(reportFields.isMeterialDetailModel()){
			count++;
		}
		if(reportFields.isMeterialDetailPartNo()){
			count++;
		}
		if(reportFields.isMeterialDetailPartName()){
			count++;
		}
		if(reportFields.isMeterialDetailQuantity()){
			count++;
		}
		if(reportFields.isMeterialDetailUOM()){
			count++;
		}
		
		if(reportFields.isMeterialDetailDrawingNo()){
			count++;
		}
		if(reportFields.isMeterialDetailRemark()){
			count++;
		}
		return count;
	}

	public static void main(String[] args){
		Document document = new Document(PageSize.A4, 72, 72, 72, 72);
		PdfWriter writer;
		try {
//			writer = PdfWriter.getInstance(document, new FileOutputStream("D:/test.pdf"));
//			HeaderFooterPageEvent event = new HeaderFooterPageEvent("Vessel Name", "Project Description");
//			writer.setPageEvent(event);
//			document.open();
//
//			ItextPdfUtil.createProjectReport(document, new Project(), new BasicInfoDTO(), "", new PdfWriter());
//
//			document.close();
//			writer.close();
//			System.out.println("Ends...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
