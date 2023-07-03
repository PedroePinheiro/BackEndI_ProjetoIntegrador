package com.example.consultorio.service.impl;

import com.example.consultorio.dto.request.PacienteRequestDTO;
import com.example.consultorio.dto.response.PacienteResponseDTO;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;
import com.example.consultorio.model.Endereco;
import com.example.consultorio.model.Paciente;
import com.example.consultorio.repository.IPacienteRepository;
import com.example.consultorio.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    private IPacienteRepository iPacienteRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Optional<PacienteResponseDTO> salvar(PacienteRequestDTO pacienteRequestDTO) throws InvalidDataException {
        try {
            Paciente paciente = mapper.convertValue(pacienteRequestDTO, Paciente.class);
            Paciente savedPaciente = iPacienteRepository.save(paciente);
            PacienteResponseDTO pacienteResponseDTO = mapper.convertValue(savedPaciente, PacienteResponseDTO.class);
            pacienteResponseDTO.setConsultas(new ArrayList<>());
            return Optional.ofNullable(pacienteResponseDTO);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Não foram informados todos os dados sobre o paciente");
        }
    }

    @Override
    public Optional<PacienteResponseDTO> buscar(int id) throws ResourceNotFoundException {
        Optional<Paciente> byId = iPacienteRepository.findById(id);
        if (byId.isPresent()) {
            return Optional.ofNullable(mapper.convertValue(byId, PacienteResponseDTO.class));
        }
        throw new ResourceNotFoundException("Recurso não encontrado para o id: " + id);
    }

    @Override
    public List<PacienteResponseDTO> buscarTodos() {
        List<Paciente> all = iPacienteRepository.findAll();
        List<PacienteResponseDTO> pacienteResponseDTOList = new ArrayList<>();
        all.forEach(paciente -> {
            pacienteResponseDTOList.add(mapper.convertValue(paciente, PacienteResponseDTO.class));
        });
        return pacienteResponseDTOList;
    }

    @Override
    public Optional<PacienteResponseDTO> atualizar(int id, PacienteRequestDTO pacienteRequestDTO) throws ResourceNotFoundException, InvalidDataException {
        Optional<Paciente> pacienteBusca = iPacienteRepository.findById(id);
        if (pacienteBusca.isPresent()) {
            try {
                Paciente paciente = pacienteBusca.get();
                paciente.setId(id);
                paciente.setNome(pacienteRequestDTO.getNome());
                paciente.setSobrenome(pacienteRequestDTO.getSobrenome());
                paciente.setRg(pacienteRequestDTO.getRg());
                paciente.setDataAlta(pacienteRequestDTO.getDataAlta());
                Endereco endereco = mapper.convertValue(pacienteRequestDTO.getEndereco(), Endereco.class);
                paciente.setEndereco(endereco);
                Paciente pacienteSalvo = iPacienteRepository.save(paciente);
                return Optional.ofNullable(mapper.convertValue(pacienteSalvo, PacienteResponseDTO.class));
            } catch (IllegalArgumentException e) {
                throw new InvalidDataException("Não foram informados todos os dados sobre o paciente");
            }
        } else throw new ResourceNotFoundException("Recurso não encontrado para o id: " + id);

    }

    @Override
    public String excluir(int id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBusca = iPacienteRepository.findById(id);
        if (pacienteBusca.isPresent()) {
            iPacienteRepository.deleteById(id);
            return "usuario removido";
        }
        throw new ResourceNotFoundException("Recurso não encontrado para o id: " + id);
    }
}

