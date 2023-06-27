package com.example.consultorio.service.impl;

import com.example.consultorio.dto.request.UsuarioRequestDTO;
import com.example.consultorio.model.Perfil;
import com.example.consultorio.model.Usuario;
import com.example.consultorio.repository.IPerfilRepository;
import com.example.consultorio.repository.IUsuarioRepository;
import com.example.consultorio.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IPerfilRepository iPerfilRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Usuario salvar(UsuarioRequestDTO requestDTO) {
        Optional<Perfil> perfilBusca = iPerfilRepository.findById(requestDTO.getPerfilId());
        if (perfilBusca.isPresent()){
            Usuario usuario = mapper.convertValue(requestDTO, Usuario.class);
            usuario.setPerfil(perfilBusca.get());
            return iUsuarioRepository.save(usuario);
        }

        return null;
    }

    @Override
    public Optional<Usuario> buscar(int id) {
        return iUsuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return iUsuarioRepository.findAll();
    }

    @Override
    public Usuario atualizar(int id, UsuarioRequestDTO requestDTO) {
        Optional<Usuario> usuarioBusca = iUsuarioRepository.findById(id);
        Optional<Perfil> perfilBusca = iPerfilRepository.findById(requestDTO.getPerfilId());
        if (usuarioBusca.isPresent() && perfilBusca.isPresent()){
            Usuario usuario = mapper.convertValue(requestDTO, Usuario.class);
            usuario.setPerfil(perfilBusca.get());
            usuario.setId(id);
            return iUsuarioRepository.save(usuario);
        }
        return null;
    }

    @Override
    public String excluir(int id) {
        iUsuarioRepository.deleteById(id);
        return "usuario removido";
    }
}
