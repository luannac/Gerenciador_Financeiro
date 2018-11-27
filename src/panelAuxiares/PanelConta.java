package panelAuxiares;

import javax.swing.JPanel;

import Tela.FramePrincipal;
import backEnd.Contas.Conta;

public class PanelConta extends JPanel {
	/************************************Atributos*******************************************/
		private Conta conta;
		
	
	/************************************Construtor*******************************************/
		public PanelConta(int x, int y,Conta conta){
			setLayout(null);
			setBackground(FramePrincipal.getColorbackground());
			setSize(x,y);
			
			
		}
}
