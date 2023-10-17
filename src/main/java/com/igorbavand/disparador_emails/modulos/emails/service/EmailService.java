package com.igorbavand.disparador_emails.modulos.emails.service;

import com.igorbavand.disparador_emails.modulos.emails.dto.EnviarIngressoClienteEmailMqDto;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

public interface EmailService {

    void enviarEmailTeste(String emailDestinatário);

    void enviarEmailIngresso(EnviarIngressoClienteEmailMqDto dto);

    FileSystemResource createImage(String fileName, String ext);

}
