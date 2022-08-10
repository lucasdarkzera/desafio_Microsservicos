package com.desafio.microservicos.pagamentos.pagamentos.repository;

import com.desafio.microservicos.pagamentos.pagamentos.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
