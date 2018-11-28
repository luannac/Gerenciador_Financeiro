package Testes;

import ClassesAuxiliares.ManipulandoData;
import backEnd.BDD;
import backEnd.Usuario;
import backEnd.Contas.Movimentacao;
import frame_Principal.FramePrincipal;

public class TestaBack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BDD.login("", "");
		Usuario usu = new Usuario();
		
		usu.despesa(usu.getContas().get(0), 100, BDD.categoriaTipo(5), "Salamandra");
		System.out.println(usu.getContas().get(0).getEntradasDespesas().get(2).getObs());
		usu.receita(usu.getContas().get(0), 100, BDD.categoriaTipo(13), "Teste 2");
		if(usu.execucaooAgendada(usu.getContas().get(0), BDD.categoriaAgendada(1)))
			System.out.println('Y');
		else
			System.out.println('N');
		
		for (Movimentacao mov : usu.getContas().get(0).getEntradasDespesas()) {
			System.out.println("Conta 1: mov= "+mov.getObs()+" \\ "+mov.getId());
		}
		for (Movimentacao mov : BDD.entradasEDespesas(1)) {
			System.out.println("Conta 1: mov= "+mov.getObs()+" \\ "+mov.getId());
			
		}
		
		BDD.insertAng_ex(new Movimentacao(100, ManipulandoData.dataAtual(), usu.getAgendadas().get(0)), usu.getContas().get(0).getId());
		
		
		
		if(usu.salvar())
			System.out.println("DeuCerto");
	}

}
