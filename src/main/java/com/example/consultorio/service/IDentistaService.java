package com.example.consultorio.service;

import com.example.consultorio.dto.request.DentistaRequestDTO;
import com.example.consultorio.dto.response.DentistaResponseDTO;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IDentistaService {

    Optional<DentistaResponseDTO> salvar(DentistaRequestDTO requestDTO) throws InvalidDataException;

    Optional<DentistaResponseDTO> atualizar(int matriculaCadastro, DentistaRequestDTO requestDTO) throws InvalidDataException, ResourceNotFoundException;

    Optional<DentistaResponseDTO> buscar(int matriculaCadastro) throws ResourceNotFoundException;

    List<DentistaResponseDTO> buscarTodos();
}
