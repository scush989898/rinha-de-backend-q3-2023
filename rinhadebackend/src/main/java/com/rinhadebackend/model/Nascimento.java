package com.rinhadebackend.model;


import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nascimento {

    private static final String REGEX_DATA = "\\d{4}-\\d{2}-\\d{2}";
    private static final Pattern PADRAO_DATA = Pattern.compile(REGEX_DATA);
    private LocalDate nascimento;
    public Nascimento(String data){
        SetData(data);

    }

    public LocalDate getData() {
        return nascimento;
    }

    public void SetData(String v){

        validarData(v);
        this.nascimento = LocalDate.parse(v);
    }


    private void validarData (String data) {

        data = data.trim();
        Matcher validador = PADRAO_DATA.matcher(data);

        if(!validador.matches()) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }

        LocalDate dataAtual = LocalDate.now();

        LocalDate dataFornecida = LocalDate.parse(data);

        if(dataAtual.isBefore(dataFornecida)) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }

    }


}
