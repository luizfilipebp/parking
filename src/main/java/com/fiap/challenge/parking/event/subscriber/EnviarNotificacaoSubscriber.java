package com.fiap.challenge.parking.event.subscriber;

import com.fiap.challenge.parking.event.NotificacaoEvent;
import com.fiap.challenge.parking.services.PeriodoService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EnviarNotificacaoSubscriber {

    private final PeriodoService service;

    @EventListener
    @Async
    public void enviaEmailPorCpf(NotificacaoEvent event) {
        //@TODO enviar email para o usuario

        service.mudarParaNotificado(event.getPeriodo());
    }
}
