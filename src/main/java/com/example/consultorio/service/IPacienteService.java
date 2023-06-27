package com.example.consultorio.service;

import com.example.consultorio.dto.request.PacienteRequestDTO;
import com.example.consultorio.dto.response.PacienteResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Optional<PacienteResponseDTO> salvar(PacienteRequestDTO  t);

    Optional<PacienteResponseDTO> buscar(int id);
    List<PacienteResponseDTO> buscarTodos();

    Optional<PacienteResponseDTO> atualizar(int id, PacienteRequestDTO t);

    String excluir(int id);


}
