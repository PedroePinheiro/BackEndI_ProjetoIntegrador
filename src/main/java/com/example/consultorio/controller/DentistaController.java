package com.example.consultorio.controller;

import com.example.consultorio.model.Dentista;
import com.example.consultorio.service.impl.DentistaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/dentistas")
@RestController

public class DentistaController {
    @Autowired
    private DentistaServiceImpl dentistaService;

    @PostMapping
    public Dentista salvar(@RequestBody Dentista dentista){
        return dentistaService.salvar(dentista);
    }

    @GetMapping ("/{matriculaCadastro}")
    public Optional<Dentista> buscar(@PathVariable int matriculaCadastro){
        return dentistaService.buscar(matriculaCadastro);
    }

    @GetMapping
    public List<Dentista> buscarTodos(){
        return dentistaService.buscarTodos();
    }

    @PutMapping("/{matriculaCadastro}")
    public Optional<Dentista> atualizar(@PathVariable int matriculaCadastro, @RequestBody Dentista dentista){
        return dentistaService.atualizar(matriculaCadastro, dentista);
    }

}
