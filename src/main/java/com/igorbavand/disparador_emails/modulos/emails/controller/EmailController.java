package com.igorbavand.disparador_emails.modulos.emails.controller;

import com.igorbavand.disparador_emails.modulos.emails.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("{emailDestinatário}")
    public void testarEnvioEmail(@PathVariable String emailDestinatário) {
        emailService.enviarEmailTeste(emailDestinatário);
    }
}
