package com.example.consultorio.service.impl;

import com.example.consultorio.model.Paciente;
import com.example.consultorio.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class PacienteServiceImpl implements IPacienteService<Paciente> {

    public static Map<Integer, Paciente> pacienteMap = new HashMap<>();

    @Override
    public Paciente salvar(Paciente paciente) {
        pacienteMap.put(pacienteMap.size()+1,paciente);
        return paciente;
    }

    @Override
    public Optional<Paciente> buscar(int id) {
        return Optional.ofNullable(pacienteMap.get(id));
    }

    @Override
    public List<Paciente> buscarTodos() {
        return new ArrayList<>(pacienteMap.values());
    }

    @Override
    public Optional<Paciente> atualizar(int id,Paciente paciente) {
        pacienteMap.put(id,paciente);
        return Optional.ofNullable(paciente);
    }

    @Override
    public String excluir(int id) {
        pacienteMap.remove(id);
        return "usuario removido";
    }
}
