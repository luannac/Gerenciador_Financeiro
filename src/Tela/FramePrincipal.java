package Tela;

import javax.swing.*;

import ClassesAuxiliares.JanelaRedimensionada;
import ClassesAuxiliares.ManipulandoData;
import backEnd.BDD;
import backEnd.Usuario;
import panelAuxiares.PanelContas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class FramePrincipal extends JFrame implements JanelaRedimensionada{
	/*************************Atributos Static *************************************/
		private static Color colorbackground = new Color(220, 220, 220);
		private static Color auxBackground = new Color(245, 245, 245);
		private static boolean alterar  = true;
		private static Dimension dimensao; 
		private static int mes= ManipulandoData.pegaMes(),ano=ManipulandoData.pegaAno();
		private static FramePrincipal idFrame;
		private static Usuario idUsuario;
	
	/*********************Atributos************************/
		private JButton botaoHome;
		private JButton botaoBalanco;
		private JButton botaoSimulacao;
		private JButton botaoLogout;
		private JLabel statusNome,statusTime;
		
		private JPanel rodape,panel;
		private JLabel rodapeLabel;
	
	/*********************Configurações Gerais************************/
	public FramePrincipal(int tot,Usuario idUsuario){
		super("Gerenciador Financeiro");
		getContentPane().setBackground(FramePrincipal.colorbackground);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		
		//Ação ao clicar em desligar
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		//Dimensao
		setDimensao(pegarResolucao());
		setSize((dimensao.width -230), (dimensao.height - 60));
		
		//Atribuindo atributos
		idFrame= this;
		this.idUsuario=idUsuario;
		
		//Criando Componentes
		criandoBotoes();
		criandoPartesVisuais();
		criandoRodape();
		
		tempoLogado();
		
	/*****************************Configuração do Panel**********************************/
		trocaPanel(new PanelHome(porWidth(93.5), porHeight(84)));
	}
	/*****************************Configuração dos Botoes************************/
	private void criandoBotoes(){
		//Botao Home
				botaoHome = new JButton("HOME");
				getContentPane().add(botaoHome);
				botaoHome.setVisible(true);
				botaoHome.setBounds(porWidth(3.5), porHeight(2.5), porWidth(10), porHeight(5.5));
				
				botaoHome.setBackground(Color.yellow);
				
				botaoHome.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						trocaPanel(new PanelHome(porWidth(93.5), porHeight(84)));
						botaoHome.setBackground(Color.yellow);
						botaoBalanco.setBackground(Color.white);
						botaoSimulacao.setBackground(Color.white);
					}
				});
			
			//Botao Balanço
				botaoBalanco = new JButton("BALANÇO");
				getContentPane().add(botaoBalanco);
				botaoBalanco.setVisible(true);
				botaoBalanco.setBounds(porWidth(13.4), porHeight(2.5), porWidth(10), porHeight(5.5));
				
				botaoBalanco.setBackground(Color.white);
				
				botaoBalanco.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						trocaPanel(new PanelBalanco(porWidth(93.5), porHeight(84)));
						botaoHome.setBackground(Color.white);
						botaoBalanco.setBackground(Color.orange);
						botaoSimulacao.setBackground(Color.white);
					}
				});
				
			//Botao Simulação
				botaoSimulacao = new JButton("SIMULAÇÂO");
				getContentPane().add(botaoSimulacao);
				botaoSimulacao.setVisible(true);
				botaoSimulacao.setBounds(porWidth(23), porHeight(2.5), porWidth(10), porHeight(5.5));
				
				botaoSimulacao.setBackground(Color.white);
				
				botaoSimulacao.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						trocaPanel(new PanelSimulador());
						botaoHome.setBackground(Color.white);
						botaoBalanco.setBackground(Color.white);
						botaoSimulacao.setBackground(Color.BLUE);
					}
				});
				
			//Botao Logout
				botaoLogout = new JButton("Logout");
				getContentPane().add(botaoLogout);
				botaoLogout.setVisible(true);
				botaoLogout.setBounds(porWidth(87), porHeight(2.5), porWidth(10), porHeight(5.5));
				
				botaoLogout.setBackground(Color.red);
				
				botaoLogout.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						new JanelaAcao().windowClosing();
					}
				});
	}
	
	/*********************************/
	private void criandoPartesVisuais(){

		/*****************************Configuração do Status*********************************/
		statusNome = new JLabel("Usuário: "+BDD.getNome());
		getContentPane().add(statusNome);
		statusNome.setVisible(true);
		statusNome.setBounds(porWidth(76),porHeight(2.5),porWidth(10),porHeight(2.6));
		
		statusTime = new JLabel();
		getContentPane().add(statusTime);
		statusTime.setVisible(true);
		statusTime.setBounds(porWidth(76),porHeight(5),porWidth(12.6),porHeight(2.6));
		
	}
	
	private void criandoRodape(){
	/*****************************Configuração do Rodape**********************************/
		//Panel
		rodape = new JPanel();
		getContentPane().add(rodape);
		rodape.setBackground(Color.gray);
		rodape.setVisible(true);
		rodape.setBounds(porWidth(0), porHeight(93), porWidth(100), porHeight(5));
		
		//Label 
		rodapeLabel = new JLabel("Powered by: Luann Campos",JLabel.RIGHT);
		rodape.add(rodapeLabel);
		rodapeLabel.setForeground(Color.WHITE);
		rodapeLabel.setVisible(true);
		rodapeLabel.setBounds(0, 0, 1700, 44);
	}
	
	/*********************************Ação ao clicar em Logout*************************/
	private class JanelaAcao extends WindowAdapter{
            public void windowClosing(){
                int i=JOptionPane.showConfirmDialog(null, "Deseja Deslogar?");
                if(i==0){
                    setVisible(false);
                    new FrameLogin();
                }
        }
	}
	
	/**********************************Metodo para alterar panel**************************/
	private void trocaPanel(JPanel painel){
		if(FramePrincipal.isAlterar()){
			getContentPane().removeAll();
			
			criandoBotoes();
			criandoPartesVisuais();
			criandoRodape();
			
			getContentPane().add(painel);
			painel.setBounds(porWidth(3.5), porHeight(8), porWidth(93.5), porHeight(84));
			painel.setVisible(true);
			getContentPane().validate();
		}else{
			JOptionPane.showMessageDialog(null, "Você não pode sair da tela até terminar o processo!");
		}
		
	}
	
	//Configuração do Tread
	private void tempoLogado(){
			Thread log = new Thread(new Runnable() {
				@Override
				public void run() {
					int segundo=0,minuto=0,milesimo=0;
					while(true){
						statusTime.setText("Tempo Logado: "+minuto+":"+segundo);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(milesimo==10) {
							if(segundo<60)
								segundo++;
							else{
								segundo=0;
								minuto++;
							}
							milesimo=0;
						}
						else
							milesimo++;
					}
				}
			});
			log.start();
	}
	
	/**********************************Metodo auxiliar**************************************/
	@Override
	public int porWidth(double d){
		return (int) ((d*(dimensao.width -230))/100);
	}
	@Override
	public int porHeight(double d){
		return (int) ((d*(dimensao.height - 60))/100);
	}
	@Override
	public Dimension pegarResolucao() {
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension dimensao = t.getScreenSize();
		return dimensao;
	}
	
	/**********************************Getters e Setters************************************/
	public static Color getColorbackground() {
		return colorbackground;
	}
	public static void setColorbackground(Color colorbackground) {
		FramePrincipal.colorbackground = colorbackground;
	}
	public static Color getAuxBackground() {
		return auxBackground;
	}
	public static void setAuxBackground(Color auxBackground) {
		FramePrincipal.auxBackground = auxBackground;
	}
	public static boolean isAlterar() {
		return alterar;
	}
	public static void setAlterar(boolean alterar) {
		FramePrincipal.alterar = alterar;
	}
	public static Dimension getDimensao() {
		return dimensao;
	}
	public static void setDimensao(Dimension dimensao) {
		FramePrincipal.dimensao = dimensao;
	}
	public static int getMes() {
		return mes;
	}
	public static void setMes(int mes) {
		FramePrincipal.mes = mes;
	}
	public static int getAno() {
		return ano;
	}
	public static void setAno(int ano) {
		FramePrincipal.ano = ano;
	}
	public static FramePrincipal getIdFrame() {
		return idFrame;
	}
	public static Usuario getIdUsuario() {
		return idUsuario;
	}
	public static void setIdUsuario(Usuario idUsuario) {
		FramePrincipal.idUsuario = idUsuario;
	}
	public static void setIdFrame(FramePrincipal idFrame) {
		FramePrincipal.idFrame = idFrame;
	}
}
