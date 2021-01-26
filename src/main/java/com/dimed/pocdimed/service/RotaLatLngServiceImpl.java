/*package com.dimed.pocdimed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dimed.pocdimed.model.RotaLatLng;
import com.dimed.pocdimed.repository.RotaLatLngRepository;

public class RotaLatLngServiceImpl implements RotaLatLngService{
	
	@Autowired
	RotaLatLngRepository rotaLatLngRepository;
	
	@Override
	public List<RotaLatLng> getAll() {
		
		List<RotaLatLng> latLng = new ArrayList<>();
		
		for (RotaLatLng rotaLatLng : latLng) {
			rotaLatLngRepository.save(rotaLatLng);
		}
		return rotaLatLngRepository.findAll();
	}
}
*/