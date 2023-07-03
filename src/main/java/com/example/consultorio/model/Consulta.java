package com.example.consultorio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "TB_CONSULTAS")

public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  horarioConsulta;
    private Boolean cancelada;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dentista_id")
    @JsonIgnoreProperties("consultas")
    private Dentista dentista;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties("consultas")
    private Paciente paciente;

}
