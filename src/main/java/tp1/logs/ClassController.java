package tp1.logs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

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
	@FXML
	private Button btnAfficher;
	
	Map<String, ArrayList<CsvDate>> map = new HashMap<String, ArrayList<CsvDate>>();
	int compteur = 0;
	int nbAction = 0;
	Comparator c;
	private static final Logger Log = Logger.getLogger(ClassController.class.getName());
	static FileReader file = null;
	BufferedReader tampon = null;
	
	public void analyserFichier(ActionEvent e) {
		try {
			file = new FileReader("samples\\data.csv");
			CSVParser p = ClassController.buildCSVParser();
			for(CSVRecord r : p) 
			{
				if (compteur != 0) 
				{
					String id = r.get(0);
					
					String dteheure = r.get(1);				
					String[] tokens = dteheure.split(" ");
					CsvDate dte = new CsvDate(tokens[0], tokens[1], tokens[2], tokens[3]);
					
					if(dte.getDte().isAfter(LocalDateTime.of(dateDebut.getValue(), LocalTime.MIN)) && dte.getDte().isBefore(LocalDateTime.of(dateFin.getValue(), LocalTime.MIN))) {
						nbAction++;
						
						if(!idEtudiant.getItems().contains(id))
						{
							idEtudiant.getItems().add(id);
						}
						
						if (this.map.containsKey(id)) 
						{
							ArrayList al = this.map.get(id);
							al.add(dte);
							this.map.remove(id);
							this.map.put(id, al);
						}
						else 
						{
							ArrayList<CsvDate> dt = new ArrayList<CsvDate>();
							dt.add(dte);
							this.map.put(id, dt);
						}
					}
				}
				compteur++;			
			}
			idEtudiant.getItems().sort(c);
			dateDebut.setDisable(true);
			dateFin.setDisable(true);
			btnAnalyser.setDisable(true);
		}
		catch (IOException o) {
			Log.severe("Erreur de lecture dans le ficier CSV");
		}
	}
	
	public void afficherInfos() {
		ta.clear();
		
		ta.appendText("Nombre d'actions sur la période sélectionnée : " + nbAction);
		
		ta.appendText("\nNombre d'étudiants sur la période sélectionnée : " + idEtudiant.getItems().size());
		
		ta.appendText("\nMoyenne du nombre d'action par étudiant sur la période sélectionnée : " + nbAction/idEtudiant.getItems().size());
		
		ta.appendText("\nNombre d'action de l'étudiant sélectionné sur la période sélectionnée : " + this.map.get(idEtudiant.getValue()).size());
		
	}
	
	public static CSVParser buildCSVParser() throws IOException{
		CSVParser res = null;
		CSVFormat csvf = CSVFormat.DEFAULT.withDelimiter(';');
		res = new CSVParser(file, csvf);
		return res;
	}
}
