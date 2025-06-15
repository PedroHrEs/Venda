package com.atividade.domains;

import com.atividade.domains.dtos.PedidoDTO;
import com.atividade.domains.enums.StatusPedido;
import com.atividade.domains.enums.TipoFrete;
import com.atividade.services.strategy.Frete;
import com.atividade.services.strategy.FreteStrategy;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name= "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
    private Long id;

    @NotNull
    private double valor;
    @NotNull
    @NotBlank
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    @Enumerated
    @JoinColumn(name="status")
    private StatusPedido statusPedido;

    @ManyToOne
    @JoinColumn(name="idcliente")
    private Cliente cliente;

    @Enumerated
    @JoinColumn(name="frete")
    private TipoFrete tipoFrete;

    public Pedido(){
    }

    public Pedido(Long id, double valor, String descricao,LocalDate dataCriacao, StatusPedido statusPedido, Cliente cliente, TipoFrete tipoFrete) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.statusPedido = statusPedido;
        this.cliente = cliente;
        this.tipoFrete = tipoFrete;
        calcularValorTotal();
    }

    public Pedido(PedidoDTO dto) {
        this.id = dto.getId();
        this.valor = dto.getValor();
        this.descricao = dto.getDescricao();
        this.dataCriacao = dto.getDataCriacao();
        this.statusPedido = StatusPedido.toEnum(dto.getStatusPedido());
        this.cliente = new Cliente();
        this.cliente.setId(dto.getCliente());
        this.tipoFrete = TipoFrete.toEnum(dto.getTipoFrete());
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        Frete frete = FreteStrategy.obterFrete(this.tipoFrete);
        double valorFrete = frete.calcula(this.valor);
        this.valor+= valorFrete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoFrete getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(TipoFrete tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
