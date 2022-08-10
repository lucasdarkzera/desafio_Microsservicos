package com.desafio.microservices.pedidos.repository;

import com.desafio.microservices.pedidos.domain.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
}
