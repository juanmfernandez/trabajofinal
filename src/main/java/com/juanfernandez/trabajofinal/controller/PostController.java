package com.juanfernandez.trabajofinal.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;

import com.juanfernandez.trabajofinal.model.Post;
import com.juanfernandez.trabajofinal.model.Usuario;
import com.juanfernandez.trabajofinal.repository.PostRepository;
import com.juanfernandez.trabajofinal.repository.UsuarioRepository;

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
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;
    //GET para toos los post
    @GetMapping // ~/api/v1/post 
    public ResponseEntity<?> getPost(){
        return new ResponseEntity<> (postRepository.findAll(), HttpStatus.OK);
    }

    //POST Create post
    @PostMapping("/autor/{id}") // ~/api/v1/post 
    public ResponseEntity<?> createPost(@PathVariable(value = "id") Long userId, @Valid @RequestBody Post post){
        Usuario author = usuarioRepository.getOne(userId);
        author.addPost(post);
        return new ResponseEntity<> (postRepository.save(post), HttpStatus.CREATED);
    }

    //PUT modif el post
    @PutMapping("/post/{postId}")
    public ResponseEntity<?> editPost(@PathVariable(value = "postId") Long postId, @Valid @RequestBody Post post){
        Post postEdit = postRepository.getOne(postId);
        postEdit.setTitle(post.getTitle());
        postEdit.setDescripcion(post.getDescripcion());
        postEdit.setContenido(post.getContenido());
        postEdit.setPublicado(post.getPublicado());
      
        return new ResponseEntity<> (postRepository.save(postEdit), HttpStatus.OK);
    }

    //DELETE borrar el post
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "postId") Long postId){        
        Post postDelete = new Post();
        try{
             postDelete = postRepository.getOne(postId);
             System.out.println(postDelete.toString());
        }catch (Exception e){
             return new ResponseEntity<> ("POST NO ENCONTRADO"/*e.getMessage()*/, HttpStatus.NOT_FOUND);
        }
        postRepository.delete(postDelete);
        return new ResponseEntity<> (HttpStatus.OK);

    }
    
    @GetMapping("/palabra/{word}") // ~/api/v1/post 
    public ResponseEntity<?> getUsuarioByWord(@PathVariable(value = "word") String word){

        return new ResponseEntity<> (postRepository.findByTitleContaining(word), HttpStatus.OK); 
    }

    @GetMapping("/no-publicados") // ~/api/v1/post 
    public ResponseEntity<?> findByNotPublic(){
        return new ResponseEntity<> (postRepository.findByPublicadoFalse(), HttpStatus.OK);
    }
}