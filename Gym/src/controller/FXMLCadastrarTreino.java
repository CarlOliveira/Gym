package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;
import java.text.SimpleDateFormat;

import dao.TreinoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Treino;

public class FXMLCadastrarTreino implements Initializable {

    @FXML
    private TableView<Treino> tableViewTreino;

    @FXML
    private TableColumn<Treino, String> tableCollumnDescricao;

    @FXML
    private Label lableDescricao;

    @FXML
    private Label labelTurno;

    @FXML
    private Label labelDataTreino;

    @FXML
    private Label labelAlunoNome;

    @FXML
    private Label labelInstrutorNome;
    
    @FXML
    private Label labelInstrutorCpf;

    @FXML
    private Label labelExercicioNome;
    
    @FXML
    private Label labelAlunoCpf;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonExcluir;

	private List<Treino> colecaoTreino;
	private ObservableList<Treino> observableListTreino;
	private TreinoDAO treinoDAO = new TreinoDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarLista();
		tableViewTreino.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableView(newValue));
	}

	private void carregarLista() {
		tableCollumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colecaoTreino = new ArrayList<Treino>();
		colecaoTreino = treinoDAO.Listartodos();
		observableListTreino = FXCollections.observableArrayList(colecaoTreino);
		tableViewTreino.setItems(observableListTreino);
	}

	private void selecionarItemTableView(Treino treino) {
		if (treino != null) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		    labelAlunoCpf.setText(treino.getAluno().getCpf());
		    labelAlunoNome.setText(treino.getAluno().getNome());
		    labelDataTreino.setText(formato.format(treino.getDataTreino()));
		    labelInstrutorCpf.setText(treino.getInstrutor().getCpf());
		    labelInstrutorNome.setText(treino.getInstrutor().getNome());
		    labelTurno.setText(treino.getTurno());
		    lableDescricao.setText(treino.getDescricao());
		    labelExercicioNome.setText(treino.getExercicio().getNome());
		} else {
			labelAlunoCpf.setText("");
			labelAlunoNome.setText("");
			labelDataTreino.setText("");
			labelInstrutorCpf.setText("");
			labelInstrutorNome.setText("");
			labelTurno.setText("");
			lableDescricao.setText("");
			labelExercicioNome.setText("");
		}

	}
	
	@FXML
	public void incluir () throws IOException {
		Treino treino = new Treino();
		Boolean botaoConfirmarUsado = showDialog(treino);
		if (botaoConfirmarUsado) {
			treinoDAO.create(treino);
			carregarLista();
		}
	}
	
	@FXML
	public void alterar () throws IOException {
		Treino treino = tableViewTreino.getSelectionModel().getSelectedItem();
		if (treino != null) {
			Boolean botaoConfirmarUsado = showDialog(treino);
			if (botaoConfirmarUsado) {
				treinoDAO.update(treino);
				carregarLista();
			}
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Por favor selecione um treino!");
		    alert.show();
		}
		
		
	}
	
	@FXML 
	public void excluir () {
		Treino treino = tableViewTreino.getSelectionModel().getSelectedItem();
		if (treino != null) {
			treinoDAO.delete(treino);
			carregarLista();
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Por favor selecione um treino!");
		    alert.show();
		}
	}
	
	public Boolean showDialog (Treino treino) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FXMLCadastrarTreinoDialog.class.getResource("../view/FXMLCadastrarTreinoDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de treino");
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		FXMLCadastrarTreinoDialog controller = loader.getController();
		controller.setStageDialog(dialogStage);
		controller.setTreino(treino);
		dialogStage.showAndWait();
		
		return controller.getConfirmarClick();
		

	}
}

