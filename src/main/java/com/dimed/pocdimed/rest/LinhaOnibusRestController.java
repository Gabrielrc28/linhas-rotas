package com.dimed.pocdimed.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dimed.pocdimed.model.LinhaOnibus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/linhas")
public class LinhaOnibusRestController {

    @Autowired
    private RestTemplate restTemplate;
    
    private String linkLinhas = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value ="", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> getAllLinhas(){

        List<LinhaOnibus> linhas = new ArrayList<>();

        linhas.addAll(restTemplate.getForObject(linkLinhas, Collection.class));
        
        return  new ResponseEntity<Collection<LinhaOnibus>>(linhas, HttpStatus.OK);
    }
           
    @RequestMapping(value ="/nome", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> getLinhasByName(@RequestParam(name = "name") String name){
    	
    	name = name.toUpperCase();
    	
        List<LinhaOnibus> linhas = new ArrayList<>();
        List<LinhaOnibus> byNome = new ArrayList<>();
        
        HttpHeaders headers = new HttpHeaders();
        LinhaOnibus linhaOnibus = new LinhaOnibus();
        
        HttpEntity<LinhaOnibus> entity = new HttpEntity<LinhaOnibus>(linhaOnibus, headers);

        ResponseEntity<List<LinhaOnibus>> result = restTemplate.exchange(linkLinhas, HttpMethod.GET, entity, new ParameterizedTypeReference<List<LinhaOnibus>>() {});
        
        linhas.addAll(result.getBody());
        
        for(int i = 0; i < linhas.size(); i++)
        {
        	if(linhas.get(i).getNome().contains(name))
        	{
        		byNome.add(linhas.get(i));
        	}
        }
        return  new ResponseEntity<Collection<LinhaOnibus>>(byNome, HttpStatus.OK);
    }
}
