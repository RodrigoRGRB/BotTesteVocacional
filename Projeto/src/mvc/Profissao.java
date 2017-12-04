package mvc;

import java.util.ArrayList;

import mvc.Local;

public class Profissao {
	String descricao;
	ArrayList<Local> local;
	ArrayList<Habilidades> habilidades;

	  public Profissao() {  //construtor
	    this.descricao = "";
	    this.local = new ArrayList();
	    this.habilidades = new ArrayList();
	  }
	  // setters e getters aqui

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Local> getLocal() {
		return local;
	}

	public void setLocal(ArrayList<Local> local) {
		this.local = local;
	}
	
	public ArrayList<Habilidades> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(ArrayList<Habilidades> habilidades) {
		this.habilidades = habilidades;
	}

}
