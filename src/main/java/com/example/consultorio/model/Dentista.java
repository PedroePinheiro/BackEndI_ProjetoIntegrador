package com.example.consultorio.model;

import com.example.consultorio.dto.response.DentistaConsultaResponseDTO;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)


@Entity
@Table(name="TB_DENTISTAS")
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula_dentista")
    private Integer matriculaCadastro; //ID


    private String nome;
    private String sobrenome;

    @OneToMany(mappedBy = "dentista")
    private List<DentistaConsultaResponseDTO> consultas;

}
