package com.example.consultorio.service;

import com.example.consultorio.dto.request.UsuarioRequestDTO;
import com.example.consultorio.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Usuario salvar(UsuarioRequestDTO t);

    Optional<Usuario> buscar(int id);

    List<Usuario> buscarTodos();

    Usuario atualizar (int id, UsuarioRequestDTO t);

    String excluir(int id);

}
