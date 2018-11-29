package panelAuxiares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ClassesAuxiliares.JanelaRedimensionada;

public class PanelEditMovimentacao extends JPanel implements JanelaRedimensionada{
/**********************************Atributos***********************************************************/
	private JComboBox jbTipos,jbAgendada;
	private JTextField tfValor,tfData,tfObs; 
	private JRadioButton rbAgendada;
	private JLabel lAgendada;
	
/**********************************Construtor**********************************************************/
	public PanelEditMovimentacao(int x,int y) {
		setLayout(null);
		setVisible(true);
		setBackground(Color.white);
		
		criandoRadioButton();
		criandoJTextField();
		criandoComboBox();
		criandoLabels();
	}
	
/**********************************Metodos De Crianção*************************************************/
	public void criandoRadioButton(){
	//Configurando Label do rbAgendada
		lAgendada = new JLabel("Agendada:");
		add(lAgendada);
		lAgendada.setVisible(true);
		lAgendada.setBounds(porWidth(2), porHeight(2), porWidth(10), porHeight(5));
		
	//Configurando rbAgendada
		rbAgendada = new JRadioButton("oi");
		add(rbAgendada);
		rbAgendada.setVisible(true);
		rbAgendada.setBounds(porWidth(12), porHeight(2), porWidth(5), porHeight(5));
		
		
	}
	public void criandoJTextField(){
		
	}
	public void criandoLabels(){
		
	}
	public void criandoComboBox(){
		
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
