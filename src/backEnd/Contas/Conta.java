package backEnd.Contas;

import java.util.ArrayList;
import java.util.List;

import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.swing.JOptionPane;

import backEnd.BDD;

public class Conta {
	/***********************************Atributos*******************************************/
	private int id,idLogin,idTipo;
	private float saldo;
	private String tipoConta,obs;
	private ArrayList<Movimentacao> entradasDespesas;
	private boolean ativa;
	
/***********************************Construtor*******************************************/	
	public Conta(int modId,int modIdLogin,int modIdTipo,float modSaldo,String modObs,boolean ativa){//Ao puxar Do Banco
		id =modId;
		idLogin = modIdLogin;
		idTipo = modIdTipo;
		saldo = modSaldo;
		tipoConta = BDD.tipoConta(idTipo);
		obs = modObs;
		this.ativa=ativa;
		
		entradasDespesas = new ArrayList<>();
		entradasDespesas.addAll(BDD.entradasEDespesas(id));
		entradasDespesas.addAll(BDD.ang_Exe(id));
	}
	public Conta(int modIdLogin,int modIdTipo,float modSaldo,String modObs,boolean ativa){//Ao Criar conta no Programa
		id =0;
		idLogin = modIdLogin;
		idTipo = modIdTipo;
		saldo = modSaldo;
		tipoConta = BDD.tipoConta(idTipo);
		obs = modObs;
		this.ativa=ativa;
	}
	
/***********************************Metodos*******************************************/
	public boolean retira(float valor){
		boolean estado =false;
		if(getSaldo()>=valor){
			if(isAtiva()){
				setSaldo(getSaldo()-valor);
				estado=true;
			}else
				JOptionPane.showMessageDialog(null, "Operação Frustada\nConta Desativada");
		}else
			JOptionPane.showMessageDialog(null, "Operação Frustada\nConta sem saldo");
		return estado;
	}
	public boolean deposita(float valor){
		boolean estado =false;
		if(isAtiva()){
			setSaldo(getSaldo()+valor);
			estado=true;
		}else
			JOptionPane.showMessageDialog(null, "Operação Frustada\nConta Desativada");

		return estado;
	}
	public boolean transfere(float valor){
		boolean estado =false;
		if(getSaldo()>=valor&&isAtiva()){
			setSaldo(getSaldo()-valor);
			estado=true;
		}
		return estado;
	}
	public boolean recebe(float valor){
		boolean estado =false;
		if(isAtiva()){
			setSaldo(getSaldo()+valor);
			estado=true;
		}
		return estado;
	}
	public boolean desativar(){
		boolean estado =false;
		if(saldo==0){
			if(isAtiva()){
				setAtiva(false);
				estado=true;
			}else
				JOptionPane.showMessageDialog(null, "Operação Frustada\nConta Já esta desativada!");
		}else
			JOptionPane.showMessageDialog(null, "Operação Frustada\nSaldo deve ser igual a zero para Desativar!");
		return estado;
	}
	public boolean ativar(){
		boolean estado =false;
			if(!isAtiva()){
				setAtiva(false);
				estado=true;
			}else
				JOptionPane.showMessageDialog(null, "Operação Frustada\nConta Já esta ativa!");

		return estado;
	}
	
/***********************************Metodos Getters e Setters*************************/
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdLogin() {
		return idLogin;
	}
	
	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}
	
	public int getIdTipo() {
		return idTipo;
	}
	
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	
	public float getSaldo() {
		return saldo;
	}
	
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public String getTipoConta() {
		return tipoConta;
	}
	
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public String getObs() {
		return obs;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public ArrayList<Movimentacao> getEntradasDespesas() {
		return entradasDespesas;
	}
	
	public void setEntradasDespesas(ArrayList<Movimentacao> entradasDespesas) {
		this.entradasDespesas = entradasDespesas;
	}
/******************************************************************************/

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	
}
