package com.example.consultorio.controller;

import com.example.consultorio.model.Dentista;
import com.example.consultorio.service.impl.DentistaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dentistas")
@RestController

public class DentistaController {
    @Autowired
    private DentistaServiceImpl dentistaService;

    @GetMapping
    public Dentista salvar(){
        return null;
    }

    @GetMapping ("/{matriculaCadastro}")
    public Dentista buscar(@PathVariable int matriculaCadastro){
        return dentistaService.buscar(matriculaCadastro);
    }

    @GetMapping ("/buscar")
    public List<Dentista> buscarTodos(){
        return dentistaService.buscarTodos();
    }

    @PutMapping("/{matriculaCadastro}")
    public Dentista atualizar(){
        return null;
    }

}
