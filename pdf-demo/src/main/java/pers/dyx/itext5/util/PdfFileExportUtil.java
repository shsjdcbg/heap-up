package pers.dyx.itext5.util;

import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfFileExportUtil {

    private static Font pdf8Font = null;

    private static Font pdf20Font = null;

    /**
     *
     * 设置PDF创建者信息
     *
     * @param pdfDocument
     */

    public static Document setCreatorInfo(Document pdfDocument) {

        if (pdfDocument == null) {
            return null;
        }

        // 文档属性
        pdfDocument.addTitle("北京XX科技有限公司综合分析PDF文档");
        pdfDocument.addAuthor("北京XX科技有限公司");
        pdfDocument.addSubject("XX分析页");
        pdfDocument.addKeywords("综合分析");// 文档关键字信息
        pdfDocument.addCreator("XX数据平台");// 应用程序名称
        return pdfDocument;
    }

    /**
     *
     * 获取中文字符集且是8号字体，常用作表格内容的字体格式
     *
//     * @param fullFilePath
     */

    public static Font getChinese8Font() throws DocumentException, IOException {
        if (pdf8Font == null) {
            // 设置中文字体和字体样式
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            pdf8Font = new Font(bfChinese, 8, Font.NORMAL);
        }
        return pdf8Font;
    }

    /**
     *
     * 获取中文字符集且是8号字体，常用作文字水印信息
     *
//     * @param fullFilePath
     */

    public static Font getChinese20Font() throws DocumentException, IOException {
        if (pdf20Font == null) {
            // 设置中文字体和字体样式
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            pdf20Font = new Font(bfChinese);
            pdf20Font.setSize(10);
            pdf20Font.setColor(BaseColor.LIGHT_GRAY);
        }
        return pdf20Font;
    }

    /**
     *
     * 设置成只读权限
     *
     * @param pdfWriter
     */
    public static PdfWriter setReadOnlyPDFFile(PdfWriter pdfWriter)
            throws DocumentException {
        pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING,
                PdfWriter.STANDARD_ENCRYPTION_128);
        return pdfWriter;
    }

    /**
     *
     * 变更一个图片对象的展示位置和角度信息
     * @param waterMarkImage
     * @param xPosition
     * @param yPosition
     * @return
     */

    public static Image getWaterMarkImage(Image waterMarkImage,
                                          float xPosition, float yPosition) {
        waterMarkImage.setAbsolutePosition(xPosition, yPosition);// 坐标
        waterMarkImage.setRotation(-20);// 旋转 弧度
        waterMarkImage.setRotationDegrees(-45);// 旋转 角度
        waterMarkImage.scalePercent(100);// 依照比例缩放
        return waterMarkImage;
    }

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

    /**
     *
     * 为PDF分页时创建添加图片水印的事件信息
     */

    public class PictureWaterMarkPdfPageEvent extends PdfPageEventHelper {
        private String waterMarkFullFilePath;
        private Image waterMarkImage;
        public PictureWaterMarkPdfPageEvent(String waterMarkFullFilePath) {
            this.waterMarkFullFilePath = waterMarkFullFilePath;
        }

        public void onEndPage(PdfWriter writer, Document document) {
            try {
                float pageWidth = document.right() + document.left();// 获取pdf内容正文页面宽度
                float pageHeight = document.top() + document.bottom();// 获取pdf内容正文页面高度
                PdfContentByte waterMarkPdfContent = writer.getDirectContent();
                // 仅设置一个图片实例对象，整个PDF文档只应用一个图片对象，极大减少因为增加图片水印导致PDF文档大小增加
                if (waterMarkImage == null) {
                    waterMarkImage = Image.getInstance(waterMarkFullFilePath);
                }
                // 添加水印图片，文档正文内容采用横向三列，竖向两列模式增加图片水印
                for(int i = 0 ; i < 100; i ++){
                    for(int j = 0 ; j < 4 ; j ++ ){
                        waterMarkPdfContent.addImage(getWaterMarkImage(waterMarkImage,pageWidth * 0.3f * j , pageHeight * 0.05f * i));
                    }
                }
                PdfGState gs = new PdfGState();
                gs.setFillOpacity(0.8f);// 设置透明度为0.2
                gs.setStrokeOpacity(0.8f);
                gs.setOverPrintStroking(true);
                waterMarkPdfContent.setGState(gs);

            } catch (DocumentException de) {
                de.printStackTrace();
                System.err.println("pdf watermark font:" + de.getMessage());
            } catch (IOException de) {
                de.printStackTrace();
                System.err.println("pdf watermark font:" + de.getMessage());
            }
        }
    }

    /**
     *
     * 为PDF分页时创建添加header和footer信息的事件信息
     */

    class HeadFootInfoPdfPageEvent extends PdfPageEventHelper {
        public HeadFootInfoPdfPageEvent() {
        }

        public void onEndPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte headAndFootPdfContent = writer
                        .getDirectContent();
                headAndFootPdfContent.saveState();
                headAndFootPdfContent.beginText();
                BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                        "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                headAndFootPdfContent.setFontAndSize(bfChinese, 10);
                // 文档页头信息设置
                float x = document.top(-20);
                // 页头信息左面
                headAndFootPdfContent.showTextAligned(
                        PdfContentByte.ALIGN_LEFT,
                        "综合分析",
                        document.left(), x, 0);
                // 页头信息中间
                headAndFootPdfContent.showTextAligned(
                        PdfContentByte.ALIGN_CENTER,
                        "第" + writer.getPageNumber() + "页",
                        (document.right() + document.left()) / 2,
                        x, 0);
                // 页头信息右面
                headAndFootPdfContent.showTextAligned(
                        PdfContentByte.ALIGN_RIGHT,
                        "北京XX科技有限公司",
                        document.right(), x, 0);
                // 文档页脚信息设置
                float y = document.bottom(-20);
                // 页脚信息左面
                headAndFootPdfContent.showTextAligned(
                        PdfContentByte.ALIGN_LEFT,
                        "--",
                        document.left(), y, 0);
                // 页脚信息中间
                headAndFootPdfContent.showTextAligned(
                        PdfContentByte.ALIGN_CENTER,
                        "-",
                        (document.right() + document.left()) / 2,
                        y, 0);
                // 页脚信息右面
                headAndFootPdfContent.showTextAligned(
                        PdfContentByte.ALIGN_RIGHT,
                        "--",
                        document.right(), y, 0);
                headAndFootPdfContent.endText();
                headAndFootPdfContent.restoreState();
            } catch (DocumentException de) {
                de.printStackTrace();
                System.err.println("pdf watermark font:" + de.getMessage());
            } catch (IOException de) {
                de.printStackTrace();
                System.err.println("pdf watermark font:" + de.getMessage());
            }
        }
    }

//    public static void main(String[] args) {
//        PdfFileExportUtil pdfFileExportUtil = new PdfFileExportUtil();
//        pdfWriter.setPageEvent(pdfFileExportUtil.new PictureWaterMarkPdfPageEvent(basePath+"/images/xxx.png"));
//    }

}
