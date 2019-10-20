package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ExercicioDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Exercicio;

public class FXMLCadastrarExercicioDialog implements Initializable {

	@FXML
	private TextField textFildNome;

	@FXML
	private TextField textFieldAgrupcmento;

	@FXML
	private TextArea textAreadDescricao;


	@FXML
	private Button botaoConfirmar;

	@FXML
	private Button botaoCancelar;

	private Exercicio exercicio;
	private Stage stageDialog;
	private Boolean confirmarClick;
	private Boolean isAlterar = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
		this.textAreadDescricao.setText(exercicio.getDescricao());
		this.textFieldAgrupcmento.setText(exercicio.getAgrupamentoMuscular());
		this.textFildNome.setText(exercicio.getNome());
		if (exercicio.getNome() != null) {
			this.isAlterar = true;
		}
	}

	public Stage getStageDialog() {
		return stageDialog;
	}

	public void setStageDialog(Stage stageDialog) {
		this.stageDialog = stageDialog;
	}

	public Boolean getConfirmarClick() {
		return confirmarClick;
	}

	public void setConfirmarClick(Boolean confirmarClick) {
		this.confirmarClick = confirmarClick;
	}

	@FXML
	public void confirmar() {
		if (validarCampos()) {
			exercicio.setNome(textFildNome.getText());
			exercicio.setAgrupamentoMuscular(textFieldAgrupcmento.getText());
			exercicio.setDescricao(textAreadDescricao.getText());
			confirmarClick = true;
			stageDialog.close();
		}
	}

	private Boolean validarCampos() {
		String mensagemErro = "";
		Boolean camposOK = true;

		if (textFildNome.getText() == null || textFildNome.getText().length() == 0) {
			mensagemErro += "Nome inválido! \n";
			camposOK = false;
		} else {
			if(!isAlterar){
				ExercicioDAO exercicioDAO = new ExercicioDAO();
				if (exercicioDAO.verificarSeJaExiste(exercicio.getNome())) {
					camposOK = false;
					mensagemErro = "Nome de exercicio ja cadastrado, por faor informe outro nome!";
				}
			}
		}
		if (!camposOK) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Erro no cadastro");
			alert.setContentText(mensagemErro);
			alert.show();
		}

		return camposOK;
	}

	@FXML
	public void cancelar() {
		confirmarClick = false;
		stageDialog.close();
	}

}
