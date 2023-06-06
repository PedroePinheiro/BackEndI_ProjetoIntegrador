package com.example.consultorio.service;

import java.util.List;

public interface IDentistaService<T> {

    T salvar(T t);

    T buscar(int matriculaCadastro);
    List<T> buscarTodos();

    T atualizar(int matriculaCadastro, T t);

}
