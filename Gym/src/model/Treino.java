package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Treino {

	private int idTreino;
	private Aluno aluno;
	private Instrutor instrutor;
	private Exercicio exercicio; 
	private String descricao;
	private String turno;
	private Date dataTreino;
	private String dataString;
	private String nomeAluno;
	private String nomeInstrutor;
	private String nomeExercicio;

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
	public String getDataString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		dataString = formato.format(this.dataTreino);
		return dataString;
	}
	public String getNomeAluno() {
		nomeAluno = this.aluno.getNome();
		return nomeAluno;
	}
	public String getNomeInstrutor() {
		nomeInstrutor = this.instrutor.getNome();
		return nomeInstrutor;
	}
	public String getNomeExercicio() {
		nomeExercicio = this.exercicio.getNome();
		return nomeExercicio;
	}
}
