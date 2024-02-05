package com.vehicle.vehicalmanagement.bean;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
public class ExportPdf {

	public void generatePdf(List<Vehicle> vehicleList, HttpServletResponse response)
			throws DocumentException, IOException {

		Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc, response.getOutputStream());
		doc.open();

		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);
		Paragraph paragraph = new Paragraph("List Of Vehicles", fontTitle);

		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		doc.add(paragraph);

		PdfPTable table = new PdfPTable(8);

		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 4, 10, 10, 10, 10, 10, 10, 10 });
		table.setSpacingBefore(20);

		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(CMYKColor.blue);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		cell.setPhrase(new Phrase("Id", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Vehicle_Registration_Number", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Owner_Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Registration_Expiry_Date", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Brand", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Active", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Created_By", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Modified_By", font));
		table.addCell(cell);

		for (Vehicle vehicle : vehicleList) {
			table.addCell(String.valueOf(vehicle.getId()));
			table.addCell(vehicle.getVehicleRegistrationNumber());
			table.addCell(vehicle.getOwnerName());
			table.addCell(vehicle.getRegistrationExpiresDate() != null ? String.valueOf(vehicle.getRegistrationExpiresDate()): "");
			table.addCell(vehicle.getBrand());
			table.addCell(String.valueOf(vehicle.isActive()));
			table.addCell(vehicle.getCreatedBy());
			table.addCell(vehicle.getModifiedBy());
		}

		doc.add(table);
		doc.close();
	}
}
