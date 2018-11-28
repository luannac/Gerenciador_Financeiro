package backEnd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ClassesAuxiliares.ManipulandoData;
import backEnd.Contas.Agendadas;
import backEnd.Contas.Conta;
import backEnd.Contas.Movimentacao;
import backEnd.Contas.Tipo;

public class BDD {
	/***********Atributos****************/
	private static int totem;
	private static String nome;
	
	private static Connection connection = null;
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://ESN509vmysql:3306/luann_projeto2infab?useSSL=false&useTimezone=true&serverTimezone=UTC";
	private static String log = "aluno";
	private static String key = "Senai1234";
	
	/***************Metodos de Conecxão**************************************/
	public static boolean conecta(){
		try{
			Class.forName(driver);
			connection =DriverManager.getConnection(url, log, key);
			return true;
		}
		catch(ClassNotFoundException erro){
			erro.printStackTrace();
			return false;
		}
		catch (SQLException erro) {			
			erro.printStackTrace();
		return false;
		}
	}

	/*****************************Metodos de Comunicação********************************************/
	public static boolean login(String usuario,String senha){/**************************************/
		boolean retorno = false;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from login where login = ? and senha = ?;");
				statement.setString(1, usuario);
				statement.setString(2, senha);
				
				resultset = statement.executeQuery();
				
				while(resultset.next()){
					totem = resultset.getInt("idlogin");
					nome = resultset.getString(4);
					retorno = true;
				}				
			}catch(SQLException e){
				retorno =false;
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return retorno;
	}
	
