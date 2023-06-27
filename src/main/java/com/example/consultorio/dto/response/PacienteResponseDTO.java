package com.example.consultorio.dto.response;

import com.example.consultorio.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class PacienteResponseDTO {
    private int id;
    private String nome;
    private String sobrenome;
    private String rg;
    private LocalDate dataAlta;
    private Endereco endereco;
    private List<ConsultaPacienteResponseDTO> consultas;

}
