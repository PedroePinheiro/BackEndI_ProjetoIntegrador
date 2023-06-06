package com.example.consultorio.service.impl;

import com.example.consultorio.model.Consulta;
import com.example.consultorio.service.IConsultaService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultaServiceImpl implements IConsultaService<Consulta> {

    public static Map<Integer, Consulta> consultaMap = new HashMap<>();

    @Override
    public Consulta salvar(Consulta consulta) {
        consultaMap.put(consulta.getId(),consulta);
        return consulta;
    }
}
