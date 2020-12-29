package pers.dyx.tool.captcha;

import pers.dyx.tool.captcha.generator.ICodeGenerator;
import pers.dyx.tool.captcha.generator.RandomGenerator;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * 验证码抽象类
 *
 * @author dyx
 * @date 2020/9/717:02
 */
public abstract class AbstractCaptcha implements ICaptcha {
    private static final long serialVersionUID = 3180820918087507254L;

    /**
     * 图片宽度
     */
    protected int width;
    /**
     * 图片高度
     */
    protected int height;
    /**
     * 验证码干扰元素个数
     */
    protected int interfereCount;
    /**
     * 字体
     */
    protected Font font;
    /**
     * 验证码字符串
     */
    protected String code;
    /**
     * 验证码图片
     */
    protected byte[] imageBytes;
    /**
     * 验证码生成器
     */
    protected ICodeGenerator generator;
    /**
     * 背景色
     */
    protected Color background;
    /**
     * 文字透明度
     */
    protected AlphaComposite alphaComposite;

    /**
     * 构造，使用随机验证码生成器生成验证码
     *
     * @param width          图片宽
     * @param height         图片高
     * @param codeCount      字符个数
     * @param interfereCount 验证码干扰元素个数
     */
    public AbstractCaptcha(int width, int height, int codeCount, int interfereCount) {
        this(width, height, new RandomGenerator(codeCount), interfereCount);
    }

    /**
     * 构造
     *
     * @param width          图片宽
     * @param height         图片高
     * @param generator      验证码生成器
     * @param interfereCount 验证码干扰元素个数
     */
    public AbstractCaptcha(int width, int height, ICodeGenerator generator, int interfereCount) {
        this.width = width;
        this.height = height;
        this.generator = generator;
        this.interfereCount = interfereCount;
        // 字体高度设为验证码高度-2，留边距
        this.font = new Font(Font.SANS_SERIF, Font.PLAIN, (int) (this.height * 0.75));
    }

    @Override
    public void createCode() {
        generateCode();

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        writePng(createImage(this.code), out);
        this.imageBytes = out.toByteArray();
    }

    /**
     * 生成验证码字符串
     */
    protected void generateCode() {
        this.code = generator.generator();
    }

    /**
     * 根据生成的code创建验证码图片
     *
     * @param code 验证码
     * @return Image
     */
    protected abstract Image createImage(String code);

    @Override
    public String getCode() {
        if (null == this.code) {
            createCode();
        }
        return this.code;
    }

    @Override
    public boolean verify(String userInputValue) {
        return this.generator.verity(getCode(), userInputValue);
    }

    @Override
    public void write(OutputStream outputStream) {
        try {
            outputStream.write(this.imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public void setAlphaComposite(AlphaComposite alphaComposite) {
        this.alphaComposite = alphaComposite;
    }

    public void setGenerator(ICodeGenerator generator) {
        this.generator = generator;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    /**
     * 绘制字符串
     *
     * @param g    {@link Graphics2D}画笔
     * @param code 验证码
     */
    protected void drawString(Graphics2D g, String code) {
        // 指定透明度
        if (null != this.alphaComposite) {
            g.setComposite(this.alphaComposite);
        }
        drawStringColourful(g, code, this.font, this.width, this.height);
    }

    private void writePng(Image image, OutputStream out) throws RuntimeException {
        ImageWriter writer = null;
        try {
            ImageOutputStream result = ImageIO.createImageOutputStream(out);
            final String imageType = "png";
            final BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            final Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(image, 0, 0, null);
            bGr.dispose();

            final ImageTypeSpecifier type = ImageTypeSpecifier.createFromRenderedImage(bimage);
            final Iterator<ImageWriter> iter = ImageIO.getImageWriters(type, imageType);
            writer = iter.hasNext() ? iter.next() : null;

            if (writer == null) {
                throw new RuntimeException("");
            }
            writer.setOutput(result);
            writer.write(bimage);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                writer.dispose();
            }
        }
    }

    /**
     * 绘制字符串，使用随机颜色，默认抗锯齿
     *
     * @param g      {@link Graphics}画笔
     * @param str    字符串
     * @param font   字体
     * @param width  字符串总宽度
     * @param height 字符串背景高度
     * @since 4.5.10
     */
    private void drawStringColourful(Graphics g, String str, Font font, int width, int height) {
        // 抗锯齿
        if (g instanceof Graphics2D) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        // 创建字体
        g.setFont(font);

        // 文字高度（必须在设置字体后调用）
        int midY = getCenterY(g, height);

        final int len = str.length();
        int charWidth = width / len;
        for (int i = 0; i < len; i++) {
            g.drawString(String.valueOf(str.charAt(i)), i * charWidth, midY);
        }
    }

    /**
     * 获取文字居中高度的Y坐标（距离上边距距离）<br>
     * 此方法依赖FontMetrics，如果获取失败，默认为背景高度的1/3
     *
     * @param g                {@link Graphics2D}画笔
     * @param backgroundHeight 背景高度
     * @return 最小高度，-1表示无法获取
     * @since 4.5.17
     */
    private int getCenterY(Graphics g, int backgroundHeight) {
        // 获取允许文字最小高度
        FontMetrics metrics = null;
        try {
            metrics = g.getFontMetrics();
        } catch (Exception e) {
            // 此处报告bug某些情况下会抛出IndexOutOfBoundsException，在此做容错处理
        }
        int y;
        if (null != metrics) {
            y = (backgroundHeight - metrics.getHeight()) / 2 + metrics.getAscent();
        } else {
            y = backgroundHeight / 3;
        }
        return y;
    }
}
