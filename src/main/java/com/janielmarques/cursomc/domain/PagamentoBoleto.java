package com.janielmarques.cursomc.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.janielmarques.cursomc.domain.enums.EstadoPagamento;

import jakarta.persistence.Entity;

@Entity
public class PagamentoBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private  Date dataVencimento;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento; 
	
	public PagamentoBoleto() {
		
	}

	public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}
