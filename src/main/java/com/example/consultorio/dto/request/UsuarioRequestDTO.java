package com.example.consultorio.dto.request;

import com.example.consultorio.model.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private Integer perfilId;
}
