package com.desafio.microservicos.pagamentos.pagamentos.domain.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum FormaDePagamento {

    PIX(1L, "Pix"),
    DINHEIRO(2L, "Dinheiro"),
    CARTAO_DEBITO(3L, "Cartao debito"),
    CARTAO_CREDITO(4L, "Cartao credito");

    private Long id;
    private String desc;

}
