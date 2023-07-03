package com.example.consultorio.service;

import com.example.consultorio.dto.request.ConsultaRequestDTO;
import com.example.consultorio.dto.response.ConsultaDentistaResponseDTO;
import com.example.consultorio.dto.response.ConsultaPacienteResponseDTO;
import com.example.consultorio.dto.response.ConsultaResponseDTO;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IConsultaService{
    Optional<ConsultaResponseDTO> salvar(ConsultaRequestDTO t) throws InvalidDataException, ResourceNotFoundException;

    Optional<ConsultaResponseDTO> atualizar(int id, ConsultaRequestDTO t) throws ResourceNotFoundException, InvalidDataException;

    void cancelar(int id);

    List<ConsultaResponseDTO> buscarTodos();

    List<ConsultaPacienteResponseDTO> buscarPorPaciente(int id);

    List<ConsultaDentistaResponseDTO> buscarPorDentista(int id);




}