	/*****************************Metodos de Iniciação**********************************************/
	public static ArrayList<Conta> carregaContas(){/************************************************/
		ArrayList<Conta> contas = new ArrayList<>();
		Conta cont;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from contas where login_idlogin = ?;");
				statement.setInt(1, totem);
				
				
				resultset = statement.executeQuery();
				
				
				while(resultset.next()){
					System.out.println("while");
					cont = new Conta(resultset.getInt(1), 
							totem, 
							resultset.getInt(3),
							resultset.getFloat(4),
							resultset.getString(5),
							resultset.getBoolean(6));
					
					
					contas.add(cont);
				}				
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return contas;
	}
	public static ArrayList<Tipo> carregaTipos(int id){/********************************************/
		ArrayList<Tipo> tipos= new ArrayList<>();
		Tipo tipo = null ;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from tipos where idtipos <> 0;");
				
				
				resultset = statement.executeQuery();
				
				while(resultset.next()){
				tipo = new Tipo(resultset.getInt(1), resultset.getString(2), resultset.getBoolean(3));
				tipos.add(tipo);
				}
								
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tipos;
	}
	public static ArrayList<Agendadas> carregaAgendadas(){/*****************************************/
		ArrayList<Agendadas> agendadas= new ArrayList<>();
		Agendadas agen= null ;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from agendadas where login_idlogin = ?;");
				statement.setInt(1, totem);
				
				resultset = statement.executeQuery();
				
				while(resultset.next()){
					agen = new Agendadas(resultset.getInt(1),
							resultset.getBoolean(3),
							resultset.getDate(2),
							resultset.getBoolean(4),
							resultset.getFloat(5),
							resultset.getString(6),
							resultset.getBoolean(8));
					agendadas.add(agen);
				}
								
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return agendadas;
	}
	public static ArrayList<Movimentacao> entradasEDespesas(int id){/*******************************/
		ArrayList<Movimentacao> array = new ArrayList<>();
		Movimentacao movimentacao;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from entrada_depesas where contas_idcontas = ?;");
				statement.setInt(1, id);
				
				
				resultset = statement.executeQuery();
				
				
				while(resultset.next()){
					movimentacao= new Movimentacao(resultset.getInt(1),
							resultset.getFloat(4),
							resultset.getString(6),
							resultset.getDate(5),
							categoriaTipo(resultset.getInt(2)));
					array.add(movimentacao);
				}				
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return array;
	}
	public static ArrayList<Movimentacao> ang_Exe(int id){/*****************************************/
		ArrayList<Movimentacao> array = new ArrayList<>();
		Agendadas agen=null;
		Movimentacao movimentacao;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from ang_exe where contas_idcontas = ?;");
				statement.setInt(1, id);
				
				
				resultset = statement.executeQuery();
				
				
				while(resultset.next()){
					agen = categoriaAgendada(resultset.getInt(3));
					movimentacao = new Movimentacao(resultset.getInt(1),
							agen.getValor(),
							agen.getObs(),
							resultset.getDate(4), 
							agen);
					array.add(movimentacao);
				}				
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return array;
	}
	public static ArrayList<Tipo> carregandoTipos(){
		ArrayList<Tipo> tipos= new ArrayList<>();
		Tipo tipo = null ;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from tipos where idtipos <> 0;");
				
				resultset = statement.executeQuery();
				
				while(resultset.next()){
					tipo = new Tipo(resultset.getInt(1), resultset.getString(2), resultset.getBoolean(3));
					tipos.add(tipo);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tipos;
	}
	
	/****************************Metodos Individuais de Select**************************************/
	public static String tipoConta(int id){/********************************************************/
		String resp = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from tipo_conta where idtipo_Conta = ?;");
				statement.setInt(1, id);
				
				resultset = statement.executeQuery();
				
				resultset.next();
				resp = resultset.getString("tipo");
					
								
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return resp;
	}
	public static Tipo categoriaTipo(int id){/******************************************************/
		Tipo tipo = null ;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from tipos where idtipos = ?;");
				statement.setInt(1, id);
				
				resultset = statement.executeQuery();
				
				resultset.next();
				tipo = new Tipo(resultset.getInt(1), resultset.getString(2), resultset.getBoolean(3));
					
								
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tipo;
	}
	public static Agendadas categoriaAgendada(int id){/*********************************************/
		Agendadas agendada= null ;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		if(conecta()){
			try{
				statement = connection.prepareStatement("select * from agendadas where idagendadas = ? and login_idlogin = ?;");
				statement.setInt(1, id);
				statement.setInt(2, totem);
				
				resultset = statement.executeQuery();
				
				resultset.next();
				agendada = new Agendadas(id,
						resultset.getBoolean(3),
						resultset.getDate(2),
						resultset.getBoolean(4),
						resultset.getFloat(5),
						resultset.getString(6),
						resultset.getBoolean(8));
					
								
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return agendada;
	}
	
	/****************************Metodos Update e Insert********************************************///==Sem testar
	public static void updateConta(Conta conta){/***************************************************/
		PreparedStatement statement = null;
		
		if(conecta()){
			try {
				statement = connection.prepareStatement("update Contas set login_idlogin = ? ,"
						+ " tipo_Conta_idtipo_Conta = ? ,"
						+ " saldo = ? ,"
						+ " obs = ? , ativa = ? "
						+ " where idcontas = ?;");
				statement.setInt(1, conta.getIdLogin());
				statement.setInt(2, conta.getIdTipo());
				statement.setFloat(3, conta.getSaldo());
				statement.setString(4, conta.getObs());
				statement.setBoolean(5, conta.isAtiva());
				statement.setInt(6, conta.getId());
				
				
				
				statement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void insertConta(Conta conta){/***************************************************/
		PreparedStatement statement = null;
		
		if(conecta()){
			try {
				statement = connection.prepareStatement("insert into Contas values (null,?,?,?,?,?);");
				statement.setInt(1, totem);
				statement.setInt(2, conta.getIdTipo());
				statement.setFloat(3, conta.getSaldo());
				statement.setString(4, conta.getObs());
				statement.setBoolean(5, conta.isAtiva());			
				
				statement.executeUpdate();					
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void updateAng_ex(Movimentacao mov,int idconta){/*********************************/
		PreparedStatement statement = null;
		
		if(conecta()){
			try {
				statement = connection.prepareStatement("update ang_exe set"
						+ " contas_idcontas = ? ,"
						+ "agendadas_idagendadas = ? ,"
						+ "data = ? ,"
						+ "valor = ? "
						+ "where idang_execol = ?;");
				statement.setInt(1, idconta);
				statement.setInt(2, mov.getAgen().getId());
				statement.setDate(3, ManipulandoData.convDataBanco(mov.getData()));//Coverter para SQl
				statement.setFloat(4, mov.getValor());
				statement.setInt(5, mov.getId());
				
				
				statement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void insertAng_ex(Movimentacao mov,int idconta){/*********************************/
		PreparedStatement statement = null;
		
		if(conecta()){
			try {
				statement = connection.prepareStatement("insert into ang_exe values (null,?,?,?,?);");
				statement.setInt(1, idconta);
				statement.setInt(2, mov.getAgen().getId());
				statement.setDate(3, ManipulandoData.convDataBanco(mov.getData()));//Coverter para SQl
				statement.setFloat(4, mov.getValor());
				
				
				statement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void updateEntrada_Despesa(Movimentacao mov,int idconta){
		PreparedStatement statement = null;
		
		if(conecta()){
			try {
				statement = connection.prepareStatement("update entrada_depesas set "
						+ "tipos_idtipos = ? ,"
						+ "contas_idcontas = ? ,"
						+ "valor = ? ,"
						+ "data = ? ,"
						+ "obs = ? "
						+ "where identrada_depesas=?;");
				statement.setInt(1, mov.getTipo().getId());
				statement.setInt(2, idconta);
				statement.setFloat(3, mov.getValor());
				statement.setDate(4, ManipulandoData.convDataBanco(mov.getData()));//Coverter para SQl
				statement.setString(5, mov.getObs());
				statement.setInt(6, mov.getId());
				
				
				statement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void insertEntrada_Despesa(Movimentacao mov,int idconta){
		PreparedStatement statement = null;
		
		if(conecta()){
			try {
				statement = connection.prepareStatement("insert into entrada_depesas values (null,?,?,?,?,?);");
				statement.setInt(1, mov.getTipo().getId());
				statement.setInt(2, idconta);
				statement.setFloat(3, mov.getValor());
				statement.setDate(4, ManipulandoData.convDataBanco(mov.getData()));//Coverter para SQl
				statement.setString(5, mov.getObs());
				
				
				statement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void updateAgendada(Agendadas age){
		PreparedStatement statement = null;
		
		if(conecta()){
			try {
				statement = connection.prepareStatement("update agendadas set "
						+ "data_criacao = ?,"
						+ "despesa = ? ,"
						+ "porcentagem = ? ,"
						+ "valor = ? ,"
						+ "obs = ? ,"
						+ "login_idlogin = ? ,"
						+ "ativa = ?  "
						+ "where idagendadas=?;");
				statement.setDate(1, ManipulandoData.convDataBanco(age.getDataCriacao()));//Coverter para SQl
				statement.setBoolean(2, age.isDespesa());
				statement.setBoolean(3, age.isPorcentagem());
				statement.setFloat(4, age.getValor());
				statement.setString(5, age.getObs());
				statement.setInt(6, totem);
				statement.setBoolean(7, age.isAtiva());
				statement.setInt(8, age.getId());
				
				
				statement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void insertAgendada(Agendadas age){
		PreparedStatement statement = null;
		
		if(conecta()){
			try {
				statement = connection.prepareStatement("insert into agendadas values (null,?,?,?,?,?,?,?);");
				statement.setDate(1, ManipulandoData.convDataBanco(age.getDataCriacao()));
				statement.setBoolean(2, age.isDespesa());
				statement.setBoolean(3, age.isPorcentagem());
				statement.setFloat(4, age.getValor());
				statement.setString(5, age.getObs());
				statement.setInt(6, totem);
				statement.setBoolean(7, age.isAtiva());
				
				statement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/***********************Metodos Auxilares************************/
	public static int getTotem() {
		return totem;
	}

	public static String getNome() {
		return nome;
	}
	
	/***********************************************************************************************/
	
}
