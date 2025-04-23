package com.engcode.notificacao.business.dto;


import com.engcode.notificacao.business.enums.StatusNotificacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {

    private String id;
    private String nomeTarefa;
    private String descricao;
    //LocaDate trabalha com data - LocalDateTime trabalha com data e hora.
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    //Colocou a @JsonFormat anotação para formatar a hora
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
    private String emailUsuario;
    //enunms são uma classe que representa um grupo fixo de constantes. (nesse caso - Pendente, Notificado e Cancelado)
    private StatusNotificacaoEnum statusNotificacaoEnum;


}
