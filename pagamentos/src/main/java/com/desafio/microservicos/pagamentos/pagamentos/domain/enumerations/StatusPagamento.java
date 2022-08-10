package com.desafio.microservicos.pagamentos.pagamentos.domain.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum StatusPagamento {


    CRIADO(1L, "Criado"),
    CONFIRMADO(2L, "Confirmado"),
    CANCELADO(3L, "Cancelado");


    private Long  id;
    private String desc;

}
