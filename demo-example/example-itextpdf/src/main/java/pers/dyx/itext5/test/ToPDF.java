package pers.dyx.itext5.test;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ToPDF {
    // 表头
    public static final String[] tableHeader= { "姓名", "性别", "年龄", "学院", "专业", "年级"};

    // 数据表字段数
    private static final int colNumber = 6;

    // 表格的设置
    private static final int spacing = 2;

    // 表格的设置
    private static final int padding = 2;

    // 导出Pdf文挡
    public static void exportPdfDocument() {
        // 创建文Pdf文挡50, 50, 50,50左右上下距离
//        Document document = new Document(new Rectangle(1500, 2000), 50, 50, 50, 50);
        Document document = new Document(PageSize.A4);
        try {
            //使用PDFWriter进行写文件操作
            PdfWriter.getInstance(document,new FileOutputStream("E:\\Itext5PDF_Test\\学生信息.pdf"));
            document.open();
            // 中文字体
            BaseFont bfChinese =BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
            // 创建有colNumber(6)列的表格
            PdfPTable datatable = new PdfPTable(colNumber);
            //定义表格的宽度
            int[] cellsWidth = { 8, 2,2, 8, 5, 3 };
            datatable.setWidths(cellsWidth);
            // 表格的宽度百分比
            datatable.setWidthPercentage(100);
            datatable.getDefaultCell().setPadding(padding);
            datatable.getDefaultCell().setBorderWidth(spacing);
            //设置表格的底色
            datatable.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
            datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            // 添加表头元素
            for (int i = 0; i <colNumber; i++) {
                datatable.addCell(new Paragraph(tableHeader[i], fontChinese));
            }
            // 添加子元素
            for (int i = 0; i <colNumber; i++) {
                datatable.addCell(new Paragraph(tableHeader[i], fontChinese));
            }
            document.add(datatable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    public static void main(String[] args)throws Exception {
        exportPdfDocument();
    }
}
