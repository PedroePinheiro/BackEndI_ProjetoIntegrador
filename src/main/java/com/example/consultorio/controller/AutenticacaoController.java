package com.example.consultorio.controller;

import com.example.consultorio.dto.request.UsuarioLoginDTO;
import com.example.consultorio.dto.request.UsuarioRequestDTO;
import com.example.consultorio.dto.response.UsuarioResponseDTO;
import com.example.consultorio.exception.UnAuthorizedException;
import com.example.consultorio.model.Usuario;
import com.example.consultorio.security.TokenDto;
import com.example.consultorio.security.TokenService;
import com.example.consultorio.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity logar(@RequestBody UsuarioLoginDTO usuarioLoginDTO) throws UnAuthorizedException {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getLogin(),usuarioLoginDTO.getSenha());
            Authentication authenticate = manager.authenticate(authenticationToken);
            String tokenJwt = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
            return ResponseEntity.ok(new TokenDto(tokenJwt));

        } catch (AuthenticationException e) {
            throw new UnAuthorizedException("Informações erradas");
        }

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuarioRequestDTO));
    }



}
