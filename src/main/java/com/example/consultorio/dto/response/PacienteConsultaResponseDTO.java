package com.example.consultorio.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "TB_CONSULTAS")
public class PacienteConsultaResponseDTO {
    private Integer id;
    private LocalDateTime horarioConsulta;
    private Boolean cancelada;

    @ManyToOne
    @JoinColumn(name = "dentista_id")
    private ConsultaDentistaResponseDTO dentista;
}
