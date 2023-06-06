package com.example.consultorio.service.impl;

import com.example.consultorio.model.Dentista;
import com.example.consultorio.model.Paciente;
import com.example.consultorio.service.IDentistaService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DentistaServiceImpl implements IDentistaService<Dentista> {

    public static Map<Integer, Dentista> dentistaMap = new HashMap<>();


    @Override
    public Dentista salvar(Dentista dentista) {
        dentistaMap.put(dentista.getMatriculaCadastro(),dentista);
        return dentista;
    }

    @Override
    public Optional<Dentista> buscar(int matriculaCadastro) {
        return Optional.ofNullable(dentistaMap.get(matriculaCadastro));
    }

    @Override
    public List<Dentista> buscarTodos() {
        return new ArrayList<>(dentistaMap.values());

    }

    @Override
    public Optional<Dentista> atualizar(int matriculaCadastro, Dentista dentista) {
        dentistaMap.put(matriculaCadastro,dentista);
        return Optional.ofNullable(dentista);
    }
}
