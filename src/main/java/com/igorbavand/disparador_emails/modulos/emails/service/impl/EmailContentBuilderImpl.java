package com.igorbavand.disparador_emails.modulos.emails.service.impl;

import com.igorbavand.disparador_emails.modulos.emails.dto.EnviarIngressoClienteEmailMqDto;
import com.igorbavand.disparador_emails.modulos.emails.service.EmailContentBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailContentBuilderImpl implements EmailContentBuilder {

    private Context contextThymeleaf;
    private final ITemplateEngine templateEngine;

    @Override
    public String buildEmailIngresso(EnviarIngressoClienteEmailMqDto dto) {

        contextThymeleaf = new Context();

        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        contextThymeleaf.setVariable("data", data);
        contextThymeleaf.setVariable("hora", hora);
        contextThymeleaf.setVariable("imagemCabecalho", "cabecalho");

        contextThymeleaf.setVariable("codigoVenda", dto.getCodigoVenda());
        contextThymeleaf.setVariable("nomeCliente", dto.getNomeCliente());
        contextThymeleaf.setVariable("cidadeCliente", dto.getCidadeCliente());
        contextThymeleaf.setVariable("cpfCliente", dto.getCpfCliente());
        contextThymeleaf.setVariable("descricaoEvento", dto.getDescricaoEvento());
        contextThymeleaf.setVariable("localEvento", dto.getLocalEvento());
        contextThymeleaf.setVariable("dataEvento", dto.getDataEvento());
        contextThymeleaf.setVariable("valor", dto.getValor());

        return templateEngine.process("template-ingresso", contextThymeleaf);

    }

    @Override
    public String buildEmailTeste() {
        contextThymeleaf = new Context();

        contextThymeleaf.setVariable("imagemCabecalho", "cabecalho");

        return templateEngine.process("emailTeste", contextThymeleaf);

    }
}
