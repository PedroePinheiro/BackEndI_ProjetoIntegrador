package com.example.consultorio;

import com.example.consultorio.dto.request.ConsultaRequestDTO;
import com.example.consultorio.dto.request.DentistaRequestDTO;
import com.example.consultorio.dto.request.PacienteRequestDTO;
import com.example.consultorio.dto.response.ConsultaResponseDTO;
import com.example.consultorio.dto.response.DentistaResponseDTO;
import com.example.consultorio.dto.response.PacienteResponseDTO;
import com.example.consultorio.model.Consulta;
import com.example.consultorio.model.Dentista;
import com.example.consultorio.model.Paciente;
import com.example.consultorio.service.IPacienteService;
import com.example.consultorio.service.impl.DentistaServiceImpl;
import com.example.consultorio.service.impl.PacienteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ConsultaConsultorioApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PacienteServiceImpl pacienteService;

	@Autowired
	private DentistaServiceImpl dentistaService;

	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	@Test
	@DisplayName("Deve CADASTRAR uma CONSULTA com sucesso")
	void salvarConsulta() throws Exception{
		PacienteRequestDTO paciente = new PacienteRequestDTO("Lusca","Santos","123456"," 2023,02,02",null);
		Optional<PacienteResponseDTO> pacienteSalvo = pacienteService.salvar(paciente);

		DentistaRequestDTO dentista = new DentistaRequestDTO(0,"Carlos","Silva",null);
		Optional<DentistaResponseDTO> dentistaSalvo = dentistaService.salvar(dentista);

		ConsultaRequestDTO consulta = new ConsultaRequestDTO(pacienteSalvo.get().getId(),dentistaSalvo.get().getMatriculaCadastro(),"2023-06-15T10:31:00");

		String consultaJson = mapper.writeValueAsString(consulta);

		mockMvc.perform(MockMvcRequestBuilders.post("/consultas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(consultaJson))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}


	@Test
	@DisplayName("Deve CANCELAR uma CONSULTA com sucesso")
	void cancelarConsulta() throws Exception{

		mockMvc.perform(MockMvcRequestBuilders.post("/consultas/cancelar/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}



}
