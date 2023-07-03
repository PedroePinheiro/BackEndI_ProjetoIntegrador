package com.example.consultorio.service;

import com.example.consultorio.dto.request.PacienteRequestDTO;
import com.example.consultorio.dto.response.PacienteResponseDTO;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Optional<PacienteResponseDTO> salvar(PacienteRequestDTO  t) throws InvalidDataException;

    Optional<PacienteResponseDTO> buscar(int id) throws ResourceNotFoundException;
    List<PacienteResponseDTO> buscarTodos();

    Optional<PacienteResponseDTO> atualizar(int id, PacienteRequestDTO t) throws ResourceNotFoundException, InvalidDataException;

    String excluir(int id) throws ResourceNotFoundException;


}
