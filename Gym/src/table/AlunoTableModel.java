package table;

import dao.AlunoDao;
import table.AlunoTableModel;
import model.Aluno;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AlunoTableModel extends AbstractTableModel {
	
	public static final int nome = 0;
	public static final int CPF = 1;
	public static final int TELEFONE = 2;
	public static final int EMAIL = 3;
	public static final int ENDERECO = 4;
	public ArrayList<Aluno> lista;
	
	public AlunoTableModel(ArrayList<Aluno> l) {
		lista = new ArrayList<Aluno>(l);
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int linhas, int colunas) {
		Aluno aluno = lista.get(linhas);
		if(colunas == nome) return aluno.getNome();
		if(colunas == CPF) return aluno.getCpf();
		if(colunas == TELEFONE) return aluno.getTelefone();
		if(colunas == EMAIL) return aluno.getEmail();
		if(colunas == ENDERECO) return aluno.getEndereco();
		return "";
	}
	
	@Override
	public String getColumnName(int colunas) {
		if(colunas == nome) return "Nome";
		if(colunas == CPF) return "CPF";
		if(colunas == TELEFONE) return "Telefone";
		if(colunas == EMAIL) return "Email";
		if(colunas == ENDERECO) return "Endereço";
		return "";
	}
	
}
