package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the facultative database table.
 * 
 */
@Entity

public class Facultative implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String cedant;

	private String commission;

	private String countries;

	private String insured;

	private String interest;

	private String occupencies;

	private String regions;

	private String suminsured;
	
	private int our_liability;

	public Facultative() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCedant() {
		return this.cedant;
	}

	public void setCedant(String cedant) {
		this.cedant = cedant;
	}

	public String getCommission() {
		return this.commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getCountries() {
		return this.countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public String getInsured() {
		return this.insured;
	}

	public void setInsured(String insured) {
		this.insured = insured;
	}

	public String getInterest() {
		return this.interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getOccupencies() {
		return this.occupencies;
	}

	public void setOccupencies(String occupencies) {
		this.occupencies = occupencies;
	}

	public String getRegions() {
		return this.regions;
	}

	public void setRegions(String regions) {
		this.regions = regions;
	}

	
	

	public String getSuminsured() {
		return suminsured;
	}

	public void setSuminsured(String suminsured) {
		this.suminsured = suminsured;
	}
	public int getOur_liability() {
		return our_liability;
	}

	public void setOur_liability(int our_liability) {
		this.our_liability = our_liability;
	}


}