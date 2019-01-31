package fr.ul.miage.papis.florent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Class1 extends Application {
	
	private static final Logger Log = Logger.getLogger(Class1.class.getName());

	Button btn = new Button("Lecture du fichier CSV");
	TextArea ta = new TextArea();
	DatePicker db = new DatePicker();
	static FileReader file = null;
	BufferedReader tampon = null;

	public void start(Stage primaryStage) {
		btn.setOnAction(this::handleButtonAction);
		
		BorderPane root = new BorderPane();
		root.setBottom(btn);
		BorderPane.setAlignment(btn, Pos.BOTTOM_CENTER);
		root.setCenter(ta);
		root.setRight(db);
		Scene scene = new Scene(root, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Application du TP1");
		primaryStage.show();
	}
	
	//action realisee si clic sur bouton
	private void handleButtonAction(ActionEvent event) {	
		try {
			file = new FileReader("samples\\data.csv");
			CSVParser p = Class1.buildCSVParser();
			for(CSVRecord r : p) {
				String id = r.get(0);
				String heure = r.get(1);
				ta.appendText(id+" : "+heure+"\n");
			}
		}
		catch (IOException e) {
			Log.severe("Erreur de lecture dans le ficier CSV");
		}
	}
	
	public static CSVParser buildCSVParser() throws IOException{
		CSVParser res = null;
		CSVFormat csvf = CSVFormat.DEFAULT.withDelimiter(';');
		res = new CSVParser(file, csvf);
		return res;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
