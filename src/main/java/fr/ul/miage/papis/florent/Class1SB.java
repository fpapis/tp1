package fr.ul.miage.papis.florent;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Class1SB extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Class1.fxml"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
