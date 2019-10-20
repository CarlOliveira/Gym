package model;

public class Treino {

	private int idTreino;
	private Aluno aluno;
	private Instrutor instrutor;
	private Exercicio exercicio; 
	private String descricao;
	private String turno;
	private String data_Treino;

	public int getIdTreino() {
		return idTreino;
	}
	public void setIdTreino(int idTreino) {
		this.idTreino = idTreino;
	}
	public String getData_Treino() {
		return data_Treino;
	}
	public void setData_Treino(String data_Treino) {
		this.data_Treino = data_Treino;
	}
	public String getTurno(){
		return turno;
	}
	public void setTurno(String turno){
		this.turno = turno;
	}	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Instrutor getInstrutor() {
		return instrutor;
	}
	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}
	public Exercicio getExercicio() {
		return exercicio;
	}
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}
}
