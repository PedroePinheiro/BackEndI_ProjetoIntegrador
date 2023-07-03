package com.example.consultorio;

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
class PacienteConsultorioApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	@Test
	@DisplayName("Deve CADASTRAR um PACIENTE com sucesso")
	void salvarPaciente() throws Exception{
		var paciente = new Paciente("Lusca","Santos","123456"," 2023,02,02");
		mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(paciente)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	@DisplayName("Deve BUSCAR um PACIENTE por ID com sucesso")
	void buscar() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 1))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Deve BUSCAR TODOS PACIENTES com sucesso")
	void buscarTodosPacientes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/pacientes"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Deve ATUALIZAR um PACIENTE com sucesso")
	void atualizarPaciente() throws Exception{
		Paciente paciente = new Paciente("Lusca Novo","Santos","123456"," 2023,02,02");
		paciente.setId(2);

		String pacienteJson = mapper.writeValueAsString(paciente);

		mockMvc.perform(MockMvcRequestBuilders.put("/pacientes/{id}",2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(pacienteJson))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


	@Test
	@DisplayName("Deve EXCLUIR um PACIENTE com sucesso")
	void excluirPaciente() throws Exception{
		Paciente paciente = new Paciente();
		paciente.setId(1);
		paciente.setNome("Lusca");
		mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}",1))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());

		mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}",1))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}


	@Test
	@DisplayName("Deve retornar 404")
	void buscar_notFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 169))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}


}
