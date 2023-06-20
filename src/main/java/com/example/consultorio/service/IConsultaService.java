package com.example.consultorio.service;

import com.example.consultorio.dto.request.ConsultaRequestDTO;
import com.example.consultorio.dto.response.ConsultaDentistaResponseDTO;
import com.example.consultorio.dto.response.ConsultaPacienteResponseDTO;
import com.example.consultorio.dto.response.ConsultaResponseDTO;
import com.example.consultorio.model.Consulta;

import java.util.List;
import java.util.Optional;

public interface IConsultaService{
    Optional<ConsultaResponseDTO> salvar(ConsultaRequestDTO t);

    Optional<ConsultaResponseDTO> atualizar(int id, ConsultaRequestDTO t);

    void cancelar(int id);

    List<ConsultaResponseDTO> buscarTodos();

    Optional<ConsultaPacienteResponseDTO> buscarPorPaciente(int id);
    Optional<ConsultaDentistaResponseDTO> buscarPorDentista(int id);




}
