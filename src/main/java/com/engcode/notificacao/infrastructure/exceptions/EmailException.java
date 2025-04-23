package com.engcode.notificacao.infrastructure.exceptions;

public class EmailException extends RuntimeException {

    //Resource Not Found Exception - indica que um recurso não foi encontrado.

    public EmailException(String mensagem) {
        super(mensagem);
    }

    public EmailException(String mensagem, Throwable throwable) {

        super(mensagem, throwable);
    }

}
