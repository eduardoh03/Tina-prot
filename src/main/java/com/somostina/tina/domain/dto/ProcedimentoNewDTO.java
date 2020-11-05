package com.somostina.tina.domain.dto;

import java.io.Serializable;

public class ProcedimentoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	// procedimento
	private String nome;
	private Double preco;
	// categoria

	private Integer categoriaId;

	public ProcedimentoNewDTO() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

}
