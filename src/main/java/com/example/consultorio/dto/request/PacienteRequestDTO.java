package com.example.consultorio.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class PacienteRequestDTO {
    private String nome;
    private String sobrenome;
    private String rg;
    private String dataAlta;
    private EnderecoRequestDTO endereco;
}
