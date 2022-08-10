package com.desafio.microservicos.pagamentos.pagamentos.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDTO {

    private int quantity;
    private String desc;
    private Double preco;

}
