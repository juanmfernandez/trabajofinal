package com.juanfernandez.trabajofinal.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;

import com.juanfernandez.trabajofinal.model.Post;
import com.juanfernandez.trabajofinal.model.Usuario;
import com.juanfernandez.trabajofinal.dto.ComentarioDTO;
import com.juanfernandez.trabajofinal.model.Comentario;
import com.juanfernandez.trabajofinal.repository.PostRepository;
import com.juanfernandez.trabajofinal.repository.UsuarioRepository;
import com.juanfernandez.trabajofinal.repository.ComentarioRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private PostRepository postRepository;

    @GetMapping // ~/api/v1/post
    public ResponseEntity<?> getComentarioLimit(@RequestParam(required = false) Integer limit){
        if (limit != null){
            return new ResponseEntity<> (comentarioRepository.getComentarioLimitTo(limit), HttpStatus.OK);
        }
        return new ResponseEntity<> (comentarioRepository.findAll(), HttpStatus.OK);
        
    }
    
    
    // @PostMapping("/autor/{idUsuario}/post/{idPost}") // ~/api/v1/post 
    // public ResponseEntity<?> createPost(@PathVariable(value = "idUsuario") Long idUsuario,  @PathVariable(value = "idPost") Long idPost,  @Valid @RequestBody Comentario comentario){
    //     Usuario author = usuarioRepository.getOne(idUsuario);
    //     author.addComentario(comentario);
    //     Post post = postRepository.getOne(idPost);
    //     post.addComentario(comentario);
    //     return new ResponseEntity<> (comentarioRepository.save(comentario), HttpStatus.CREATED);
    // }

    //POST Create comentario
    @PostMapping("/post/{idPost}/comentario") // ~/api/v1/post 
    public ResponseEntity<?> createPost(@PathVariable Long idPost,  @Valid @RequestBody ComentarioDTO comentarioDto){

        Comentario comentario = new Comentario();

        Usuario author = usuarioRepository.getOne(comentarioDto.getIdAutor());
        author.addComentario(comentario);
        Post post = postRepository.getOne(idPost);
        post.addComentario(comentario);

        comentario.setComentario(comentarioDto.getComentario());

        return new ResponseEntity<> (comentarioRepository.save(comentario), HttpStatus.CREATED);
    }


    //PUT modif el comentario
    @PutMapping("/comentario/{comId}")
    public ResponseEntity<?> editPost(@PathVariable(value = "comId") Long comId, @Valid @RequestBody Comentario comentario){
        Comentario comentarioEdit = comentarioRepository.getOne(comId);
        comentarioEdit.setComentario(comentario.getComentario());
        return new ResponseEntity<> (comentarioRepository.save(comentarioEdit), HttpStatus.OK);
    }

    //DELETE borrar el comentario
    @DeleteMapping("/comentario/{comId}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "comId") Long comId){
        Comentario comentarioDelete = comentarioRepository.getOne(comId);
        comentarioRepository.delete(comentarioDelete);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}