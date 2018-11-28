package frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ClassesAuxiliares.JanelaRedimensionada;

public class FrameMovimentacao extends JFrame implements JanelaRedimensionada {
/**********************************Atributos************************************************************/
	private JLabel lRefenceRB;
	private ButtonGroup radioGroup;
	private JRadioButton rbReceita,rbDespesa,rbTransferencia;
	
	private JPanel panel;
	
	private JButton bSalvar,bCancelar;
	
/**********************************Construtores*********************************************************/
	public FrameMovimentacao() {
		super("Movimentação");
		setSize((pegarResolucao().width -700), (pegarResolucao().height - 300));
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		criandoRadioMenu();
	}
	
/**********************************Metodos de Criação***************************************************/
	private void criandoRadioMenu(){
		//Label Informativo*****************************************************************************/
			lRefenceRB = new JLabel("Você está Cadastrando uma nova:");
			getContentPane().add(lRefenceRB);
			lRefenceRB.setVisible(true);
			lRefenceRB.setBounds(porWidth(3), porHeight(5), porWidth(33), porHeight(4));
		
		//Radio Button Receita**************************************************************************/
			rbReceita = new JRadioButton("Receita");
			getContentPane().add(rbReceita);
			rbReceita.setVisible(true);
			rbReceita.setBounds(porWidth(33), porHeight(5), porWidth(15), porHeight(4));
			
		//Radio Button Despesa***************************************************************************/
			rbDespesa = new JRadioButton("Despesa");
			getContentPane().add(rbDespesa);
			rbDespesa.setVisible(true);
			rbDespesa.setBounds(porWidth(47), porHeight(5), porWidth(15), porHeight(4));
			
		//Radio Button Transferencia*********************************************************************/
			rbTransferencia = new JRadioButton("Transferencia");
			getContentPane().add(rbTransferencia);
			rbTransferencia.setVisible(true);
			rbTransferencia.setBounds(porWidth(62), porHeight(5), porWidth(15), porHeight(4));
			
		//Configurando Grupo de Radio Button*************************************************************/
			radioGroup =  new ButtonGroup();
			radioGroup.add(rbReceita);
			radioGroup.add(rbDespesa);
			radioGroup.add(rbTransferencia);

	}
	private void configurandoPanel(){/******************************************************************/
		
	}
	private void criandoBotoes(){/**********************************************************************/
		//Criando Botao Salvar
		
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
