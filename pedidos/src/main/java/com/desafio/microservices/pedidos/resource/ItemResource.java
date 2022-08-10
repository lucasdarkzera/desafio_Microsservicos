package com.desafio.microservices.pedidos.resource;

import com.desafio.microservices.pedidos.domain.Item;
import com.desafio.microservices.pedidos.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemResource {

    private final ItemService itemService;

    @GetMapping("/items/init")
    public ResponseEntity<List<Item>> init(){
        return ResponseEntity.ok(
                itemService.createDefaultData()
        );
    }

}
