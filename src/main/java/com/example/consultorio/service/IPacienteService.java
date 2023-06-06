package com.example.consultorio.service;

import java.util.List;

public interface IPacienteService<T> {
    T salvar(T t);

    T buscar(int id);
    List<T> buscarTodos();

    T atualizar(int id, T t);

    String excluir(int id);


}
