package com.example.consultorio.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class ConsultaResponseDTO {
    private int id;
    private String horarioConsulta;
    private boolean cancelada;
    @JsonIgnoreProperties("consultas")
    private DentistaResponseDTO dentista;
    @JsonIgnoreProperties("consultas")
    private PacienteResponseDTO paciente;
}
