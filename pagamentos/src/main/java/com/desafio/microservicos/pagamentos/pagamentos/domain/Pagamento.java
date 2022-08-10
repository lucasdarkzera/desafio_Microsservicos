package com.desafio.microservicos.pagamentos.pagamentos.domain;

import com.desafio.microservicos.pagamentos.pagamentos.domain.enumerations.FormaDePagamento;
import com.desafio.microservicos.pagamentos.pagamentos.domain.enumerations.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "pagamentos")
@Builder
public class Pagamento {

    // TODO :: add Validator nesse projeto pra usar as notacoes @Min, @Max, @Mail etc..

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(1L)
    private double valor;
    @NotNull
    private String nome;
    private String numero;
    private String email;
    private String expiracao;
    private String codigo;
    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

}
