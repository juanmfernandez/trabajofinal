package com.juanfernandez.trabajofinal.repository;

import java.sql.Date;
import java.util.List;

import com.juanfernandez.trabajofinal.model.Post;
import com.juanfernandez.trabajofinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByPublicadoFalse();
    List<Post> findByTitleContaining(String word);
}