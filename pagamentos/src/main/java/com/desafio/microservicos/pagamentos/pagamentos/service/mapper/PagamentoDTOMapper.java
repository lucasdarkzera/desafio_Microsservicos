package com.desafio.microservicos.pagamentos.pagamentos.service.mapper;

import com.desafio.microservicos.pagamentos.pagamentos.domain.Pagamento;
import com.desafio.microservicos.pagamentos.pagamentos.service.dto.PagamentoDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PagamentoDTOMapper extends SharedMapper<Pagamento, PagamentoDTO>{

}
