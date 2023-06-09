package com.example.consultorio.controller;

import com.example.consultorio.dto.request.ConsultaRequestDTO;
import com.example.consultorio.dto.response.ConsultaDentistaResponseDTO;
import com.example.consultorio.dto.response.ConsultaPacienteResponseDTO;
import com.example.consultorio.dto.response.ConsultaResponseDTO;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;
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
    public ResponseEntity<Optional<ConsultaResponseDTO>> salvar(@RequestBody ConsultaRequestDTO requestDTO) throws InvalidDataException, ResourceNotFoundException {
        Optional<ConsultaResponseDTO> consultaResponseDTO = consultaService.salvar(requestDTO);
        if (consultaResponseDTO.isPresent()){
           return ResponseEntity.status(HttpStatus.CREATED).body(consultaResponseDTO);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @RequestBody ConsultaRequestDTO requestDTO) throws InvalidDataException, ResourceNotFoundException {
        Optional<ConsultaResponseDTO> consultaResponseDTO = consultaService.atualizar(id,requestDTO);
        if (consultaResponseDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/cancelar/{id}")
    public ResponseEntity<Object> cancelar(@PathVariable int id){
        consultaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{id}")
    public List<ConsultaPacienteResponseDTO> buscarPorPaciente(@PathVariable int id){
        return consultaService.buscarPorPaciente(id);
    }

    @GetMapping("/dentista/{id}")
    public List<ConsultaDentistaResponseDTO> buscarPorDentista(@PathVariable int id){
        return consultaService.buscarPorDentista(id);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.buscarTodos());
    }


}
