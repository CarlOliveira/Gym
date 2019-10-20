package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Exercicio;

public class ExercicioDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;

	public ExercicioDAO(){
		 conn = new ConnectionFactory().getConexao();
		}
		
		public void create(Exercicio exercicio) {
			String sql = "INSERT INTO instrutor (exercicio, agrupamento_muscular, descrição) VALUES (?,?,?)";
		    try {
		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, exercicio.getExercicio());
		        stmt.setString(2, exercicio.getAgrupamentoMuscular());
		        stmt.setString(3, exercicio.getDescricao());
		        stmt.execute();
		        stmt.close();
		        }
		    catch(Exception erro){
		        throw new RuntimeException("ERRO: " + erro);
		    }
		}


	public void update(Exercicio exercicio) {
	    String sql = "UPDATE exercicio set agrupamento_muscular = ?, descrição = ? WHERE exercicio = ?";
	    try{
	        stmt = conn.prepareStatement (sql);
	        stmt.setString(1, exercicio.getAgrupamentoMuscular());
	        stmt.setString(2, exercicio.getDescricao());
	        stmt.setString(3, exercicio.getExercicio());
	        stmt.execute();
	        stmt.close();
	    }
	    catch(Exception erro){
	        throw new RuntimeException ("ERRO: " + erro);
	    }
	}

	public void delete (Exercicio exercicio) {
	    String sql = "DELETE FROM exercicio WHERE exercicio = ?";
	    try{
	    	stmt = conn.prepareStatement(sql);
	    	stmt.setString(1, exercicio.getExercicio());
	    	stmt.close();
	    } 
	    catch(Exception erro){
	        throw new RuntimeException ("ERRO 4: " + erro);
	    }
	}

	public ArrayList <Exercicio> Listartodos(){
	    String sql = "select * from exercicio";
	    ArrayList<Exercicio> lista = new ArrayList<Exercicio>();
	    try{
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next ()){
	        	Exercicio exercicio = new Exercicio();
	        	exercicio.setExercicio(rs.getString("exercicio"));
	        	exercicio.setAgrupamentoMuscular(rs.getString("agrupamento_muscular"));
	        	exercicio.setDescricao(rs.getString("descricao"));
	            lista.add(exercicio);
	        }
	    }    
	    catch(Exception erro){
	        throw new RuntimeException ("Erro 5: " + erro);
	    }
	    return lista;
	}	  
}

