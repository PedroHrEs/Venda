package com.atividade.services.strategy;

public class FreteAereo implements Frete {

    @Override
    public double calcula(double valorPedido) {
        return valorPedido*0.1;
    }
}
