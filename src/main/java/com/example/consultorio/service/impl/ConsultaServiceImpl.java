package com.example.consultorio.service.impl;

import com.example.consultorio.model.Consulta;
import com.example.consultorio.service.IConsultaService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements IConsultaService<Consulta> {

    public static Map<Integer, Consulta> consultaMap = new HashMap<>();

    @Override
    public Consulta salvar(Consulta consulta) {
        consultaMap.put(consulta.getId(),consulta);
        return consulta;
    }

    @Override
    public Optional<Consulta> atualizar(int id, Consulta consulta) {
        consultaMap.put(id,consulta);
        return Optional.ofNullable(consulta);
    }

    @Override
    public void cancelar(int id) {
        Consulta consulta = consultaMap.get(id);
        consulta.setCancelada(true);
        consultaMap.put(id,consulta);
    }
}
