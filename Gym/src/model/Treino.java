package model;

public class Treino {

	private int idTreino;
	private int idAluno;
	private int idInstrutor;
	private int idFicha;
	private String dataTreino;
	private String status;
	
	public int getIdFicha() {
		return idFicha;
	}
	public void setIdFicha(int idFicha) {
		this.idFicha = idFicha;
	}
	public int getIdTreino() {
		return idTreino;
	}
	public void setIdTreino(int idTreino) {
		this.idTreino = idTreino;
	}
	public int getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	public int getIdInstrutor() {
		return idInstrutor;
	}
	public void setIdInstrutor(int idInstrutor) {
		this.idInstrutor = idInstrutor;
	}
	public String getDataTreino() {
		return dataTreino;
	}
	public void setDataTreino(String dataTreino) {
		this.dataTreino = dataTreino;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
