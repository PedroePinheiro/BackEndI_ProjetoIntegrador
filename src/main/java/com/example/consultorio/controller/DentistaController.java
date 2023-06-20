package com.example.consultorio.controller;

import com.example.consultorio.dto.response.DentistaResponseDTO;
import com.example.consultorio.model.Dentista;
import com.example.consultorio.service.impl.DentistaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/dentistas")
@RestController

public class DentistaController {
    @Autowired
    private DentistaServiceImpl dentistaService;

    @PostMapping
    public ResponseEntity<DentistaResponseDTO> salvar(@RequestBody Dentista dentista){
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistaService.salvar(dentista));
    }

    @GetMapping ("/{matriculaCadastro}")
    public ResponseEntity<Optional<DentistaResponseDTO>> buscar(@PathVariable int matriculaCadastro){
        Optional<DentistaResponseDTO> dentista = dentistaService.buscar(matriculaCadastro);
        if (dentista.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(dentista);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public List<DentistaResponseDTO> buscarTodos(){
        return dentistaService.buscarTodos();
    }

    @PutMapping("/{matriculaCadastro}")
    public ResponseEntity<Optional<DentistaResponseDTO>> atualizar(@PathVariable int matriculaCadastro, @RequestBody Dentista dentista){
       Optional<DentistaResponseDTO> dentistaBusca = dentistaService.buscar(matriculaCadastro);
       if (dentistaBusca.isPresent()){
           dentistaService.atualizar(matriculaCadastro, dentista);
           return ResponseEntity.status(HttpStatus.OK).build();
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

}
