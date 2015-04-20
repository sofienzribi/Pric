package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DataUtile
 *
 */
@Entity

public class DataUtile implements Serializable {

	
	private int id;
	private String classs;
	private String occupancy;
	private int PD;
	private int BI;
	private int MB;
	private int MLoP;
	private int DedPD;
	private int DedBI;
	private String PDCurve;
	private String MBCurve;
	private int RiskClass;

	
	private static final long serialVersionUID = 1L;

	public DataUtile() {
		super();
	}   
	@Id    
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getClasss() {
		return this.classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}   
	public String getOccupancy() {
		return this.occupancy;
	}

	public void setOccupancy(String occupancy) {
		this.occupancy = occupancy;
	}
	public int getPD() {
		return PD;
	}
	public void setPD(int pD) {
		PD = pD;
	}
	public int getBI() {
		return BI;
	}
	public void setBI(int bI) {
		BI = bI;
	}
	public int getMB() {
		return MB;
	}
	public void setMB(int mB) {
		MB = mB;
	}
	public int getMLoP() {
		return MLoP;
	}
	public void setMLoP(int mLoP) {
		MLoP = mLoP;
	}
	public int getDedPD() {
		return DedPD;
	}
	public void setDedPD(int dedPD) {
		DedPD = dedPD;
	}
	public int getDedBI() {
		return DedBI;
	}
	public void setDedBI(int dedBI) {
		DedBI = dedBI;
	}
	public String getPDCurve() {
		return PDCurve;
	}
	public void setPDCurve(String pDCurve) {
		PDCurve = pDCurve;
	}
	public String getMBCurve() {
		return MBCurve;
	}
	public void setMBCurve(String mBCurve) {
		MBCurve = mBCurve;
	}
	public int getRiskClass() {
		return RiskClass;
	}
	public void setRiskClass(int riskClass) {
		RiskClass = riskClass;
	}
		
   
}
