package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.Conta;
import com.indracompany.treinamento.model.repository.ContaRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ContaService extends GenericCrudService<Conta, Long, ContaRepository>{
	
	@Autowired
	private ContaRepository contaRepository;
	
	public Double consultarSaldo(String agencia, String numero) {
		Conta conta = contaRepository.findByAgenciaAndNumero(agencia, numero);
		return conta.getSaldo();
	}
	
	public List<Conta> consultarContasClientes(String cpf){
		return contaRepository.findByClienteCpf(cpf);
	}
	
	public Double saque (SaqueDTO saqueDto) {
		
		Conta conta = contaRepository.findByAgenciaAndNumero(saqueDto.getAgencia(), saqueDto.getNumeroConta());
		conta.setSaldo(conta.getSaldo() - saqueDto.getValor());
		contaRepository.save(conta);
		return conta.getSaldo();
	}
	
	public Double deposito (DepositoDTO depositoDto) {
		
		Conta conta = contaRepository.findByAgenciaAndNumero(depositoDto.getAgencia(), depositoDto.getNumeroConta());
		conta.setSaldo(conta.getSaldo() + depositoDto.getValor());
		contaRepository.save(conta);
		return conta.getSaldo();
	}
	
	public Double transferencia (TransferenciaBancariaDTO transferenciaBancariaDto) {
		
		SaqueDTO saqueDto = transferenciaBancariaDto.getOrigem();
		saqueDto.setValor(transferenciaBancariaDto.getValor());
		Double retornoSaque = saque(saqueDto);
		
		
		DepositoDTO depositoDto = transferenciaBancariaDto.getDestino();
		depositoDto.setValor(transferenciaBancariaDto.getValor());
		deposito(depositoDto);
		
		log.info("retorno saque: {}", retornoSaque);
		
		return retornoSaque;
}
}
