package panelAuxiares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ClassesAuxiliares.JanelaRedimensionada;
import backEnd.BDD;
import backEnd.Contas.Conta;
import backEnd.Contas.Movimentacao;
import frame_Principal.FramePrincipal;

public class PanelMovimentacao extends JPanel implements JanelaRedimensionada {
/************************************Atributo*********************************************/
	private JTextArea lValor,lConta,lObs,lData,lTipo;
	private Movimentacao mov;
	private Conta conta;
	
/************************************Construtor*******************************************/
		public PanelMovimentacao(int x,int y,Movimentacao mov,Conta conta) {
			setLayout(null);
			setBackground(Color.white);
			setSize(x,y);
			
			this.mov=mov;
			this.conta=conta;
			
			criandoLabels();
			definindoLabels();
		}

/************************************Criando Labels****************************************/
		private void criandoLabels(){
			//Tipo
				lTipo = new JTextArea();
				lTipo.setVisible(true);
				lTipo.setBounds(porWidth(2), porHeight(30), porWidth(20), porHeight(80));
				add(lTipo);
				
			//OBS
				lObs = new JTextArea();
				lObs.setVisible(true);
				lObs.setBounds(porWidth(22), porHeight(30), porWidth(20), porHeight(80));
				add(lObs);
				
			//Conta
				lConta = new JTextArea();
				lConta.setVisible(true);
				lConta.setBounds(porWidth(42), porHeight(30), porWidth(20), porHeight(80));
				add(lConta);
				
			//lValor
				lValor = new JTextArea();
				lValor.setVisible(true);
				lValor.setBounds(porWidth(62), porHeight(30), porWidth(10), porHeight(80));
				add(lValor);
				
			//Data
				lData = new JTextArea();
				lData.setVisible(true);
				lData.setBounds(porWidth(72), porHeight(30), porWidth(10), porHeight(80));
				add(lData);
		}
		private void definindoLabels(){
			//Tipo
				if(mov.isAgendada()){
					lTipo.setText(mov.getAgen().getObs());
				}else{
					lTipo.setText(BDD.categoriaTipo(mov.getTipo().getId()).getNome());
				}
				
			//OBS
				if(mov.isAgendada()){
					lObs.setText(mov.getAgen().getObs());
				}else{
					lObs.setText(mov.getObs());
				}
				
			//Conta
				lConta.setText(BDD.tipoConta(conta.getIdTipo())+"\\"+conta.getObs());
				
			//Valor
				lValor.setText(String.valueOf(mov.getValor()));
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
