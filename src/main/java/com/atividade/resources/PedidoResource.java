package com.atividade.resources;

import com.atividade.domains.Pedido;
import com.atividade.domains.dtos.PedidoDTO;
import com.atividade.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value= "/pedido")
@Tag(name = "Pedido", description = "API para gerenciamento de pedidos")
public class PedidoResource {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Listar todos os pedidos",
            description = "Retorna uma lista com todos os pedidos cadastrados")
    public ResponseEntity<List<PedidoDTO>> findAll(){
        return ResponseEntity.ok().body(pedidoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um pedido por id",
            description = "Realiza a busca de um pedido cadastrado por id")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id){
        Pedido obj = this.pedidoService.findById(id);
        return ResponseEntity.ok().body(new PedidoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo pedido",
            description = "Cria um novo pedido com base nos dados fornecidos")
    public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera pedido",
            description = "Altera um pedido existente")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @Valid @RequestBody PedidoDTO objDto){
        Pedido Obj = pedidoService.update(id, objDto);
        return ResponseEntity.ok().body(new PedidoDTO(Obj));
    }
    @PutMapping("/{id}/avancar")
    @Operation(summary = "Avança o status do pedido",
            description = "Avança o status de um pedido existente")
    public ResponseEntity<PedidoDTO> avancar(@PathVariable Long id) {
        Pedido statusPedido = pedidoService.avancar(id);
        return ResponseEntity.ok(new PedidoDTO(statusPedido));
    }

    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancela do pedido",
            description = "Cancela um pedido existente")
    public ResponseEntity<PedidoDTO> cancelar(@PathVariable Long id) {
        Pedido statusPedido = pedidoService.cancelar(id);
        return ResponseEntity.ok(new PedidoDTO(statusPedido));
    }
}
