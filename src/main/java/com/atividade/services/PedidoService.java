package com.atividade.services;

import com.atividade.domains.Pedido;
import com.atividade.domains.dtos.PedidoDTO;
import com.atividade.repositories.PedidoRepository;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;


    public List<PedidoDTO> findAll(){
        return pedidoRepo.findAll().stream().map(obj -> new PedidoDTO(obj)).collect(Collectors.toList());
    }

    public Pedido findById(Long id){
        Optional<Pedido> obj = pedidoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado id:"+id));
    }

    public Pedido create(PedidoDTO dto){
        dto.setId(null);
        Pedido obj = new Pedido(dto);
        return pedidoRepo.save(obj);
    }

    public Pedido update(Long id, PedidoDTO objDto){
        objDto.setId(id);
        Pedido oldObj = findById(id);
        oldObj = new Pedido(objDto);
        return pedidoRepo.save(oldObj);
    }
}
