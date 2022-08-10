package com.desafio.microservicos.pagamentos.pagamentos.service.mapper;

public interface SharedMapper<E, DTO> {

   DTO toDTO(E e);

   E toEntity(DTO dto);

}
