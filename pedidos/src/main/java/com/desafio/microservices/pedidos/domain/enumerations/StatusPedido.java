package com.desafio.microservices.pedidos.domain.enumerations;

import com.desafio.microservices.pedidos.service.exceptions.AlteracaoStatusPedidoInvalido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum StatusPedido {

    REALIZADO(1L, "Realizado"){
        @Override
        public void validadeNextState(StatusPedido statusPedido) {
            if (!statusPedido.equals(PAGO) | !statusPedido.equals(NAO_AUTORIZADO))
                throw new AlteracaoStatusPedidoInvalido();
        }
    },

    CANCELADO(2L, "cancelado"){
        @Override
        public void validadeNextState(StatusPedido statusPedido) {
//            if (!statusPedido.equals(null))
//                throw new AlteracaoStatusPedidoInvalido();
        }
    },

    PAGO(3L, "pago"){
        @Override
        public void validadeNextState(StatusPedido statusPedido) {
            if (!statusPedido.equals(CONFIRMADO))
                throw new AlteracaoStatusPedidoInvalido();
        }
    },

    NAO_AUTORIZADO(4L, "nao autorizado"){
        @Override
        public void validadeNextState(StatusPedido statusPedido) {
            if (!statusPedido.equals(CANCELADO))
                throw new AlteracaoStatusPedidoInvalido();
        }
    },

    CONFIRMADO(5L, "confirmado"){
        @Override
        public void validadeNextState(StatusPedido statusPedido) {
            if (!statusPedido.equals(PRONTO))
                throw new AlteracaoStatusPedidoInvalido();
        }
    },

    PRONTO(6L, "pronto"){
        @Override
        public void validadeNextState(StatusPedido statusPedido) {
            if (!statusPedido.equals(SAIU_PARA_ENTREGA))
                throw new AlteracaoStatusPedidoInvalido();
        }
    },

    SAIU_PARA_ENTREGA(7L, "Saiu para entrega"){
        @Override
        public void validadeNextState(StatusPedido statusPedido) {
            if (!statusPedido.equals(ENTREGUE))
                throw new AlteracaoStatusPedidoInvalido();
        }
    },

    ENTREGUE(8L, "entregue"){
        @Override
        public void validadeNextState(StatusPedido statusPedido) { }
    };

    private Long id;
    private String description;

    public abstract void validadeNextState(StatusPedido statusPedido);


    public static StatusPedido getDescById(Long id){
        return Stream.of(StatusPedido.values())
                .filter(enm -> enm.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    // TODO :: Mudar para getByDesc e getById
    public static StatusPedido generateByDesc(String description){
        return Stream.of(StatusPedido.values())
                .filter(enm -> enm.getDescription().equalsIgnoreCase(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
