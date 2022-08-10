package com.desafio.microservicos.pagamentos.pagamentos.service.dto;

import com.desafio.microservicos.pagamentos.pagamentos.domain.enumerations.FormaDePagamento;
import com.desafio.microservicos.pagamentos.pagamentos.domain.enumerations.StatusPagamento;

import java.math.BigDecimal;

public class PagamentoDTO {

    private BigDecimal valor;
    private String nome;
    private String numero;
    private String email;
    private String codigo;
    private Long pedidoId;
    private FormaDePagamento formaDePagamento;
    private StatusPagamento statusPagamento;
}
