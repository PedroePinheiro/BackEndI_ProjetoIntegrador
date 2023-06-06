package com.example.consultorio.service;

import java.util.Optional;

public interface IConsultaService<T> {
    T salvar(T t);

    Optional<T> atualizar(int id, T t);

    void cancelar(int id);

}
