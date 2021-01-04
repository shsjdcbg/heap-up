package pers.dyx.itext5.examples;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UnequalPages {
    public static final String DEST = "E:\\Itext5PDF_Test\\examples\\unequal_pages.pdf";

    public static void main(String[] args) throws IOException,
        DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new UnequalPages().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        Rectangle one = new Rectangle(70,140);// 定义不同大小页面
        Rectangle two = new Rectangle(700,400);
        document.setPageSize(one);
        document.setMargins(2, 2, 2, 2);
        document.open();
        Paragraph p = new Paragraph("Hi");
        document.add(p);
        document.setPageSize(two);
        document.setMargins(20, 20, 20, 20);
        document.newPage();
        document.add(p);
        document.close();
    }
}
