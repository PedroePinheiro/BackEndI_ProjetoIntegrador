package com.example.consultorio.service;

import com.example.consultorio.dto.request.PerfilRequestDTO;
import com.example.consultorio.model.Perfil;

import java.util.List;
import java.util.Optional;

public interface IPerfilService {

    Perfil salvar(PerfilRequestDTO t);

    Optional<Perfil> buscar(int id);

    List<Perfil> buscarTodos();

    Perfil atualizar(int id, PerfilRequestDTO t);

    String excluir(int id);

}
