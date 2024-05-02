package br.com.alura.fipe.main;

import br.com.alura.fipe.model.Dados;
import br.com.alura.fipe.model.DadosModelo;
import br.com.alura.fipe.model.DadosVeiculo;
import br.com.alura.fipe.service.APIConsumer;
import br.com.alura.fipe.service.DataConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * 1. Buscar marcas por tipo do veiculo;
 * 2. Buscar uma lista modelos por código da marca;
 * 3. Buscar um modelo específico por nome do modelo;
 * 4. mostrar os valores de um modelo específico.
 * */

public class MainApplication {

    private final Scanner sc = new Scanner(System.in);
    private final APIConsumer consumer;
    private final DataConverter converter;

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public MainApplication(APIConsumer consumer, DataConverter converter) {
        this.consumer = consumer;
        this.converter = converter;
    }


    public void showMenu(){


        var menu = """
               *** OPÇÕES ***
               Carro
               Moto
               Caminhão
               
               Digite uma das opções para consulta: 
               """;

        System.out.println(menu);
        var option = sc.nextLine();
        String url;

        // 1. Buscar marcas por tipo do veiculo;

        if(option.toLowerCase().contains("carr")){
            url = URL_BASE + "carros/marcas";
        }else if(option.toLowerCase().contains("mot")){
            url = URL_BASE + "motos/marcas";
        }else {
            url = URL_BASE + "caminhoes/marcas";
        }

        var json = consumer.getData(url);
        List<Dados> marcas = converter.getList(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::code))
                .forEach(System.out::println);

        // 2. Buscar todos os modelos por código da marca;

        System.out.println("Digite o código da marca: ");
        option = sc.nextLine();

        url = url + "/" + option + "/modelos";
        json = consumer.getData(url);
        var listaModelos = converter.getData(json, DadosModelo.class);

        System.out.println("Modelos desta marca");
        listaModelos.models().stream()
                .sorted(Comparator.comparing(Dados::code))
                .forEach(System.out::println);

        // 3. Filtro de modelo por nome.

        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        var nomeVeiculo = sc.nextLine();

        var modelosFiltrados = listaModelos.models().stream()
                .filter(m -> m.name().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .toList();

        System.out.println("\nModelos filtrados:");
        modelosFiltrados.forEach(System.out::println);

        // 4. Mostrar os valores de um modelo específico.

        System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação: ");
        var codigoModelo = sc.nextLine();

        url = url + "/" + codigoModelo + "/anos";
        json = consumer.getData(url);
        List<Dados> anos = converter.getList(json, Dados.class);
        List<DadosVeiculo> veiculos = new ArrayList<>();

        for (Dados ano : anos) {
            var enderecoAnos = url + "/" + ano.code();
            json = consumer.getData(enderecoAnos);
            DadosVeiculo veiculo = converter.getData(json, DadosVeiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);

    }

}
