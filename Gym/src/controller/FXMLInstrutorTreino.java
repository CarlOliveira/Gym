package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.InstrutorDAO;
import dao.TreinoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Instrutor;
import model.Treino;

public class FXMLInstrutorTreino implements Initializable {

	@FXML
    private TableView<Instrutor> tableViewInstrutor;

    @FXML
    private TableColumn<Instrutor, String> tableCollumnInstrutorNome;

    @FXML
    private TableColumn<Instrutor, String> tableCollumnCpf;

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

	

	private List<Instrutor> colecaoInstrutor;
	private ObservableList<Instrutor> observableListInstrutor;
	private InstrutorDAO instrutorDAO = new InstrutorDAO();

	public void initialize(URL location, ResourceBundle resources) {
		carregarLista();
		tableViewInstrutor.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableView(newValue));
	}

	private void carregarLista() {
		tableCollumnInstrutorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableCollumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colecaoInstrutor = new ArrayList<Instrutor>();
		colecaoInstrutor = instrutorDAO.Listartodos();
		observableListInstrutor = FXCollections.observableArrayList(colecaoInstrutor);
		tableViewInstrutor.setItems(observableListInstrutor);
	}

	private void selecionarItemTableView(Instrutor instrutor) {
		if (instrutor != null) {
			TreinoDAO treinoDAO = new TreinoDAO();
			colummTreino.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			colummTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
			colummData.setCellValueFactory(new PropertyValueFactory<>("dataString"));
			colummAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
			colummInstrutor.setCellValueFactory(new PropertyValueFactory<>("nomeInstrutor"));
			colummExercicio.setCellValueFactory(new PropertyValueFactory<>("nomeExercicio"));
			List<Treino> colecaoTreino = new ArrayList<Treino>();
			colecaoTreino = treinoDAO.ListartodosPorInstrutor(instrutor);
			ObservableList<Treino> observableLisTreino;
			observableLisTreino = FXCollections.observableArrayList(colecaoTreino);
			tableTreinos.setItems(observableLisTreino);
		}
		

	}
}