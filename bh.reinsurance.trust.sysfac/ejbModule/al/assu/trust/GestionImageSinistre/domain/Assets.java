package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Assets
 * 
 */
@Entity
public class Assets implements Serializable {

	private int id;
	private String COB;
	private String Occupancy;
	private String Country;
	private int idproject;
	private int loc;
	private double C;
	private double Rate_Adj;

	private double EML;

	private String PD_Curve;
	private double PD_SI;
	private double PD_PML;
	private double PD_PML_RATE;
	private double PD_Rate;
	private double PD_Base_Premium;
	private double PD_PML_Adjusted_Premium;
	private double PD_Discount_premium;
	private double PD_DeductibleB;

	private String MB_Curve;
	private double MB_SI;
	private double MB_PML;
	private double MB_PML_RATE;
	private double MB_Rate;
	private double MB_Base_Premium;
	private double MB_PML_Adjusted_Premium;
	private double MB_Discount_premium;
	private double MB_Dedpremium;

	private double BI_SI;
	private String BI_Curve;
	private double BI_PML_RATE;
	private double BI_PML;
	private double BI_Rate;
	private double BI_Base_Premium;
	private double BI_PML_Adjusted_Premium;
	private double BI_Discount_premium;
	private double BI_Dedpremium;

	private double Mlop_SI;
	private double Mlop_PML_RATE;
	private double Mlop_PML;
	private double Mlop_Rate;
	private double Mlop_Base_Premium;
	private double Mlop_PML_Adjusted_Premium;
	private double Mlop_Discount_premium;
	private double Mlop_Dedpremium;

	private double Stock_SI;
	private double Stock_PML;
	private double Stock_RATE;
	private double Stock_PML_RATE;
	private double Stock_Base_Premium;
	private double Stock_PML_Adjusted_Premium;
	private double Stock_Discount_premium;
	private double Stock_Dedpremium;

	private double OTHER_SI;
	private double OTHER_PML;
	private double OTHER_Rate;
	private double OTHER_PML_RATE;
	private double OTHER_Base_Premium;
	private double OTHER_PML_Adjusted_Premium;
	private double OTHER_Discount_premium;
	private double OTHER_Dedpremium;

	private double OPDFac;
	private double LY1Fac;
	private double LY2Fac;
	private double LY3Fac;
	private double LY4Fac;
	private double LY5Fac;

	private static final long serialVersionUID = 1L;

