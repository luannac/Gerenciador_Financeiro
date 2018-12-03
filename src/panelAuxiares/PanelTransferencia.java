package panelAuxiares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ClassesAuxiliares.JanelaRedimensionada;
import backEnd.Contas.Tipo;
import frame_Principal.FramePrincipal;

public class PanelTransferencia extends JPanel implements JanelaRedimensionada{
/**********************************Atributos***********************************************************/
	private JComboBox<String> jbTipos,jbAgendada;
	private JTextField tfValor,tfData,tfObs; 
	private JLabel lTipos,lAgendada,lValor,lData,lObs;
	
	private int tipo;
	
/**********************************Construtor**********************************************************/
	public PanelEditMovimentacao(int x,int y,int tipo) {
		setSize(x,y);
		setLayout(null);
		setVisible(true);
		setBackground(Color.white);
		this.tipo=tipo;
		
		criandoJTextField();
		criandoComboBox();
		criandoLabels();
	}
	
/**********************************Metodos De Crianï¿½ï¿½o*************************************************/
	public void criandoLabels(){
		//Configurando Label do lTipos
		lTipos = new JLabel("Tipo:");
		add(lTipos);
		lTipos.setVisible(true);
		lTipos.setBounds(porWidth(2), porHeight(5), porWidth(8), porHeight(7));
		
		//Configurando Label lAgendada
		lAgendada = new JLabel("Agendada:");
		add(lAgendada);
		lAgendada.setVisible(false);
		lAgendada.setBounds(porWidth(38), porHeight(5), porWidth(15), porHeight(7));
		
		//Configurando Label a Valor
		lValor = new JLabel("Valor:");
		add(lValor);
		lValor.setVisible(true);
		lValor.setBounds(porWidth(2), porHeight(18), porWidth(7), porHeight(7));
		
		//Configurando label Data
		lData = new JLabel("Data:");
		add(lData);
		lData.setVisible(true);
		lData.setBounds(porWidth(2), porHeight(30), porWidth(7), porHeight(7));
		
		//Configurando label Obs
		lObs = new JLabel("Descrição: ");
		add(lObs);
		lObs.setVisible(true);
		lObs.setBounds(porWidth(2), porHeight(50), porWidth(7), porHeight(7));
		
	}
	public void criandoJTextField(){
		//Configurando JTValor
		tfValor = new JTextField();
		add(tfValor);
		tfValor.setVisible(true);
		tfValor.setBounds(porWidth(11), porHeight(18), porWidth(15), porHeight(7));
		
		//Configurando jtData
		tfData = new JTextField();
		add(tfData);
		tfData.setVisible(true);
		tfData.setBounds(porWidth(11), porHeight(30), porWidth(15), porHeight(7));
		
		//Configurando jtObs
		tfObs = new JTextField();
		add(tfObs);
		tfObs.setVisible(true);
		tfObs.setBounds(porWidth(11), porHeight(50), porWidth(15), porHeight(7));
	}
	public void criandoComboBox(){
		
		//Agendadas
		jbAgendada = new JComboBox();
		add(jbAgendada);
		jbAgendada.setVisible(false);
		jbAgendada.setBounds(porWidth(52), porHeight(5), porWidth(20), porHeight(7));
		
		//Tipo
		jbTipos = new JComboBox<String>();
		add(jbTipos);
		jbTipos.setVisible(true);
		jbTipos.setBounds(porWidth(10), porHeight(5), porWidth(20), porHeight(7));
		
		preenchendoTipos();
		
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
	private void preenchendoTipos(){
		jbTipos.addItem("Nada selecionado");
		jbTipos.addItem("Agendada");
		
		for (Tipo tipos : FramePrincipal.getIdUsuario().getTipos()) {
			if(tipo==1){
				if(!tipos.isDespesa())
					jbTipos.addItem(tipos.getNome());
			}else{
				if(tipos.isDespesa())
					jbTipos.addItem(tipos.getNome());
			}
		}
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
