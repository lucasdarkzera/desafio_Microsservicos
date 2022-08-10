package com.desafio.microservices.pedidos.resource;

import com.desafio.microservices.pedidos.domain.Item;
import com.desafio.microservices.pedidos.domain.PedidoModel;
import com.desafio.microservices.pedidos.service.PedidoService;
import com.desafio.microservices.pedidos.service.dto.ItemDTO;
import com.desafio.microservices.pedidos.service.dto.PedidoDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class PedidoResource {

    private final String API_PREFIX="/api/pedidos";

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/pedidos/init")
    public ResponseEntity<?> alt(){

        pedidoService.init();
        return ResponseEntity.ok("k");
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<?> findById(@RequestHeader Map<String, String> headers,
                                      @PathVariable("id") Long id){

        if (headers.containsKey("myatt")){
            return ResponseEntity.ok(
                    pedidoService.findById(id)
            );
        }

        return ResponseEntity.ok(
                pedidoService.findById(id)
        );

    }

    @PostMapping("/pedidos")
    public ResponseEntity<?> create(@RequestBody PedidoDTO pedidoDTO){
        pedidoService.save(pedidoDTO);
        return ResponseEntity.ok("ok");
    }

//    @PostMapping("/pedidos/{idPedido}/addItem")
//    public PedidoDTO addItemToPedido(@RequestBody ItemDTO itemDTO,
//                                     @PathVariable("idPedido") Long idPedido){
//        return pedidoService.addItemInPedido(itemDTO, idPedido);
//
//    }

    @GetMapping("/pedidos/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @PutMapping("/pedidos/{idPedido}")
    public ResponseEntity<?> updateStatus(@RequestBody @NonNull PedidoDTO pedidoNovoStatus,
                                          @PathVariable("idPedido") Long idPedido){
        return ResponseEntity.ok(
                pedidoService.trocaStatusPedido(pedidoNovoStatus, idPedido)
        );
    }


}
