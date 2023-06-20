package com.example.consultorio.controller;

import com.example.consultorio.dto.response.PacienteResponseDTO;
import com.example.consultorio.model.Paciente;
import com.example.consultorio.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/pacientes")
@RestController

public class PacienteController {
    @Autowired
    private PacienteServiceImpl pacienteService;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> salvar(@RequestBody Paciente paciente){
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.salvar(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PacienteResponseDTO>> buscar(@PathVariable int id){
        Optional<PacienteResponseDTO> paciente = pacienteService.buscar(id);
        if (paciente.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(paciente);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public List<PacienteResponseDTO> buscarTodos(){
        return pacienteService.buscarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<PacienteResponseDTO>> atualizar(@PathVariable int id, @RequestBody Paciente paciente){
        Optional<PacienteResponseDTO> pacienteBusca = pacienteService.buscar(id);
        if (pacienteBusca.isPresent() ){
            pacienteService.atualizar(id,paciente);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable int id){
        Optional<PacienteResponseDTO> paciente = pacienteService.buscar(id);
        if (paciente.isPresent()){
            pacienteService.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
