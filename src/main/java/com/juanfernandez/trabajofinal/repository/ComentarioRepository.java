package com.juanfernandez.trabajofinal.repository;

import java.sql.Date;
import java.util.List;

import com.juanfernandez.trabajofinal.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    @Query(nativeQuery = true, value = "SELECT * FROM comentario s ORDER BY s.fecha_creacion DESC LIMIT ?1")
    List<Comentario> getComentarioLimitTo(@Param("limit") Integer limit);
}