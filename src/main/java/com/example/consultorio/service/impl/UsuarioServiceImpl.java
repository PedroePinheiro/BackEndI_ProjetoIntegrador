package com.example.consultorio.service.impl;

import com.example.consultorio.dto.request.UsuarioRequestDTO;
import com.example.consultorio.dto.response.UsuarioResponseDTO;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;
import com.example.consultorio.model.Usuario;
import com.example.consultorio.repository.IUsuarioRepository;
import com.example.consultorio.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO) throws InvalidDataException {
        UserDetails usuario = iUsuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
        if (usuario == null) {
            try {
                Usuario usuarioModel = mapper.convertValue(usuarioRequestDTO, Usuario.class);
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String senhaCriptografada = encoder.encode(usuarioModel.getSenha());
                usuarioModel.setSenha(senhaCriptografada);
                Usuario usuarioSalvo = iUsuarioRepository.save(usuarioModel);
                UsuarioResponseDTO usuarioResponseDto = mapper.convertValue(usuarioSalvo, UsuarioResponseDTO.class);
                return usuarioResponseDto;
            } catch (IllegalArgumentException e) {
                throw new InvalidDataException("Não foram informados todos os dados do usuario");
            }
        }
        throw new InvalidDataException("Usuario já está cadastrado");
    }

    @Override
    public Optional<UsuarioResponseDTO> buscar(int id) throws ResourceNotFoundException {
        Optional<Usuario> byId = iUsuarioRepository.findById(id);
        if (byId.isPresent()) {
            return Optional.ofNullable(mapper.convertValue(byId, UsuarioResponseDTO.class));
        }
        throw new ResourceNotFoundException("Recurso não encontrado para o id: " + id);
    }

    @Override
    public List<UsuarioResponseDTO> buscarTodos() {
        List<Usuario> all = iUsuarioRepository.findAll();
        List<UsuarioResponseDTO> responseDTOList = new ArrayList<>();
        all.forEach(usuario -> {
            responseDTOList.add(mapper.convertValue(usuario, UsuarioResponseDTO.class));
        });
        return responseDTOList;
    }

    @Override
    public UsuarioResponseDTO atualizar(int id, UsuarioRequestDTO requestDTO) throws InvalidDataException, ResourceNotFoundException {
        Optional<Usuario> usuario = iUsuarioRepository.findById(id);
        if (usuario.isPresent()) {
            try {
                usuario.get().setNome(requestDTO.getNome());
                usuario.get().setEmail(requestDTO.getEmail());
                usuario.get().setSenha(requestDTO.getSenha());
                usuario.get().setUsuarioRole(requestDTO.getUsuarioRole());
                Usuario usuarioSalvo = iUsuarioRepository.save(usuario.get());
                return mapper.convertValue(usuarioSalvo, UsuarioResponseDTO.class);
            } catch (IllegalArgumentException e) {
                throw new InvalidDataException("Não foram informados todos os dados sobre o usuario");
            }
        } else throw new ResourceNotFoundException("Recurso não encontrado para o id: " + id);
    }

    @Override
    public String excluir(int id) throws ResourceNotFoundException {
        Optional<Usuario> usuario = iUsuarioRepository.findById(id);
        if (usuario.isPresent()){
            iUsuarioRepository.deleteById(id);
            return "usuario removido";
        }
        throw new ResourceNotFoundException("Recurso não encontrado para o id: " + id);
    }

    @Override
    public UserDetails findByEmail(String email) {
        return iUsuarioRepository.findByEmail(email);
    }


}
