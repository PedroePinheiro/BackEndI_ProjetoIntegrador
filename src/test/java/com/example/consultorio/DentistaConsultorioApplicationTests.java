package com.example.consultorio;

import com.example.consultorio.model.Dentista;
import com.example.consultorio.model.Paciente;
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
class DentistaConsultorioApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	@Test
	@DisplayName("Deve CADASTRAR um DENTISTA com sucesso")
	void salvarDentista() throws Exception{
		var dentista = new Dentista("Carlos","Silva");

		mockMvc.perform(MockMvcRequestBuilders.post("/dentistas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dentista)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	@DisplayName("Deve BUSCAR um DENTISTA por Matricula com sucesso")
	void buscar() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/dentistas/{matriculaCadastro}", 1))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Deve BUSCAR TODOS DENTISTAS com sucesso")
	void buscarTodos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/dentistas"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Deve ATUALIZAR um DENTISTA com sucesso")
	void atualizarDentista() throws Exception{
		Dentista dentista = new Dentista("Carlos Novo","Silva");
		dentista.setMatriculaCadastro(2);

		String pacienteJson = mapper.writeValueAsString(dentista);

		mockMvc.perform(MockMvcRequestBuilders.put("/dentistas/{id}",2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(pacienteJson))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Deve retornar 404")
	void buscar_notFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/dentistas/{matriculaCadastro}", 123))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}




}
