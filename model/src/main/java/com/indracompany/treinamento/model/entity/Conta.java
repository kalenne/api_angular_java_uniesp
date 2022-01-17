package com.indracompany.treinamento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "contas")
@Data
@EqualsAndHashCode(callSuper = true)
public class Conta extends GenericEntity<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 4, nullable = false)
	private String agencia;
	
	@Column(length = 6, nullable = false) 
	private String numero;
	
	@Column 
	private Double saldo;
	
	@ManyToOne
	@JoinColumn(name = "fk_cliente_id")
	private Cliente cliente;
}
