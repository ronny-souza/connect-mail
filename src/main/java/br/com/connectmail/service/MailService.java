package br.com.connectmail.service;

import br.com.connectmail.model.MailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public MailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendMail(MailDTO mailDTO) throws MessagingException {
        mailDTO.properties().put("currentYear", LocalDate.now().getYear());

        LOGGER.debug("Building mime message and helper...");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();

        LOGGER.debug("Setting properties on template context...");
        context.setVariables(mailDTO.properties());

        helper.setFrom(mailDTO.from());
        helper.setTo(mailDTO.to());
        helper.setSubject(mailDTO.subject());

        LOGGER.debug("Processing template...");
        String template = templateEngine.process(mailDTO.type().getTemplate(), context);
        helper.setText(template, true);

        LOGGER.info("Sending email...");
        mailSender.send(message);
    }
}
