package com.indracompany.treinamento.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.Conta;
import com.indracompany.treinamento.model.service.ContaService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("rest/contas")
public class ContaRest extends GenericCrudRest<Conta, Long, ContaService>{
	
	@Autowired
	private ContaService contaService;
	
	@GetMapping(value = "/consultar-saldo/{agencia}/{numero}")
	public @ResponseBody ResponseEntity<Double> consultarSaldo (@PathVariable String agencia, @PathVariable String numero) throws AplicacaoException {
		
		Double saldo = contaService.consultarSaldo(agencia, numero);
		return new ResponseEntity<>(saldo, HttpStatus.OK);
	}
	
	@PostMapping (value = "/saque")
	public @ResponseBody ResponseEntity<Double> saque (@RequestBody SaqueDTO saqueDto){
		Double saldo = contaService.saque(saqueDto);
		return new ResponseEntity<>(saldo, HttpStatus.OK);
	}
	
	@GetMapping(value = "/consultar-cpf/{cpf}")
    public @ResponseBody ResponseEntity<List<Conta>> contaCpf (@PathVariable String cpf){
        List<Conta> contas = contaService.consultarContasClientes(cpf);
        return new ResponseEntity<>(contas, HttpStatus.OK);
    }
	
	@PostMapping(value = "/deposito")
	public @ResponseBody ResponseEntity<Double> deposito (@RequestBody DepositoDTO depositoDto){
		Double saldo = contaService.deposito(depositoDto);
		return new ResponseEntity<>(saldo, HttpStatus.OK);
	}
	
	@PostMapping(value="/transferencia")
	public @ResponseBody ResponseEntity<Double> transferencia (@RequestBody TransferenciaBancariaDTO transferenciaBancariaDto){
		Double saldo = contaService.transferencia(transferenciaBancariaDto);
		return new ResponseEntity<>(saldo, HttpStatus.OK);
	}
	
	
}
