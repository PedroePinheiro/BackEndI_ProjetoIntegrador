package com.example.consultorio.service;

import java.util.List;
import java.util.Optional;

public interface IPacienteService<T> {
    T salvar(T t);

    Optional<T> buscar(int id);
    List<T> buscarTodos();

    Optional<T> atualizar(int id, T t);

    String excluir(int id);


}
