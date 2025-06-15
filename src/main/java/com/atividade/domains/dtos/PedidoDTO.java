package com.atividade.domains.dtos;


import com.atividade.domains.Pedido;
import com.atividade.domains.enums.TipoFrete;
import com.atividade.services.strategy.Frete;
import com.atividade.services.strategy.FreteStrategy;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PedidoDTO {

    private Long id;

    @NotNull(message="O campo valor não pode ser nulo")
    private double valor;

    @NotNull(message = "O campo descricao não pode ser nulo")
    @NotBlank(message = "O campo descricao não pode estar vazio")
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    @NotNull(message = "O campo Grupo Produto é requerido")
    private Long cliente;
    private String nomeCliente;

    private int statusPedido;
    private int tipoFrete;

    public PedidoDTO(){

    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.valor = pedido.getValor();
        this.descricao = pedido.getDescricao();
        this.dataCriacao = LocalDate.now();
        this.cliente = pedido.getCliente().getId();
        this.nomeCliente = pedido.getCliente().getNome();
        this.statusPedido = pedido.getStatusPedido().getId();
        this.tipoFrete = pedido.getTipoFrete().getId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O campo valor não pode ser nulo") double getValor() {
        return valor;
    }

    public void setValor(@NotNull(message = "O campo valor não pode ser nulo") double valor) {
        this.valor = valor;
    }

    public @NotNull(message = "O campo descricao não pode ser nulo") @NotBlank(message = "O campo descricao não pode estar vazio") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull(message = "O campo descricao não pode ser nulo") @NotBlank(message = "O campo descricao não pode estar vazio") String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public @NotNull(message = "O campo Grupo Produto é requerido") Long getCliente() {
        return cliente;
    }

    public void setCliente(@NotNull(message = "O campo Grupo Produto é requerido") Long cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(int statusPedido) {
        this.statusPedido = statusPedido;
    }

    public int getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(int tipoFrete) {
        this.tipoFrete = tipoFrete;
    }
}
