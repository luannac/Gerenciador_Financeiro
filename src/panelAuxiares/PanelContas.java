package panelAuxiares;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import Tela.FramePrincipal;
import backEnd.Contas.Conta;

public class PanelContas extends JScrollPane{
	/************************************Atributos*******************************************/
		private List<Conta> contas;
	
	/************************************Construtor*******************************************/
		public PanelContas(int x, int y){
			contas = FramePrincipal.getIdUsuario().getContas();
			
			setLayout(null);
			setBackground(FramePrincipal.getAuxBackground());
			setSize(x,y);
			
			criandoPanelContas();
		}

	
	/************************************Criação de Contas*******************************************/
		private void criandoPanelContas() {
			for (Conta conta : contas) {
				
			}
			
		}
	

}
