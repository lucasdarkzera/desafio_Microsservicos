package com.desafio.microservicos.pagamentos.pagamentos.service;

import com.desafio.microservicos.pagamentos.pagamentos.domain.Pagamento;
import com.desafio.microservicos.pagamentos.pagamentos.domain.enumerations.FormaDePagamento;
import com.desafio.microservicos.pagamentos.pagamentos.domain.enumerations.StatusPagamento;
import com.desafio.microservicos.pagamentos.pagamentos.repository.PagamentoRepository;
import com.desafio.microservicos.pagamentos.pagamentos.service.dto.ExecucaoPagamentoDTO;
import com.desafio.microservicos.pagamentos.pagamentos.service.dto.PagamentoDTO;
import com.desafio.microservicos.pagamentos.pagamentos.service.dto.PedidoDTO;
import com.desafio.microservicos.pagamentos.pagamentos.service.exception.NoContent;
import com.desafio.microservicos.pagamentos.pagamentos.service.feign.PedidoClient;
import com.desafio.microservicos.pagamentos.pagamentos.service.mapper.PagamentoDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    // TODO:: criar mapper para transformar Collection<T>
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoDTOMapper pagamentoDTOMapper;
    private final PedidoClient pedidoClient;

    @Transactional
    public PagamentoDTO create(PagamentoDTO pagamentoDTO){
        var pagamento = pagamentoDTOMapper.toEntity(pagamentoDTO);

        return pagamentoDTOMapper.toDTO(
                pagamentoRepository.save(pagamento)
        );
    }

    public PagamentoDTO findById(Long id){
       return pagamentoDTOMapper.toDTO(
               pagamentoRepository.findById(id).orElseThrow(NoContent::new)
       );
    }

    public List<Pagamento> findAll(){
        var pagamentos = pagamentoRepository.findAll();
        return pagamentos;

    }

    public PagamentoDTO executarPagamento(ExecucaoPagamentoDTO execPagamento,
                                          Long id){
        final var pagamentoBanco = pagamentoRepository.findById(id).orElseThrow();
        final var dto = pagamentoDTOMapper.toDTO(pagamentoBanco);

        PedidoDTO pedido = pedidoClient.getPedidoById(pagamentoBanco.getPedidoId());

//        pagamentoBanco.setValor(pedido);

        if (isPagamentoValido()){

        }

        return dto;
    }

    public boolean isPagamentoValido(){
        return true;
    }

    @Transactional
    public List<Pagamento> init(Long idPedido) {

        var pedidoDTO = pedidoClient.getPedidoById(idPedido);

        List<Pagamento> pagamentos = Arrays.asList(
                Pagamento.builder()
                        .formaDePagamento(FormaDePagamento.CARTAO_CREDITO)
                        .statusPagamento(StatusPagamento.CRIADO)
                        .email("pedido@Client.net")
                        .nome("Robertinho")
                        .pedidoId(pedidoDTO.getId())
                        .valor(pedidoDTO.getValor())
                        .build()
        );

        pagamentoRepository.saveAll(pagamentos);
        return pagamentoRepository.findAll();

    }
}
