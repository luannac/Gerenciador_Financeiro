package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ClassesAuxiliares.JanelaRedimensionada;
import panelAuxiares.PanelEditMovimentacao;

public class FrameMovimentacao extends JFrame implements JanelaRedimensionada {
/**********************************Atributos************************************************************/
	//Menu
	private JLabel lRefenceRB;
	private ButtonGroup radioGroup;
	private JRadioButton rbReceita,rbDespesa,rbTransferencia;
	
	//conteudo
	private JPanel panel;
	private int tipo;
	
	//Inferior
	private JButton bSalvar,bCancelar;
	
/**********************************Construtores*********************************************************/
	public FrameMovimentacao(int tipo) {
		super("Movimentação");
		setSize((pegarResolucao().width -700), (pegarResolucao().height - 300));
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		this.tipo = tipo;
		
		criandoRadioMenu();
		configurandoPanel();
		criandoBotoes();
		
	}
	
/**********************************Metodos de CriaÃ§Ã£o***************************************************/
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
			rbReceita.setBounds(porWidth(33), porHeight(5), porWidth(12), porHeight(4));
			
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
			
		if(tipo==1)
			rbReceita.setSelected(true);
		if(tipo==2)
			rbDespesa.setSelected(true);
		if(tipo==3)
			rbTransferencia.setSelected(true);

	}
	private void configurandoPanel(){/******************************************************************/
		panel = new PanelEditMovimentacao(porWidth(95), porHeight(75),tipo);
		getContentPane().add(panel);
		
		panel.setBounds(porWidth(3), porHeight(10), porWidth(95), porHeight(75));
		
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	private void criandoBotoes(){/**********************************************************************/
		//Criando Botao Salvar
		bSalvar = new JButton("Salvar");
		getContentPane().add(bSalvar);
		bSalvar.setVisible(true);
		bSalvar.setBounds(porWidth(25), porHeight(88), porWidth(15), porHeight(5));
		
		//Criando Botao Cancelar
		bCancelar = new JButton("Cancelar");
		getContentPane().add(bCancelar);
		bCancelar.setVisible(true);
		bCancelar.setBounds(porWidth(45), porHeight(88), porWidth(15), porHeight(5));
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
