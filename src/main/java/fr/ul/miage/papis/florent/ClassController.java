package fr.ul.miage.papis.florent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class ClassController {
	@FXML
	private DatePicker dateDebut;
	@FXML
	private DatePicker dateFin;
	@FXML
	private TextArea ta;
	@FXML
	private Button btnAnalyser;
	@FXML
	private ComboBox idEtudiant;
	
	
	
	private int[] id_tab;
	private Date[] heure_tab;
	int compteur = 0;
	
	private static final Logger Log = Logger.getLogger(ClassController.class.getName());
	static FileReader file = null;
	BufferedReader tampon = null;
	
	public void analyserFichier(ActionEvent e) {
		try {
			file = new FileReader("samples\\data - Copie.csv");
			CSVParser p = ClassController.buildCSVParser();
			for(CSVRecord r : p) {
				compteur++;
				this.id_tab[compteur] = Integer.parseInt(r.get(0));
				
				//ta.appendText(id+" : "+heure+"\n");
			}
			System.out.println(compteur);
		}
		catch (IOException o) {
			Log.severe("Erreur de lecture dans le ficier CSV");
		}
	}
	
	public static CSVParser buildCSVParser() throws IOException{
		CSVParser res = null;
		CSVFormat csvf = CSVFormat.DEFAULT.withDelimiter(';');
		res = new CSVParser(file, csvf);
		return res;
	}
}
