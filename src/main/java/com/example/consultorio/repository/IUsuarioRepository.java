package com.example.consultorio.repository;

import com.example.consultorio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    UserDetails findByEmail(String email);

}
