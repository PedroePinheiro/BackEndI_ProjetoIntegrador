package com.example.consultorio.controller;


import com.example.consultorio.dto.request.PerfilRequestDTO;
import com.example.consultorio.model.Perfil;
import com.example.consultorio.service.impl.PerfilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/perfils")
@RestController
public class PerfilController {


    @Autowired
    private PerfilServiceImpl perfilService;

    @PostMapping
    public ResponseEntity<Perfil> salvar(@RequestBody PerfilRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilService.salvar(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Perfil>> buscar(@PathVariable int id){
        Optional<Perfil> perfil = perfilService.buscar(id);
        if (perfil.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(perfil);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> buscarTodos(){
        List<Perfil> perfilList = perfilService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(perfilList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizar (@PathVariable int id, @RequestBody PerfilRequestDTO requestDTO){
        Optional<Perfil> perfilBusca = perfilService.buscar(id);
        if (perfilBusca.isPresent()){
            perfilService.atualizar(id,requestDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable int id){
        Optional<Perfil> perfil = perfilService.buscar(id);
        if (perfil.isPresent()){
            perfilService.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
