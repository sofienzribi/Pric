package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Project
 * 
 */
@Entity
public class Project implements Serializable {

	private int id;
	private Boolean Privacy;
	private Date DateCreation;
	private String NameOfTheProject;
	private String status;
	private static final long serialVersionUID = 1L;
	private List<Assets> assets;
	private int user;
	private String password;
	private Date quoted_Date;
	private String Currency;
	private String Insured;
	private String Broker;
	
	private String Policy;
	private String Country;
	private String Tool;
	
	private Date expiry_date;
	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public Date getQuoted_Date() {
		return quoted_Date;
	}

	public void setQuoted_Date(Date quoted_Date) {
		this.quoted_Date = quoted_Date;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getInsured() {
		return Insured;
	}

	public void setInsured(String insured) {
		Insured = insured;
	}

	public String getBroker() {
		return Broker;
	}

	public void setBroker(String broker) {
		Broker = broker;
	}

	public String getPolicy() {
		return Policy;
	}

	public void setPolicy(String policy) {
		Policy = policy;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getTool() {
		return Tool;
	}

	public void setTool(String tool) {
		Tool = tool;
	}

	

	
	
	

	public Project() {
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

	public Date getDateCreation() {
		return this.DateCreation;
	}

	public void setDateCreation(Date DateCreation) {
		this.DateCreation = DateCreation;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "project")
	public List<Assets> getAssets() {
		return assets;
	}

	public void setAssets(List<Assets> assets) {
		this.assets = assets;
	}

	public String getNameOfTheProject() {
		return NameOfTheProject;
	}

	public void setNameOfTheProject(String nameOfTheProject) {
		NameOfTheProject = nameOfTheProject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getPrivacy() {
		return Privacy;
	}

	public void setPrivacy(Boolean privacy) {
		Privacy = privacy;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
