package com.dimed.pocdimed.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dimed.pocdimed.model.PontoDeTaxi;
import com.dimed.pocdimed.service.PontoDeTaxiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/taxi")
public class PontoDeTaxiController {
	
	@Autowired
	PontoDeTaxiService pontoDeTaxiService;
	
	@ApiOperation("Retorna o ponto de taxi com o nome informado.")
	@RequestMapping(value ="/procurar", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<PontoDeTaxi> getPontoDeTaxiByName(@RequestParam(name = "nome") String nome) throws IOException{
		
		PontoDeTaxi pontoDeTaxi = pontoDeTaxiService.getPontoDeTaxiByName(nome);
		
		return  new ResponseEntity<PontoDeTaxi>(pontoDeTaxi, HttpStatus.OK);
	}
	
	@ApiOperation("Cria um novo ponto de taxi.")
	@RequestMapping(value ="/cadastro", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<PontoDeTaxi> insertPontoDeTaxi(@RequestBody PontoDeTaxi pontoDeTaxi) throws IOException{

		pontoDeTaxiService.insertPontoDeTaxi(pontoDeTaxi);
		
		return  new ResponseEntity<PontoDeTaxi>(HttpStatus.OK);
	}
	
	@ApiOperation("Retorna todos os pontos de taxi cadastrados.")
	@RequestMapping(value ="/pontos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<PontoDeTaxi>> getAllPontosTaxi() throws IOException{
		
		List<PontoDeTaxi> pontosDeTaxi = pontoDeTaxiService.getAllPontosTaxi();
		
		return  new ResponseEntity<List<PontoDeTaxi>>(pontosDeTaxi, HttpStatus.OK);
	}
}
