package panelAuxiares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
	private JLabel lValor,lConta,lObs,lData,lTipo;
	private JButton bEditar;
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
			criandoButtons();
			
			if(mov.isAgendada()){
				if(mov.getAgen().isDespesa())
					setBackground(new Color(255, 90, 71));
				else
					setBackground(new Color(152, 251, 152));
			}else{
				if(mov.getTipo().isDespesa())
					setBackground(new Color(255, 90, 71));
				else
					setBackground(new Color(152, 251, 152));
			}
		}

/************************************Criando Labels****************************************/
		private void criandoLabels(){
			//Tipo
				lTipo = new JLabel();
				lTipo.setVisible(true);
				lTipo.setBounds(porWidth(2), porHeight(30), porWidth(20), porHeight(80));
				add(lTipo);
				
			//OBS
				lObs = new JLabel();
				lObs.setVisible(true);
				lObs.setBounds(porWidth(22), porHeight(30), porWidth(20), porHeight(80));
				add(lObs);
				
			//Conta
				lConta = new JLabel();
				lConta.setVisible(true);
				lConta.setBounds(porWidth(42), porHeight(30), porWidth(20), porHeight(80));
				add(lConta);
				
			//lValor
				lValor = new JLabel();
				lValor.setVisible(true);
				lValor.setBounds(porWidth(62), porHeight(30), porWidth(10), porHeight(80));
				add(lValor);
				
			//Data
				lData = new JLabel();
				lData.setVisible(true);
				lData.setBounds(porWidth(72), porHeight(30), porWidth(10), porHeight(80));
				add(lData);
		}
		private void criandoButtons(){
			bEditar = new JButton("Editar");
			add(bEditar);
			bEditar.setVisible(true);
			bEditar.setBounds(porWidth(83), porHeight(10), porWidth(13), porHeight(80));
			
			bEditar.setForeground(Color.white);
			bEditar.setBackground(Color.gray);
			bEditar.setBorder(null);
			
		}
		private void definindoLabels(){
			//Tipo
				if(mov.isAgendada()){
					lTipo.setText("Agendada");
				}else{
					lTipo.setText("Tipo: "+BDD.categoriaTipo(mov.getTipo().getId()).getNome());
				}
				
			//OBS
				if(mov.isAgendada()){
					lObs.setText("identificação : "+mov.getAgen().getObs());
				}else{
					lObs.setText("identificação: "+mov.getObs());
				}
				
			//Conta
				lConta.setText(BDD.tipoConta(conta.getIdTipo())+"\\"+conta.getObs());
				
			//Valor
				lValor.setText(String.valueOf("Valor: "+mov.getValor()));
				
			//Data 
				lData.setText(String.valueOf(mov.getData()));
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
