package com.dimed.pocdimed.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RotasOnibus")
public class RotaOnibus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idlinha;
	
	@Column (name = "nome")
	private String nome;
	
	@Column (name = "codigo")
	private String codigo;
	
	@Column (name = "coord")
	@OneToMany(cascade=CascadeType.ALL)
	private List<RotaLatLng> coord;
	
	public RotaOnibus(Integer idlinha, String nome, String codigo, List<RotaLatLng> coord) {
		this.idlinha = idlinha;
		this.nome = nome;
		this.codigo = codigo;
		this.coord = coord;
	}
	
	public RotaOnibus() {}

	public Integer getIdlinha() {
		return idlinha;
	}
	public void setIdlinha(Integer idlinha) {
		this.idlinha = idlinha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setCoord(List<RotaLatLng> coord)
	{
		this.coord = coord;
	}
	
	public List<RotaLatLng> getCoord()
	{
		return coord;
	}
}
