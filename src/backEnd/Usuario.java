package backEnd;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.AEADBadTagException;
import javax.swing.JOptionPane;

import ClassesAuxiliares.ManipulandoData;
import backEnd.Contas.Agendadas;
import backEnd.Contas.Conta;
import backEnd.Contas.Movimentacao;
import backEnd.Contas.Tipo;

public class Usuario {
	/***********************************Atributos*******************************************/
		private List<Conta> contas;
		private List<Agendadas> agendadas;
		private ArrayList<Tipo> tipos;
		
	/***********************************Construtor*******************************************/
		public Usuario(){
			try{
				contas = BDD.carregaContas();
			}catch(Exception e){
				contas = new ArrayList<>();
			}
			try{
				agendadas = BDD.carregaAgendadas();
			}catch(Exception e){
				agendadas = new ArrayList<>();
			}
			try{
				tipos = BDD.carregandoTipos();				
			}catch(Exception e){
				tipos = new ArrayList<>();
				e.printStackTrace();
			}
		}
		
	/***********************************Metodos*******************************************/
		public boolean transferencia(Conta transferidor,Conta receptor,float valor){/*****/
			boolean estado =false;
			if(transferidor.getSaldo()>=valor){
				if(receptor.isAtiva()){
					transferidor.transfere(valor);
					receptor.recebe(valor);
					estado=true;
				}else
					JOptionPane.showMessageDialog(null, "Opera��o Frustada\nConta Desativada");
			}else
				JOptionPane.showMessageDialog(null, "Opera��o Frustada\nConta sem saldo");
			return estado;
		}
		public boolean receita(Conta conta,float valor,Tipo tipo,String obs){/************/
			boolean estado =false;
			if(conta.deposita(valor)){
				conta.getEntradasDespesas().add(new Movimentacao( valor, obs, ManipulandoData.dataAtual(), tipo));
				estado=true;
			}
			return estado;
		}
		public boolean despesa(Conta conta,float valor,Tipo tipo,String obs){/************/
			boolean estado =false;
			if(conta.retira(valor)){
				conta.getEntradasDespesas().add(new Movimentacao( valor, obs, ManipulandoData.dataAtual(), tipo));
				estado=true;
			}
			return estado;
		}
		public boolean execucaooAgendada(Conta conta,Agendadas age){/**********************/
			boolean estado =false;
			if(!age.isDespesa()){
				if(conta.recebe(age.getValor())){
					conta.getEntradasDespesas().add(new Movimentacao(age.getValor(), ManipulandoData.dataAtual(), age));
					estado=true;
				}
			}else{
				if(conta.retira(age.getValor())){
					conta.getEntradasDespesas().add(new Movimentacao(age.getValor(), ManipulandoData.dataAtual(), age));
					estado=true;
				}
			}
			return estado;
		}
		
		public boolean criarContaComRenda(int tipo,float saldo,String obs,boolean despesa,boolean por,float val,String agenObs){
			boolean estado=false;
			
			Conta conta = new Conta(BDD.getTotem(), tipo, saldo, obs, true);
			Agendadas agen = new Agendadas(despesa, ManipulandoData.dataAtual(), por, val, agenObs, true);
			
			contas.add(conta);
			agendadas.add(agen);
			
			return estado;
		}
		public boolean criarConta(int tipo,float saldo,String obs){
			boolean estado=false;
			
			Conta conta = new Conta(BDD.getTotem(), tipo, saldo, obs, true);
			
			contas.add(conta);
			
			return estado;
		}
		public ArrayList<Movimentacao> getMovimentacoes(){
			ArrayList<Movimentacao> mov = new ArrayList<>();
			try{
				for (Conta conta : contas) {
					mov.addAll(conta.getEntradasDespesas());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
				
			return mov;
		}
		public Conta pegarContaMov(Movimentacao mov){
			for (Conta conta : contas) {
				if(conta.contaTem(mov))
					return conta;
			}
			return null;
		}
	/***********************************Salvar*******************************************/
		public boolean salvar(){
			boolean estado=false;
			if(salvarContas() && salvarAgendadas())
				estado=true;
			return estado;
		}
		private boolean salvarContas(){
			boolean estado=false;
			for (Conta conta : this.contas) {
				if(conta.getId()==0){
					BDD.insertConta(conta);
					salvarMovimentcao(conta);
					estado=true;
				}else{
					BDD.updateConta(conta);
					salvarMovimentcao(conta);
					estado=true;
				}
			}
			
			return estado;
		}
		private boolean salvarAgendadas(){
			boolean estado=false;
			for (Agendadas agendadas : this.agendadas) {
				if(agendadas.getId()==0){
					BDD.insertAgendada(agendadas);
					estado=true;
				}else
					BDD.updateAgendada(agendadas);
					estado=true;
			}
			return estado;
		}
		private boolean salvarMovimentcao(Conta conta){
			boolean estado=false;
			int idconta= conta.getId();
			
			for (Movimentacao movimentacao : conta.getEntradasDespesas()) {
				if(!movimentacao.isAgendada()){
					if(movimentacao.getId()==0){
						BDD.updateEntrada_Despesa(movimentacao, idconta);
						estado=true;
					}
					else{
						BDD.insertEntrada_Despesa(movimentacao, idconta);
						estado=true;
					}
				}else{
					if(movimentacao.getId()==0){
						BDD.insertAng_ex(movimentacao, idconta);
						estado=true;
					}
					else{
						BDD.updateAng_ex(movimentacao, idconta);
						estado=true;
					}
				}
			}
			return estado;
		}
		
	/***********************************Getters e Setters*******************************************/
		public float getSaldo(){
			float valor=0;
			for (Conta conta : contas) {
				valor+=conta.getSaldo();
			}
			return valor;
		}
		public float getSaldoPositivoMes(byte mes,int ano){
			float valor=0;
			for (Conta conta : contas) {
				for (Movimentacao mov : conta.getEntradasDespesas()) {
					if(mov.getData().getMonth()==mes && mov.getData().getYear() == ano){
						if(mov.isAgendada()){
							if(!mov.getAgen().isDespesa());
								valor+=mov.getValor();
						}else{
							if(!mov.getTipo().isDespesa())
								valor+=mov.getValor();
						}
					}
				}
			}
			return valor;
		}
		public float getSaldoNegativoMes(byte mes,int ano){
			float valor=0;
			for (Conta conta : contas) {
				for (Movimentacao mov : conta.getEntradasDespesas()) {
					if(mov.getData().getMonth()==mes && mov.getData().getYear()==ano){
						if(mov.isAgendada()){
							if(mov.getAgen().isDespesa());
								valor+=mov.getValor();
						}else{
							if(mov.getTipo().isDespesa())
								valor+=mov.getValor();
						}
					}
				}
			}
			return valor;
		}
		public List<Conta> getContas() {
			return contas;
		}
	
		public void setContas(List<Conta> contas) {
			this.contas = contas;
		}
	
		public List<Agendadas> getAgendadas() {
			return agendadas;
		}
	
		public void setAgendadas(List<Agendadas> agendadas) {
			this.agendadas = agendadas;
		}
	
		public ArrayList<Tipo> getTipos() {
			return tipos;
		}
	
		public void setTipos(ArrayList<Tipo> tipos) {
			this.tipos = tipos;
		}
		
	/******************************************************************************/
}
