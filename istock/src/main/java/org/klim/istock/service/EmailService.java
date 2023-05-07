package org.klim.istock.service;

import org.klim.istock.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final String from;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender,
                        @Value("${spring.mail.username}") String from, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
        this.from = from;
    }

    public void sendOrderConfirmation(
            String from,
            String to,
            String subject,
            Locale locale,
            Order order) throws MessagingException {
        Context ctx = new Context(locale);
        ctx.setVariable("order", order);
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper message =
            new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject(subject);
        message.setFrom(from);
        message.setTo(to);
        String htmlContent = this.templateEngine.process("OrderConfirmation.html", ctx);
        message.setText(htmlContent, true);
        mailSender.send(mimeMessage);
    }
}
