package com.indracompany.treinamento.model.entity;

import java.time.LocalDateTime;

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
@Table(name = "operacoes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Operacao extends GenericEntity<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true)
	private LocalDateTime dataHora;
	
	@Column(length = 100) 
	private String observacao;
	
	@Column (nullable = false)
	private Double valor;
	
	@Column (length = 1, nullable = false) 
	private Character tpoperacao;
	
	@ManyToOne
	@JoinColumn(name = "fk_cliente_id")
	private Cliente cliente;
}
