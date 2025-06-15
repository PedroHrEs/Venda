package com.atividade.services;

import com.atividade.domains.Cliente;
import com.atividade.services.exceptions.DataIntegrityViolationException;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atividade.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.atividade.domains.dtos.ClienteDTO;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    
    public List<ClienteDTO> findAll(){
        return clienteRepo.findAll().stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
    }
    
    public Cliente findById(Long id){
        Optional<Cliente> obj = clienteRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado id:"+id));
    }
    public Cliente findByCpf(String cpf){
        Optional<Cliente> obj = clienteRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado cpf:"+cpf));
    }
    public Cliente create(ClienteDTO dto){
        dto.setId(null);
        validaCliente(dto);
        Cliente obj = new Cliente(dto);
        return clienteRepo.save(obj);
    }
    private void validaCliente(ClienteDTO dto){
        Optional<Cliente> obj = clienteRepo.findByCpf(dto.getCpf());
        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }

    }
    public Cliente update(Long id, ClienteDTO objDto){
        objDto.setId(id);
        Cliente oldObj = findById(id);
        validaCliente(objDto);
        oldObj = new Cliente(objDto);
        return clienteRepo.save(oldObj);
    }
    public void delete(Long id){
        Cliente obj = findById(id);
        if (obj.getPedidos().size()>0){
            throw new DataIntegrityViolationException("Cliente não pode ser deletado pois possui Pedidos vinculados");
        }
        clienteRepo.deleteById(id);
    }
}
