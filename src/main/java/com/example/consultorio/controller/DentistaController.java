package com.example.consultorio.controller;

import com.example.consultorio.dto.request.DentistaRequestDTO;
import com.example.consultorio.dto.response.DentistaResponseDTO;
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
    public ResponseEntity<Optional<DentistaResponseDTO>> salvar(@RequestBody DentistaRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistaService.salvar(requestDTO));
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
    public  ResponseEntity<List<DentistaResponseDTO>> buscarTodos(){
        List<DentistaResponseDTO> dentistaResponseDTOList = dentistaService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(dentistaResponseDTOList);
    }

    @PutMapping("/{matriculaCadastro}")
    public ResponseEntity<Optional<DentistaResponseDTO>> atualizar(@PathVariable int matriculaCadastro, @RequestBody DentistaRequestDTO requestDTO){
       Optional<DentistaResponseDTO> dentistaBusca = dentistaService.atualizar(matriculaCadastro,requestDTO);
       if (dentistaBusca.isPresent()){
           dentistaService.atualizar(matriculaCadastro, requestDTO);
           return ResponseEntity.status(HttpStatus.OK).build();
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

}
