package panelAuxiares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ClassesAuxiliares.JanelaRedimensionada;
import backEnd.BDD;
import backEnd.Contas.Conta;
import frame_Principal.FramePrincipal;

public class PanelEditTransferencia extends JPanel implements JanelaRedimensionada{
	/***********************************Atributos*******************************************/
		private JComboBox<String> cbReceptor,cbTransferidor;
		private JLabel lReceptor,lTransferidor;
		private JTextField tfValor;
		private JLabel lValor;
	
	/***********************************Construtor*******************************************/
		public PanelEditTransferencia (int x,int y) {
			setSize(x,y);
			setLayout(null);
			setVisible(true);
			setBackground(Color.white);
			
			criandoComboBox();
			criandoTextField();
		}
		
	/***********************************Metodos*******************************************/
		private void criandoComboBox(){
			//Label Transferidor
			lTransferidor = new JLabel("Tranferidor:");
			add(lTransferidor);
			lTransferidor.setBounds(porWidth(10), porHeight(15), porWidth(10), porHeight(7));
			
			//ComboBox Transferidor
			cbTransferidor = new JComboBox<>();
			add(cbTransferidor);
			cbTransferidor.setBounds(porWidth(20), porHeight(15), porWidth(20), porHeight(7));
			
			//Label Receptor
			lReceptor = new JLabel("Receptor:");
			add(lReceptor);
			lReceptor.setBounds(porWidth(58), porHeight(15), porWidth(20), porHeight(7));
			
			//ComboBox Receptor
			cbReceptor = new JComboBox<>();
			add(cbReceptor);
			cbReceptor.setBounds(porWidth(63), porHeight(15), porWidth(20), porHeight(7));
			
			
			//Preenchendo ComboBox
			for (Conta conta : FramePrincipal.getIdUsuario().getContas()) {
				cbTransferidor.addItem(conta.getObs()+"/"+BDD.tipoConta(conta.getIdTipo()));
				cbReceptor.addItem(conta.getObs()+"/"+BDD.tipoConta(conta.getIdTipo()));
			}
		}
		private void criandoTextField(){
			//Label lValor
			lValor = new JLabel("Valor:");
			add(lValor);
			lValor.setBounds(porWidth(10), porHeight(40), porWidth(10), porHeight(7));
			
			//TextField VAlor
			tfValor = new JTextField();
			add(tfValor);
			tfValor.setBounds(porWidth(20), porHeight(40), porWidth(20), porHeight(7));
		}
		
	/******************************************************************************/
	/**********************************Metodos da JanelaRedimencionada**************************************/
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
