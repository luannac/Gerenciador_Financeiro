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
			contas = BDD.carregaContas();
			agendadas = BDD.carregaAgendadas();
			tipos = BDD.carregandoTipos();
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
					JOptionPane.showMessageDialog(null, "Operação Frustada\nConta Desativada");
			}else
				JOptionPane.showMessageDialog(null, "Operação Frustada\nConta sem saldo");
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
		public boolean execuçãoAgendada(Conta conta,Agendadas age){/**********************/
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
		
	/***********************************Salvar*******************************************/
		public boolean salvar(){
			boolean estado=false;
			salvarContas();
			salvarAgendadas();
			return estado;
		}
		private boolean salvarContas(){
			boolean estado=false;
			for (Conta conta : this.contas) {
				if(conta.getId()==0){
					BDD.insertConta(conta);
					salvarMovimentcao(conta);
				}else{
					BDD.updateConta(conta);
					salvarMovimentcao(conta);
				}
			}
			
			return estado;
		}
		private boolean salvarAgendadas(){
			boolean estado=false;
			for (Agendadas agendadas : this.agendadas) {
				if(agendadas.getId()==0){
					BDD.insertAgendada(agendadas);
				}else
					BDD.updateAgendada(agendadas);
			}
			return estado;
		}
		private boolean salvarMovimentcao(Conta conta){
			boolean estado=false;
			int idconta= conta.getId();
			ArrayList<Movimentacao> mov = conta.getEntradasDespesas();
			
			for (Movimentacao movimentacao : mov) {
				if(movimentacao.getTipo()!=null){
					if(movimentacao.getTipo().getId()==0){
						BDD.insertEntrada_Despesa(movimentacao, idconta);
						estado=true;
					}
					else{
						BDD.updateEntrada_Despesa(movimentacao, idconta);
						estado=true;
					}
				}
				if(movimentacao.getAgen()!=null){
					if(movimentacao.getAgen().getId()==0){
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
