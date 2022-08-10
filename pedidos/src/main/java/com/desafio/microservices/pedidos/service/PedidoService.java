package com.desafio.microservices.pedidos.service;

import com.desafio.microservices.pedidos.domain.Item;
import com.desafio.microservices.pedidos.domain.PedidoModel;
import com.desafio.microservices.pedidos.domain.enumerations.StatusPedido;
import com.desafio.microservices.pedidos.repository.PedidoRepository;
import com.desafio.microservices.pedidos.service.dto.ItemDTO;
import com.desafio.microservices.pedidos.service.dto.PedidoDTO;
import com.desafio.microservices.pedidos.service.exceptions.NoContent;
import com.desafio.microservices.pedidos.service.mapper.ItemDTOMapper;
import com.desafio.microservices.pedidos.service.mapper.PedidoDTOMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private static Long counter = 0l;

    private final PedidoDTOMapper pedidoDTOMapper;

    private final PedidoRepository pedidoRepository;
    private final ItemService itemService;

    PedidoModel buildDummyData(){
        Set<Item> itens = Set.copyOf(Arrays.asList(
                Item.builder()
                        .desc("Desc item 1")
                        .quantity(2)
                        .id(1L).build(),
                Item.builder()
                        .desc("Desc item 2")
                        .quantity(1)
                        .id(2L).build()
        ));

        PedidoModel pedido = PedidoModel.builder()
                .dateTime(LocalDateTime.now())
                .id(++counter)
                .orderStatus(StatusPedido.REALIZADO)
                .items(itens)
                .build();

//        for (Item i : itens) i.setPedidoModel(pedido);
        return pedido;
    }

    public PedidoDTO findByIdFake(Long id){
        PedidoModel item = buildDummyData();

        PedidoDTO pedidoDTO = pedidoDTOMapper.toDto(item);

        return pedidoDTO;

    }

    public PedidoDTO findById(Long id){

        PedidoDTO pedidoDTO = pedidoDTOMapper.toDto(
                pedidoRepository.findById(id)
                        .orElse(new PedidoModel())
        );

        return pedidoDTO;
    }

    @Transactional
    public PedidoDTO save(@NonNull PedidoDTO pedidoDTO){
        pedidoDTO.setDateTime(LocalDateTime.now());
        PedidoModel pedidoAdicionado = pedidoRepository.save(pedidoDTOMapper.toEntity(pedidoDTO));

        return pedidoDTOMapper.toDto(pedidoAdicionado);

    }

    public PedidoDTO addItemInPedido(@NonNull Item item,
                                     @NonNull Long idPedido){
        Optional<PedidoModel> pedidoBanco = pedidoRepository.findById(idPedido);


        pedidoBanco.ifPresent( (PedidoModel pedido) -> {
            final var valorAtualPedido = pedido.getValor();
            final var valorItem = item.getPreco() * item.getQuantity();
            pedido.getItems().add(item);
            pedido.setValor(valorAtualPedido + valorItem);
            pedidoRepository.save(pedido);
        });

        return pedidoDTOMapper.toDto(pedidoRepository
                .findById(idPedido)
                .orElseThrow(NoContent::new)
        );

    }

    @Transactional
    public PedidoDTO trocaStatusPedido(@NonNull PedidoDTO pedidoDTO,
                                       @NonNull Long idPedido){
        var novoStatus = StatusPedido.generateByDesc(pedidoDTO.getOrderStatus());
        var pedidoEmBanco = pedidoRepository.findById(idPedido).orElseThrow(NoContent::new);

        validaProximoStatus(pedidoEmBanco, novoStatus);
        pedidoRepository.save(pedidoEmBanco);
        return pedidoDTOMapper.toDto(pedidoEmBanco);
    }

    public void validaProximoStatus(PedidoModel pedido, StatusPedido proximoStatusPedido){
        pedido.getOrderStatus().validadeNextState(proximoStatusPedido);
        pedido.setOrderStatus(proximoStatusPedido);
    }

    public List<PedidoModel> findAll(){
        return pedidoRepository.findAll();
    }


    @Transactional
    public void init() {
        itemService.createDefaultData();
        List<Item> itensCadastrados = itemService.findAll();

        List<PedidoModel> pedidos = Arrays.asList(
                PedidoModel.builder()
                        .dateTime(LocalDateTime.now())
                        .orderStatus(StatusPedido.REALIZADO)
                        .items(new HashSet<>())
                        .valor(0.00)
                        .build(),
                PedidoModel.builder()
                        .dateTime(LocalDateTime.now())
                        .orderStatus(StatusPedido.CANCELADO)
                        .items(new HashSet<>())
                        .valor(0.00)
                        .build()
        );

        pedidoRepository.saveAll(pedidos);
        List<PedidoModel> pedidosCadastrados = pedidoRepository.findAll();

        final var idPedido =  pedidosCadastrados.get(0).getId();

        addItemInPedido(itensCadastrados.get(0), idPedido);
        addItemInPedido(itensCadastrados.get(1), idPedido);
        addItemInPedido(itensCadastrados.get(2), idPedido);

        pedidoRepository.saveAll(pedidos);

    }
}
