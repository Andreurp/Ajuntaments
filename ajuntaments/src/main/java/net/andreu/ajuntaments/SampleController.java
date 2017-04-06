package net.andreu.ajuntaments;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.csvreader.CsvReader;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SampleController implements Initializable {
	@FXML
	private ComboBox<String> cbxAjuntament;
	@FXML
	private Button btnCarregar;
	@FXML
	private Button btnSortir;
	@FXML
	private TextField tbxAlcade;
	@FXML
	private TextField tbxPartit;
	@FXML
	private TextField tbxTelefon;
	@FXML
	private TextField tbxPoblacio;
	@FXML
	private TextField tbxCodiPostal;

	private List<Ajuntament> llistaAjuntamentsDB = new ArrayList<Ajuntament>();
	InterficieAjuntament repositori = new ConexioMYSQL();

	public void initialize(URL arg0, ResourceBundle arg1) {
		ompleCbx();
	}

	public void ompleCbx() {
		llistaAjuntamentsDB = repositori.obtenirAjuntaments();
		
		for (Ajuntament a : llistaAjuntamentsDB) {
			cbxAjuntament.getItems().add(a.getMunicipi());
		}
		
	}

	// Event Listener on ComboBox[#cbxAjuntament].onAction
	@FXML
	public void ConsultarDades(ActionEvent event) {
		String nomMunicipi = cbxAjuntament.getValue();
		int posicioAjuntament = 0;
		
		while (!nomMunicipi.equals(llistaAjuntamentsDB.get(posicioAjuntament).getMunicipi()) && posicioAjuntament < llistaAjuntamentsDB.size()) {
			posicioAjuntament++;
		}
		
		tbxAlcade.setText(llistaAjuntamentsDB.get(posicioAjuntament).getPresident());
		tbxPartit.setText(llistaAjuntamentsDB.get(posicioAjuntament).getPartit());
		tbxTelefon.setText(llistaAjuntamentsDB.get(posicioAjuntament).getTalefon());
		tbxPoblacio.setText(llistaAjuntamentsDB.get(posicioAjuntament).getCens());
		tbxCodiPostal.setText(llistaAjuntamentsDB.get(posicioAjuntament).getCodiPostal());
	}

	// Event Listener on Button[#btnCarregar].onMouseClicked
	@FXML
	public void CarregaDades(MouseEvent event) {
		List<Ajuntament> llistaAjuntamntsCsv = new ArrayList<Ajuntament>();

		String idCsv = null;
		String municipiCsv = null;
		String talefonCsv = null;
		String presidentCsv = null;
		String partitCsv = null;
		String censCsv = null;
		String codiPostalCsv = null;

		FileChooser buscador = new FileChooser();
		buscador.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
		File selectedFile = buscador.showOpenDialog(null);

		if (selectedFile != null) {
			try {
				CsvReader File = new CsvReader(selectedFile.getAbsolutePath(), ',', Charset.defaultCharset());
				File.readHeaders();

				while (File.readRecord()) {
					idCsv = File.get("CODIENS");
					municipiCsv = File.get("MUNICIPI");
					talefonCsv = File.get("TELEFON");
					presidentCsv = File.get("PRESIDENT");
					partitCsv = File.get("PARTITPOL");
					censCsv = File.get("CENS");
					codiPostalCsv = File.get("CODIPOSTAL");

					llistaAjuntamntsCsv.add(new Ajuntament(idCsv, municipiCsv, talefonCsv, presidentCsv, partitCsv, censCsv, codiPostalCsv));
				}
				File.close();

				repositori.insertarAjuntaments(llistaAjuntamntsCsv);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("No se ha seleccionat cap fitxer.");
			alert.showAndWait();
		}
		ompleCbx();
	}

	// Event Listener on Button[#btnSortir].onMouseClicked
	@FXML
	public void Sortir(MouseEvent event) {
		Platform.exit();
	}
}
