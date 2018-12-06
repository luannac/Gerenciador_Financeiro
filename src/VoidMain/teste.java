package VoidMain;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import backEnd.BDD;
import backEnd.Usuario;
import frame_Principal.FramePrincipal;
import frames.FrameLogin;
import frames.FrameMovimentacao;
import frames.FrameNewUsuario;

public class teste {

	public static void main(String[] args) {
		new FrameLogin();
		System.out.println(FramePrincipal.getAno());
		System.out.println(FramePrincipal.getMes());
		//new FramePrincipal(1);
		//new FrameNewUsuario();
		//new FrameMovimentacao();
	}
}
