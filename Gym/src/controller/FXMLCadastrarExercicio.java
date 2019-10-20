package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.ExercicioDAO;
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
import model.Exercicio;

public class FXMLCadastrarExercicio implements Initializable {

	@FXML
	private TableView<Exercicio> tableViewExercicio;

	@FXML
	private TableColumn<Exercicio, String> tableCollumnNome;

	@FXML
	private Label lableNome;
	
    @FXML
    private Label labelAgrupamento;

    @FXML
    private Label labelDescricao;
	@FXML
	private Button buttonAlterar;

	@FXML
	private Button buttonInserir;

	@FXML
	private Button buttonExcluir;

	private List<Exercicio> colecaoExercicio;
	private ObservableList<Exercicio> observableListExercicio;
	private ExercicioDAO exercicioDAO = new ExercicioDAO();

	public void initialize(URL location, ResourceBundle resources) {
		carregarLista();
		tableViewExercicio.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableView(newValue));
	}

	private void carregarLista() {
		tableCollumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colecaoExercicio = new ArrayList<Exercicio>();
		colecaoExercicio = exercicioDAO.Listartodos();
		observableListExercicio = FXCollections.observableArrayList(colecaoExercicio);
		tableViewExercicio.setItems(observableListExercicio);
	}

	private void selecionarItemTableView(Exercicio exercicio) {
		if (exercicio != null) {
		    this.lableNome.setText(exercicio.getNome());
		    this.labelAgrupamento.setText(exercicio.getAgrupamentoMuscular());
		    this.labelDescricao.setText(exercicio.getDescricao());
		} else {
			lableNome.setText("");
			labelAgrupamento.setText("");
			labelDescricao.setText("");
		}

	}
	
	@FXML
	public void incluir () throws IOException {
		Exercicio exercicio = new Exercicio();
		Boolean botaoConfirmarUsado = showDialog(exercicio);
		if (botaoConfirmarUsado) {
			exercicioDAO.create(exercicio);
			carregarLista();
		}
	}
	
	@FXML
	public void alterar () throws IOException {
		Exercicio exercicio = tableViewExercicio.getSelectionModel().getSelectedItem();
		if (exercicio != null) {
			Boolean botaoConfirmarUsado = showDialog(exercicio);
			if (botaoConfirmarUsado) {
				exercicioDAO.update(exercicio);
				carregarLista();
			}
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Por favor selecione um exercicio!");
		    alert.show();
		}
		
		
	}
	
	@FXML 
	public void excluir () {
		Exercicio exercicio = tableViewExercicio.getSelectionModel().getSelectedItem();
		if (exercicio != null) {
			exercicioDAO.delete(exercicio);
			carregarLista();
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Por favor selecione um exercicio!");
		    alert.show();
		}
	}
	
	public Boolean showDialog (Exercicio exercicio) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FXMLCadastrarExercicioDialog.class.getResource("../view/FXMLCadastrarExercicioDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de exercicio");
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		FXMLCadastrarExercicioDialog controller = loader.getController();
		controller.setStageDialog(dialogStage);
		controller.setExercicio(exercicio);
		dialogStage.showAndWait();
		
		return controller.getConfirmarClick();
		

	}
}

