package com.example.consultorio.service;

import com.example.consultorio.dto.request.DentistaRequestDTO;
import com.example.consultorio.dto.response.DentistaResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IDentistaService {

    Optional<DentistaResponseDTO> salvar(DentistaRequestDTO requestDTO);

    Optional<DentistaResponseDTO> atualizar(int matriculaCadastro, DentistaRequestDTO requestDTO);

    Optional<DentistaResponseDTO> buscar(int matriculaCadastro);

    List<DentistaResponseDTO> buscarTodos();
}
