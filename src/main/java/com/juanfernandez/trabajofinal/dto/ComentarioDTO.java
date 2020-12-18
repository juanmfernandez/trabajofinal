package com.juanfernandez.trabajofinal.dto;

public class ComentarioDTO{

    private Long idAutor;

    private String comentario;

	public Long getIdAutor() {
		return this.idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}



}