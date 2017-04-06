package net.andreu.ajuntaments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConexioMYSQL implements InterficieAjuntament {

	private Connection con = null;
	private PreparedStatement pre;
	private Statement consulta;

	public ConexioMYSQL() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajuntaments", "ball", "foot");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void tancaConnexio() {
		// TODO Auto-generated method stub

	}

	public List<Ajuntament> obtenirAjuntaments() {
		List<Ajuntament> llistaAjuntamentsDB = new ArrayList<Ajuntament>();

		try {
			consulta = con.createStatement();
			ResultSet resultat = consulta.executeQuery("SELECT * FROM dades");
			while (resultat.next()) {
				llistaAjuntamentsDB.add(new Ajuntament(	resultat.getString("id"), 
														resultat.getString("municipi"),
														resultat.getString("telefon"), 
														resultat.getString("president"), 
														resultat.getString("partit"),
														resultat.getString("cens"), 
														resultat.getString("codiPostal")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return llistaAjuntamentsDB;
	}

	public void insertarAjuntaments(List<Ajuntament> llistaAjuntamntsCsv) {
		try {

			pre = con.prepareStatement("INSERT INTO dades VALUES (?, ?, ?, ?, ?, ?, ?);");

			for (Ajuntament a : llistaAjuntamntsCsv) {

				pre.setString(1, a.getId());
				pre.setString(2, a.getMunicipi());
				pre.setString(3, a.getTalefon());
				pre.setString(4, a.getPresident());
				pre.setString(5, a.getPartit());
				pre.setString(6, a.getCens());
				pre.setString(7, a.getCodiPostal());
				pre.execute();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
