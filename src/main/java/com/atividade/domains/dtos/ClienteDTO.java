package com.atividade.domains.dtos;


import com.atividade.domains.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {


    private Long id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode estar vazio")
    private String nome;

    @NotNull(message = "O campo cpf não pode ser nulo")
    @CPF
    private String cpf;

    public ClienteDTO(){
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "O campo nome não pode estar vazio") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "O campo nome não pode estar vazio") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O campo cpf não pode ser nulo") @CPF String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull(message = "O campo cpf não pode ser nulo") @CPF String cpf) {
        this.cpf = cpf;
    }
}
