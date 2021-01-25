package com.dimed.pocdimed.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.dimed.pocdimed.model.LinhaOnibus;
import com.dimed.pocdimed.repository.LinhaOnibusRepository;

@Service
public class LinhaOnibusServiceImpl implements LinhaOnibusService{
	
	private LinhaOnibusService linhaOnibusService;
	private LinhaOnibusRepository linhaOnibusRepository;
	
	@Autowired
	LinhaOnibusServiceImpl(@Lazy LinhaOnibusService linhaOnibusService)
	{
		this.linhaOnibusService = linhaOnibusService;
	}

	@Override
	public Collection<LinhaOnibus> getAllLinhas() {		
		for (LinhaOnibus linhaOnibus : linhaOnibusService.getAllLinhas()) {
			linhaOnibusRepository.save(linhaOnibus);
		}
		
		return linhaOnibusService.getAllLinhas();
	}

	@Override
	public Collection<LinhaOnibus> getLinhasByName(String name) {
		return linhaOnibusService.getLinhasByName(name);
	}
}
