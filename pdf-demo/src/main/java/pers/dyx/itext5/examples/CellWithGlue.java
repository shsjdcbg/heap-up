package pers.dyx.itext5.examples;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CellWithGlue {
    public static final String DEST = "E:\\Itext5PDF_Test\\examples\\cell_with_glue.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CellWithGlue().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table;
        PdfPCell cell;
        table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidthPercentage(60);
        table.setSpacingAfter(20);
        cell = new PdfPCell(new Phrase("Received Rs (in Words):"));
        cell.setBorder(PdfPCell.LEFT | PdfPCell.TOP | PdfPCell.BOTTOM);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Priceless"));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(PdfPCell.RIGHT | PdfPCell.TOP | PdfPCell.BOTTOM);
        table.addCell(cell);
        document.add(table);
        table.setWidthPercentage(50);
        document.add(table);
        table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setWidthPercentage(50);
        Phrase p = new Phrase();
        p.add(new Chunk("Received Rs (In Words):"));
        p.add(new Chunk(new VerticalPositionMark()));
        p.add(new Chunk("Priceless"));
        table.addCell(p);
        document.add(table);
        document.close();
    }
}
