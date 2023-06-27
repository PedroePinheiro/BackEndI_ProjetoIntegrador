package com.example.consultorio.service.impl;

import com.example.consultorio.dto.request.DentistaRequestDTO;
import com.example.consultorio.dto.response.DentistaResponseDTO;
import com.example.consultorio.model.Dentista;
import com.example.consultorio.repository.IDentistaRepository;
import com.example.consultorio.service.IDentistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DentistaServiceImpl implements IDentistaService{

   @Autowired
   private IDentistaRepository iDentistaRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Optional<DentistaResponseDTO> salvar(DentistaRequestDTO requestDTO) {
        Dentista dentista = mapper.convertValue(requestDTO,Dentista.class);
        Dentista save = iDentistaRepository.save(dentista);
        DentistaResponseDTO responseDTO = mapper.convertValue(save,DentistaResponseDTO.class);
        responseDTO.setConsultas(new ArrayList<>());
        return Optional.ofNullable(responseDTO);
    }

    @Override
    public Optional<DentistaResponseDTO> buscar(int matriculaCadastro) {
        Optional<Dentista> dentistas = iDentistaRepository.findById(matriculaCadastro);
        return Optional.ofNullable(mapper.convertValue(dentistas, DentistaResponseDTO.class));
    }

    @Override
    public List<DentistaResponseDTO> buscarTodos() {
        List<Dentista> dentistas = iDentistaRepository.findAll();
        List<DentistaResponseDTO> dentistaResponseDTOList = new ArrayList<>();
        dentistas.forEach(dentista -> {
            dentistaResponseDTOList.add(mapper.convertValue(dentista,DentistaResponseDTO.class));
        });
        return dentistaResponseDTOList;
    }

    @Override
    public Optional<DentistaResponseDTO> atualizar(int matriculaCadastro, DentistaRequestDTO requestDTO) {
        Optional<Dentista> dentista1 = iDentistaRepository.findById(matriculaCadastro);
        Optional<DentistaResponseDTO> responseDTO = null;
        if (dentista1.isPresent()){
            Dentista dentista2 = dentista1.get();
            dentista2.setMatriculaCadastro(matriculaCadastro);
            dentista2.setNome(requestDTO.getNome());
            dentista2.setSobrenome(requestDTO.getSobrenome());
            Dentista dentistaSalvo = iDentistaRepository.save(dentista2);
            responseDTO = Optional.ofNullable(mapper.convertValue(dentistaSalvo, DentistaResponseDTO.class));
        }
        return responseDTO;
    }
}
