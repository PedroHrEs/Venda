package com.atividade.domains.enums;

public enum StatusPedido {

    AGUARDANDO_PAGAMENTO(0, "AGUARDANDO_PAGAMENTO") {
        @Override
        public StatusPedido avancar() {
            return PAGO;
        }
        @Override
        public StatusPedido cancelar() {
            return CANCELADO;
        }

    },
    CANCELADO(2, "CANCELADO") {
        @Override
        public StatusPedido avancar() {
            throw new IllegalStateException("Pedido cancelado – não pode avançar.");
        }

        @Override
        public StatusPedido cancelar() {
            throw new IllegalStateException("Pedido já está cancelado.");
        }
    },
    PAGO(1, "PAGO") {
        @Override
        public StatusPedido avancar() {
            return ENVIADO;
        }
        @Override
        public StatusPedido cancelar() {
            return CANCELADO;
        }
    },
    ENVIADO(3, "ENVIADO") {
        @Override
        public StatusPedido avancar() {
            throw new IllegalStateException("Pedido já foi enviado.");
        }
        @Override
        public StatusPedido cancelar() {
            throw new IllegalStateException("Não é possível cancelar um pedido já enviado.");
        }
    };

    private Integer id;
    private String descricao;

    private StatusPedido(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() { return id; }

    public String getDescricao() { return descricao; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public abstract StatusPedido avancar();
    public abstract StatusPedido cancelar();

    public static StatusPedido toEnum(Integer id) {
        if (id == null) return null;
        for (StatusPedido s : values()) {
            if (s.getId().equals(id)) return s;
        }
        throw new IllegalArgumentException("Status do Pedido inválido: " + id);
    }
}
