package com.igorbavand.disparador_emails.modulos.emails.service.impl;

import com.igorbavand.disparador_emails.modulos.emails.dto.EnviarIngressoClienteEmailMqDto;
import com.igorbavand.disparador_emails.modulos.emails.service.EmailContentBuilder;
import com.igorbavand.disparador_emails.modulos.emails.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender disparadorEmail;
    private final EmailContentBuilder emailContentBuilder;

    private File fileTemp;
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void enviarEmailIngresso(EnviarIngressoClienteEmailMqDto dto) {
        try {
            MimeMessagePreparator emailPreparator = mimeMessage -> {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                email.setTo(dto.getEmailCliente().trim());
                email.setFrom(emailFrom, "disparador.teste@gmail.com");
                email.setSubject("Ingresso");
                String content = emailContentBuilder.buildEmailIngresso(dto);
                email.setText(content, true);
                FileSystemResource resCabecalho = this.createImage("cabecalho.png", "png");
                email.addInline("cabecalho", resCabecalho, "image/png");
            };

            disparadorEmail.send(emailPreparator);
            deleteImagem();
            log.info("O e-mail foi enviado com sucesso.");

        } catch (Exception exception) {
            log.info("Erro ao enviar e-mail. Motivo: " + exception.getMessage());
        }
    }

    private void deleteImagem() {
        if (fileTemp != null) {
            fileTemp.delete();
        }
    }

    @Override
    public void enviarEmailTeste(String emailDestinatário) {
        try {
            MimeMessagePreparator emailPreparator = mimeMessage -> {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                email.setTo(emailDestinatário);
                email.setFrom(emailFrom, "disparador.teste@gmail.com");
                email.setSubject("Email de teste - desparador API");
                String content = emailContentBuilder.buildEmailTeste();
                email.setText(content, true);
                FileSystemResource resCabecalho = this.createImage("cabecalho.png", "png");
                email.addInline("cabecalho", resCabecalho, "image/png");
            };

            disparadorEmail.send(emailPreparator);
            deleteImagem();
            log.info("O e-mail foi enviado com sucesso.");

        } catch (Exception exception) {
            log.info("Erro ao enviar e-mail. Motivo: " + exception.getMessage());
        }
    }

    public FileSystemResource createImage(String fileName, String ext) {
        FileSystemResource f = null;
        try {
            fileTemp = File.createTempFile(UUID.randomUUID().toString(), "." + ext);

            FileUtils.copyInputStreamToFile(this.getClass().getResourceAsStream("/static/image/" + fileName), fileTemp);

            f = new FileSystemResource(fileTemp);

        } catch (IOException e) {
            throw new Exception("Problema ao criar imagem do email. motivo: " + e.getMessage());
        } finally {
            return f;
        }
    }
}
