package com.desafio.microservices.pedidos.service;

import com.desafio.microservices.pedidos.domain.Item;
import com.desafio.microservices.pedidos.repository.ItemRepository;
import com.desafio.microservices.pedidos.service.dto.ItemDTO;
import com.desafio.microservices.pedidos.service.mapper.ItemDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemDTOMapper itemDTOMapper;

    public List<Item> createDefaultData(){
        List<Item> items = Arrays.asList(
                Item.builder().desc("Abacaxi").quantity(1).preco(10.00)
                        .build(),
                Item.builder().desc("Abacate").quantity(2).preco(3.50)
                        .build(),
                Item.builder().desc("Maca").quantity(1).preco(9.00)
                        .build()
        );

        return itemRepository.saveAll(items);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

}
