package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.AlunoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Aluno;

public class FXMLCadastrarAluno implements Initializable {

	@FXML
	private TableView<Aluno> tableViewAluno;

	@FXML
	private TableColumn<Aluno, String> tableCollumnAlunoNome;

	@FXML
	private TableColumn<Aluno, String> tableCollumnCpf;

	@FXML
	private Label lableCPF;

	@FXML
	private Label lableNome;

	@FXML
	private Label lableEmail;

	@FXML
	private Label lableTelefone;

	@FXML
	private Label lableEndereco;

	@FXML
	private Button buttonAlterar;

	@FXML
	private Button buttonInserir;

	@FXML
	private Button buttonExcluir;

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
			lableCPF.setText(aluno.getCpf());
			lableEmail.setText(aluno.getEmail());
			lableEndereco.setText(aluno.getEndereco());
			lableNome.setText(aluno.getNome());
			lableTelefone.setText(aluno.getTelefone());
		} else {
			lableCPF.setText("");
			lableEmail.setText("");
			lableEndereco.setText("");
			lableNome.setText("");
			lableTelefone.setText("");
		}

	}
	
	@FXML
	public void incluir () throws IOException {
		Aluno aluno = new Aluno();
		Boolean botaoConfirmarUsado = showDialog(aluno);
		if (botaoConfirmarUsado) {
			alunoDAO.create(aluno);
			carregarLista();
		}
	}
	
	@FXML
	public void alterar () throws IOException {
		Aluno aluno = tableViewAluno.getSelectionModel().getSelectedItem();
		if (aluno != null) {
			Boolean botaoConfirmarUsado = showDialog(aluno);
			if (botaoConfirmarUsado) {
				alunoDAO.update(aluno);
				carregarLista();
			}
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Por favor selecione um aluno!");
		    alert.show();
		}
		
		
	}
	
	@FXML 
	public void excluir () {
		Aluno aluno = tableViewAluno.getSelectionModel().getSelectedItem();
		if (aluno != null) {
			alunoDAO.delete(aluno);
			carregarLista();
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Por favor selecione um aluno!");
		    alert.show();
		}
	}
	
	public Boolean showDialog (Aluno aluno) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FXMLCadastrarAlunoDialog.class.getResource("../view/FXMLCadastrarAlunoDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de aluno");
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		FXMLCadastrarAlunoDialog controller = loader.getController();
		controller.setStageDialog(dialogStage);
		controller.setAluno(aluno);
		dialogStage.showAndWait();
		
		return controller.getConfirmarClick();
		

	}
}