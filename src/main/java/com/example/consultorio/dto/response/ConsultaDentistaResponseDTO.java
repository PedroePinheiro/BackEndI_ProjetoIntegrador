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

public class ConsultaDentistaResponseDTO {
    private Integer matriculaCadastro; //ID
    private String nome;
    private String sobrenome;

}
