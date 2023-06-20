package com.example.consultorio.service;

import com.example.consultorio.dto.response.PacienteResponseDTO;
import com.example.consultorio.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    PacienteResponseDTO salvar(Paciente t);

    Optional<PacienteResponseDTO> buscar(int id);
    List<PacienteResponseDTO> buscarTodos();

    Optional<PacienteResponseDTO> atualizar(int id, Paciente t);

    String excluir(int id);


}
