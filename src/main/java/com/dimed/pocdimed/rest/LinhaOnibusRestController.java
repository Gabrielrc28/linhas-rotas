package com.dimed.pocdimed.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dimed.pocdimed.model.LinhaOnibus;
import com.dimed.pocdimed.model.RotaOnibus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/linhas")
public class LinhaOnibusRestController {

    @Autowired
    private RestTemplate restTemplate;
    
    private String linkLinhas = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
    private String linkRotaById = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value ="", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> getAllLinhas(){

        List<LinhaOnibus> linhas = new ArrayList<>();

        linhas.addAll(restTemplate.getForObject(linkLinhas, Collection.class));
        
        return  new ResponseEntity<Collection<LinhaOnibus>>(linhas, HttpStatus.OK);
    }
    
    @RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RotaOnibus> getLinhasById(@PathVariable Integer id){

        RotaOnibus rota = restTemplate.getForObject(linkRotaById + id, RotaOnibus.class);

        return  new ResponseEntity<RotaOnibus>(rota, HttpStatus.OK);
    }
}
