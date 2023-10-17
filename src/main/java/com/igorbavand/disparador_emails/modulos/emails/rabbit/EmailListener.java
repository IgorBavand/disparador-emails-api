package com.igorbavand.disparador_emails.modulos.emails.rabbit;

import com.igorbavand.disparador_emails.modulos.emails.dto.EnviarIngressoClienteEmailMqDto;
import com.igorbavand.disparador_emails.modulos.emails.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailListener {

    private final EmailService emailService;

    @RabbitListener(queues = "enviar-email-queue")
    public void enviarIngressoEmail(EnviarIngressoClienteEmailMqDto dto) {
        emailService.enviarEmailIngresso(dto);
    }

}
