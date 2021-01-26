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

@RestController
@RequestMapping("/rota")
public class RotaOnibusController {
		
	@Autowired
    private RotaOnibusService rotaOnibusService;
	
	@RequestMapping(value ="/id", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RotaOnibus> getRotasById(@RequestParam(name = "id") Integer id) throws JsonMappingException, JsonProcessingException{
    	
		RotaOnibus rotaOnibus = rotaOnibusService.getRotasById(id);

		return  new ResponseEntity<RotaOnibus>(rotaOnibus, HttpStatus.OK);
    }
	
	@RequestMapping(value ="/db/id", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RotaOnibus> getRotaById(@RequestParam(name = "id") Integer id){
    	
		RotaOnibus rotaOnibus = rotaOnibusService.getRotaById(id);

		return  new ResponseEntity<RotaOnibus>(rotaOnibus, HttpStatus.OK);
    }
	
	@RequestMapping(value ="/db/inserir_rota", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<RotaOnibus> insertRota(@RequestBody RotaOnibus rotaOnibus){
    			
		rotaOnibusService.insertRota(rotaOnibus);

		return  new ResponseEntity<RotaOnibus>(HttpStatus.OK);
    }
	
	@RequestMapping(value ="/db/deletar_rota/id", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<RotaOnibus> deleteRotaById(@RequestParam(name = "id") Integer id){
    	
		rotaOnibusService.deleteRotaById(id);

		return  new ResponseEntity<RotaOnibus>(HttpStatus.OK);
    }
}
