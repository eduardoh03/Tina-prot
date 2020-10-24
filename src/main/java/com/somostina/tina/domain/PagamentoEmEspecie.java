package com.somostina.tina.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.somostina.tina.domain.enums.EstadoPagamento;

@Entity
public class PagamentoEmEspecie extends Pagamento{
	private static final long serialVersionUID = 1L;
	private Date dataPagamento;
	
	public PagamentoEmEspecie() {		
	}
	
	public PagamentoEmEspecie(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
	}
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
}
