package VoidMain;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ClassesAuxiliares.ManipulandoData;
import backEnd.BDD;
import backEnd.Contas.Conta;
import backEnd.Contas.Movimentacao;
import backEnd.Contas.Tipo;

public class TestaBDD {
	public static void main(String[] args) {
		if(BDD.login("", "")){
			System.out.println("Deu Certo");
		}else{
			System.out.println("Deu Errado");
		}
		System.out.println(BDD.getTotem());
		//ArrayList<Conta> contas = BDD.carregaContas();
		/*
		 if(contas.isEmpty())
		 
			System.out.println("Esta Vazio");
		else{
			for (Conta conta : contas) {
				System.out.println(conta.getEntradasDespesas().get(0).getValor());
			}
		}*/
		Tipo tipo = BDD.categoriaTipo(1);
		System.out.println(tipo.getNome());
		System.out.println(new SimpleDateFormat("dd/MM/YYYY").format(BDD.categoriaAgendada(1).getDataCriacao()));
		
		
		ArrayList<Movimentacao> mov = BDD.ang_Exe(1);
		for (Movimentacao movimentacao : mov) {
			
			System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(movimentacao.getData()));
		}
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(ManipulandoData.dataAtual()));
		System.out.println(ManipulandoData.pegaMes());
		System.exit(0);
	}
}
