package com.rinhadebackend.model;

import java.time.LocalDate;

public abstract class Data {


    public void validarData(String data) {

        boolean dataEhFutura = aDataEhFutura(data);

        if(!dataEhFutura) {
            throw new IllegalArgumentException("Data inválida.");
        }
    }

    public boolean aDataEhFutura(String data) {

        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFornecida = LocalDate.parse(data);
        return dataAtual.isBefore(dataFornecida);

    }

    public boolean aDataEhFutura(LocalDate data) {
        return LocalDate.now().isBefore(data);
    }


    public boolean aDataEhPassada(String data) {

        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFornecida = LocalDate.parse(data);
        return dataAtual.isAfter(dataFornecida);

    }

    public boolean aDataEhPassada(LocalDate data) {
        return LocalDate.now().isAfter(data);
    }


}
