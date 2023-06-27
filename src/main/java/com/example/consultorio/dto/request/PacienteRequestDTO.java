package com.example.consultorio.dto.request;

import com.example.consultorio.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.time.LocalDate;
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
    private LocalDate dataAlta;
    private Endereco endereco;
}