	public Assets() {
		super();
		Country="Benin";
		Rate_Adj=0;
		PD_Curve = "Medium";
		PD_PML_RATE = 100;
		PD_Rate=0.2;
		BI_Curve= "Medium";
		BI_PML_RATE=100;
		BI_Rate=0.44;
		MB_Curve="Low";
		MB_PML_RATE=100;
		MB_Rate=0.31;
		Mlop_PML_RATE=100;
		Mlop_Rate=0.27;
		Stock_PML_RATE=100;
		Stock_RATE=0.2;
		OTHER_Rate=0.2;
		OTHER_PML_RATE=100;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLoc() {
		return this.loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public int getIdproject() {
		return idproject;
	}

	public void setIdproject(int idproject) {
		this.idproject = idproject;
	}

	public String getCOB() {
		return COB;
	}

	public void setCOB(String cOB) {
		COB = cOB;
	}

	public String getOccupancy() {
		return Occupancy;
	}

	public void setOccupancy(String occupancy) {
		Occupancy = occupancy;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public double getPD_PML_RATE() {
		return PD_PML_RATE;
	}

	public void setPD_PML_RATE(double pD_PML_RATE) {
		PD_PML_RATE = pD_PML_RATE;
	}

	public double getPD_SI() {
		return PD_SI;
	}

	public void setPD_SI(double pD_SI) {
		PD_SI = pD_SI;
	}

	public double getPD_PML() {
		return PD_PML;
	}

	public void setPD_PML(double pD_PML) {
		PD_PML = pD_PML;
	}

	public double getPD_Rate() {
		return PD_Rate;
	}

	public void setPD_Rate(double pD_Rate) {
		PD_Rate = pD_Rate;
	}

	public String getPD_Curve() {
		return PD_Curve;
	}

	public void setPD_Curve(String pD_Curve) {
		PD_Curve = pD_Curve;
	}

	public double getMB_SI() {
		return MB_SI;
	}

	public void setMB_SI(double mB_SI) {
		MB_SI = mB_SI;
	}

	public double getMB_PML() {
		return MB_PML;
	}

	public void setMB_PML(double mB_PML) {
		MB_PML = mB_PML;
	}

	public double getMB_Rate() {
		return MB_Rate;
	}

	public void setMB_Rate(double mB_Rate) {
		MB_Rate = mB_Rate;
	}

	public double getMB_PML_RATE() {
		return MB_PML_RATE;
	}

	public void setMB_PML_RATE(double mB_PML_RATE) {
		MB_PML_RATE = mB_PML_RATE;
	}

	public String getMB_Curve() {
		return MB_Curve;
	}

	public void setMB_Curve(String mB_Curve) {
		MB_Curve = mB_Curve;
	}

	public double getBI_SI() {
		return BI_SI;
	}

	public void setBI_SI(double bI_SI) {
		BI_SI = bI_SI;
	}

	public String getBI_Curve() {
		return BI_Curve;
	}

	public void setBI_Curve(String bI_Curve) {
		BI_Curve = bI_Curve;
	}

	public double getBI_PML_RATE() {
		return BI_PML_RATE;
	}

	public void setBI_PML_RATE(double bI_PML_RATE) {
		BI_PML_RATE = bI_PML_RATE;
	}

	public double getBI_PML() {
		return BI_PML;
	}

	public void setBI_PML(double bI_PML) {
		BI_PML = bI_PML;
	}

	public double getBI_Rate() {
		return BI_Rate;
	}

	public void setBI_Rate(double bI_Rate) {
		BI_Rate = bI_Rate;
	}

	public double getOTHER_SI() {
		return OTHER_SI;
	}

	public void setOTHER_SI(double oTHER_SI) {
		OTHER_SI = oTHER_SI;
	}

	public double getOTHER_PML() {
		return OTHER_PML;
	}

	public void setOTHER_PML(double oTHER_PML) {
		OTHER_PML = oTHER_PML;
	}

	public double getOTHER_Rate() {
		return OTHER_Rate;
	}

	public void setOTHER_Rate(double oTHER_Rate) {
		OTHER_Rate = oTHER_Rate;
	}

	public double getOTHER_PML_RATE() {
		return OTHER_PML_RATE;
	}

	public void setOTHER_PML_RATE(double oTHER_PML_RATE) {
		OTHER_PML_RATE = oTHER_PML_RATE;
	}

	public double getMlop_SI() {
		return Mlop_SI;
	}

	public void setMlop_SI(double mlop_SI) {
		Mlop_SI = mlop_SI;
	}

	
	public double getMlop_PML_RATE() {
		return Mlop_PML_RATE;
	}

	public void setMlop_PML_RATE(double mlop_PML_RATE) {
		Mlop_PML_RATE = mlop_PML_RATE;
	}

	public double getMlop_PML() {
		return Mlop_PML;
	}

	public void setMlop_PML(double mlop_PML) {
		Mlop_PML = mlop_PML;
	}

	public double getMlop_Rate() {
		return Mlop_Rate;
	}

	public void setMlop_Rate(double mlop_Rate) {
		Mlop_Rate = mlop_Rate;
	}

	public double getStock_SI() {
		return Stock_SI;
	}

	public void setStock_SI(double stock_SI) {
		Stock_SI = stock_SI;
	}

	public double getStock_PML() {
		return Stock_PML;
	}

	public void setStock_PML(double stock_PML) {
		Stock_PML = stock_PML;
	}

	public double getStock_RATE() {
		return Stock_RATE;
	}

	public void setStock_RATE(double stock_RATE) {
		Stock_RATE = stock_RATE;
	}

	public double getStock_PML_RATE() {
		return Stock_PML_RATE;
	}

	public void setStock_PML_RATE(double stock_PML_RATE) {
		Stock_PML_RATE = stock_PML_RATE;
	}

	public double getRate_Adj() {
		return Rate_Adj;
	}

	public void setRate_Adj(double rate_Adj) {
		Rate_Adj = rate_Adj;
	}

	public double getPD_Base_Premium() {
		return PD_Base_Premium;
	}

	public void setPD_Base_Premium(double pD_Base_Premium) {
		PD_Base_Premium = pD_Base_Premium;
	}

	public double getPD_PML_Adjusted_Premium() {
		return PD_PML_Adjusted_Premium;
	}

	public void setPD_PML_Adjusted_Premium(double pD_PML_Adjusted_Premium) {
		PD_PML_Adjusted_Premium = pD_PML_Adjusted_Premium;
	}

	public double getPD_Discount_premium() {
		return PD_Discount_premium;
	}

	public void setPD_Discount_premium(double pD_Discount_premium) {
		PD_Discount_premium = pD_Discount_premium;
	}

	public double getPD_DeductibleB() {
		return PD_DeductibleB;
	}

	public void setPD_DeductibleB(double pD_DeductibleB) {
		PD_DeductibleB = pD_DeductibleB;
	}

	public double getMB_Base_Premium() {
		return MB_Base_Premium;
	}

	public void setMB_Base_Premium(double mB_Base_Premium) {
		MB_Base_Premium = mB_Base_Premium;
	}

	public double getMB_PML_Adjusted_Premium() {
		return MB_PML_Adjusted_Premium;
	}

	public void setMB_PML_Adjusted_Premium(double mB_PML_Adjusted_Premium) {
		MB_PML_Adjusted_Premium = mB_PML_Adjusted_Premium;
	}

	public double getMB_Discount_premium() {
		return MB_Discount_premium;
	}

	public void setMB_Discount_premium(double mB_Discount_premium) {
		MB_Discount_premium = mB_Discount_premium;
	}

	public double getMB_Dedpremium() {
		return MB_Dedpremium;
	}

	public void setMB_Dedpremium(double mB_Dedpremium) {
		MB_Dedpremium = mB_Dedpremium;
	}

	public double getBI_Base_Premium() {
		return BI_Base_Premium;
	}

	public void setBI_Base_Premium(double bI_Base_Premium) {
		BI_Base_Premium = bI_Base_Premium;
	}

	public double getBI_PML_Adjusted_Premium() {
		return BI_PML_Adjusted_Premium;
	}

	public void setBI_PML_Adjusted_Premium(double bI_PML_Adjusted_Premium) {
		BI_PML_Adjusted_Premium = bI_PML_Adjusted_Premium;
	}

	public double getBI_Discount_premium() {
		return BI_Discount_premium;
	}

	public void setBI_Discount_premium(double bI_Discount_premium) {
		BI_Discount_premium = bI_Discount_premium;
	}

	public double getBI_Dedpremium() {
		return BI_Dedpremium;
	}

	public void setBI_Dedpremium(double bI_Dedpremium) {
		BI_Dedpremium = bI_Dedpremium;
	}

	public double getMlop_Base_Premium() {
		return Mlop_Base_Premium;
	}

	public void setMlop_Base_Premium(double mlop_Base_Premium) {
		Mlop_Base_Premium = mlop_Base_Premium;
	}

	public double getMlop_PML_Adjusted_Premium() {
		return Mlop_PML_Adjusted_Premium;
	}

	public void setMlop_PML_Adjusted_Premium(double mlop_PML_Adjusted_Premium) {
		Mlop_PML_Adjusted_Premium = mlop_PML_Adjusted_Premium;
	}

	public double getMlop_Discount_premium() {
		return Mlop_Discount_premium;
	}

	public void setMlop_Discount_premium(double mlop_Discount_premium) {
		Mlop_Discount_premium = mlop_Discount_premium;
	}

	public double getMlop_Dedpremium() {
		return Mlop_Dedpremium;
	}

	public void setMlop_Dedpremium(double mlop_Dedpremium) {
		Mlop_Dedpremium = mlop_Dedpremium;
	}

	public double getStock_Base_Premium() {
		return Stock_Base_Premium;
	}

	public void setStock_Base_Premium(double stock_Base_Premium) {
		Stock_Base_Premium = stock_Base_Premium;
	}

	public double getStock_PML_Adjusted_Premium() {
		return Stock_PML_Adjusted_Premium;
	}

	public void setStock_PML_Adjusted_Premium(double stock_PML_Adjusted_Premium) {
		Stock_PML_Adjusted_Premium = stock_PML_Adjusted_Premium;
	}

	public double getStock_Discount_premium() {
		return Stock_Discount_premium;
	}

	public void setStock_Discount_premium(double stock_Discount_premium) {
		Stock_Discount_premium = stock_Discount_premium;
	}

	public double getStock_Dedpremium() {
		return Stock_Dedpremium;
	}

	public void setStock_Dedpremium(double stock_Dedpremium) {
		Stock_Dedpremium = stock_Dedpremium;
	}

	public double getOTHER_Base_Premium() {
		return OTHER_Base_Premium;
	}

	public void setOTHER_Base_Premium(double oTHER_Base_Premium) {
		OTHER_Base_Premium = oTHER_Base_Premium;
	}

	public double getOTHER_PML_Adjusted_Premium() {
		return OTHER_PML_Adjusted_Premium;
	}

	public void setOTHER_PML_Adjusted_Premium(double oTHER_PML_Adjusted_Premium) {
		OTHER_PML_Adjusted_Premium = oTHER_PML_Adjusted_Premium;
	}

	public double getOTHER_Discount_premium() {
		return OTHER_Discount_premium;
	}

	public void setOTHER_Discount_premium(double oTHER_Discount_premium) {
		OTHER_Discount_premium = oTHER_Discount_premium;
	}

	public double getOTHER_Dedpremium() {
		return OTHER_Dedpremium;
	}

	public void setOTHER_Dedpremium(double oTHER_Dedpremium) {
		OTHER_Dedpremium = oTHER_Dedpremium;
	}

	public double getEML() {
		return EML;
	}

	public void setEML(double eML) {
		EML = eML;
	}

	public double getOPDFac() {
		return OPDFac;
	}

	public void setOPDFac(double oPDFac) {
		OPDFac = oPDFac;
	}

	public double getLY1Fac() {
		return LY1Fac;
	}

	public void setLY1Fac(double lY1Fac) {
		LY1Fac = lY1Fac;
	}

	public double getLY2Fac() {
		return LY2Fac;
	}

	public void setLY2Fac(double lY2Fac) {
		LY2Fac = lY2Fac;
	}

	public double getLY3Fac() {
		return LY3Fac;
	}

	public void setLY3Fac(double lY3Fac) {
		LY3Fac = lY3Fac;
	}

	public double getLY4Fac() {
		return LY4Fac;
	}

	public void setLY4Fac(double lY4Fac) {
		LY4Fac = lY4Fac;
	}

	public double getLY5Fac() {
		return LY5Fac;
	}

	public void setLY5Fac(double lY5Fac) {
		LY5Fac = lY5Fac;
	}

	public double getC() {
		return C;
	}

	public void setC(double c) {
		C = c;
	}

}
