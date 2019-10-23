package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.AlunoDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Aluno;

public class FXMLCadastrarAlunoDialog implements Initializable {

	@FXML
	private TextField textFildNome;

	@FXML
	private TextField textFieldCpf;

	@FXML
	private TextField textFieldEmail;

	@FXML
	private TextField textFieldTelefone;

	@FXML
	private TextField textFieldEndereco;

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
	private Button botaoConfirmar;

	@FXML
	private Button botaoCancelar;

	private Aluno aluno;
	private Stage stageDialog;
	private Boolean confirmarClick;
	private Boolean isAlterar = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
		this.textFieldCpf.setText(aluno.getCpf());
		this.textFieldEmail.setText(aluno.getEmail());
		this.textFieldEndereco.setText(aluno.getEndereco());
		this.textFieldTelefone.setText(aluno.getTelefone());
		this.textFildNome.setText(aluno.getNome());
		if (aluno.getCpf() != null) {
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
			aluno.setCpf(textFieldCpf.getText());
			aluno.setNome(textFildNome.getText());
			aluno.setEmail(textFieldEmail.getText());
			aluno.setEndereco(textFieldEndereco.getText());
			aluno.setTelefone(textFieldTelefone.getText());

			confirmarClick = true;
			stageDialog.close();
		}
	}

	private Boolean validarCampos() {
		String mensagemErro = "";
		Boolean camposOK = true;

		if (textFieldCpf.getText() == null || textFieldCpf.getText().length() == 0) {
			mensagemErro += "CPF inválido! \n";
			camposOK = false;
		} else {
			if (!isAlterar) {
				AlunoDAO alunoDao = new AlunoDAO();
				Boolean existeCpf = alunoDao.VeficaSeCpfJaExiste(textFieldCpf.getText());
				if (existeCpf) {
					mensagemErro += "CPF já cadastrado! \n";
					camposOK = false;
				}
			}
		}
		if (textFildNome.getText() == null || textFildNome.getText().length() == 0) {
			mensagemErro += "Nome inválido! \n";
			camposOK = false;
		}
		if (textFieldEndereco.getText() == null || textFieldEndereco.getText().length() == 0) {
			mensagemErro += "Endereço inválido! \n";
			camposOK = false;
		}
		if (textFieldTelefone.getText() == null || textFieldTelefone.getText().length() == 0) {
			mensagemErro += "Telefone inválido! \n";
			camposOK = false;
		}
		if (textFieldTelefone.getText() == null || textFieldTelefone.getText().length() == 0) {
			mensagemErro += "E-mail inválido! \n";
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
