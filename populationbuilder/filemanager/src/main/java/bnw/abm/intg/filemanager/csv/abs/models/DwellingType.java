package bnw.abm.intg.filemanager.csv.abs.models;

public class DwellingType {
	final private String type;
	final private int dwellings;

	public DwellingType(String type, String dwellCount) {
		this.type = type;
		this.dwellings = Integer.parseInt(dwellCount);
	}

	public String type() {
		return this.type;
	}

	public int dwellings() {
		return this.dwellings;
	}

}