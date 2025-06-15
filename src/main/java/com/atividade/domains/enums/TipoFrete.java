package com.atividade.domains.enums;

public enum TipoFrete {

    TERRESTRE(0, "TERRESTRE"), AEREO(1, "AEREO");


    private int id;
    private String tipoFrete;

    TipoFrete(int id, String tipoFrete) {
        this.id = id;
        this.tipoFrete = tipoFrete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(String tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public static TipoFrete toEnum(Integer id){
        if(id==null) return null;
        for(TipoFrete x : TipoFrete.values()){
            if(id.equals(x.getId())){
                return x;
            }

        }
        throw new IllegalArgumentException("Tipo do Frete inválido");
    }
}
