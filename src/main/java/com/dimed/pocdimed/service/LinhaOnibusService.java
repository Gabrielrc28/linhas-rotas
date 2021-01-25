package com.dimed.pocdimed.service;

import java.util.Collection;

import com.dimed.pocdimed.model.LinhaOnibus;

public interface LinhaOnibusService {
	Collection<LinhaOnibus> getAllLinhas();
	
	Collection<LinhaOnibus> getLinhasByName(String name);
}
