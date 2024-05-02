package br.com.alura.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelo(@JsonAlias("modelos") List<Dados> models) {

    @Override
    public String toString() {
        return
                "MODELOS: " + models;
    }
}
