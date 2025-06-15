package com.atividade.services.strategy;

import com.atividade.domains.enums.TipoFrete;

public class FreteStrategy {

    public static Frete obterFrete(TipoFrete tipoFrete) {
        return switch (tipoFrete) {
            case AEREO -> new FreteAereo();
            case TERRESTRE -> new FreteTerrestre();
        };
    }
}
