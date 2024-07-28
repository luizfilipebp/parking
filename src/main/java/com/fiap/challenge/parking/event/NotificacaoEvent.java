package com.fiap.challenge.parking.event;

import com.fiap.challenge.parking.dominio.periodo.entidade.Periodo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NotificacaoEvent extends ApplicationEvent {

    private final Periodo periodo;

    public NotificacaoEvent(Object source, Periodo periodo) {
        super(source);
        this.periodo = periodo;
    }


}
