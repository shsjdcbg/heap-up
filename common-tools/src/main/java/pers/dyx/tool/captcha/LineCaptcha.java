package pers.dyx.tool.captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 线条干扰验证码
 *
 * @author dyx
 * @date 2020/9/89:08
 */
public class LineCaptcha extends AbstractCaptcha {

    /**
     * 构造，默认5位验证码，150条干扰线
     *
     * @param width  图片宽
     * @param height 图片高
     */
    public LineCaptcha(int width, int height) {
        this(width, height, 5, 150);
    }

    /**
     * 构造
     *
     * @param width     图片宽
     * @param height    图片高
     * @param codeCount 字符个数
     * @param lineCount 干扰线条数
     */
    public LineCaptcha(int width, int height, int codeCount, int lineCount) {
        super(width, height, codeCount, lineCount);
    }

    @Override
    protected Image createImage(String code) {
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Color color = this.background == null ? Color.WHITE : this.background;
        final Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        // 随机画干扰线
        drawInterfere(g);

        // 画字符串
        drawString(g, code);
        return image;
    }

    /**
     * 绘制干扰线
     *
     * @param g {@link Graphics2D}画笔
     */
    private void drawInterfere(Graphics2D g) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        // 干扰线
        for (int i = 0; i < this.interfereCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width / 8);
            int ye = ys + random.nextInt(height / 8);
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(xs, ys, xe, ye);
        }
    }


}
