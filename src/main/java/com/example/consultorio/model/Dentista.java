package com.example.consultorio.model;

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

@Entity
@Table(name="TB_DENTISTAS")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula_dentista")
    private Integer matriculaCadastro; //ID

    private String nome;
    private String sobrenome;

    @OneToMany(mappedBy = "dentista",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas;

}
