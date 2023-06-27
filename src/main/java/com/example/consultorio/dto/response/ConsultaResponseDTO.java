package com.example.consultorio.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class ConsultaResponseDTO {
    private int id;
    private LocalDateTime horarioConsulta;
    private boolean cancelada;
    @JsonIgnoreProperties("consultas")
    private DentistaResponseDTO dentista;
    @JsonIgnoreProperties("consultas")
    private PacienteResponseDTO paciente;
}
