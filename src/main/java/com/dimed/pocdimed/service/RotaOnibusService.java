package com.dimed.pocdimed.service;

import com.dimed.pocdimed.model.RotaOnibus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface RotaOnibusService {
	RotaOnibus getRotasById (Integer id) throws JsonMappingException, JsonProcessingException;
	
	void insertRota(RotaOnibus rotaOnibus);
	
	void deleteRotaById(Integer id);
	
	RotaOnibus getRotaById (Integer id);
}
