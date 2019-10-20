package model;

import java.util.Date;

public class Treino {

	private int idTreino;
	private Aluno aluno;
	private Instrutor instrutor;
	private Exercicio exercicio; 
	private String descricao;
	private String turno;
	private Date dataTreino;

	public int getIdTreino() {
		return idTreino;
	}
	public void setIdTreino(int idTreino) {
		this.idTreino = idTreino;
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
	public Date getDataTreino() {
		return dataTreino;
	}
	public void setDataTreino(Date dataTreino) {
		this.dataTreino = dataTreino;
	}
}
