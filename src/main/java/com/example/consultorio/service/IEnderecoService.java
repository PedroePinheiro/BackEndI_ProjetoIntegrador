package com.example.consultorio.service;

import java.util.List;

public interface IEnderecoService<T> {
    T salvar(T t);

    T buscar(int id);
    List<T> buscarTodos();

    T atualizar(int idPaciente, T t);

    String excluir(int id);
}
