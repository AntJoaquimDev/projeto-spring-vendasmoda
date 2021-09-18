package com.produtos.apivendas.model;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Vendas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nomeProduto;
	private int quantidade;	
	private BigDecimal valor;
	private String dataVenda;

	
	
}
