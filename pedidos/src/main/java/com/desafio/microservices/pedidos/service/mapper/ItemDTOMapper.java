package com.desafio.microservices.pedidos.service.mapper;

import com.desafio.microservices.pedidos.domain.Item;
import com.desafio.microservices.pedidos.service.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ItemDTOMapper extends SharedMapper<Item, ItemDTO>{

}
