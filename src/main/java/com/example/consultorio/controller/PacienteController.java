package com.example.consultorio.controller;

import com.example.consultorio.dto.request.PacienteRequestDTO;
import com.example.consultorio.dto.response.PacienteResponseDTO;
import com.example.consultorio.exception.InvalidDataException;
import com.example.consultorio.exception.ResourceNotFoundException;
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
    public ResponseEntity<Optional<PacienteResponseDTO>> salvar(@RequestBody PacienteRequestDTO requestDTO) throws InvalidDataException {
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.salvar(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PacienteResponseDTO>> buscar (@PathVariable int id) throws ResourceNotFoundException {
        Optional<PacienteResponseDTO> paciente = pacienteService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(paciente);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> buscarTodos(){
        List<PacienteResponseDTO> pacienteResponseDTOList = pacienteService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable int id, @RequestBody PacienteRequestDTO requestDTO) throws ResourceNotFoundException, InvalidDataException {
        Optional<PacienteResponseDTO> pacienteBusca = pacienteService.buscar(id);
        if (pacienteBusca.isPresent() ){
            pacienteService.atualizar(id,requestDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Atualizado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable int id) throws ResourceNotFoundException {
        pacienteService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
