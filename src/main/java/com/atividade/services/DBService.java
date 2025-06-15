package com.atividade.services;

import com.atividade.domains.Cliente;
import com.atividade.domains.Pedido;
import com.atividade.domains.enums.StatusPedido;
import com.atividade.domains.enums.TipoFrete;
import com.atividade.repositories.ClienteRepository;
import com.atividade.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DBService {


        @Autowired
        private ClienteRepository clienteRepo;

        @Autowired
        private PedidoRepository pedidoRepo;

        public void initDB(){

            Cliente cliente01 = new Cliente(null, "Pedro", "52570865818");

            clienteRepo.save(cliente01);

            Pedido pedido01 = new Pedido(null, 300, "bolo", LocalDate.now(), StatusPedido.AGUARDANDO_PAGAMENTO, cliente01, TipoFrete.AEREO);

            pedidoRepo.save(pedido01);
        }

    }

