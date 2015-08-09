package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ExposureSxale
 *
 */
@Entity

public class ExposureScale implements Serializable {

	
	private int id;
	private String Country;
	private String Severity;
	private double Exposure; 
	private static final long serialVersionUID = 1L;

	public ExposureScale() {
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
	public String getCountry() {
		return this.Country;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}   
	public String getSeverity() {
		return this.Severity;
	}

	public void setSeverity(String Severity) {
		this.Severity = Severity;
	}
	public double getExposure() {
		return Exposure;
	}
	public void setExposure(double exposure) {
		Exposure = exposure;
	}
   
}
