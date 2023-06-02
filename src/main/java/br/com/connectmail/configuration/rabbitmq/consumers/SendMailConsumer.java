package br.com.connectmail.configuration.rabbitmq.consumers;

import br.com.connectmail.configuration.rabbitmq.RabbitQueues;
import br.com.connectmail.model.MailDTO;
import br.com.connectmail.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SendMailConsumer {

    private final MailService mailService;

    public SendMailConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = RabbitQueues.SEND_MAIL)
    public void receive(MailDTO mailDTO) throws MessagingException {
        this.mailService.sendMail(mailDTO);
    }
}
