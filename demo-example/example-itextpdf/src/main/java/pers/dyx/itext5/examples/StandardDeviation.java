package pers.dyx.itext5.examples;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 标准差用字体
 */
public class StandardDeviation {
    public static final String DEST = "E:\\Itext5PDF_Test\\examples\\standard_deviation.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new StandardDeviation().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("The standard deviation symbol doesn't exist in Helvetica."));
        Font symbol = new Font(FontFamily.SYMBOL);
        Paragraph p = new Paragraph("So we use the Symbol font: ");
        p.add(new Chunk("s", symbol));
        document.add(p);
        document.close();

    }
}
