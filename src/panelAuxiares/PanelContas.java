package panelAuxiares;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ClassesAuxiliares.JanelaRedimensionada;
import backEnd.Contas.Conta;
import frame_Principal.FramePrincipal;

public class PanelContas extends JPanel implements JanelaRedimensionada{
	/************************************Atributos*******************************************/
	
	/************************************Construtor*******************************************/
		public PanelContas(int x, int y){
			
			
			setLayout(null);
			setBackground(FramePrincipal.getAuxBackground());
			setSize(x,y);
			setPreferredSize(new Dimension(x, FramePrincipal.getIdUsuario().getContas().size()*porHeight(61)));
			
			criandoPanelContas();
		}

	
	/************************************Criação de Contas*******************************************/
		private void criandoPanelContas() {
			PanelConta pConta;
			int i=0;
			for (Conta conta : FramePrincipal.getIdUsuario().getContas()) {
				pConta = new PanelConta(porWidth(100), porHeight(60), conta);
				pConta.setBounds(porWidth(0),i*(porHeight(61)),porWidth(100),porHeight(60));
				pConta.setVisible(true);
				add(pConta);
				i++;
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
