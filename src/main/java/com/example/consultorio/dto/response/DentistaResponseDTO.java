package com.example.consultorio.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class DentistaResponseDTO {
    private Integer matriculaCadastro; //ID
    private String nome;
    private String sobrenome;

    @OneToMany(mappedBy = "dentista")
    private List<ConsultaResponseDTO> consultas;
}
