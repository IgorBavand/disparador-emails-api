package com.igorbavand.disparador_emails.modulos.emails.service;

import com.igorbavand.disparador_emails.modulos.emails.dto.EnviarIngressoClienteEmailMqDto;

public interface EmailContentBuilder {
    String buildEmailIngresso(EnviarIngressoClienteEmailMqDto dto);

    String buildEmailTeste();
}
