package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class FXMLInicioController implements Initializable {
	@FXML
    private MenuItem menuItemAluno;

    @FXML
    private MenuItem menuItemInstrutor;
    
    @FXML
    private MenuItem menuItemExercicio;
    
    @FXML
    private MenuItem menuItemTreino;

    @FXML
    private AnchorPane ancorPane;
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	@FXML
	public void abrirMenuItemCadastroInstrutor(ActionEvent event) throws IOException {
		AnchorPane pane = (AnchorPane) 
				FXMLLoader.load(getClass().getResource("../view/FXMLCadastrarInstrutor.fxml"));
		ancorPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void abrirMenuItemCadastroAluno(ActionEvent event) throws IOException {
		AnchorPane pane = (AnchorPane) 
				FXMLLoader.load(getClass().getResource("../view/FXMLCadastrarAluno.fxml"));
		ancorPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void abrirMenuItemCadastroExercicio(ActionEvent event) throws IOException {
		AnchorPane pane = (AnchorPane) 
				FXMLLoader.load(getClass().getResource("../view/FXMLCadastrarExercicio.fxml"));
		ancorPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void abrirMenuItemCadastroTreino(ActionEvent event) throws IOException {
		AnchorPane pane = (AnchorPane) 
				FXMLLoader.load(getClass().getResource("../view/FXMLCadastrarTreino.fxml"));
		ancorPane.getChildren().setAll(pane);
	}
	
	@FXML
	public void abrirMenuItemTreinoAluno(ActionEvent event) throws IOException {
		AnchorPane pane = (AnchorPane) 
				FXMLLoader.load(getClass().getResource("../view/FXMLAlunoTreino.fxml"));
		ancorPane.getChildren().setAll(pane);
	}

	@FXML
	public void abrirMenuItemTreinoInstrutor(ActionEvent event) throws IOException {
		AnchorPane pane = (AnchorPane) 
				FXMLLoader.load(getClass().getResource("../view/FXMLInstrutorTreino.fxml"));
		ancorPane.getChildren().setAll(pane);
	}
    

}
