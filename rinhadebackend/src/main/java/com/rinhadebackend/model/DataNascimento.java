package com.rinhadebackend.model;


import java.time.LocalDate;


public class DataNascimento extends Data {


    private LocalDate nascimento;

    public DataNascimento(String data){
        SetData(data);
    }

    public LocalDate getData() {

        return nascimento;
    }

    public void SetData(String v){

        validarData(v);
        this.nascimento = LocalDate.parse(v);
    }


}
