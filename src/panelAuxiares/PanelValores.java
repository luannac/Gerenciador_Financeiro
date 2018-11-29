package panelAuxiares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ClassesAuxiliares.JanelaRedimensionada;
import frame_Principal.FramePrincipal;

public class PanelValores extends JPanel implements JanelaRedimensionada{
/**********************************Atributos************************************************************/
	private JLabel lValor,lLabel;
	
/**********************************Construtor***********************************************************/
	public PanelValores(int x, int y,float valor,String nome,boolean testa) {
		setLayout(null);
		setBackground(FramePrincipal.getAuxBackground());
		setSize(x,y);
		setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)));
		
		criandoLabels();
		lLabel.setText(nome+": ");
		lValor.setText("R$"+valor);
		
		if(testa){
			if(valor>0)
				setBackground(Color.green);
			else
				setBackground(Color.red);
		}
	}
	
/*********************************Metodos Auxiliares****************************************************/
	private void criandoLabels(){	
		//Configuração lSaldo
		lValor = new JLabel();
		add(lValor);
		lValor.setVisible(true);
		lValor.setBounds(porWidth(10), porHeight(45), porWidth(89), porHeight(50));
		
		//Configurando lLabel
		lLabel = new JLabel();
		add(lLabel);
		lLabel.setVisible(true);
		lLabel.setBounds(porWidth(10), porHeight(10), porWidth(60), porHeight(30));
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
