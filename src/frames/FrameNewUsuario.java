package frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import ClassesAuxiliares.JanelaRedimensionada;

public class FrameNewUsuario extends JFrame implements JanelaRedimensionada {
/**********************************Atributos************************************************************/
	
/**********************************Construtores*********************************************************/
	public FrameNewUsuario() {
		super("Novo Usuário");
		setSize((pegarResolucao().width -700), (pegarResolucao().height - 300));
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		

		
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
