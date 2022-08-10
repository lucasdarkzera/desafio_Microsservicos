package com.desafio.microservicos.pagamentos.pagamentos.resource;


import com.desafio.microservicos.pagamentos.pagamentos.service.PagamentoService;
import com.desafio.microservicos.pagamentos.pagamentos.service.dto.ExecucaoPagamentoDTO;
import com.desafio.microservicos.pagamentos.pagamentos.service.dto.PagamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PagamentoResource {

    private final PagamentoService pagamentoService;

    private final String API_PREFIX = "/api/pagamentos";

    @GetMapping(API_PREFIX + "/{id}")
    public ResponseEntity<?> findById_2(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                "ok");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                "ok");
    }

    @PutMapping("/{id}/execPagamento")
    public ResponseEntity<?> alteraEstadoPagamento(@PathVariable("id") Long id,
                                                   @RequestBody ExecucaoPagamentoDTO pagamentoNovoEstado){

        var pagamentoAlterado = pagamentoService.executarPagamento(pagamentoNovoEstado, id);
        return ResponseEntity.ok(pagamentoAlterado);
    }

    @PostMapping("/pagamentos/{idPedido}")
    public ResponseEntity<?> create(@RequestBody PagamentoDTO pagamentoDTO,
                                    @PathVariable("idPedido") Long idPedido){
        var pagamento = pagamentoService.create(pagamentoDTO);

        return ResponseEntity.ok(pagamento);
    }

    @GetMapping("/pagamentos/init/{id}")
    public String init(@PathVariable("id") Long id){
        pagamentoService.init(id);
        return "-1";
    }




}
