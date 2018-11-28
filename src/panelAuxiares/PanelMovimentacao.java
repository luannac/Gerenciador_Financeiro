package panelAuxiares;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

import ClassesAuxiliares.JanelaRedimensionada;

public class PanelMovimentacao extends JPanel implements JanelaRedimensionada {
	
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
