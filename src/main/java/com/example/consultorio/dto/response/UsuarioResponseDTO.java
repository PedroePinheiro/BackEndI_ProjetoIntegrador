package com.example.consultorio.dto.response;

import com.example.consultorio.security.UsuarioRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioResponseDTO {
    private Long id;
    private String email;
    private UsuarioRole usuarioRole;
}
