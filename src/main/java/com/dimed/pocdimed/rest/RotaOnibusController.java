package com.dimed.pocdimed.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dimed.pocdimed.model.RotaLatLng;
import com.dimed.pocdimed.model.RotaOnibus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/rota")
public class RotaOnibusController {
	
	private String linkRotaById = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";
	
	@Autowired
    private RestTemplate restTemplate;
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RotaOnibus> getLinhasById(@PathVariable Integer id) throws JsonMappingException, JsonProcessingException{
    	
    	ResponseEntity<String> rota = restTemplate.getForEntity(linkRotaById + id, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        
        String json = rota.getBody();
        JsonNode jsonNode = objectMapper.readTree(json);
        
        int aux = 0;
        
        List <RotaLatLng> coord = new ArrayList<>();
        RotaOnibus rotaOnibus = new RotaOnibus();
        
        rotaOnibus.setIdlinha(jsonNode.path("idlinha").asInt());
        rotaOnibus.setNome(jsonNode.path("nome").asText());
        rotaOnibus.setCodigo(jsonNode.path("codigo").asText());
        
        while(true) {
        	JsonNode rotaFinal = jsonNode.path("" + aux);
        	if(rotaFinal.isMissingNode())
        	{
        		break;
        	}else
        	{
        		RotaLatLng latLng = new RotaLatLng(rotaFinal.path("lat").asDouble(),rotaFinal.path("lng").asDouble());
        		coord.add(latLng);
        		aux++;
        	}
        }
        
        rotaOnibus.setCoord(coord);
        
        return  new ResponseEntity<RotaOnibus>(rotaOnibus, HttpStatus.OK);
    }
}
