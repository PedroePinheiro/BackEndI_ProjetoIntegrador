package com.example.consultorio.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class DentistaRequestDTO {
    private int matriculaCadastro;
    private String nome;
    private String sobrenome;
    private List<ConsultaRequestDTO> consultas;
}
