package Testes;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Tela.FrameLogin;
import Tela.FramePrincipal;
import backEnd.BDD;

public class teste {

	public static void main(String[] args) {
		new FrameLogin();
		//new FramePrincipal(1);
	}
	private static Dimension pegarResolucao() {
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension dimensao = t.getScreenSize();
		return dimensao;
	}
}
