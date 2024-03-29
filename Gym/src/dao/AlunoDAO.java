package dao;

import model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AlunoDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	    
	public AlunoDAO(){
	 conn = new ConnectionFactory().getConexao();
	}
	
	public void create(Aluno aluno) {
		String sql = "INSERT INTO aluno (nome, cpf, telefone, email, endereco) VALUES (?,?,?,?,?)";
	    try {
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, aluno.getNome());
	        stmt.setString(2, aluno.getCpf());
	        stmt.setString(3, aluno.getTelefone());
	        stmt.setString(4, aluno.getEmail());
	        stmt.setString(5, aluno.getEndereco());
	        stmt.execute();
	        stmt.close();
	        }
	     catch(Exception erro){
	        throw new RuntimeException("ERRO: " + erro);
	    }
	}


public void update(Aluno aluno) {
    String sql = "UPDATE aluno set nome = ?, cpf = ?, telefone = ?, email = ?, endereco = ?  WHERE cpf = ?";
    try{
        stmt = conn.prepareStatement (sql);
        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getCpf());
        stmt.setString(3, aluno.getTelefone());
        stmt.setString(4, aluno.getEmail());
        stmt.setString(5, aluno.getEndereco());
        stmt.setString(6, aluno.getCpf());
        stmt.execute();
        stmt.close();
    }
    catch(Exception erro){
        throw new RuntimeException ("ERRO: " + erro);
    }
}

public void delete (Aluno aluno) {
    String sql = "DELETE FROM aluno WHERE cpf = ?";
    try{
    	stmt = conn.prepareStatement(sql);
    	stmt.setString(1, aluno.getCpf());
    	stmt.execute();
    	stmt.close();
    } 
    catch(Exception erro){
        throw new RuntimeException ("ERRO 4: " + erro);
    }
}

public ArrayList<Aluno> Listartodos(){
    String sql = "select * from aluno";
    ArrayList<Aluno> lista = new ArrayList<Aluno>();
    try{
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next ()){
            Aluno aluno = new Aluno();
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setEmail(rs.getString("email"));
            aluno.setEndereco(rs.getString("endereco"));
            lista.add(aluno);
        }
    }    
    catch(Exception erro){
        throw new RuntimeException ("Erro 5: " + erro);
    }
    return lista;
}

 public ArrayList<Aluno> Listartodosnomes(String nome){
    String sql = "select * from aluno where nome like '%"+nome+"%' " ;
    ArrayList<Aluno> lista = new ArrayList<Aluno>();
    try{
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next ()){
        	Aluno aluno = new Aluno();
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setEmail(rs.getString("email"));
            aluno.setEndereco(rs.getString("endereco"));
            lista.add(aluno);
        }
    }    
    catch(Exception erro){
        throw new RuntimeException ("Erro 5: " + erro);
    }
    return lista;
}
 public Aluno ListarPorCpf(String cpf){
	    String sql = "select * from aluno where nome like '%"+cpf+"%' ";
	    Aluno aluno = new Aluno();
	    try{
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next ()){
	        	
	            aluno.setNome(rs.getString("nome"));
	            aluno.setCpf(rs.getString("cpf"));
	            aluno.setTelefone(rs.getString("telefone"));
	            aluno.setEmail(rs.getString("email"));
	            aluno.setEndereco(rs.getString("endereco"));
	        }
	    }    
	    catch(Exception erro){
	        throw new RuntimeException ("Erro 5: " + erro);
	    }
	    return aluno;
	}  
    public Boolean VeficaSeCpfJaExiste(String cpf){
	    String sql = "select cpf from aluno where cpf like '%"+cpf+"%' ";
	    Aluno aluno = new Aluno();
	    try{
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next ()){
	            aluno.setCpf(rs.getString("cpf"));
	        }
	    }    
	    catch(Exception erro){
	        throw new RuntimeException ("Erro 5: " + erro);
	    }
	    if (aluno.getCpf() != null) {
	    	return true;
	    } else {
	    	return false;
	    }
	}  
}
