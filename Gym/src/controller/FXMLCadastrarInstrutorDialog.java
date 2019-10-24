package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.InstrutorDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Instrutor;

public class FXMLCadastrarInstrutorDialog implements Initializable {

	@FXML
	private TextField textFieldNome;

	@FXML
	private TextField textFieldCpf;

	@FXML
	private TextField textFieldEmail;

	@FXML
	private TextField textFieldTelefone;

	@FXML
	private TextField textFieldEndereco;
	
	@FXML
	private TextField textFieldTurno;

	@FXML
	private Label labelNome;

	@FXML
	private Label labelCpf;

	@FXML
	private Label labelEmail;

	@FXML
	private Label labelTelefone;

	@FXML
	private Label labelEndereco;
	
	@FXML
	private Label labelTurno;

	@FXML
	private Button botaoConfirmar;

	@FXML
	private Button botaoCancelar;

	private Instrutor instrutor;
	private Stage stageDialog;
	private Boolean confirmarClick;
	private Boolean isAlterar = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
		this.textFieldCpf.setText(instrutor.getCpf());
		this.textFieldEmail.setText(instrutor.getEmail());
		this.textFieldEndereco.setText(instrutor.getEndereco());
		this.textFieldTelefone.setText(instrutor.getTelefone());
		this.textFieldNome.setText(instrutor.getNome());
		this.textFieldTurno.setText(instrutor.getTurno());
		if (instrutor.getCpf() != null) {
			this.isAlterar = true;
			textFieldCpf.setDisable(true);
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
			instrutor.setCpf(textFieldCpf.getText());
			instrutor.setNome(textFieldNome.getText());
			instrutor.setEmail(textFieldEmail.getText());
			instrutor.setEndereco(textFieldEndereco.getText());
			instrutor.setTelefone(textFieldTelefone.getText());
			instrutor.setTurno(textFieldTurno.getText());
			confirmarClick = true;
			stageDialog.close();
		}
	}

	private Boolean validarCampos() {
		String mensagemErro = "";
		Boolean camposOK = true;

		if (textFieldCpf.getText() == null || textFieldCpf.getText().length() == 0) {
			mensagemErro += "CPF inv�lido! \n";
			camposOK = false;
		} else {
			if (!isAlterar) {
				InstrutorDAO instrutorDao = new InstrutorDAO();
				Boolean existeCpf = instrutorDao.VeficaSeCpfJaExiste(textFieldCpf.getText());
				if (existeCpf) {
					mensagemErro += "CPF j� cadastrado! \n";
					camposOK = false;
				}
			}
		}
		if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
			mensagemErro += "Nome inv�lido! \n";
			camposOK = false;
		}
		if (textFieldEndereco.getText() == null || textFieldEndereco.getText().length() == 0) {
			mensagemErro += "Endere�o inv�lido! \n";
			camposOK = false;
		}
		if (textFieldTelefone.getText() == null || textFieldTelefone.getText().length() == 0) {
			mensagemErro += "Telefone inv�lido! \n";
			camposOK = false;
		}
		if (textFieldTelefone.getText() == null || textFieldTelefone.getText().length() == 0) {
			mensagemErro += "E-mail inv�lido! \n";
			camposOK = false;
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