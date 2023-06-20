package com.example.consultorio.repository;

import com.example.consultorio.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta,Integer> {

    Optional<Consulta> findByPacienteId(int id);
    Optional<Consulta> findByDentistaMatriculaCadastro(int id);


}
