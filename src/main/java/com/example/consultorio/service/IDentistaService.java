package com.example.consultorio.service;

import com.example.consultorio.dto.response.DentistaResponseDTO;
import com.example.consultorio.model.Dentista;

import java.util.List;
import java.util.Optional;

public interface IDentistaService {

    DentistaResponseDTO salvar(Dentista dentista);

    Optional<DentistaResponseDTO> buscar(int matriculaCadastro);
    List<DentistaResponseDTO> buscarTodos();

    Optional<DentistaResponseDTO> atualizar(int matriculaCadastro, Dentista dentista);
}
