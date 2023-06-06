package com.example.consultorio.service.impl;

import com.example.consultorio.model.Paciente;
import com.example.consultorio.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class PacienteServiceImpl implements IPacienteService<Paciente> {

    public static Map<Integer, Paciente> pacienteMap = new HashMap<>();

    @Override
    public Paciente salvar(Paciente paciente) {
        pacienteMap.put(paciente.getId(),paciente);
        return paciente;
    }

    @Override
    public Paciente buscar(int id) {
        return pacienteMap.get(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return new ArrayList<>(pacienteMap.values());
    }

    @Override
    public Paciente atualizar(int id,Paciente paciente) {
        pacienteMap.put(id,paciente);
        return paciente;
    }

    @Override
    public String excluir(int id) {
        pacienteMap.remove(id);
        return "usuario removido";
    }
}
