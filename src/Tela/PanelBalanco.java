package Tela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ClassesAuxiliares.JanelaRedimensionada;
import ClassesAuxiliares.ManipulandoData;

public class PanelBalanco extends JPanel implements JanelaRedimensionada,ActionListener{
	/******************************Atributos********************************/
	private JButton botaoReceita,botaoDespesa,botaoTransferencia,botaoNewConta;
	private JButton botaoSetaEsq,botaoSetaDir,botaoMesAtual;
	private JLabel labelMes;

/******************************Construtor********************************/
	public PanelBalanco(int x, int y){
	/****************************Configurações Gerais**************************/
		setLayout(null);
		setBackground(FramePrincipal.getAuxBackground());
		setSize(x,y);
		//setBorder(BorderFactory.createLineBorder(Color.black));
		
		criandoBotoes();
		criandoPartesVisuais();

	}

/************************************Criando e Configurando Botoes*************************/
	private void criandoBotoes(){
	/****************************Configurações Botao Receita***************************/
		botaoReceita = new JButton("Receita");
		add(botaoReceita);
		botaoReceita.setVisible(true);
		botaoReceita.setBackground(Color.GREEN);
		botaoReceita.setBounds(porWidth(20), porHeight(5), porWidth(10), porHeight(5));
		botaoReceita.setBorder(new LineBorder(null,0,true));

	/****************************Configurações Botao Receita***************************/
		botaoDespesa = new JButton("Despesa");
		add(botaoDespesa);
		botaoDespesa.setVisible(true);
		botaoDespesa.setBackground(Color.red);
		botaoDespesa.setBounds(porWidth(32), porHeight(5), porWidth(10), porHeight(5));
		botaoDespesa.setBorder(new LineBorder(null,0,true));
		
	/****************************Configurações Botao Transferencia***************************/
		botaoTransferencia = new JButton("Tranferencia");
		add(botaoTransferencia);
		botaoTransferencia.setVisible(true);
		botaoTransferencia.setBackground(Color.ORANGE);
		botaoTransferencia.setBounds(porWidth(44), porHeight(5), porWidth(10), porHeight(5));
		botaoTransferencia.setBorder(new LineBorder(null,0,true));
		
	/****************************Configurações Botao Nova Conta***************************/
		botaoNewConta = new JButton("Nova Conta");
		add(botaoNewConta);
		botaoNewConta.setVisible(true);
		botaoNewConta.setBackground(Color.cyan);
		botaoNewConta.setBounds(porWidth(56), porHeight(5), porWidth(10), porHeight(5));
		botaoNewConta.setBorder(new LineBorder(null,0,true));
		
	/****************************Configurações Botao Seta Esquerda***************************/
		botaoSetaEsq = new JButton("Mes anterior");
		add(botaoSetaEsq);
		botaoSetaEsq.setVisible(true);
		botaoSetaEsq.setBackground(Color.gray);
		botaoSetaEsq.setIcon(new ImageIcon("src/imagens/seta_esq.png"));
		botaoSetaEsq.setBounds(porWidth(2), porHeight(94), porWidth(7), porHeight(5));
		botaoSetaEsq.setBorder(new LineBorder(null,0,true));
		botaoSetaEsq.addActionListener(this);
		
	/****************************Configurações Botao Seta Direita***************************/
		botaoSetaDir = new JButton("Proximo Mes");
		add(botaoSetaDir);
		botaoSetaDir.setVisible(true);
		botaoSetaDir.setBackground(Color.gray);
		botaoSetaDir.setIcon(new ImageIcon("src/imagens/seta_dir.png"));
		botaoSetaDir.setBounds(porWidth(91), porHeight(94), porWidth(7), porHeight(5));
		botaoSetaDir.setBorder(new LineBorder(null,0,true));
		botaoSetaDir.addActionListener(this);
		
	/****************************Configurações Botao Mes Atual***************************/
		botaoMesAtual = new JButton("Mes Atual");
		add(botaoMesAtual);
		botaoMesAtual.setVisible(true);
		botaoMesAtual.setBackground(Color.gray);
		botaoMesAtual.setBounds(porWidth(8.6), porHeight(94), porWidth(6), porHeight(5));
		botaoMesAtual.setBorder(new LineBorder(null,0,true));
	}
	
/************************************Criando e Configurando Partes Visuais*******************/
	private void criandoPartesVisuais(){
	/********************************Label do Mes********************************************/
		labelMes = new JLabel(ManipulandoData.retornaMes(FramePrincipal.getMes())+" "+FramePrincipal.getAno());
		add(labelMes);
		labelMes.setVisible(true);
		labelMes.setBackground(Color.blue);
		labelMes.setBounds(porWidth(45), porHeight(94), porWidth(15), porHeight(5));
		
	}
	
/**********************************Metodos da ActionListener**************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==botaoReceita){
			
		}
		if(e.getSource()== botaoSetaEsq){
			if(FramePrincipal.getMes()==1){
				FramePrincipal.setMes(12);
				FramePrincipal.setAno(FramePrincipal.getAno()-1);
			}else{
				FramePrincipal.setMes(FramePrincipal.getMes()-1);
			}
			labelMes.setText(ManipulandoData.retornaMes(FramePrincipal.getMes())+" "+FramePrincipal.getAno());
		}
		if(e.getSource()== botaoSetaDir){
			if(FramePrincipal.getMes()==12){
				FramePrincipal.setMes(1);
				FramePrincipal.setAno(FramePrincipal.getAno()+1);
			}else{
				FramePrincipal.setMes(FramePrincipal.getMes()+1);
			}
			labelMes.setText(ManipulandoData.retornaMes(FramePrincipal.getMes())+" "+FramePrincipal.getAno());
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
