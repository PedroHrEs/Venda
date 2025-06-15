package com.atividade.resources;

import com.atividade.domains.Cliente;
import com.atividade.domains.dtos.ClienteDTO;
import com.atividade.services.ClienteService;
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
@RequestMapping(value= "/cliente")
@Tag(name = "Cliente", description = "API para gerenciamento de clientes")
public class ClienteResource {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Listar todos os clientes",
            description = "Retorna uma lista com todos os clientes cadastrados")
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um cliente por id",
            description = "Realiza a busca de um cliente cadastrado por id")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
        Cliente obj = this.clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Busca um cliente por cpf",
            description = "Realiza a busca de um cliente cadastrado por cpf")
    public ResponseEntity<ClienteDTO> findByCpf(@PathVariable String cpf){
        Cliente obj = this.clienteService.findByCpf(cpf);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo cliente",
            description = "Cria um novo cliente com base nos dados fornecidos")
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto){
        Cliente cliente = clienteService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera cliente",
            description = "Altera um cliente existente")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO objDto){
        Cliente Obj = clienteService.update(id, objDto);
        return ResponseEntity.ok().body(new ClienteDTO(Obj));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um cliente",
            description = "Remove cliente a partir do seu Id")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
