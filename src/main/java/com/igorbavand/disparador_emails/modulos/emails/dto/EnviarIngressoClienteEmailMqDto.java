package com.igorbavand.disparador_emails.modulos.emails.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnviarIngressoClienteEmailMqDto {
    private String codigoVenda;
    private String nomeCliente;
    private String emailCliente;
    private String cidadeCliente;
    private String cpfCliente;
    private String descricaoEvento;
    private String localEvento;
    private LocalDateTime dataEvento;
    private Double valor;
}