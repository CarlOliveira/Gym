package dao;

import model.Instrutor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InstrutorDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private ArrayList<Instrutor> lista = new ArrayList<Instrutor>();

	public InstrutorDAO(){
		 conn = new ConnectionFactory().getConexao();
		}
		
		public void create(Instrutor instrutor) {
			String sql = "INSERT INTO instrutor (nome, cpf, telefone, email, endereco, turno) VALUES (?,?,?,?,?)";
		    try {
		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, instrutor.getNome());
		        stmt.setString(2, instrutor.getCpf());
		        stmt.setString(3, instrutor.getTelefone());
		        stmt.setString(4, instrutor.getEmail());
		        stmt.setString(5, instrutor.getEndereco());
		        stmt.setString(6, instrutor.getTurno());
		        stmt.execute();
		        stmt.close();
		        }
		    catch(Exception erro){
		        throw new RuntimeException("ERRO: " + erro);
		    }
		}


	public void update(Instrutor instrutor) {
	    String sql = "UPDATE instrutor set nome = ?, cpf = ?, telefone = ?, email = ?, endereco = ?, turno = ?  WHERE cpf = ?";
	    try{
	        stmt = conn.prepareStatement (sql);
	        stmt.setString(1, instrutor.getNome());
	        stmt.setString(2, instrutor.getCpf());
	        stmt.setString(3, instrutor.getTelefone());
	        stmt.setString(4, instrutor.getEmail());
	        stmt.setString(5, instrutor.getEndereco());
	        stmt.setString(6, instrutor.getTurno());
	        stmt.setString(7, instrutor.getCpf());
	        stmt.execute();
	        stmt.close();
	    }
	    catch(Exception erro){
	        throw new RuntimeException ("ERRO: " + erro);
	    }
	}

	public void delete (Instrutor instrutor) {
	    String sql = "DELETE FROM instrutor WHERE cpf = ?";
	    try{
	        st = conn.createStatement();
	        st.execute(sql);
	        st.close();
	    } 
	    catch(Exception erro){
	        throw new RuntimeException ("ERRO 4: " + erro);
	    }
	}

	public ArrayList <Instrutor> Listartodos(){
	    String sql = "select * from instrutor";
	    try{
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next ()){
	        	Instrutor instrutor = new Instrutor();
	        	instrutor.setNome(rs.getString("nome"));
	        	instrutor.setCpf(rs.getString("cpf"));
	        	instrutor.setTelefone(rs.getString("telefone"));
	        	instrutor.setEmail(rs.getString("email"));
	        	instrutor.setEndereco(rs.getString("Endereco"));
	        	instrutor.setTurno(rs.getString("turno"));
	            lista.add(instrutor);
	        }
	    }    
	    catch(Exception erro){
	        throw new RuntimeException ("Erro 5: " + erro);
	    }
	    return lista;
	}

	 public ArrayList <Instrutor> Listartodosnomes(String nome){
	    String sql = "select * from instrutor where nome like '%"+nome+"%' " ;
	    try{
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next ()){
	        	Instrutor instrutor = new Instrutor();
	        	instrutor.setNome(rs.getString("nome"));
	        	instrutor.setCpf(rs.getString("cpf"));
	        	instrutor.setTelefone(rs.getString("telefone"));
	        	instrutor.setEmail(rs.getString("email"));
	        	instrutor.setEndereco(rs.getString("Endereco"));
	        	instrutor.setTurno(rs.getString("turno"));
	            lista.add(instrutor);
	        }
	    }    
	    catch(Exception erro){
	        throw new RuntimeException ("Erro 5: " + erro);
	    }
	    return lista;
	}
	  
}
