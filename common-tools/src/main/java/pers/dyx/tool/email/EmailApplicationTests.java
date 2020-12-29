package pers.dyx.tool.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @author dyx
 * @version 1.0
 * @date 2020/8/6 16:37
 */
public class EmailApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送邮件
     */
    public void sendEmail() {
        // new一个邮件对象
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置主题
        mailMessage.setSubject("测试邮件");
        // 设置发送人
        mailMessage.setFrom("1973984292@qq.com");
        // 设置接收人，可以有多个
        mailMessage.setTo("1973984292@qq.com");
        // 设置抄送人，可以有多个
        mailMessage.setCc("1973984292@qq.com");
        // 设置隐秘抄送人，可以有多个
        mailMessage.setBcc("1973984292@qq.com");
        // 设置发送日期
        mailMessage.setSentDate(new Date());
        // 设置邮件正文
        mailMessage.setText("您的验证码为8081");
        // 调用邮件发送接口方法
        javaMailSender.send(mailMessage);
    }

    /**
     * 发送邮件：带附件
     *
     * @throws MessagingException
     */
    public void sendEmailFile() throws MessagingException {
        // 获取复杂的邮件对象
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        // 邮件配置辅助工具类
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setSubject("测试邮件带附件发送");
        helper.setFrom("1973984292@qq.com");
        helper.setTo("1973984292@qq.com");
        helper.setCc("1973984292@qq.com");
        helper.setBcc("1973984292@qq.com");
        helper.setSentDate(new Date());
        helper.setText("您的验证码为8082");
        // 设置附件
        helper.addAttachment("1.jpg", new File("D://imgtest//1.jpg"));
        // 调用邮件发送接口方法
        javaMailSender.send(mailMessage);
    }

    /**
     * 发送邮件：添加图片
     *
     * @throws MessagingException
     */
    public void sendEmailResFile() throws MessagingException {
        // 获取复杂的邮件对象
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        // 邮件配置辅助工具类
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setSubject("测试邮件带附件发送");
        helper.setFrom("1973984292@qq.com");
        helper.setTo("1973984292@qq.com");
        helper.setCc("1973984292@qq.com");
        helper.setBcc("1973984292@qq.com");
        helper.setSentDate(new Date());
        helper.setText("<p>测试邮件，包含两种图片，分别如下：</p><p>第一张图片：<p/><p><img src='cid:it1'/></p><p>第二张图片：<p/><p><img src='cid:it2'/></p>", true);
        // 设置图片类资源
        helper.addInline("it1", new FileSystemResource(new File("D://imgtest//it01.jpg")));
        helper.addInline("it2", new FileSystemResource(new File("D://imgtest//it02.jpg")));
        // 调用邮件发送接口方法
        javaMailSender.send(mailMessage);
    }

    /**
     * 发送邮件：使用文字模板
     *
     * @throws MessagingException
     */
    public void sendEmailTemplate() throws MessagingException {
        // 获取复杂的邮件对象
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        // 邮件配置辅助工具类
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setSubject("thymeleaf邮件发送");
        helper.setFrom("1973984292@qq.com");
        helper.setTo("1973984292@qq.com");
        helper.setCc("1973984292@qq.com");
        helper.setBcc("1973984292@qq.com");
        helper.setSentDate(new Date());

        // 利用 Thymeleaf 模板构建 html 文本
        Context context = new Context();
        context.setVariable("username", "DT小白");
        context.setVariable("position", "Java开发工程师");
        context.setVariable("avatar", "http://ydfblog.cn/build/img/ydflogo4-f9dc927993e6e370c62e92b331fd5fc3.jpeg");
        context.setVariable("link", "http://ydfblog.cn");
        // 指定模板路径
        String emailText = templateEngine.process("email.html", context);
        helper.setText(emailText, true);
        // 调用邮件发送接口方法
        javaMailSender.send(mailMessage);
    }
}
