package com.example.consultorio.service;

import com.example.consultorio.dto.request.UsuarioRequestDTO;
import com.example.consultorio.dto.response.UsuarioResponseDTO;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO) throws InvalidDataException;

    Optional<UsuarioResponseDTO> buscar(int id) throws ResourceNotFoundException;

    List<UsuarioResponseDTO> buscarTodos();

    UsuarioResponseDTO atualizar (int id, UsuarioRequestDTO t) throws InvalidDataException, ResourceNotFoundException;

    String excluir(int id) throws ResourceNotFoundException;

    UserDetails findByEmail(String email);




}
