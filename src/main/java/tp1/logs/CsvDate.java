package tp1.logs;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.sun.javafx.scene.layout.region.Margins.Converter;

public class CsvDate {
	

	private LocalDateTime dte;
	private LocalTime temps;
	
	public CsvDate(String numero, String mois, String annee, String temps) {
		
		String mm = "00";
		
		switch (mois) {
		case "janv.":
			mm = "01";
			break;
		case "f�vr.":
			mm = "02";
			break;
		case "mars":
			mm = "03";
			break;
		case "avril":
			mm = "04";
			break;
		case "mai":
			mm = "05";
			break;
		case "juin":
			mm = "06";
			break;
		case "juil.":
			mm = "07";
			break;
		case "ao�t":
			mm = "08";
			break;
		case "sept.":
			mm = "09";
			break;
		case "oct.":
			mm = "10";
			break;
		case "nov.":
			mm = "11";
			break;
		case "d�c.":
			mm = "12";
			break;

		default:
			break;
		}
		
		switch (numero) {
		case "1":
			numero = "01";
			break;
		case "2":
			numero = "02";
			break;
		case "3":
			numero = "03";
			break;
		case "4":
			numero = "04";
			break;
		case "5":
			numero = "05";
			break;
		case "6":
			numero = "06";
			break;
		case "7":
			numero = "07";
			break;
		case "8":
			numero = "08";
			break;
		case "9":
			numero = "09";
			break;
		default:
			break;
		}
		
		String d = numero + "-" + mm + "-20" + annee.substring(0, annee.length()-1) + " 00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime dd = LocalDateTime.parse(d, formatter);
		this.dte = dd;
		
		LocalTime l = LocalTime.parse(temps);
		this.temps = l;
	}

	public LocalTime getTemps() {
		return temps;
	}

	public void setTemps(LocalTime temps) {
		this.temps = temps;
	}

	public LocalDateTime getDte() {
		return dte;
	}

	public void setDte(LocalDateTime dte) {
		this.dte = dte;
	}

}
