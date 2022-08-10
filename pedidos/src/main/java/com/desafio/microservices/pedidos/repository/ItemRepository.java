package com.desafio.microservices.pedidos.repository;

import com.desafio.microservices.pedidos.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
