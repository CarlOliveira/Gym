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
    private AnchorPane ancorPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	@FXML
	public void abrirMenuItemCadastroAluno(ActionEvent event) throws IOException {
		AnchorPane pane = (AnchorPane) 
				FXMLLoader.load(getClass().getResource("../view/FXMLCadastrarAluno.fxml"));
		ancorPane.getChildren().setAll(pane);
	}
    	
    

}
