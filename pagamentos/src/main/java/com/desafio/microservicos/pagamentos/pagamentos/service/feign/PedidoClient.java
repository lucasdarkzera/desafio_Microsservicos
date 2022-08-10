package com.desafio.microservicos.pagamentos.pagamentos.service.feign;

import com.desafio.microservicos.pagamentos.pagamentos.service.dto.PedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pedidos",
        url = "http://localhost:8765/pedidos"
)
public interface PedidoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{idPedido}")
    PedidoDTO getPedidoById(Long idPedido);

}
