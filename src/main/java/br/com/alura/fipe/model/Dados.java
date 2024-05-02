package br.com.alura.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Dados(@JsonAlias("codigo") String code, @JsonAlias("nome") String name) {

    @Override
    public String toString() {
        return "CODE: " + code + '\'' +
                ", NAME: " + name + '\'';
    }
}
