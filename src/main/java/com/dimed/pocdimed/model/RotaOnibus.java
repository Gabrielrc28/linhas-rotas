package com.dimed.pocdimed.model;


public class RotaOnibus {
	private Integer idlinha;
	private String nome;
	private String codigo;
	
	public RotaOnibus(Integer idlinha, String nome, String codigo) {
		this.idlinha = idlinha;
		this.nome = nome;
		this.codigo = codigo;
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
	
}
