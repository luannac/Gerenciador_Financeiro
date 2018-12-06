package frames;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ClassesAuxiliares.JanelaRedimensionada;
import backEnd.BDD;
import backEnd.Usuario;
import frame_Principal.FramePrincipal;

public class FrameNewUsuario extends JFrame implements JanelaRedimensionada {
/**********************************Atributos************************************************************/
	private JLabel lTitulo,lLogin,lSenha,lSegundaSenha,lNome;
	private JTextField tfLogin,tfSenha,tfSegundaSenha,tfNome;
	private JButton botao;
	private Font font = new Font("Garamond", 0, 16);
	
/**********************************Construtores*********************************************************/
	public FrameNewUsuario() {
		super("Novo Usuário");
		setSize((pegarResolucao().width -1100), (pegarResolucao().height - 700));
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		criandoComponentes();
	}
	
/**********************************Criando Componentes**************************************************/
	private void criandoComponentes(){
		//LAbel Titulo
		lTitulo = new JLabel("Novo Usuário");
		getContentPane().add(lTitulo);
		lTitulo.setBounds(porWidth(45), porHeight(10), porWidth(20), porHeight(7));
		lTitulo.setFont(new Font("Garamond", 1, 17));
		
		//lAbel Login
		lLogin = new JLabel("Login:");
		getContentPane().add(lLogin);
		lLogin.setBounds(porWidth(10), porHeight(20), porWidth(8), porHeight(7));
		lLogin.setFont(this.font);
		
		//JTextField Login
		tfLogin = new JTextField();
		getContentPane().add(tfLogin);
		tfLogin.setBounds(porWidth(20), porHeight(20), porWidth(20), porHeight(7));
		
		//Label Senha
		lSenha = new JLabel("Senha:");
		getContentPane().add(lSenha);
		lSenha.setBounds(porWidth(10), porHeight(38), porWidth(8), porHeight(7));
		
		//JTextField Senha
		tfSenha = new JTextField();
		getContentPane().add(tfSenha);
		tfSenha.setBounds(porWidth(20), porHeight(38), porWidth(20), porHeight(7));
		tfSenha.setFont(this.font);
		
		//Label Nova Senha
		lSegundaSenha = new JLabel("Confirmação:");
		getContentPane().add(lSegundaSenha);
		lSegundaSenha.setBounds(porWidth(10), porHeight(50), porWidth(10), porHeight(7));
				
		//JTextField Nova Senha
		tfSegundaSenha = new JTextField();
		getContentPane().add(tfSegundaSenha);
		tfSegundaSenha.setBounds(porWidth(20), porHeight(50), porWidth(20), porHeight(7));
		tfSegundaSenha.setFont(this.font);
		
		//Label Nome
		lNome = new JLabel("Nome:");
		getContentPane().add(lNome);
		lNome.setBounds(porWidth(55), porHeight(20), porWidth(10), porHeight(7));
		lNome.setFont(this.font);
		
		//JTextField Nome
		tfNome = new JTextField();
		getContentPane().add(tfNome);
		tfNome.setBounds(porWidth(65), porHeight(20), porWidth(10), porHeight(7));
		tfNome.setFont(this.font);
		
		//Botao 
		botao = new JButton("Criar");
		getContentPane().add(botao);
		botao.setBounds(porWidth(40), porHeight(65), porWidth(15), porHeight(7));
		
		botao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(tfSenha.getText().equals(tfSegundaSenha.getText())){
					if(BDD.novoUsuario(tfLogin.getText(), tfSenha.getText(),tfNome.getText())){
						Usuario usu = new Usuario();
						usu.criarConta(1, 0, "Inicio");
						FramePrincipal frame = new FramePrincipal(BDD.getTotem(),usu);
						setVisible(false);
					}
				}
				
			}
		});
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
