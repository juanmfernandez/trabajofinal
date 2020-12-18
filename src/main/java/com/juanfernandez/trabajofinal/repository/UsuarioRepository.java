package com.juanfernandez.trabajofinal.repository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import com.juanfernandez.trabajofinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByCiudad(String city);
    List<Usuario> findByFechaCreacionAfter(LocalDate fecha);
}