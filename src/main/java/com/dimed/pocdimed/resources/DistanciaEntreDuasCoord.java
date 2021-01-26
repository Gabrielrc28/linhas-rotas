package com.dimed.pocdimed.resources;

public class DistanciaEntreDuasCoord {	
	public Boolean calcula (Double lat1, Double lng1, Double lat2, Double lng2, Double dist)
	{
		Double aux;
		
		aux = ((lng2 - lng1) * (lng2 - lng1)) + ((lat2 - lat1) * (lat2 - lat1));
		aux = Math.sqrt(aux);
		System.out.println(aux + "KKKKKKKKKKKKKKKKKKKKKKKKKKKK");
		if(aux <= dist)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
