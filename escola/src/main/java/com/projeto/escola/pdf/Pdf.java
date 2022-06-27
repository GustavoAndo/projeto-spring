package com.projeto.escola.pdf;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.projeto.escola.entidade.Usuario;

public class Pdf {
	
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("User ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table, Usuario usuario) {
        table.addCell(String.valueOf(usuario.getId()));
        table.addCell(usuario.getUsername());
        table.addCell(usuario.getNome());
        table.addCell(usuario.getNivelAcesso().toString());
    }
	
    public void export(HttpServletResponse response, Usuario usuario) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitulo.setSize(18);
        fontTitulo.setColor(Color.BLUE);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        font.setColor(Color.BLACK);
         
        Paragraph t = new Paragraph("Relatórios", fontTitulo);
        t.setAlignment(Paragraph.ALIGN_CENTER);
        
        Paragraph p1 = new Paragraph("\nConsumo:" + usuario.getNome() + "\nValor total:\nMédia do Consumo Anual:\nMédia Ideal de Consumo:", font);
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table, usuario);
        
	    document.add(t);
	    document.add(p1);
        document.add(table);
        
        document.close();
         
    }
}