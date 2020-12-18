package com.juanfernandez.trabajofinal.model;

//import java.util.Date;
//import java.time.temporal.Temporal;
import javax.persistence.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String nombre;
    
    @Column(nullable = false)
    @NotBlank
    private String apellido;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String email;   

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

	@Column(updatable = false)
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCreacion;


    @Column(nullable = false)
    @NotBlank
    private String ciudad;

    @Column(nullable = false)
    @NotBlank
    private String provincia;

    @Column(nullable = false)
    @NotBlank
	private String pais;
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Post> post;

	@JsonIgnore
	public List<Post> getPost() {
		return this.post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}


	public void addPost(Post post){
		this.post.add(post);
		post.setUsuario(this);
	}

	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	private List<Comentario> comentario;

	public List<Comentario> getComentario() {
		return this.comentario;
	}

	public void setComentario(List<Comentario> comentario) {
		this.comentario = comentario;
	}


	public void addComentario(Comentario comentario){
		this.comentario.add(comentario);
		comentario.setAutor(this);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @JsonIgnore
    public String getPassword() {
        return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}