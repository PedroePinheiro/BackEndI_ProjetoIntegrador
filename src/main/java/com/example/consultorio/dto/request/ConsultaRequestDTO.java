package com.example.consultorio.dto.request;

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

public class ConsultaRequestDTO {
    private int pacienteId;
    private int matriculaDentista;
    private LocalDateTime horarioConsulta;
}
