package com.dimed.pocdimed.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimed.pocdimed.model.PontoDeTaxi;
import com.dimed.pocdimed.repository.PontoDeTaxiRepository;

@Service
public class PontoDeTaxiServiceImpl implements PontoDeTaxiService {

	@Autowired
	PontoDeTaxiRepository pontoDeTaxiRepository;
	
	@Override
	public List<PontoDeTaxi> getAllPontosTaxi() throws IOException
	{
		String path = ".\\pontosDeTaxi.txt";
		File file = new File(path);
		
		if(!file.exists())
		{
			file.createNewFile();
		}
		
		FileReader reader = new FileReader(path);
		
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		PontoDeTaxi ponto = new PontoDeTaxi();
		
		List<PontoDeTaxi> pontos = new ArrayList<>();
		
		String aux = "";
		String[] arrOfStr;
		
		while(aux != null)
		{
			aux = bufferedReader.readLine();
			if(aux != null) {
				arrOfStr = aux.split("#");
				ponto.setNome(arrOfStr[0]);
				ponto.setLat(Double.parseDouble(arrOfStr[1]));
				ponto.setLng(Double.parseDouble(arrOfStr[2]));
				ponto.setData(LocalDateTime.parse(arrOfStr[3]));
				pontos.add(ponto);
			}
		}
		
		reader.close();
		//pontoDeTaxiRepository.saveAll(pontos);
		return pontos;
	}
	
	@Override
	public PontoDeTaxi getPontoDeTaxiByName (String nome) throws IOException{
		
		List<PontoDeTaxi> pontosDeTaxi = getAllPontosTaxi();
		PontoDeTaxi pontoDeTaxi = new PontoDeTaxi();
		
		for(PontoDeTaxi aux : pontosDeTaxi)
		{
			if(aux.getNome().equals(nome))
			{
				pontoDeTaxi =  aux;
			}
		}
		return pontoDeTaxi;
	}
	
	@Override
	public void insertPontoDeTaxi (PontoDeTaxi pontoDeTaxi) throws IOException {
		FileWriter fileWriter = new FileWriter(".\\\\pontosDeTaxi.tx");
		
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		printWriter.print((pontoDeTaxi.getNome() + "#").toUpperCase());
		printWriter.print(pontoDeTaxi.getLat() + "#");
		printWriter.print(pontoDeTaxi.getLng() + "#");
		printWriter.println(pontoDeTaxi.getData());
		
		printWriter.flush();
		fileWriter.close();
		
		pontoDeTaxiRepository.save(pontoDeTaxi);
	}
}
