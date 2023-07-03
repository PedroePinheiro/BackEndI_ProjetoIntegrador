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

public class ConsultaRequestDTO {
    private int pacienteId;
    private int matriculaDentista;
    private String horarioConsulta;
}
