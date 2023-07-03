package com.example.consultorio.service.impl;

import com.example.consultorio.dto.request.ConsultaRequestDTO;
import com.example.consultorio.dto.response.*;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;
import com.example.consultorio.model.Consulta;
import com.example.consultorio.model.Dentista;
import com.example.consultorio.model.Paciente;
import com.example.consultorio.repository.IConsultaRepository;
import com.example.consultorio.service.IConsultaService;
import com.example.consultorio.service.IDentistaService;
import com.example.consultorio.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements IConsultaService{

    @Autowired
    private IConsultaRepository iConsultaRepository;
    @Autowired
    private IDentistaService dentistaService;
    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Optional<ConsultaResponseDTO> salvar(ConsultaRequestDTO requestDTO) throws InvalidDataException, ResourceNotFoundException {
        Optional<PacienteResponseDTO> pacienteDTO = pacienteService.buscar(requestDTO.getPacienteId());
        Optional<DentistaResponseDTO> dentistaDTO = dentistaService.buscar(requestDTO.getMatriculaDentista());
        if (pacienteDTO.isPresent() && dentistaDTO.isPresent()){
            try {
                mapper.registerModule(new JavaTimeModule());
                Paciente paciente = mapper.convertValue(pacienteDTO.get(),Paciente.class);
                Dentista dentista = mapper.convertValue(dentistaDTO.get(),Dentista.class);
                Consulta consulta = new Consulta(0,requestDTO.getHorarioConsulta(),false,dentista,paciente);
                Consulta save = iConsultaRepository.save(consulta);
                return Optional.ofNullable(mapper.convertValue(save, ConsultaResponseDTO.class));
            } catch (IllegalArgumentException e) {
                throw new InvalidDataException("Não foram informados todos os dados da consulta");
            }
        } else throw new ResourceNotFoundException("Recurso não encontrado para os id's informados");
    }

    @Override
    public Optional<ConsultaResponseDTO> atualizar(int id, ConsultaRequestDTO requestDTO) throws ResourceNotFoundException, InvalidDataException {
        Optional<PacienteResponseDTO> pacienteDTO = pacienteService.buscar(requestDTO.getPacienteId());
        Optional<DentistaResponseDTO> dentistaDTO = dentistaService.buscar(requestDTO.getMatriculaDentista());
        if (pacienteDTO.isPresent() && dentistaDTO.isPresent()){
            try {
                mapper.registerModule(new JavaTimeModule());
                Paciente paciente = mapper.convertValue(pacienteDTO,Paciente.class);
                Dentista dentista = mapper.convertValue(dentistaDTO,Dentista.class);
                Consulta consulta = new Consulta(id,requestDTO.getHorarioConsulta(),false,dentista,paciente);
                Consulta save = iConsultaRepository.save(consulta);
                return Optional.ofNullable(mapper.convertValue(save, ConsultaResponseDTO.class));
            } catch (IllegalArgumentException e) {
                throw new InvalidDataException("Não foram informados todas as dados da consulta");
            }
        }else throw new ResourceNotFoundException("Recurso não encontrado para o id: " + (pacienteDTO.isPresent() ? requestDTO.getMatriculaDentista() : id));
    }

    @Override
    public void cancelar(int id) {
        Optional<Consulta> consulta = iConsultaRepository.findById(id);
        consulta.ifPresent(consultaBanco -> {
            consultaBanco.setCancelada(true);
            iConsultaRepository.save(consultaBanco);
        });
    }

    @Override
    public List<ConsultaResponseDTO> buscarTodos() {
        List<Consulta> all = iConsultaRepository.findAll();
        List<ConsultaResponseDTO> consultaResponseDTOList = new ArrayList<>();
        all.forEach(consulta -> {
            consultaResponseDTOList.add(mapper.convertValue(consulta,ConsultaResponseDTO.class));
        });
        return consultaResponseDTOList;
    }

    @Override
    public List<ConsultaPacienteResponseDTO> buscarPorPaciente(int id) {
        List<Consulta> byPacienteId = iConsultaRepository.findByPacienteId(id);
        List<ConsultaPacienteResponseDTO> responseDTOList = new ArrayList();
        byPacienteId.forEach(consulta -> {
            responseDTOList.add(mapper.convertValue(consulta,ConsultaPacienteResponseDTO.class));
        });
        return responseDTOList;
    }

    @Override
    public List<ConsultaDentistaResponseDTO> buscarPorDentista(int id) {
        List<Consulta> byDentistaMatriculaCadastro = iConsultaRepository.findByDentistaMatriculaCadastro(id);
        List<ConsultaDentistaResponseDTO> dentistaResponseDTOList = new ArrayList();
        byDentistaMatriculaCadastro.forEach(consulta -> {
            dentistaResponseDTOList.add(mapper.convertValue(consulta,ConsultaDentistaResponseDTO.class));
        });
        return dentistaResponseDTOList;
    }
}
