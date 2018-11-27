package backEnd.Contas;

import java.util.Date;

public class Movimentacao {
/***********************************Atributos*******************************************/
	private int id = 0;
	private float valor = 0;
	private String obs = null;
	private Date data = null;
	private Categoria tipo = null;
	private Categoria agen = null;
	
/***********************************Construtor*******************************************/
	public Movimentacao(int id, float valor, String obs, Date data, Categoria tipo, Categoria agen) {
		super();
		this.id = id;
		this.valor = valor;
		this.obs = obs;
		this.data = data;
		this.tipo = tipo;
		this.agen = agen;
	}
	public Movimentacao(float valor,Date data,Agendadas agen) {
		super();
		this.valor = valor;
		this.obs = obs;
		this.data = data;
		this.agen = agen;
	}
	public Movimentacao(float valor, String obs, Date data, Tipo tipo) {
		super();
		this.valor = valor;
		this.obs = obs;
		this.data = data;
		this.tipo = tipo;
	}
	
/***********************************Metodos Getters e Setters*************************/
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public String getObs() {
		return obs;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Categoria getTipo() {
		return tipo;
	}
	
	public void setTipo(Categoria tipo) {
		this.tipo = tipo;
	}
	
	public Categoria getAgen() {
		return agen;
	}
	
	public void setAgen(Categoria agen) {
		this.agen = agen;
}	
}
