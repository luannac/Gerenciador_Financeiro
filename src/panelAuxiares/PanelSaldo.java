package panelAuxiares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ClassesAuxiliares.JanelaRedimensionada;
import Tela.FramePrincipal;

public class PanelSaldo extends JPanel implements JanelaRedimensionada{
/**********************************Atributos************************************************************/
	private JLabel lSaldo;
	
/**********************************Construtor***********************************************************/
	public PanelSaldo(int x, int y,float saldo) {
		setLayout(null);
		setBackground(FramePrincipal.getAuxBackground());
		setSize(x,y);
		setBackground(Color.green);
		setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saldo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 15), Color.BLACK));
		
		criandoPanels();
		lSaldo.setText("R$"+saldo);
	}
	
/*********************************Metodos Auxiliares****************************************************/
	private void criandoPanels(){	
		//Configuração lSaldo
		lSaldo = new JLabel();
		add(lSaldo);
		lSaldo.setVisible(true);
		lSaldo.setBounds(porWidth(10), porHeight(10), porWidth(80), porHeight(99));
		
	}
	
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
