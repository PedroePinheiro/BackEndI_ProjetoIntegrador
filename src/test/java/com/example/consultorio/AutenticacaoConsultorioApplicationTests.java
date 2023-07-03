package com.example.consultorio;

import com.example.consultorio.dto.request.UsuarioLoginDTO;
import com.example.consultorio.dto.request.UsuarioRequestDTO;
import com.example.consultorio.security.UsuarioRole;
import com.example.consultorio.service.impl.UsuarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AutenticacaoConsultorioApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UsuarioServiceImpl usuarioService;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	@DisplayName("Deve fazer um CADASTRO com sucesso")
	void cadastrar() throws Exception{
		var usuarioCadastrado = new UsuarioRequestDTO("Paulo2","userl2@email.com","12345678", UsuarioRole.ROLE_ADMIN);

		mockMvc.perform(MockMvcRequestBuilders.post("/autenticacao/cadastrar")
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(usuarioCadastrado)))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	@DisplayName("Deve logar um usuario com sucesso")
	void logar() throws Exception{
		var usuarioCadastrado = new UsuarioRequestDTO("Paulo","userl@email.com","12345678", UsuarioRole.ROLE_ADMIN);
		usuarioService.salvar(usuarioCadastrado);

		UsuarioLoginDTO loginDTO = new UsuarioLoginDTO(usuarioCadastrado.getEmail(),usuarioCadastrado.getSenha());

		String loginRequestJson = mapper.writeValueAsString(loginDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/autenticacao/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(loginRequestJson))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Deve dar erro ao logar um usuario se o usuario não existir")
	void logar_Forbidden() throws Exception{
		var usuarioCadastrado = new UsuarioRequestDTO("Paulo","userl23@email.com","12345678", UsuarioRole.ROLE_ADMIN);

		UsuarioLoginDTO loginDTO = new UsuarioLoginDTO(usuarioCadastrado.getEmail(),usuarioCadastrado.getSenha());

		String loginRequestJson = mapper.writeValueAsString(loginDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/autenticacao/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginRequestJson))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	}

