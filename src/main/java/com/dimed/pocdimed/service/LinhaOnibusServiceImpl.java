package com.dimed.pocdimed.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dimed.pocdimed.model.LinhaOnibus;
import com.dimed.pocdimed.model.RotaLatLng;
import com.dimed.pocdimed.model.RotaOnibus;
import com.dimed.pocdimed.repository.LinhaOnibusRepository;
import com.dimed.pocdimed.resources.DistanciaEntreDuasCoord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class LinhaOnibusServiceImpl implements LinhaOnibusService{
	
	private String linkLinhas = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	LinhaOnibusRepository linhaOnibusRepository;

	@Override
	public Collection<LinhaOnibus> getAllLinhas() {		
		List<LinhaOnibus> linhas = new ArrayList<>();
		
		HttpHeaders headers = new HttpHeaders();
        LinhaOnibus linhaOnibus = new LinhaOnibus();
        
        HttpEntity<LinhaOnibus> entity = new HttpEntity<LinhaOnibus>(linhaOnibus, headers);

        ResponseEntity<List<LinhaOnibus>> result = restTemplate.exchange(linkLinhas, HttpMethod.GET, entity, new ParameterizedTypeReference<List<LinhaOnibus>>() {});
        
        linhas.addAll(result.getBody());
        
        for (LinhaOnibus linhaOnibus2 : linhas) {
        	linhaOnibusRepository.save(linhaOnibus2);
		}
        
        return linhas;
	}

	@Override
	public Collection<LinhaOnibus> getLinhasByName(String name) {
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
        return  byNome;
	}
	
	@Override
	public LinhaOnibus getLinhaById (Integer id)
	{
		Optional<LinhaOnibus> linha = linhaOnibusRepository.findById(id);
		if(linha.isEmpty())
		{
			return null;
		}
		else
		{
			return linha.get();
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void insertLinha (LinhaOnibus linha)
	{
		if(!linhaOnibusRepository.existsById(linha.getId()))
		{
			linhaOnibusRepository.save(linha);
		}
		else
		{
			if(!linhaOnibusRepository.equals(linha))
			{
				linhaOnibusRepository.deleteById(linha.getId());
				linhaOnibusRepository.save(linha);
			}
		}
	}
	
	@Override
	public Collection <LinhaOnibus> findAllLinhas()
	{
		return linhaOnibusRepository.findAll();
	}
	
	@Override
	public void deleteLinhaById(Integer id)
	{
		linhaOnibusRepository.deleteById(id);
	}
	
	@Autowired
	RotaOnibusService rotaOnibusService;
	
	@Override
	public Collection <LinhaOnibus> filtraLinhaPorRaio(Double lat1, Double lng1, Double dist) throws JsonMappingException, JsonProcessingException
	{	
		DistanciaEntreDuasCoord distanciaEntreDuasCoord = new DistanciaEntreDuasCoord();
		List<LinhaOnibus> linhasBuffer = new ArrayList<>();
		List<LinhaOnibus> linhas = new ArrayList<>();
		
		RotaOnibus rotaOnibus = new RotaOnibus();
		
		linhasBuffer = (List<LinhaOnibus>) getAllLinhas();
		
		for(int i = 0; i <linhasBuffer.size(); i++)
		{
			rotaOnibus = rotaOnibusService.getRotasById(linhasBuffer.get(i).getId());
			List<RotaLatLng> coord = rotaOnibus.getCoord();
			for(RotaLatLng latLng : coord)
			{
				if(distanciaEntreDuasCoord.calcula(lat1, lng1, latLng.getLat(), latLng.getLng(), dist))
				{
					linhas.add(linhasBuffer.get(i));
					break;
				}
			}
		}
		return linhas;
	}
}
