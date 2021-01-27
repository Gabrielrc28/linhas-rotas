package com.dimed.pocdimed.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dimed.pocdimed.model.RotaOnibus;
import com.dimed.pocdimed.service.RotaOnibusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/rota")
public class RotaOnibusController {
		
	@Autowired
    private RotaOnibusService rotaOnibusService;
	
	@ApiOperation("Retorna as rotas por ID, que est�o na datapoa API.")
	@RequestMapping(value ="/id", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RotaOnibus> getRotasById(@RequestParam(name = "id") Integer id) throws JsonMappingException, JsonProcessingException{
    	
		RotaOnibus rotaOnibus = rotaOnibusService.getRotasById(id);

		return  new ResponseEntity<RotaOnibus>(rotaOnibus, HttpStatus.OK);
    }
	
	@ApiOperation("Retorna as rotas por ID, que est�o no banco local.")
	@RequestMapping(value ="/db/id", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RotaOnibus> getRotaById(@RequestParam(name = "id") Integer id){
    	
		RotaOnibus rotaOnibus = rotaOnibusService.getRotaById(id);

		return  new ResponseEntity<RotaOnibus>(rotaOnibus, HttpStatus.OK);
    }
	
	@ApiOperation("Insere uma nova rota no banco local.")
	@RequestMapping(value ="/db/inserir_rota", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<RotaOnibus> insertRota(@RequestBody RotaOnibus rotaOnibus){
    			
		rotaOnibusService.insertRota(rotaOnibus);

		return  new ResponseEntity<RotaOnibus>(HttpStatus.OK);
    }
	
	@ApiOperation("Deleta uma rota, por ID no banco local.")
	@RequestMapping(value ="/db/deletar_rota/id", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<RotaOnibus> deleteRotaById(@RequestParam(name = "id") Integer id){
    	
		rotaOnibusService.deleteRotaById(id);

		return  new ResponseEntity<RotaOnibus>(HttpStatus.OK);
    }
}
