package pers.dyx.itext5.examples;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 编号列表
 */
public class ListNumbers {
    public static final String DEST = "E:\\Itext5PDF_Test\\examples\\list_without_indent.pdf";
    public static void main(String[] args) throws IOException,
        DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListNumbers().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Numbered list with automatic indentation"));
        List list1 = new List(List.ORDERED);
        list1.setFirst(8);
        for (int i = 0; i < 5; i++) {
            list1.add("item");
        }
        document.add(list1);
        document.add(new Paragraph("Numbered list without indentation"));
        List list2 = new List(List.ORDERED);
        list2.setFirst(8);
        list2.setAlignindent(false);
        for (int i = 0; i < 5; i++) {
            list2.add("item");
        }
        document.add(list2);
        document.add(new Paragraph("Nested numbered list"));
        List list3 = new List(List.ORDERED);
        list3.setFirst(8);
        list3.setAlignindent(false);
        list3.setPostSymbol(" ");
        for (int i = 0; i < 5; i++) {
            list3.add("item");
            List list = new List(List.ORDERED);
            list.setPreSymbol(String.valueOf(8 + i) + "_");
            list.setPostSymbol(" ");
            list.add("item 1");
            list.add("item 2");
            list3.add(list);
        }
        document.add(list3);
        document.close();
    }
}
