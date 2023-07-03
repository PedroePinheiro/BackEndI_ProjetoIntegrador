package com.example.consultorio.service.impl;

import com.example.consultorio.dto.request.DentistaRequestDTO;
import com.example.consultorio.dto.response.DentistaResponseDTO;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;
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
    public Optional<DentistaResponseDTO> salvar(DentistaRequestDTO requestDTO) throws InvalidDataException {
        try {
            Dentista dentista = mapper.convertValue(requestDTO,Dentista.class);
            Dentista save = iDentistaRepository.save(dentista);
            DentistaResponseDTO responseDTO = mapper.convertValue(save,DentistaResponseDTO.class);
            responseDTO.setConsultas(new ArrayList<>());
            return Optional.ofNullable(responseDTO);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Não foram informados todos os dados sobre o dentista");
        }

    }

    @Override
    public Optional<DentistaResponseDTO> buscar(int matriculaCadastro) throws ResourceNotFoundException {
        Optional<Dentista> dentistas = iDentistaRepository.findById(matriculaCadastro);
        if (dentistas.isPresent()){
            return Optional.ofNullable(mapper.convertValue(dentistas, DentistaResponseDTO.class));
        }
        throw new ResourceNotFoundException("Recurso não encontrado para o id: " + matriculaCadastro);

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
    public Optional<DentistaResponseDTO> atualizar(int matriculaCadastro, DentistaRequestDTO requestDTO) throws InvalidDataException, ResourceNotFoundException {
        Optional<Dentista> dentista1 = iDentistaRepository.findById(matriculaCadastro);
        if (dentista1.isPresent()){
            try {
                Dentista dentista2 = dentista1.get();
                dentista2.setMatriculaCadastro(matriculaCadastro);
                dentista2.setNome(requestDTO.getNome());
                dentista2.setSobrenome(requestDTO.getSobrenome());
                Dentista dentistaSalvo = iDentistaRepository.save(dentista2);
                return Optional.ofNullable(mapper.convertValue(dentistaSalvo, DentistaResponseDTO.class));
            } catch (IllegalArgumentException e) {
                throw new InvalidDataException("Não foram informados todas as dados sobre o dentista");
            }
        }else throw new ResourceNotFoundException("Recurso não encontrado para o id: " + matriculaCadastro);
    }
}
