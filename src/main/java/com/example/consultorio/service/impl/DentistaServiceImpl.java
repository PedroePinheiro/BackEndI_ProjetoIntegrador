package com.example.consultorio.service.impl;

import com.example.consultorio.model.Dentista;
import com.example.consultorio.model.Paciente;
import com.example.consultorio.service.IDentistaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DentistaServiceImpl implements IDentistaService<Dentista> {

    public static Map<Integer, Dentista> dentistaMap = new HashMap<>();


    @Override
    public Dentista salvar(Dentista dentista) {
        dentistaMap.put(dentista.getMatriculaCadastro(),dentista);
        return dentista;
    }

    @Override
    public Dentista buscar(int matriculaCadastro) {
        return dentistaMap.get(matriculaCadastro);
    }

    @Override
    public List<Dentista> buscarTodos() {
        return new ArrayList<>(dentistaMap.values());

    }

    @Override
    public Dentista atualizar(int matriculaCadastro, Dentista dentista) {
        dentistaMap.put(matriculaCadastro,dentista);
        return dentista;
    }
}
