package com.atividade.services.strategy;

public class FreteTerrestre implements Frete {

    @Override
    public double calcula(double valorPedido) {
        return valorPedido*0.05;
    }
}
