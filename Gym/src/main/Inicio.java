package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Inicio extends Application {


	public void start(Stage palco) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLInicio.fxml"));
		Scene cena = new Scene(root);
		palco.setScene(cena);
		palco.setTitle("Cross Full");
		palco.setResizable(false);
		palco.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
