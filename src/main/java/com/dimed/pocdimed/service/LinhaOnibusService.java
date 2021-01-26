package com.dimed.pocdimed.service;

import java.util.Collection;

import com.dimed.pocdimed.model.LinhaOnibus;

public interface LinhaOnibusService {
	Collection<LinhaOnibus> getAllLinhas();
	
	Collection<LinhaOnibus> getLinhasByName(String name);
	
	void insertLinha (LinhaOnibus linha);
	
	Collection <LinhaOnibus> findAllLinhas();
	
	void deleteLinhaById(Integer id);
	
	LinhaOnibus getLinhaById (Integer id);
}
