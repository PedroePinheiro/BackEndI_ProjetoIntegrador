package com.example.consultorio.service.impl;

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
    public PacienteResponseDTO salvar(Paciente paciente) {
        Paciente save = iPacienteRepository.save(paciente);
        save.setConsultas(new ArrayList());
        return mapper.convertValue(save,PacienteResponseDTO.class);
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
    public Optional<PacienteResponseDTO> atualizar(int id,Paciente paciente){
        Optional<Paciente> pacienteBusca = iPacienteRepository.findById(id);
        Optional<PacienteResponseDTO> pacienteResponseDTO = null;
        if (pacienteBusca.isPresent()){
            paciente.setId(id);
            Paciente save = iPacienteRepository.save(paciente);
            pacienteResponseDTO = Optional.ofNullable(mapper.convertValue(save, PacienteResponseDTO.class));
        }
        return pacienteResponseDTO;
    }

    @Override
    public String excluir(int id) {
        iPacienteRepository.deleteById(id);
        return "usuario removido";
    }
}
