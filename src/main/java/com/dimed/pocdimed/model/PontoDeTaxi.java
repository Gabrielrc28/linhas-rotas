package com.dimed.pocdimed.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pontosDeTaxi")
public class PontoDeTaxi implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	@Column (name = "nome")
	private String nome;
	@Column (name = "lat")
	private Double lat;
	@Column (name = "lng")
	private Double lng;
	@Column (name = "data")
	private LocalDateTime data;
	
	public PontoDeTaxi(String nome, Double lat, Double lng, LocalDateTime data) {
		this.nome = nome;
		this.lat = lat;
		this.lng = lng;
		this.data = data;
	}
	
	public PontoDeTaxi(){}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
}