package com.desafio.microservicos.pagamentos.pagamentos.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContent extends RuntimeException{
}
