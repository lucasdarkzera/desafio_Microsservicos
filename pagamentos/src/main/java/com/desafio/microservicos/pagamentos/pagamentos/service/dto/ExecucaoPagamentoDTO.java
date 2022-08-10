package com.desafio.microservicos.pagamentos.pagamentos.service.dto;

import java.math.BigDecimal;

public class ExecucaoPagamentoDTO {

    private Long idPayment;
    private Long idOrder;
    private String nameClient;
    private String orderStatus;
    private BigDecimal amount;

}
