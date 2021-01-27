package com.dimed.pocdimed.service;

import java.util.Collection;

import com.dimed.pocdimed.model.LinhaOnibus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LinhaOnibusService {
	Collection<LinhaOnibus> getAllLinhas();
	
	Collection<LinhaOnibus> getLinhasByName(String name);
	
	void insertLinha (LinhaOnibus linha);
	
	Collection <LinhaOnibus> findAllLinhas();
	
	void deleteLinhaById(Integer id);
	
	LinhaOnibus getLinhaById (Integer id);
	
	Collection <LinhaOnibus> filtraLinhaPorRaio(Double lat1, Double lng1, Double dist) throws JsonMappingException, JsonProcessingException, InterruptedException;
}
