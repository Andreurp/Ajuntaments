package net.andreu.ajuntaments;

public class Ajuntament {

	private String id;
	private String municipi;
	private String talefon;
	private String president;
	private String partit;
	private String cens;
	private String codiPostal;
	
	public Ajuntament(String id, String municipi, String talefon, String president, String partit, String cens, String codiPostal) {
		this.id = id;
		this.municipi = municipi;
		this.talefon = talefon;
		this.president = president;
		this.partit = partit;
		this.cens = cens;
		this.codiPostal = codiPostal;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMunicipi() {
		return municipi;
	}

	public void setMunicipi(String municipi) {
		this.municipi = municipi;
	}

	public String getTalefon() {
		return talefon;
	}

	public void setTalefon(String talefon) {
		this.talefon = talefon;
	}

	public String getPresident() {
		return president;
	}

	public void setPresident(String president) {
		this.president = president;
	}

	public String getPartit() {
		return partit;
	}

	public void setPartit(String partit) {
		this.partit = partit;
	}

	public String getCens() {
		return cens;
	}

	public void setCens(String cens) {
		this.cens = cens;
	}

	public String getCodiPostal() {
		return codiPostal;
	}

	public void setCodiPostal(String codiPostal) {
		this.codiPostal = codiPostal;
	}
	
	
}
