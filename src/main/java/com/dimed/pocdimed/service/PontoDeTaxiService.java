package com.dimed.pocdimed.service;

import java.io.IOException;
import java.util.List;

import com.dimed.pocdimed.model.PontoDeTaxi;

public interface PontoDeTaxiService {
	
	List<PontoDeTaxi> getAllPontosTaxi() throws IOException;
	
	PontoDeTaxi getPontoDeTaxiByName (String nome) throws IOException;
	
	void insertPontoDeTaxi (PontoDeTaxi pontoDeTaxi) throws IOException;
}
