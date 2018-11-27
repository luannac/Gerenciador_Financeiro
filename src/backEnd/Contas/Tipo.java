package backEnd.Contas;

public class Tipo extends Categoria{
/***********************************Atributos*******************************************/
	private String nome;

/***********************************Construtor*******************************************/
	public Tipo(int id,String nome,boolean despesa) {
		super();
		this.id =id;
		this.nome = nome;
		this.despesa= despesa;
	}
	
/***********************************Metodos Getters e Setters*************************/
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
