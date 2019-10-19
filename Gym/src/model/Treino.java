package model;

public class Treino {

	private int idTreino;
	private String cpf;
	private String cpf_Instrutor;
	private String descricao;
	private String turno;
	private String data_Treino;

	public int getIdTreino() {
		return idTreino;
	}
	public void setIdTreino(int idTreino) {
		this.idTreino = idTreino;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getCpf_Instrutor() {
		return cpf_Instrutor;
	}
	public void setCpf_Instrutor(String cpf_Instrutor) {
		this.cpf_Instrutor = cpf_Instrutor;
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
	public void setTruno(String turno){
		this.turno = turno;
	}	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
