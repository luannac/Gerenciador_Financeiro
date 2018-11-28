package panelAuxiares;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ClassesAuxiliares.JanelaRedimensionada;
import Tela.FramePrincipal;
import backEnd.Contas.Conta;

public class PanelContas extends JPanel implements JanelaRedimensionada{
	/************************************Atributos*******************************************/
		private List<Conta> contas;
	
	/************************************Construtor*******************************************/
		public PanelContas(int x, int y){
			contas= new ArrayList<>();
			contas.addAll(FramePrincipal.getIdUsuario().getContas());
			
			setLayout(null);
			setBackground(FramePrincipal.getAuxBackground());
			setSize(x,y);
			setPreferredSize(new Dimension(x, contas.size()*porHeight(30)));
			
			criandoPanelContas();
		}

	
	/************************************Criação de Contas*******************************************/
		private void criandoPanelContas() {
			PanelConta pConta;
			int i=0;
			for (Conta conta : contas) {
				pConta = new PanelConta(porWidth(100), porHeight(60), conta);
				pConta.setBounds(porWidth(0),i*(porHeight(30)),porWidth(100),porHeight(60));
				pConta.setVisible(true);
				add(pConta);
			}
			
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
