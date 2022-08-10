package com.desafio.microservices.pedidos.service.mapper;

import com.desafio.microservices.pedidos.domain.PedidoModel;
import com.desafio.microservices.pedidos.service.dto.PedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.desafio.microservices.pedidos.domain.enumerations.StatusPedido;

@Mapper(
        imports = { StatusPedido.class },
        uses    = { ItemDTOMapper.class }
)
public interface PedidoDTOMapper {

    PedidoDTOMapper INSTANCE = Mappers.getMapper(PedidoDTOMapper.class);

    @Mapping(target = "orderStatus",
            expression = "java(" +
                    "pedido.orderStatus.getDescById(pedido.orderStatus.getId()).getDescription()" +
                    ")"
    )
    PedidoDTO toDto(PedidoModel pedido);

    @Mapping(target = "orderStatus",
            expression = "java(" +
                    "StatusPedido.generateByDesc(dto.getOrderStatus())" +
                    ")"
    )
    PedidoModel toEntity(PedidoDTO dto);

}
