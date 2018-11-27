package backEnd.Contas;

public class Categoria {
/***********************************Atributos*******************************************/
	protected int id;
	protected boolean despesa;
	
/***********************************Metodos Getters e Setters*************************/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isDespesa() {
		return despesa;
	}
	public void setDespesa(boolean despesa) {
		this.despesa = despesa;
	}
}
