package com.desafio.microservices.pedidos.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "Alteração de status inválida.")
public class AlteracaoStatusPedidoInvalido extends RuntimeException{ }
