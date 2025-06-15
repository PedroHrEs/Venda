package com.atividade.domains.enums;

public enum StatusPedido {

    AGUARDANDO_PAGAMENTO(0,"AGUARDANDO_PAGAMENTO"), PAGO(1,"PAGO"), CANCELADO(2,"CANCELADO"), ENVIADO(3,"ENVIADO");


    private Integer id;
    private String statusPedido;

    private StatusPedido(Integer id, String statusPedido) {
        this.id = id;
        this.statusPedido = statusPedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }
    @Override
    public String toString(){
        return "StatusPedido("+"id="+", statusPedido="+statusPedido+")";
    }

    public static StatusPedido toEnum(Integer id){
        if(id==null) return null;
        for(StatusPedido x : StatusPedido.values()){
            if(id.equals(x.getId())){
                return x;
            }

        }
        throw new IllegalArgumentException("Status Pedido inválido");
    }
}
