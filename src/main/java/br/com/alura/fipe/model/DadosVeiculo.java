package br.com.alura.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(@JsonAlias("Modelo") String modelo,
                           @JsonAlias("AnoModelo") String ano,
                           @JsonAlias("Valor") String valor,
                           @JsonAlias("Combustivel") String combustivel) {

    @Override
    public String toString() {
        return  modelo +
                ", ano: " + ano +
                ", valor " + valor +
                ", combustível: " + combustivel;
    }
}
