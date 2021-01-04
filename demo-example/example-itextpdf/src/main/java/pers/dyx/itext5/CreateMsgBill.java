package pers.dyx.itext5;

import pers.dyx.itext5.util.PdfFileExportUtil;
import pers.dyx.itext5.util.PdfReportM1HeaderFooter;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Itext5生成报文账单
 */
public class CreateMsgBill {
    public static final String DEST = "src/main/resources/pdf/MsgBill.pdf";

    // 柱状图路径
    public static final String DESTBAR = "src/main/resources/images/msgRateBar.jpg";
    // 饼图路径
    public static final String DESTPIE = "src/main/resources/images/msgRatePie.jpg";

    public static void main(String[] args) throws IOException,
        DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CreateMsgBill().createPdf(DEST);
    }

//    public class Watermark extends PdfPageEventHelper {
//        protected Phrase watermark = new Phrase("ADCC", new Font(Font.FontFamily.HELVETICA, 60, Font.NORMAL, BaseColor.LIGHT_GRAY));
//        @Override
//        public void onEndPage(PdfWriter writer, Document document) {
//            PdfContentByte canvas = writer.getDirectContentUnder();
//            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 298, 421, 45);
//        }
//    }

    /**
     *
     * 为PDF分页时创建添加文本水印的事件信息
     */
    public class TextWaterMarkPdfPageEvent extends PdfPageEventHelper {
        private String waterMarkText;
        public TextWaterMarkPdfPageEvent(String waterMarkText) {
            this.waterMarkText = waterMarkText;
        }

        public void onEndPage(PdfWriter writer, Document document) {
            try {
                float pageWidth = document.right() + document.left();// 获取pdf内容正文页面宽度
                float pageHeight = document.top() + document.bottom();// 获取pdf内容正文页面高度
                // 设置水印字体格式
                Font waterMarkFont = PdfFileExportUtil.getChinese20Font();
                PdfContentByte waterMarkPdfContent = writer.getDirectContent();//决定了水印图层高低
                Phrase phrase = new Phrase(waterMarkText, waterMarkFont);
                for(int i = 0 ; i < 100; i ++){
                    for(int j = 0 ; j < 20 ; j ++ ){
                        ColumnText.showTextAligned(waterMarkPdfContent,Element.ALIGN_CENTER, phrase,pageWidth * 0.2f * j, pageHeight * 0.08f * i, 45);
                    }
                }
            } catch (DocumentException de) {
                de.printStackTrace();
                System.err.println("pdf watermark font:" + de.getMessage());
            } catch (IOException de) {
                de.printStackTrace();
                System.err.println("pdf watermark font:" + de.getMessage());
            }
        }
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // 添加水印
        writer.setPageEvent(new TextWaterMarkPdfPageEvent("民航数据有限责任公司ADCC"));
        // 添加页码
        PdfReportM1HeaderFooter footer = new PdfReportM1HeaderFooter();
        writer.setPageEvent(footer);
        document.open();

        Paragraph header = new Paragraph("航班报文转发收费单", getChineseFont(18));
        header.setAlignment(Element.ALIGN_CENTER);
        //设置行间距
//        header.setLeading(22f);

        PdfPTable tableMain = new PdfPTable(2);
        tableMain.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 设置表格上面空白宽度
        tableMain.setSpacingBefore(40f);
        //设置表格默认为无边框
//        tableMain.getDefaultCell().setBorder(0);

        PdfPCell cell = new PdfPCell(new Phrase("用户：", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        tableMain.addCell(cell);

        cell = new PdfPCell(new Phrase("广州白云机场", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setFixedHeight(30f);
        tableMain.addCell(cell);

        cell = new PdfPCell(new Phrase("开始时间：", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        tableMain.addCell(cell);

        cell = new PdfPCell(new Phrase("2017-01-01", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setFixedHeight(30f);
        tableMain.addCell(cell);

        cell = new PdfPCell(new Phrase("截止时间：", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        tableMain.addCell(cell);

        cell = new PdfPCell(new Phrase("2017-09-30", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setFixedHeight(30f);
        tableMain.addCell(cell);

        cell = new PdfPCell(new Phrase("总流量/份：", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        tableMain.addCell(cell);

        cell = new PdfPCell(new Phrase("2000000", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setFixedHeight(30f);
        tableMain.addCell(cell);

        cell = new PdfPCell(new Phrase("总费用/元：", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        tableMain.addCell(cell);

        cell = new PdfPCell(new Phrase("5500", getChineseFont(14)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setFixedHeight(50f);
        tableMain.addCell(cell);

        Paragraph costInfoTitle = new Paragraph("流量及费用明细：", getChineseFont(15));
        costInfoTitle.setAlignment(Element.ALIGN_LEFT);
        //设置行间距
//        costInfoTitle.setLeading(20f);

        //设置明细表
        PdfPTable tableInfo = new PdfPTable(3);
        // 设置表格上面空白宽度
        tableInfo.setSpacingBefore(20f);
        // 设置表格各列宽度
        int widthInfo[] = {20,30,50};
        tableInfo.setWidths(widthInfo);
        tableInfo.setWidthPercentage(100);
        PdfPCell cellInfo;

        cellInfo = new PdfPCell(new Paragraph("报文类型", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        cellInfo.setBackgroundColor(BaseColor.LIGHT_GRAY); // 背景色
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("报文数量", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        cellInfo.setBackgroundColor(BaseColor.LIGHT_GRAY); // 背景色
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("费用", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        cellInfo.setBackgroundColor(BaseColor.LIGHT_GRAY); // 背景色
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("POS", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("150000", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("2000", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("ONN", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("15000", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("500", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("OFF", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("20000", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("1500", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("CLS", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("30000", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        cellInfo = new PdfPCell(new Paragraph("2500", getChineseFont(13)));
        cellInfo.setFixedHeight(20);
        cellInfo.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平靠左显示
        cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
        tableInfo.addCell(cellInfo);

        //加入空行
        Paragraph blankRow2 = new Paragraph(120f, " ", getChineseFont(15));

//        for (int i=0; i<4; i++) {
//            cellInfo = new PdfPCell(new Paragraph("POS", getChineseFont(13)));
//            cellInfo.setFixedHeight(20);
//            cellInfo.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平靠左显示
//            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
//            tableInfo.addCell(cellInfo);
//
//            cellInfo = new PdfPCell(new Paragraph("150000", getChineseFont(13)));
//            cellInfo.setFixedHeight(20);
//            cellInfo.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平靠左显示
//            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
//            tableInfo.addCell(cellInfo);
//
//            cellInfo = new PdfPCell(new Paragraph("2000", getChineseFont(13)));
//            cellInfo.setFixedHeight(20);
//            cellInfo.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平靠左显示
//            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中
//            tableInfo.addCell(cellInfo);
//        }


        // 插入柱状图
        Paragraph paragraphBarTitle = new Paragraph("航空报文季度流量分析柱状图：", getChineseFont(15));
        paragraphBarTitle.setAlignment(Element.ALIGN_LEFT);
        // 本地生成柱状图
        createBar();
        PdfPTable tableBar = new PdfPTable(1);
        // 设置表格上面空白宽度
        tableBar.setSpacingBefore(10f);
        tableBar.setWidthPercentage(100);

        PdfPCell cellBar = createImageCell(DESTBAR);
        cellBar.setBorder(PdfPCell.NO_BORDER);
        tableBar.addCell(cellBar);
//        tableBar.setSkipFirstHeader(true);//换页
//        tableBar.setSplitLate(true);
        //加入空行
        Paragraph blankRow = new Paragraph(60f, " ", getChineseFont(15));

        // 插入饼图
        Paragraph paragraphPieTitle = new Paragraph("航空报文流量分析饼图：", getChineseFont(15));
        paragraphPieTitle.setAlignment(Element.ALIGN_LEFT);
//        paragraphPieTitle.setLeading(50f);
        // 本地生成饼图
        createPie();
        PdfPTable tablePier = new PdfPTable(1);
        // 设置表格上面空白宽度
        tablePier.setSpacingBefore(10f);
        tablePier.setWidthPercentage(100);

        PdfPCell cellPie = createImageCell(DESTPIE);
        cellPie.setBorder(PdfPCell.NO_BORDER);
        tablePier.addCell(cellPie);


        document.add(header);
        document.add(tableMain);
        document.add(costInfoTitle);
        document.add(tableInfo);
        document.add(blankRow2);
        document.add(paragraphBarTitle);
        document.add(tableBar);
        document.add(blankRow);
        document.add(paragraphPieTitle);
        document.add(tablePier);
        document.close();
    }

    /**
     * 设置中文字体
     * @param size
     * @return
     */
    private Font getChineseFont(float size) {
        Font FontChinese = null;
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            FontChinese = new Font(bfChinese, size, Font.NORMAL);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return FontChinese;
    }

    private PdfPCell createImageCell(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        return cell;
    }

    /**
     * 生成季度流量柱状图
     */
    private void createBar() {
        try {
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            ds.addValue(200, "POS", "一季度");
            ds.addValue(230, "CLS", "一季度");
            ds.addValue(280, "OFF", "一季度");
            ds.addValue(330, "ON", "一季度");

            ds.addValue(480, "POS", "二季度");
            ds.addValue(430, "CLS", "二季度");
            ds.addValue(320, "OFF", "二季度");
            ds.addValue(180, "ON", "二季度");

            ds.addValue(150, "POS", "三季度");
            ds.addValue(260, "CLS", "三季度");
            ds.addValue(390, "OFF", "三季度");
            ds.addValue(210, "ON", "三季度");

            String title = "前三季度用户报文流量统计" ;
            JFreeChart chart = ChartFactory.createBarChart3D(title, "季度", "流量(单位:万份)", ds, PlotOrientation.VERTICAL, true, false, false);

            //中文
            chart.getTitle().setFont(new java.awt.Font("宋体", java.awt.Font.BOLD, 15));//大标题

            //提示条
            chart.getLegend().setItemFont(new java.awt.Font("宋体", java.awt.Font.BOLD, 10));

            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            //域轴字体
            plot.getDomainAxis().setLabelFont(new java.awt.Font("宋体", java.awt.Font.BOLD, 12));
            plot.getDomainAxis().setTickLabelFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 10));//小标签字体

            //range
            plot.getRangeAxis().setLabelFont(new java.awt.Font("宋体", java.awt.Font.BOLD, 10));

            plot.setForegroundAlpha(0.6f);

            ChartUtilities.saveChartAsJPEG(new File(DESTBAR), chart, 600, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成本地流量饼图
     */
    private void createPie() {
        try {
            String title = "流量计费统计" ;
            DefaultPieDataset ds = new DefaultPieDataset();
            ds.setValue("POS", 2000);
            ds.setValue("CLS", 3500);
            ds.setValue("OFF", 2000);
            ds.setValue("ON", 3000);
            JFreeChart chart = ChartFactory.createPieChart3D(title, ds, true, false, false);

            //中文
            chart.getTitle().setFont(new java.awt.Font("宋体", java.awt.Font.BOLD, 15));//标题字体
            chart.getLegend().setItemFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 10));

            //绘图区
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 10));

            //设置分裂效果
            plot.setExplodePercent("POS", 0.1f);
            plot.setExplodePercent("OFF", 0.2f);

            //设置前景色透明度
            plot.setForegroundAlpha(0.7f);

            //设置标签生成器
            //{0}:名称
            //{1}:销量
            //{2}:百分比
            //{3}:总量
            //{4}:
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}/{3}-{2})"));
            ChartUtilities.saveChartAsJPEG(new File(DESTPIE), chart, 600, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
