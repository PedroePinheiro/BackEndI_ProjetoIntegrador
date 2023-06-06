package com.example.consultorio.controller;

import com.example.consultorio.model.Consulta;
import com.example.consultorio.service.impl.ConsultaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/consultas")
@RestController

public class ConsultaController {

    @Autowired
    private ConsultaServiceImpl consultaService;

    @PostMapping
    public Consulta salvar(@RequestBody Consulta consulta){
        return consultaService.salvar(consulta);
    }

    @PutMapping("/{id}")
    public Optional<Consulta> atualizar(@PathVariable int id, @RequestBody Consulta consulta){
        return consultaService.atualizar(id,consulta);
    }

    @PostMapping("/cancelar/{id}")
    public void cancelar(@PathVariable int id){
        consultaService.cancelar(id);
    }

}
