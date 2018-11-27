package backEnd.Contas;

import java.util.Date;

public class Agendadas extends Categoria{
/***********************************Atributos************************************/
	private Date dataCriacao;
	private boolean porcentagem;
	private float valor;
	private String obs;
	private boolean ativa;
	
/************************************Construtor***********************************/
	public Agendadas(int id,boolean despesa,Date data,boolean por,float val,String obs,boolean ativa){
		this.id = id;
		this.despesa = despesa;
		this.dataCriacao=data;
		this.porcentagem=por;
		this.valor=val;
		this.obs=obs;
		this.ativa=ativa;
	}
	public Agendadas(boolean despesa,Date data,boolean por,float val,String obs,boolean ativa){
		this.id = 0;
		this.despesa = despesa;
		this.dataCriacao=data;
		this.porcentagem=por;
		this.valor=val;
		this.obs=obs;
		this.ativa=ativa;
	}

/************************************Getters e Setters***********************************/
public Date getDataCriacao() {
	return dataCriacao;
}

public void setDataCriacao(Date dataCriacao) {
	this.dataCriacao = dataCriacao;
}

public boolean isPorcentagem() {
	return porcentagem;
}

public void setPorcentagem(boolean porcentagem) {
	this.porcentagem = porcentagem;
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

public boolean isAtiva() {
	return ativa;
}

public void setAtiva(boolean ativa) {
	this.ativa = ativa;
}
	
	
}
