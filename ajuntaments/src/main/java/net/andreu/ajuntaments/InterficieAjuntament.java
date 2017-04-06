package net.andreu.ajuntaments;

import java.util.List;

public interface InterficieAjuntament {
	
	public void tancaConnexio();
	
	public List<Ajuntament> obtenirAjuntaments();
	
	public void insertarAjuntaments(List<Ajuntament> llistaAjuntamnts);
}
