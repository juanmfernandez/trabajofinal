package com.juanfernandez.trabajofinal.model;

//import java.sql.Date;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Comentario{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(updatable = false)
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    @Size(max = 200)
	private String comentario;
	
	@ManyToOne
    @JoinColumn(name="autor_fk", referencedColumnName = "id")
    //@JsonIgnore
	private Usuario autor;
	
	@ManyToOne
    @JoinColumn(name="post_fk", referencedColumnName = "id")
    //@JsonIgnore
    private Post post;

	public Long getPost() {
		return this.post.getId();
	}

	public void setPost(Post post) {
		this.post = post;
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getAutor() {
		return this.autor.getId();
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public LocalDateTime getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


}