package com.example.consultorio.service.impl;

import com.example.consultorio.dto.request.PacienteRequestDTO;
import com.example.consultorio.dto.response.PacienteResponseDTO;
import com.example.consultorio.model.Paciente;
import com.example.consultorio.repository.IPacienteRepository;
import com.example.consultorio.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class PacienteServiceImpl implements IPacienteService{

    @Autowired
    private IPacienteRepository iPacienteRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Optional<PacienteResponseDTO> salvar(PacienteRequestDTO pacienteRequestDTO) {
        Paciente paciente = mapper.convertValue(pacienteRequestDTO, Paciente.class);
        Paciente savedPaciente = iPacienteRepository.save(paciente);
        PacienteResponseDTO pacienteResponseDTO = mapper.convertValue(savedPaciente, PacienteResponseDTO.class);
        pacienteResponseDTO.setConsultas(new ArrayList<>());
        return Optional.ofNullable(pacienteResponseDTO);
    }

    @Override
    public Optional<PacienteResponseDTO> buscar(int id) {
        Optional<Paciente> byId = iPacienteRepository.findById(id);
        return Optional.ofNullable(mapper.convertValue(byId, PacienteResponseDTO.class));
    }

    @Override
    public List<PacienteResponseDTO> buscarTodos() {
        List<Paciente> all = iPacienteRepository.findAll();
        List<PacienteResponseDTO> pacienteResponseDTOList = new ArrayList<>();
        all.forEach(paciente -> {
            pacienteResponseDTOList.add(mapper.convertValue(paciente,PacienteResponseDTO.class));
        });
        return pacienteResponseDTOList;
    }

    @Override
    public Optional<PacienteResponseDTO> atualizar(int id,PacienteRequestDTO pacienteRequestDTO){
        Optional<Paciente> pacienteBusca = iPacienteRepository.findById(id);
        Optional<PacienteResponseDTO> responseDTO = null;
        if (pacienteBusca.isPresent()){
            Paciente paciente = pacienteBusca.get();
            paciente.setId(id);
            paciente.setNome(pacienteRequestDTO.getNome());
            paciente.setSobrenome(pacienteRequestDTO.getSobrenome());
            paciente.setRg(pacienteRequestDTO.getRg());
            paciente.setDataAlta(pacienteRequestDTO.getDataAlta());
            paciente.setEndereco(pacienteRequestDTO.getEndereco());
            Paciente pacienteSalvo = iPacienteRepository.save(paciente);
            responseDTO = Optional.ofNullable(mapper.convertValue(pacienteSalvo, PacienteResponseDTO.class));
        }
        return responseDTO;
    }

    @Override
    public String excluir(int id) {
        iPacienteRepository.deleteById(id);
        return "usuario removido";
    }
}
