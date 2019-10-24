package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.InstrutorDAO;
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
import model.Instrutor;

public class FXMLCadastrarInstrutor implements Initializable {

	@FXML
	private TableView<Instrutor> tableViewInstrutor;

	@FXML
	private TableColumn<Instrutor, String> tableCollumnInstrutorNome;

	@FXML
	private TableColumn<Instrutor, String> tableCollumnCpf;

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
	private Label lableTurno;

	@FXML
	private Button buttonAlterar;

	@FXML
	private Button buttonInserir;

	@FXML
	private Button buttonExcluir;

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
			lableCPF.setText(instrutor.getCpf());
			lableEmail.setText(instrutor.getEmail());
			lableEndereco.setText(instrutor.getEndereco());
			lableNome.setText(instrutor.getNome());
			lableTelefone.setText(instrutor.getTelefone());
			lableTurno.setText(instrutor.getTurno());
		} else {
			lableCPF.setText("");
			lableEmail.setText("");
			lableEndereco.setText("");
			lableNome.setText("");
			lableTelefone.setText("");
			lableTurno.setText("");
		}

	}
	
	@FXML
	public void incluir () throws IOException {
		Instrutor instrutor = new Instrutor();
		Boolean botaoConfirmarUsado = showDialog(instrutor);
		if (botaoConfirmarUsado) {
			instrutorDAO.create(instrutor);
			carregarLista();
		}
	}
	
	@FXML
	public void alterar () throws IOException {
		Instrutor instrutor = tableViewInstrutor.getSelectionModel().getSelectedItem();
		if (instrutor != null) {
			Boolean botaoConfirmarUsado = showDialog(instrutor);
			if (botaoConfirmarUsado) {
				instrutorDAO.update(instrutor);
				carregarLista();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Por favor, selecione um Instrutor!");
		    alert.show();
		}
		
		
	}
	
	@FXML 
	public void excluir () {
		Instrutor instrutor = tableViewInstrutor.getSelectionModel().getSelectedItem();
		if (instrutor != null) {
			instrutorDAO.delete(instrutor);
			carregarLista();
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Por favor, selecione um Instrutor!");
		    alert.show();
		}
	}
	
	public Boolean showDialog (Instrutor instrutor) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FXMLCadastrarInstrutorDialog.class.getResource("../view/FXMLCadastrarInstrutorDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de instrutor");
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		FXMLCadastrarInstrutorDialog controller = loader.getController();
		controller.setStageDialog(dialogStage);
		controller.setInstrutor(instrutor);
		dialogStage.showAndWait();
		
		return controller.getConfirmarClick();
	}
}