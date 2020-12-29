package pers.dyx.itext5.examples;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 首段落跨多行
 */
public class ParagraphSpacingBefore {
    public static final String DEST = "E:\\Itext5PDF_Test\\examples\\paragraph_spacebefore.pdf";

    public static void main(String[] args) throws IOException,
        DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParagraphSpacingBefore().createPdf(DEST);
    }
    public void createPdf(String filename) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        Paragraph paragraph1 = new Paragraph("First paragraph");
        Paragraph paragraph2 = new Paragraph("Second paragraph");
        paragraph2.setSpacingBefore(380f);
        document.add(paragraph1);
        document.add(paragraph2);
        document.close();
    }
}
