package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Offer
 * 
 */
@Entity
public class Offer implements Serializable {

	private int id;
	private int id_underwriter;
	private Date inception_Date;
	private Date expiry_date;
	private Date quoted_Date;
	private String Currency;
	private String Insured;
	private String Broker;
	private int id_project;
	private String Policy;
	private String Country;
	private int risk_Grade;
	private String insurance_Basis;
	private static final long serialVersionUID = 1L;

	public Offer() {
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

	public Date getInception_Date() {
		return this.inception_Date;
	}

	public void setInception_Date(Date inception_Date) {
		this.inception_Date = inception_Date;
	}

	

	public Date getQuoted_Date() {
		return this.quoted_Date;
	}

	public void setQuoted_Date(Date quoted_Date) {
		this.quoted_Date = quoted_Date;
	}

	public String getCurrency() {
		return this.Currency;
	}

	public void setCurrency(String Currency) {
		this.Currency = Currency;
	}

	public String getInsured() {
		return this.Insured;
	}

	public void setInsured(String Insured) {
		this.Insured = Insured;
	}

	public String getBroker() {
		return this.Broker;
	}

	public void setBroker(String Broker) {
		this.Broker = Broker;
	}

	public String getPolicy() {
		return this.Policy;
	}

	public void setPolicy(String Policy) {
		this.Policy = Policy;
	}

	public String getCountry() {
		return this.Country;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}

	public int getRisk_Grade() {
		return this.risk_Grade;
	}

	public void setRisk_Grade(int risk_Grade) {
		this.risk_Grade = risk_Grade;
	}

	public String getInsurance_Basis() {
		return this.insurance_Basis;
	}

	public void setInsurance_Basis(String insurance_Basis) {
		this.insurance_Basis = insurance_Basis;
	}

	public int getId_project() {
		return id_project;
	}

	public void setId_project(int id_project) {
		this.id_project = id_project;
	}

	public int getId_underwriter() {
		return id_underwriter;
	}

	public void setId_underwriter(int id_underwriter) {
		this.id_underwriter = id_underwriter;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	

	

}
