package panelAuxiares;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ClassesAuxiliares.JanelaRedimensionada;
import Tela.FramePrincipal;
import backEnd.BDD;
import backEnd.Contas.Conta;

public class PanelConta extends JPanel implements JanelaRedimensionada {
	/************************************Atributos*******************************************/
		private Conta conta;
		private JLabel lIidentificacao,lTipo,lSaldo;
		private JButton bEdit;
	
	/************************************Construtor*******************************************/
		public PanelConta(int x, int y,Conta conta){
			this.conta = conta;
			
			setLayout(null);
			setBackground(FramePrincipal.getColorbackground());
			setSize(x,y);
			
			criandoLabels();
			criandoButton();
			
		}
	
	/************************************Metodos de Criação**********************************/
		private void criandoLabels(){
		/********************************Label do Tipo de Conta******************************/
			lTipo = new JLabel("Tipo: "+BDD.tipoConta(conta.getIdTipo()));
			lTipo.setVisible(true);
			lTipo.setBounds(porWidth(10), porHeight(10), porWidth(30), porHeight(30));
			add(lTipo);
			
		/********************************Label de Identificaçao******************************/
			lIidentificacao = new JLabel("Identificação: "+conta.getObs());
			lIidentificacao.setVisible(true);
			lIidentificacao.setBounds(porWidth(10), porHeight(40), porWidth(30), porHeight(30));
			add(lIidentificacao);
			
		/********************************Label de Saldo**************************************/
			lSaldo = new JLabel("Saldo: "+conta.getSaldo());
			lSaldo.setVisible(true);
			lSaldo.setBounds(porWidth(55), porHeight(30), porWidth(30), porHeight(30));
			add(lSaldo);
			
		}
		private void criandoButton(){
			
		}
		
	/************************************Metodos da Interface JanelaRedimensionada************/
		@Override
		public int porWidth(double d){
			return (int) ((d*(this.getWidth()))/100);
		}
		@Override
		public int porHeight(double d){
			return (int) ((d*(this.getHeight()))/100);
		}
		@Override
		public Dimension pegarResolucao() {
			Toolkit t = Toolkit.getDefaultToolkit();
			Dimension dimensao = t.getScreenSize();
			return dimensao;
		}
	
}
