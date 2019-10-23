package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Aluno;
import model.Exercicio;
import model.Instrutor;
import model.Treino;

public class TreinoDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public TreinoDAO(){
		 conn = new ConnectionFactory().getConexao();
		}
		
		public void create(Treino treino) {
			String sql = "INSERT INTO treino (cpf, cpf_Instrutor, exercicio, descricao, data_treino, turno) VALUES (?,?,?,?,?,?)";
		    try {
		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, treino.getAluno().getCpf());
		        stmt.setString(2, treino.getInstrutor().getCpf());
		        stmt.setString(3, treino.getExercicio().getNome());
		        stmt.setString(4, treino.getDescricao());
		        stmt.setString(5, formato.format(treino.getDataTreino()));
		        stmt.setString(6, treino.getTurno());
		        stmt.execute();
		        stmt.close();
		        }
		    catch(Exception erro){
		        throw new RuntimeException("ERRO: " + erro);
		    }
		}


	public void update(Treino treino) {
	    String sql = "UPDATE treino set cpf = ?, cpf_Instrutor = ?, exercicio = ? , descricao = ?,"
	    		+ " data_treino = ?, turno = ? WHERE idTreino = ?";
	    
	    try{
	        stmt = conn.prepareStatement (sql);
	        stmt.setString(1, treino.getAluno().getCpf());
	        stmt.setString(2, treino.getInstrutor().getCpf());
	        stmt.setString(3, treino.getExercicio().getNome());
	        stmt.setString(4, treino.getDescricao());
	        stmt.setString(5, formato.format(treino.getDataTreino()));
	        stmt.setString(6, treino.getTurno());
	        stmt.setInt(7, treino.getIdTreino());
	        stmt.execute();
	        stmt.close();
	    }
	    catch(Exception erro){
	        throw new RuntimeException ("ERRO: " + erro);
	    }
	}

	public void delete (Treino treino) {
	    String sql = "DELETE FROM treino WHERE idTreino = ?";
	    try{
	    	stmt = conn.prepareStatement(sql);
	    	stmt.setInt(1, treino.getIdTreino());
	    	stmt.execute();
	    	stmt.close();
	    } 
	    catch(Exception erro){
	        throw new RuntimeException ("ERRO 4: " + erro);
	    }
	}

	public ArrayList <Treino> Listartodos(){
	    String sql = "select a.nome, a.cpf, a.email, a.endereco, " + 
	    		"i.cpf, i.email, i.endereco, i.nome, i.telefone, i.turno, " + 
	    		"e.exercicio, e.agrupamento_muscular, e.descrição, " + 
	    		"t.descricao, t.data_treino, t.turno, t.idTreino " + 
	    		"from treino as t join aluno a on t.cpf = a.cpf " + 
	    		"join instrutor as i on t.cpf_instrutor = i.cpf " + 
	    		"join exercicio as e on e.exercicio = t.exercicio ";
	    ArrayList<Treino> lista = new ArrayList<Treino>();
	    try{
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);
	        while (rs.next ()){
	        	Treino treino = new Treino();
	        	Aluno aluno = new Aluno();
	        	
	        	aluno.setCpf(rs.getString("a.cpf"));
	        	aluno.setNome(rs.getString("a.nome"));
	        	
	        	Instrutor instrutor = new Instrutor();
	        	instrutor.setCpf(rs.getString("i.cpf"));
	        	instrutor.setNome(rs.getString("i.nome"));
	        	
	        	Exercicio exercicio = new Exercicio();
	        	exercicio.setNome(rs.getString("e.exercicio"));
	        	exercicio.setAgrupamentoMuscular(rs.getString("e.agrupamento_muscular"));
	        	exercicio.setDescricao(rs.getString("e.descrição"));
	        	
	        	treino.setAluno(aluno);
	        	treino.setInstrutor(instrutor);
	        	treino.setExercicio(exercicio);
	        	treino.setDataTreino(formato.parse(rs.getString("t.data_treino")));
	        	treino.setTurno(rs.getString("t.turno"));
	        	treino.setDescricao(rs.getString("t.descricao"));
	        	treino.setIdTreino(rs.getInt("t.idTreino"));
	            lista.add(treino);
	        }
	    }    
	    catch(Exception erro){
	        throw new RuntimeException ("Erro 5: " + erro);
	    }
	    return lista;
	}	  

}
