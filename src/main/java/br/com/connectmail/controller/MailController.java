package br.com.connectmail.controller;

import br.com.connectmail.exception.SendMailException;
import br.com.connectmail.model.MailDTO;
import br.com.connectmail.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody @Valid MailDTO mailDTO) {
        try {
            this.mailService.sendMail(mailDTO);
        } catch (MessagingException e) {
            throw new SendMailException(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}
