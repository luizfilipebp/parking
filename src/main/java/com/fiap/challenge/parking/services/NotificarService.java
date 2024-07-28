package com.fiap.challenge.parking.services;

import com.fiap.challenge.parking.dominio.periodo.entidade.Periodo;
import com.fiap.challenge.parking.event.NotificacaoEvent;
import com.fiap.challenge.parking.repository.PeriodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
@AllArgsConstructor
public class NotificarService {
    private final long TEMPO = 1000 * 60 * 10; // Verifica a cada 10 minutos se há novas notificações
    private final PeriodoRepository repository;
    private final ApplicationEventPublisher publisher;

    @Scheduled(fixedDelay =  TEMPO, zone = "America/Sao_Paulo")
    public void verificarNotificacao() {
        List<Periodo> all = repository.findAllNotificable(LocalDateTime.now().plusMinutes(15));
        all.forEach(periodo -> publisher.publishEvent(new NotificacaoEvent(this, periodo)));
    }
}
