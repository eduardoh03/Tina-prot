package com.somostina.tina.domain.dto;

import java.io.Serializable;

public class ProcedimentoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	//procedimento
	private String nome;
	private Double preco;
	//servico
	private String nomeService;

	private Integer servicoId;

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

	public String getNomeService() {
		return nomeService;
	}

	public void setNomeService(String nomeService) {
		this.nomeService = nomeService;
	}

	public Integer getServicoId() {
		return servicoId;
	}

	public void setServicoId(Integer servicoId) {
		this.servicoId = servicoId;
	}

	
}
