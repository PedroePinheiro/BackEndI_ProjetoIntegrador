package com.example.consultorio.service;

import java.util.List;
import java.util.Optional;

public interface IDentistaService<T> {

    T salvar(T t);

    Optional<T> buscar(int matriculaCadastro);
    List<T> buscarTodos();

    Optional<T> atualizar(int matriculaCadastro, T t);

}
