package Tela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backEnd.BDD;
import backEnd.Usuario;

public class FrameLogin extends JFrame {
	/**********************Atributos*************************/
	private JLabel label[]= new JLabel[3];
	private JTextField field;
	private JPasswordField password;
	private JButton botao[]= new JButton[2];
	
	public FrameLogin(){
	/**********************Configurações Gerais**************/
		super("Login");
		setSize(300, 300);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	/***********************Configuração dos Componentes*****/
		//******************label 0 - login
			label[0] = new JLabel("Usuário:");
			getContentPane().add( label[0]);
			label[0].setBounds(80, 20, 110, 30);
			label[0].setVisible(true);
		
		//******************field 0	- login
			field = new JTextField();
			getContentPane().add(field);
			field.setBounds(80, 50, 130, 30);
			field.setVisible(true);
			
		//******************label 1	- Senha
			label[1] = new JLabel("Senha:");
			getContentPane().add( label[1]);
			label[1].setBounds(80, 100, 110, 30);
			label[1].setVisible(true);
			
		//******************field 1 - Senha
			password = new JPasswordField();
			getContentPane().add(password);
			password.setBounds(80, 130, 130, 30);
			password.setVisible(true);
			//Colocar senha visivel
			//password.setEchoChar('\u0000');
			password.setEchoChar('*');
			
		//******************Botao 0	- Login
			botao[0] = new JButton("Login");
			getContentPane().add(botao[0]);
			botao[0].setBounds(100, 180, 80, 30);
			botao[0].setVisible(true);
			
			botao[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(BDD.login(field.getText(), password.getText())){
						Usuario usu =new Usuario();
						new FramePrincipal(BDD.getTotem(),usu);
						setVisible(false);
					}else{
						label[2].setVisible(true);
					}
					
				}
			});
			
		//******************label 2	- Erro
			label[2] = new JLabel("Usuario ou senha inválidos",JLabel.CENTER);
			getContentPane().add(label[2]);
			label[2].setVisible(false);
			label[2].setBounds(0, 210, 300, 30);
			label[2].setForeground(Color.red);
			
			
		//******************Botao 1 - novo usuario
			botao[1] = new JButton("Novo Usuário");
			getContentPane().add(botao[1]);
			botao[1].setBounds(0, 241, 300, 30);
			botao[1].setVisible(true);
			
	}
	//
		private Dimension pegarResolucao() {
			Toolkit t = Toolkit.getDefaultToolkit();
			Dimension dimensao = t.getScreenSize();
			return dimensao;
		}
}
