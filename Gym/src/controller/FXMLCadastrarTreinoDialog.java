package controller;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import dao.AlunoDAO;
import dao.ExercicioDAO;
import dao.InstrutorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Aluno;
import model.Exercicio;
import model.Instrutor;
import model.Treino;

public class FXMLCadastrarTreinoDialog implements Initializable {
	
	@FXML
    private TextField textFildDescricao;

    @FXML
    private TextField textFieldTurno;

    @FXML
    private DatePicker dataSelecionada;

    @FXML
    private ComboBox<Aluno> comboAluno;

    @FXML
    private ComboBox<Exercicio> comboExercicio;

    @FXML
    private ComboBox<Instrutor> comboInstrutor;
	
	@FXML
	private Button botaoConfirmar;

	@FXML
	private Button botaoCancelar;

	private Treino treino;
	private Stage stageDialog;
	private Boolean confirmarClick;
	private AlunoDAO alunoDao = new AlunoDAO();
	private InstrutorDAO instrutorDao = new InstrutorDAO();
	private ExercicioDAO exercicioDao = new ExercicioDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Aluno> colecaoAluno = alunoDao.Listartodos();
		ArrayList<Instrutor> colecaoInstrutor = instrutorDao.Listartodos();
		ArrayList<Exercicio> colecaoExercicio = exercicioDao.Listartodos();
		ObservableList<Aluno> observableListAluno = FXCollections.observableArrayList(colecaoAluno);
		ObservableList<Exercicio> observableListExercicio = FXCollections.observableArrayList(colecaoExercicio);
		ObservableList<Instrutor> observableListInstrutor = FXCollections.observableArrayList(colecaoInstrutor);
		comboAluno.setItems(observableListAluno);
		comboExercicio.setItems(observableListExercicio);
		comboInstrutor.setItems(observableListInstrutor);
		

	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
		if (treino.getDataTreino() != null) {
		textFieldTurno.setText(treino.getTurno());
		textFildDescricao.setText(treino.getDescricao());
		comboAluno.setValue(treino.getAluno()); 
		comboExercicio.setValue(treino.getExercicio());
		comboInstrutor.setValue(treino.getInstrutor());
		Instant instant = Instant.ofEpochMilli(treino.getDataTreino().getTime());
		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        dataSelecionada.setValue(localDate);
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
			treino.setAluno(comboAluno.getValue());
			treino.setExercicio(comboExercicio.getValue());
			treino.setInstrutor(comboInstrutor.getValue());
			LocalDate ld = dataSelecionada.getValue();
            Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            treino.setDataTreino(Date.from(instant));
            treino.setTurno(textFieldTurno.getText());
            treino.setDescricao(textFildDescricao.getText());
            confirmarClick = true;
			stageDialog.close();
		}
	}

	private Boolean validarCampos() {
		String mensagemErro = "";
		Boolean camposOK = true;
		if (textFildDescricao.getText().length() == 0 || textFildDescricao.getText() == null) {
			camposOK = false;
			mensagemErro += "O campo descrição é obrigatorio! \n";
		}
		if (textFieldTurno.getText().length() == 0 || textFieldTurno.getText() == null) {
			camposOK = false;
			mensagemErro += "O campo turno é obrigatorio! \n";
		}
		if (dataSelecionada.getValue() == null) {
			camposOK = false;
			mensagemErro += "selecione uma Data!\n";
		}
		if (comboAluno.getValue() == null) {
			camposOK = false;
			mensagemErro += "selecione um Aluno!\n";
		}
		if (comboExercicio.getValue() == null) {
			camposOK = false;
			mensagemErro += "selecione um Exercicio!\n";
		}
		if (comboInstrutor.getValue() == null) {
			camposOK = false;
			mensagemErro += "selecione um Instrutor!\n";
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
