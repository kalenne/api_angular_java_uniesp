package com.indracompany.treinamento.model.repository;

import java.util.List;

import com.indracompany.treinamento.model.entity.Conta;

public interface ContaRepository extends GenericCrudRepository<Conta, Long> {

	public Conta findByAgenciaAndNumero(String agencia, String numero);
	
	public List<Conta> findByClienteCpf (String cpf);
	
	
	
}
