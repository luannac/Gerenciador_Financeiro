package panelAuxiares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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
	private JLabel lTipos,lAgendada,lValor,lData;
	
	private String[] opTipos;
	
/**********************************Construtor**********************************************************/
	public PanelEditMovimentacao(int x,int y) {
		setSize(x,y);
		setLayout(null);
		setVisible(true);
		setBackground(Color.white);
		
		opTipos = new String[10];
		opTipos[0] = "Nada selecionado";
		opTipos[1] = "Agendada";
		
		criandoJTextField();
		criandoComboBox();
		criandoLabels();
	}
	
/**********************************Metodos De Crian��o*************************************************/
	public void criandoLabels(){
		//Configurando Label do lTipos
		lTipos = new JLabel("Tipo:");
		add(lTipos);
		lTipos.setVisible(true);
		lTipos.setBounds(porWidth(2), porHeight(2), porWidth(8), porHeight(7));
		
		//Configurando Label lAgendada
		lAgendada = new JLabel("Agendada:");
		add(lAgendada);
		lAgendada.setVisible(false);
		lAgendada.setBounds(porWidth(38), porHeight(2), porWidth(15), porHeight(7));
		
		//Configurando Label a Valor
		lValor = new JLabel("Valor:");
		add(lValor);
		lValor.setVisible(true);
		lValor.setBounds(porWidth(2), porHeight(12), porWidth(7), porHeight(7));
		
		//Configurando label Data
		lData = new JLabel("Data:");
		add(lData);
		lData.setVisible(true);
		lData.setBounds(porWidth(2), porHeight(21), porWidth(7), porHeight(7));
		
	}
	public void criandoJTextField(){
		//Configurando JTValor
		tfValor = new JTextField();
		add(tfValor);
		tfValor.setVisible(true);
		tfValor.setBounds(porWidth(11), porHeight(12), porWidth(15), porHeight(7));
		
		//Configurando jtData
		tfData = new JTextField();
		add(tfData);
		tfData.setVisible(true);
		tfData.setBounds(porWidth(11), porHeight(21), porWidth(15), porHeight(7));
	}
	public void criandoComboBox(){
		
		//Agendadas
		jbAgendada = new JComboBox<String>();
		add(jbAgendada);
		jbAgendada.setVisible(false);
		jbAgendada.setBounds(porWidth(52), porHeight(2), porWidth(20), porHeight(7));
		
		//Tipo
		
		jbTipos = new JComboBox<String>(opTipos);
		add(jbTipos);
		jbTipos.setVisible(true);
		jbTipos.setBounds(porWidth(10), porHeight(2), porWidth(20), porHeight(7));
		
		jbTipos.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// 
				if (e.getStateChange() == ItemEvent.SELECTED) {
					//pegando o texto do item selecionado
					String valorSelecionado = e.getItem().toString();
					if(valorSelecionado.equals("Agendada")){
						//altere aqui quando ativo selecionado
						jbAgendada.setVisible(true);
						lAgendada.setVisible(true);
					}else{
						//altere aqui quando inativo selecionado
						jbAgendada.setVisible(false);
						lAgendada.setVisible(false);
					}
					
				}
			} });
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
