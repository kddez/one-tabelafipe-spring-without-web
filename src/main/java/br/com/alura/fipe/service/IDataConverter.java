package br.com.alura.fipe.service;

import java.util.List;

public interface IDataConverter {

    <T> T getData(String json, Class <T> tClass);
    <T> List<T> getList(String json, Class <T> tClass);
}
