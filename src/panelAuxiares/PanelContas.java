package panelAuxiares;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import Tela.FramePrincipal;
import backEnd.Contas.Conta;

public class PanelContas extends JScrollPane{
	/************************************Atributos*******************************************/
		
	
	/************************************Construtor*******************************************/
		public PanelContas(int x, int y,ArrayList<Conta> contas){
			setLayout(null);
			setBackground(FramePrincipal.getAuxBackground());
			setSize(x,y);
			
			criandoPanelContas();
		}

	
	/************************************Criação de Contas*******************************************/
		private void criandoPanelContas() {
			
			
		}
	

}
