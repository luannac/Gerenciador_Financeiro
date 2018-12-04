package panelAuxiares;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ClassesAuxiliares.JanelaRedimensionada;
import backEnd.Usuario;
import backEnd.Contas.Conta;
import backEnd.Contas.Movimentacao;
import frame_Principal.FramePrincipal;

public class PanelMovimentacoes extends JPanel implements JanelaRedimensionada {
/************************************Atributo*********************************************/
	private Usuario usu;
	
/************************************Construtor*******************************************/
	public PanelMovimentacoes(int x,int y,Usuario usu) {
		setLayout(null);
		setBackground(FramePrincipal.getAuxBackground());
		setSize(x,y);
		setPreferredSize(new Dimension(x-porWidth(12.), usu.getMovimentacoes().size()*porHeight(8.7)));
		
		this.usu=usu;
		
		criandoPanelMovimemtacao();
	}
	
/************************************Criação de Contas*******************************************/
	private void criandoPanelMovimemtacao() {
		PanelMovimentacao pMovimentacao;
		int i=0;
		if(!usu.getMovimentacoes().isEmpty()){
			for (Movimentacao movimentacao : usu.getMovimentacoes()) {
				if(movimentacao.getData().getMonth()==FramePrincipal.getMes() && movimentacao.getData().getYear()==FramePrincipal.getAno()){
					pMovimentacao = new PanelMovimentacao(porWidth(98), porHeight(8), movimentacao,usu.pegarContaMov(movimentacao));
					pMovimentacao.setBounds(porWidth(0), i*porHeight(8.7), porWidth(99), porHeight(8));
					add(pMovimentacao);
					i++;
				}
			}
		}else{
			JLabel label = new JLabel("Sem Movimentações este Més");
			label.setBounds(porWidth(40), porHeight(50), porWidth(45), porHeight(5));
			label.setVisible(true);
			add(label);
		}
		
	}
	
/************************************Metodos da Interface JanelaRedimensionada************/
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
