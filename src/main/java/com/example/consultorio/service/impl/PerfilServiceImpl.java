package com.example.consultorio.service.impl;

import com.example.consultorio.dto.request.PerfilRequestDTO;
import com.example.consultorio.model.Perfil;
import com.example.consultorio.repository.IPerfilRepository;
import com.example.consultorio.service.IPerfilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilServiceImpl implements IPerfilService {

    @Autowired
    private IPerfilRepository iPerfilRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Perfil salvar(PerfilRequestDTO requestDTO) {
        Perfil perfil = mapper.convertValue(requestDTO, Perfil.class);
        return iPerfilRepository.save(perfil);
    }

    @Override
    public Optional<Perfil> buscar(int id) {
        return iPerfilRepository.findById(id);
    }

    @Override
    public List<Perfil> buscarTodos() {
        return iPerfilRepository.findAll();
    }

    @Override
    public Perfil atualizar(int id, PerfilRequestDTO requestDTO) {
        Optional<Perfil> perfilBusca = iPerfilRepository.findById(id);
        if (perfilBusca.isPresent()){
            Perfil perfil = mapper.convertValue(requestDTO,Perfil.class);
            perfil.setId(id);
            return iPerfilRepository.save(perfil);
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        iPerfilRepository.deleteById(id);
        return "usuario removido";
    }
}
