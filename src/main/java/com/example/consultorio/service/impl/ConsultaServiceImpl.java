package com.example.consultorio.service.impl;

import com.example.consultorio.dto.request.ConsultaRequestDTO;
import com.example.consultorio.dto.response.*;
import com.example.consultorio.model.Consulta;
import com.example.consultorio.model.Dentista;
import com.example.consultorio.model.Paciente;
import com.example.consultorio.repository.IConsultaRepository;
import com.example.consultorio.service.IConsultaService;
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
    private DentistaServiceImpl dentistaService;
    @Autowired
    private PacienteServiceImpl pacienteService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Optional<ConsultaResponseDTO> salvar(ConsultaRequestDTO requestDTO) {
        Optional<PacienteResponseDTO> pacienteDTO = pacienteService.buscar(requestDTO.getPacienteId());
        Optional<DentistaResponseDTO> dentistaDTO = dentistaService.buscar(requestDTO.getMatriculaDentista());
        ConsultaResponseDTO consultaResponseDTO = null;
        if (pacienteDTO.isPresent() && dentistaDTO.isPresent()){
            mapper.registerModule(new JavaTimeModule());
            Paciente paciente = mapper.convertValue(pacienteDTO,Paciente.class);
            Dentista dentista = mapper.convertValue(dentistaDTO,Dentista.class);
            Consulta consulta = new Consulta(0,requestDTO.getHorarioConsulta(),false,dentista,paciente);
            Consulta save = iConsultaRepository.save(consulta);
            consultaResponseDTO = mapper.convertValue(save,ConsultaResponseDTO.class);
        }
        return Optional.ofNullable(consultaResponseDTO);
    }

    @Override
    public Optional<ConsultaResponseDTO> atualizar(int id, ConsultaRequestDTO requestDTO) {
        Optional<PacienteResponseDTO> pacienteDTO = pacienteService.buscar(requestDTO.getPacienteId());
        Optional<DentistaResponseDTO> dentistaDTO = dentistaService.buscar(requestDTO.getMatriculaDentista());
        ConsultaResponseDTO consultaResponseDTO = null;
        if (pacienteDTO.isPresent() && dentistaDTO.isPresent()){
            mapper.registerModule(new JavaTimeModule());
            Paciente paciente = mapper.convertValue(pacienteDTO,Paciente.class);
            Dentista dentista = mapper.convertValue(dentistaDTO,Dentista.class);
            Consulta consulta = new Consulta(id,requestDTO.getHorarioConsulta(),false,dentista,paciente);
            Consulta save = iConsultaRepository.save(consulta);
            consultaResponseDTO = mapper.convertValue(save,ConsultaResponseDTO.class);
        }
        return Optional.ofNullable(consultaResponseDTO);
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
    public Optional<ConsultaPacienteResponseDTO> buscarPorPaciente(int id) {
        Optional<Consulta> byPacienteId = iConsultaRepository.findByPacienteId(id);
        return Optional.ofNullable(mapper.convertValue(byPacienteId, ConsultaPacienteResponseDTO.class));

    }

    @Override
    public Optional<ConsultaDentistaResponseDTO> buscarPorDentista(int id) {
        Optional<Consulta> byDentistaMatriculaCadastro = iConsultaRepository.findByDentistaMatriculaCadastro(id);
        return Optional.ofNullable(mapper.convertValue(byDentistaMatriculaCadastro,ConsultaDentistaResponseDTO.class));
    }
}
