package com.dimed.pocdimed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dimed.pocdimed.model.RotaLatLng;
import com.dimed.pocdimed.model.RotaOnibus;
import com.dimed.pocdimed.repository.RotaOnibusRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RotaOnibusServiceImpl implements RotaOnibusService {	
	private String linkRotaById = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";
	
	@Autowired
    private RestTemplate restTemplate;
	@Autowired
	private RotaOnibusRepository rotaOnibusRepository;
	
	@Override
	public RotaOnibus getRotasById(Integer id) throws JsonMappingException, JsonProcessingException, RestClientException {
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
        rotaOnibusRepository.save(rotaOnibus);
        
        return  rotaOnibus;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void insertRota(RotaOnibus rotaOnibus)
	{
		if(!rotaOnibusRepository.existsById(rotaOnibus.getIdlinha()))
		{
			rotaOnibusRepository.save(rotaOnibus);
		}
		else
		{
			if(!rotaOnibusRepository.equals(rotaOnibus))
			{
				rotaOnibusRepository.deleteById(rotaOnibus.getIdlinha());
				rotaOnibusRepository.save(rotaOnibus);
			}
		}
	}
	
	@Override
	public void deleteRotaById(Integer id)
	{
		rotaOnibusRepository.deleteById(id);
	}
	
	@Override
	public RotaOnibus getRotaById (Integer id)
	{
		Optional<RotaOnibus> linha = rotaOnibusRepository.findById(id);
		if(linha.isEmpty())
		{
			return null;
		}
		else
		{
			return linha.get();
		}
	}
}
