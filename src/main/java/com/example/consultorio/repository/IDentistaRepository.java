package com.example.consultorio.repository;

import com.example.consultorio.dto.response.DentistaResponseDTO;
import com.example.consultorio.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistaRepository  extends JpaRepository<Dentista,Integer> {

}
