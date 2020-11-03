package com.somostina.tina.domain.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.somostina.tina.domain.Procedimento;

public class ProcedimentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@Length(min = 3, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	private Double preco;

	public ProcedimentoDTO() {

	}

	public ProcedimentoDTO(Procedimento obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
