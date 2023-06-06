package com.example.consultorio.controller;

import com.example.consultorio.model.Paciente;
import com.example.consultorio.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/pacientes")
@RestController

public class PacienteController {
    @Autowired
    private PacienteServiceImpl pacienteService;

    @PostMapping
    public Paciente salvar(@RequestBody Paciente paciente){
        return pacienteService.salvar(paciente);
    }

    @GetMapping("/{id}")
    public Optional<Paciente> buscar(@PathVariable int id){
        return pacienteService.buscar(id);
    }

    @GetMapping
    public List<Paciente> buscarTodos(){
        return pacienteService.buscarTodos();
    }

    @PutMapping("/{id}")
    public Optional<Paciente> atualizar(@PathVariable int id, @RequestBody Paciente paciente){
        return pacienteService.atualizar(id,paciente);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable int id){
        pacienteService.excluir(id);
    }

}
