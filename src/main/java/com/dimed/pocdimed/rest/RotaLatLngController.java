/*package com.dimed.pocdimed.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dimed.pocdimed.model.RotaLatLng;
import com.dimed.pocdimed.service.RotaLatLngService;

public class RotaLatLngController {
	@Autowired
	RotaLatLngService rotaLatLngService;
	
	public ResponseEntity<Collection<RotaLatLng>> getAll(){
		List<RotaLatLng> rotas = new ArrayList<>();

		rotas.addAll(this.rotaLatLngService.getAll());
        
        return  new ResponseEntity<Collection<RotaLatLng>>(rotas, HttpStatus.OK);
	}
}
*/