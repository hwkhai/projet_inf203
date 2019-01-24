/**
 * 
 */
package com.csdnes.utils;



/**
 * @author weiliu
 *
 */
public class Diagnostic {
	private int NumDiagnostic;
	private int NumHospitalisation;
	private String CodeCIM10;
	private int DGRang;
	private String DGType;
	
	/**
	 * 
	 */
	public Diagnostic() {
		super();
	}
	/**
	 * @param numDiagnostic
	 * @param numHospitalisation
	 * @param codeCIM10
	 * @param dGRang
	 * @param dGType
	 */
	public Diagnostic(int numDiagnostic, int numHospitalisation, String codeCIM10, int dGRang, String dGType) {
		super();
		NumDiagnostic = numDiagnostic;
		NumHospitalisation = numHospitalisation;
		CodeCIM10 = codeCIM10;
		DGRang = dGRang;
		DGType = dGType;
	}
	/**
	 * @return the numDiagnostic
	 */
	public int getNumDiagnostic() {
		return NumDiagnostic;
	}
	/**
	 * @param numDiagnostic the numDiagnostic to set
	 */
	public void setNumDiagnostic(int numDiagnostic) {
		NumDiagnostic = numDiagnostic;
	}
	/**
	 * @return the numHospitalisation
	 */
	public int getNumHospitalisation() {
		return NumHospitalisation;
	}
	/**
	 * @param numHospitalisation the numHospitalisation to set
	 */
	public void setNumHospitalisation(int numHospitalisation) {
		NumHospitalisation = numHospitalisation;
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
	 * @return the dGRang
	 */
	public int getDGRang() {
		return DGRang;
	}
	/**
	 * @param dGRang the dGRang to set
	 */
	public void setDGRang(int dGRang) {
		DGRang = dGRang;
	}
	/**
	 * @return the dGType
	 */
	public String getDGType() {
		return DGType;
	}
	/**
	 * @param dGType the dGType to set
	 */
	public void setDGType(String dGType) {
		DGType = dGType;
	}

	
	

}
