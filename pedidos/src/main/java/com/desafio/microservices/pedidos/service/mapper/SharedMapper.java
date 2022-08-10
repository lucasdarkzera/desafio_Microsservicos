package com.desafio.microservices.pedidos.service.mapper;

public interface SharedMapper<ENTITY, DTO> {

    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);

}
