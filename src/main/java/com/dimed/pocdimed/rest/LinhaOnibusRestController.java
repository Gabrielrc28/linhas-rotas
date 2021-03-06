package com.dimed.pocdimed.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dimed.pocdimed.model.LinhaOnibus;
import com.dimed.pocdimed.service.LinhaOnibusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Api
@RestController
@RequestMapping("/linhas")
public class LinhaOnibusRestController {
    
    @Autowired
    private LinhaOnibusService linhaOnibusService;
    @ApiOperation("Pega todas as linhas de onibus que est?o na datapoa API.")
	@RequestMapping(value ="", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> getAllLinhas(){

        List<LinhaOnibus> linhas = new ArrayList<>();

        linhas.addAll(this.linhaOnibusService.getAllLinhas());
        
        return  new ResponseEntity<Collection<LinhaOnibus>>(linhas, HttpStatus.OK);
    }
    
    @ApiOperation("Retorna as linhas por nome consultando a datapoa API.")
    @RequestMapping(value ="/nome", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> getLinhasByName(@RequestParam(name = "name") String name){

        List<LinhaOnibus> byNome = new ArrayList<>();
        
        byNome.addAll(this.linhaOnibusService.getLinhasByName(name));

        return  new ResponseEntity<Collection<LinhaOnibus>>(byNome, HttpStatus.OK);
    }
    
    @ApiOperation("Insere uma linha no banco, respeitando as regras da parte 3.")
    @RequestMapping(value = "/bd/inserir_linha", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LinhaOnibus> insertLinha(@RequestBody LinhaOnibus linha){

        linhaOnibusService.insertLinha(linha);
        
        return  new ResponseEntity<LinhaOnibus>(linha, HttpStatus.CREATED);
    }
    
    @ApiOperation("Retorna todas as linhas cadastradas no banco.")
    @RequestMapping(value = "/bd/linhas", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> findAllLinhas(){

    	List<LinhaOnibus> linhas = new ArrayList<>();

        linhas.addAll(this.linhaOnibusService.findAllLinhas());
        
        return  new ResponseEntity<Collection<LinhaOnibus>>(linhas, HttpStatus.OK);
    }
    
    @ApiOperation("Deleta uma linha por ID no banco.")
    @RequestMapping(value ="/bd/deletar_linha/id", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<LinhaOnibus> deleteLinhaById(@RequestParam(name = "id") Integer id){

    	this.linhaOnibusService.deleteLinhaById(id);
    	
        return  new ResponseEntity<LinhaOnibus>(HttpStatus.OK);
    }
    
    @ApiOperation("Retorna do banco uma linha filtrada por um ID.")
    @RequestMapping(value ="/bd/id", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LinhaOnibus> getLinhasById(@RequestParam(name = "id") Integer id){

        LinhaOnibus linha = new LinhaOnibus();
        
        linha = this.linhaOnibusService.getLinhaById(id);

        return  new ResponseEntity<LinhaOnibus>(linha, HttpStatus.OK);
    }
    
    @ApiOperation("Dado uma cordenada e um raio, a api retorna todas as rotas que passam nesse raio.")
    @RequestMapping(value ="/filtra", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> filtraLinhaPorRaio(@RequestParam(name = "lat") Double lat1, @RequestParam(name = "lng") Double lng1, @RequestParam(name = "dist") Double dist) throws JsonMappingException, JsonProcessingException, InterruptedException{
    	List<LinhaOnibus> linhas = new ArrayList<>();
    	
    	linhas.addAll(this.linhaOnibusService.filtraLinhaPorRaio(lat1, lng1, dist));
    	
    	return  new ResponseEntity<Collection<LinhaOnibus>>(linhas, HttpStatus.OK);
    }
}