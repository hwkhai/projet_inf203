/**
 * 
 */
package com.csdnes.utils;

import java.util.ArrayList;

/**
 * @author weiliu
 *
 */
public class CIM10 {

	private String CodeCIM10;
	private String Libelle_FR;
	private String Libelle_EN;
	private ArrayList<Diagnostic> listeDiagnostics =new ArrayList<Diagnostic>();
	
	
	
	/**
	 * @param codeCIM10
	 * @param libelle_FR
	 * @param libelle_EN
	 * @param listeDiagnostics
	 */
	public CIM10(String codeCIM10, String libelle_FR, String libelle_EN, ArrayList<Diagnostic> listeDiagnostics) {
		super();
		CodeCIM10 = codeCIM10;
		Libelle_FR = libelle_FR;
		Libelle_EN = libelle_EN;
		this.listeDiagnostics = listeDiagnostics;
	}
	/**
	 * @return the libelle_FR
	 */
	public String getLibelle_FR() {
		return Libelle_FR;
	}
	/**
	 * @param libelle_FR the libelle_FR to set
	 */
	public void setLibelle_FR(String libelle_FR) {
		Libelle_FR = libelle_FR;
	}
	/**
	 * @return the libelle_EN
	 */
	public String getLibelle_EN() {
		return Libelle_EN;
	}
	/**
	 * @param libelle_EN the libelle_EN to set
	 */
	public void setLibelle_EN(String libelle_EN) {
		Libelle_EN = libelle_EN;
	}
	/**
	 * 
	 */
	public CIM10() {
		super();
	}

	/**
	 * @return the codeCIM10
	 */
	public String getCodeCIM10() {
		return CodeCIM10;
	}
	/**
	 * @param codeCIM10 the codeCIM10 to set
	 */
	public void setCodeCIM10(String codeCIM10) {
		CodeCIM10 = codeCIM10;
	}

	/**
	 * @return the listeDiagnostics
	 */
	public ArrayList<Diagnostic> getListeDiagnostics() {
		return listeDiagnostics;
	}
	/**
	 * @param listeDiagnostics the listeDiagnostics to set
	 */
	public void setListeDiagnostics(ArrayList<Diagnostic> listeDiagnostics) {
		this.listeDiagnostics = listeDiagnostics;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CIM10 [CodeCIM10=" + CodeCIM10 + ", Libelle_FR=" + Libelle_FR + ", Libelle_EN=" + Libelle_EN
				+ ", listeDiagnostics=" + listeDiagnostics + "]";
	}

	
	
	
	
}
