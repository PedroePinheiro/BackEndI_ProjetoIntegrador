package com.example.consultorio.service.impl;

import com.example.consultorio.dto.response.DentistaConsultaResponseDTO;
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
    public DentistaResponseDTO salvar(Dentista dentista) {
        Dentista save = iDentistaRepository.save(dentista);
        save.setConsultas(new ArrayList());
        System.out.println(save);
        return mapper.convertValue(save,DentistaResponseDTO.class);
    }

    @Override
    public Optional<DentistaResponseDTO> buscar(int matriculaCadastro) {
        Optional<Dentista> byId = iDentistaRepository.findById(matriculaCadastro);
//        List<DentistaConsultaResponseDTO> consultaResponseDTOList = new ArrayList<>();
        DentistaResponseDTO dentistaResponse = mapper.convertValue(byId, DentistaResponseDTO.class);
//        byId.get().getConsultas().forEach(consulta -> {
//            consultaResponseDTOList.add(mapper.convertValue(consulta,DentistaConsultaResponseDTO.class));
//        });
//        dentistaResponse.setConsultas(consultaResponseDTOList);
        return Optional.ofNullable(dentistaResponse);
    }



    @Override
    public List<DentistaResponseDTO> buscarTodos() {
        List<Dentista> all = iDentistaRepository.findAll();
        List<DentistaResponseDTO> dentistaResponseDTOList = new ArrayList<>();
        all.forEach(dentista -> {
//            List<DentistaConsultaResponseDTO> consultaResponseDTO = new ArrayList<>();
//            dentista.getConsultas().forEach(consulta -> {
//                consultaResponseDTO.add(mapper.convertValue(consulta,DentistaConsultaResponseDTO.class));
//            });
            DentistaResponseDTO dentistaResponse = mapper.convertValue(dentista, DentistaResponseDTO.class);
//            dentistaResponse.setConsultas(consultaResponseDTO);
            dentistaResponseDTOList.add(dentistaResponse);
        });
        return dentistaResponseDTOList;
    }

    @Override
    public Optional<DentistaResponseDTO> atualizar(int matriculaCadastro, Dentista dentista) {
//        Optional<DentistaResponseDTO> dentistaBusca = iDentistaRepository.findById(matriculaCadastro);
//        Optional<DentistaResponseDTO> dentistaResponseDTO = null;
//        if (dentistaBusca.isPresent()){
//            dentista.setMatriculaCadastro(matriculaCadastro);
//            Dentista save = iDentistaRepository.save(dentista);
//            List<DentistaConsultaResponseDTO> consultaResponseDTO = new ArrayList<>();
//            save.getConsultas().forEach(consulta -> {
//                consultaResponseDTO.add(mapper.convertValue(consulta,DentistaConsultaResponseDTO.class));
//            });
//            DentistaResponseDTO dentistaResponse = mapper.convertValue(save, DentistaResponseDTO.class);
////            dentistaResponse.setConsultas(consultaResponseDTO);
//            dentistaResponseDTO = Optional.ofNullable(dentistaResponse);
//        }
//        return dentistaResponseDTO;
        return null;
    }
}
