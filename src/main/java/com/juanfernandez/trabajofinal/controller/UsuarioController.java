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
import java.util.TimeZone;

import com.juanfernandez.trabajofinal.model.Usuario;
import com.juanfernandez.trabajofinal.repository.UsuarioRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //GET para toos los usuario
    @GetMapping // ~/api/v1/usuario 
    public ResponseEntity<?> getUsuario(){
        return new ResponseEntity<> (usuarioRepository.findAll(), HttpStatus.OK);
    }

    //POST Create usuario
    @PostMapping // ~/api/v1/usuario 
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuario){
        return new ResponseEntity<> (usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    //PUT modif el usuario
    @PutMapping("/usuario/{userId}")
    public ResponseEntity<?> editUsuario(@PathVariable(value = "userId") Long userId, @Valid @RequestBody Usuario usuario){
        Usuario userEdit = usuarioRepository.getOne(userId);        
        userEdit.setNombre(usuario.getNombre());
        userEdit.setApellido(usuario.getApellido());
        userEdit.setEmail(usuario.getEmail());
        userEdit.setPassword(usuario.getPassword());
        userEdit.setCiudad(usuario.getCiudad());
        userEdit.setProvincia(usuario.getProvincia());
        userEdit.setPais(usuario.getPais());

        return new ResponseEntity<> (usuarioRepository.save(userEdit), HttpStatus.OK);
    }

    //DELETE borrar el usuario
    @DeleteMapping("/usuario/{userId}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "userId") Long userId){
        Usuario userDelete = usuarioRepository.getOne(userId);
        usuarioRepository.delete(userDelete);
        return new ResponseEntity<> (HttpStatus.OK);
    }
    
    @GetMapping("/ciudad") // ~/api/v1/usuario/ciudad
    public ResponseEntity<?> getUsuarioByCity(@RequestParam String city){
        return new ResponseEntity<> (usuarioRepository.findByCiudad(city), HttpStatus.OK);
    }

    @GetMapping("/fecha-creacion") // ~/api/v1/usuario/fecha-creacion
    public ResponseEntity<?> getUsuarioCreatedAfter(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha){
        return new ResponseEntity<> (usuarioRepository.findByFechaCreacionAfter(fecha), HttpStatus.OK);
    }
}