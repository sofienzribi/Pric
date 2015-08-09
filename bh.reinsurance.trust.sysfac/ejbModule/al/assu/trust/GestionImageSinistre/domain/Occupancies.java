package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.lang.Double;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Occupancies
 *
 */
@Entity

public class Occupancies implements Serializable {

	
	private int id;
	private String cob;
	private String occupancy;
	private double PD;
	private double BI;
	private double MB;
	private double MLOP;
	private double DedPD;
	private double DedBI;
	private String PDcurve;
	private String MBcurve;
	private int RiskClass;
	private static final long serialVersionUID = 1L;

	public Occupancies() {
		super();
	}   
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getCob() {
		return this.cob;
	}

	public void setCob(String cob) {
		this.cob = cob;
	}   
	public String getOccupancy() {
		return this.occupancy;
	}

	public void setOccupancy(String occupancy) {
		this.occupancy = occupancy;
	}
	public double getPD() {
		return PD;
	}
	public void setPD(double pD) {
		PD = pD;
	}
	public double getBI() {
		return BI;
	}
	public void setBI(double bI) {
		BI = bI;
	}
	public double getMB() {
		return MB;
	}
	public void setMB(double mB) {
		MB = mB;
	}
	public double getMLOP() {
		return MLOP;
	}
	public void setMLOP(double mLOP) {
		MLOP = mLOP;
	}
	public double getDedPD() {
		return DedPD;
	}
	public void setDedPD(double dedPD) {
		DedPD = dedPD;
	}
	public double getDedBI() {
		return DedBI;
	}
	public void setDedBI(double dedBI) {
		DedBI = dedBI;
	}
	public String getPDcurve() {
		return PDcurve;
	}
	public void setPDcurve(String pDcurve) {
		PDcurve = pDcurve;
	}
	public String getMBcurve() {
		return MBcurve;
	}
	public void setMBcurve(String mBcurve) {
		MBcurve = mBcurve;
	}
	public int getRiskClass() {
		return RiskClass;
	}
	public void setRiskClass(int riskClass) {
		RiskClass = riskClass;
	}   

	
   
}
