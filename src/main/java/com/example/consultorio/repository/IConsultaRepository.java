package com.example.consultorio.repository;

import com.example.consultorio.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta,Integer> {

    List<Consulta> findByPacienteId(int id);
    List<Consulta> findByDentistaMatriculaCadastro(int id);


}
