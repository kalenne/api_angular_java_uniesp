package com.indracompany.treinamento.model.service;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.repository.OperacaoRepository;

@Service
public class OperacaoService extends GenericCrudService<Operacao, Long, OperacaoRepository> {

}
	