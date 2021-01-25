package com.dimed.pocdimed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.dimed.pocdimed.model.RotaOnibus;

@Service
public class RotaOnibusServiceImpl implements RotaOnibusService {
	private RotaOnibusService rotaOnibusService;
	
	@Autowired
	RotaOnibusServiceImpl(@Lazy RotaOnibusService rotaOnibusService){
		this.rotaOnibusService = rotaOnibusService;
	}
	
	@Override
	public RotaOnibus getLinhasById(Integer id) {
		return rotaOnibusService.getLinhasById(id);
	}
}
