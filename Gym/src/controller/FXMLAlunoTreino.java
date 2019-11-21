package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.AlunoDAO;
import dao.TreinoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Aluno;
import model.Treino;

public class FXMLAlunoTreino implements Initializable {

	@FXML
    private TableView<Aluno> tableViewAluno;

    @FXML
    private TableColumn<Aluno, String> tableCollumnAlunoNome;

    @FXML
    private TableColumn<Aluno, String> tableCollumnCpf;

    @FXML
    private TableView<Treino> tableTreinos;

    @FXML
    private TableColumn<Treino, String> colummTreino;

    @FXML
    private TableColumn<Treino, String> colummTurno;

    @FXML
    private TableColumn<Treino, String> colummData;

    @FXML
    private TableColumn<Treino, String> colummAluno;

    @FXML
    private TableColumn<Treino, String> colummInstrutor;

    @FXML
    private TableColumn<Treino, String> colummExercicio;

	

	private List<Aluno> colecaoAluno;
	private ObservableList<Aluno> observableListAluno;
	private AlunoDAO alunoDAO = new AlunoDAO();

	public void initialize(URL location, ResourceBundle resources) {
		carregarLista();
		tableViewAluno.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableView(newValue));
	}

	private void carregarLista() {
		tableCollumnAlunoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableCollumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colecaoAluno = new ArrayList<Aluno>();
		colecaoAluno = alunoDAO.Listartodos();
		observableListAluno = FXCollections.observableArrayList(colecaoAluno);
		tableViewAluno.setItems(observableListAluno);
	}

	private void selecionarItemTableView(Aluno aluno) {
		if (aluno != null) {
			TreinoDAO treinoDAO = new TreinoDAO();
			colummTreino.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			colummTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
			colummData.setCellValueFactory(new PropertyValueFactory<>("dataString"));
			colummAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
			colummInstrutor.setCellValueFactory(new PropertyValueFactory<>("nomeInstrutor"));
			colummExercicio.setCellValueFactory(new PropertyValueFactory<>("nomeExercicio"));
			List<Treino> colecaoTreino = new ArrayList<Treino>();
			colecaoTreino = treinoDAO.ListartodosPorAluno(aluno);
			ObservableList<Treino> observableLisTreino;
			observableLisTreino = FXCollections.observableArrayList(colecaoTreino);
			tableTreinos.setItems(observableLisTreino);
		}
		

	}
}