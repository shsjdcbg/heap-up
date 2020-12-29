package pers.dyx.tool.captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 圆圈干扰验证码
 *
 * @author dyx
 * @date 2020/9/89:00
 */
public class CircleCaptcha extends AbstractCaptcha {

    /**
     * 构造
     *
     * @param width  图片宽
     * @param height 图片高
     */
    public CircleCaptcha(int width, int height) {
        this(width, height, 5);
    }

    /**
     * 构造
     *
     * @param width     图片宽
     * @param height    图片高
     * @param codeCount 字符个数
     */
    public CircleCaptcha(int width, int height, int codeCount) {
        this(width, height, codeCount, 15);
    }

    /**
     * 构造
     *
     * @param width          图片宽
     * @param height         图片高
     * @param codeCount      字符个数
     * @param interfereCount 验证码干扰元素个数
     */
    public CircleCaptcha(int width, int height, int codeCount, int interfereCount) {
        super(width, height, codeCount, interfereCount);
    }

    @Override
    protected Image createImage(String code) {
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Color color = this.background == null ? Color.WHITE : this.background;
        final Graphics2D g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        // 随机画干扰圈圈
        drawInterfere(g);

        // 画字符串
        drawString(g, code);
        return image;
    }

    /**
     * 画随机干扰
     *
     * @param g {@link Graphics2D}
     */
    private void drawInterfere(Graphics2D g) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < this.interfereCount; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawOval(random.nextInt(width), random.nextInt(height), random.nextInt(height >> 1), random.nextInt(height >> 1));
        }
    }

}
