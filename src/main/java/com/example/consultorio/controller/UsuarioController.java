package com.example.consultorio.controller;


import com.example.consultorio.dto.request.UsuarioRequestDTO;
import com.example.consultorio.model.Usuario;
import com.example.consultorio.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody UsuarioRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscar(@PathVariable int id){
        Optional<Usuario> usuario = usuarioService.buscar(id);
        if (usuario.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos(){
        List<Usuario> usuarioList = usuarioService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(usuarioList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar (@PathVariable int id, @RequestBody UsuarioRequestDTO requestDTO){
        Optional<Usuario> usuarioBusca = usuarioService.buscar(id);
        if (usuarioBusca.isPresent()){
            usuarioService.atualizar(id,requestDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable int id){
        Optional<Usuario> usuario = usuarioService.buscar(id);
        if (usuario.isPresent()){
            usuarioService.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
