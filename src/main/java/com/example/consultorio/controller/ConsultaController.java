package com.example.consultorio.controller;

import com.example.consultorio.dto.request.ConsultaRequestDTO;
import com.example.consultorio.dto.response.ConsultaDentistaResponseDTO;
import com.example.consultorio.dto.response.ConsultaPacienteResponseDTO;
import com.example.consultorio.dto.response.ConsultaResponseDTO;
import com.example.consultorio.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/consultas")
@RestController

public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Optional<ConsultaResponseDTO>> salvar(@RequestBody ConsultaRequestDTO requestDTO){
        Optional<ConsultaResponseDTO> consultaResponseDTO = consultaService.salvar(requestDTO);
        if (consultaResponseDTO.isPresent()){
           return ResponseEntity.status(HttpStatus.CREATED).body(consultaResponseDTO);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public Optional<ConsultaResponseDTO> atualizar(@PathVariable int id, @RequestBody ConsultaRequestDTO consulta){
        return consultaService.atualizar(id,consulta);
    }

    @PostMapping("/cancelar/{id}")
    public void cancelar(@PathVariable int id){
        consultaService.cancelar(id);
    }

    @GetMapping("/paciente/{id}")
    public Optional<ConsultaPacienteResponseDTO> buscarPorPaciente(@PathVariable int id){
        return consultaService.buscarPorPaciente(id);
    }

    @GetMapping("/dentista/{id}")
    public Optional<ConsultaDentistaResponseDTO> buscarPorDentista(@PathVariable int id){
        return consultaService.buscarPorDentista(id);
    }

    @GetMapping
    public List<ConsultaResponseDTO> buscarTodos(){
        return consultaService.buscarTodos();
    }


}
