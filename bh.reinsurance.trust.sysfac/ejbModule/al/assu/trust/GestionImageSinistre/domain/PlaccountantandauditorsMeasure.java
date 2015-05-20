package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: PlaccountantandauditorsMeasure
 * 
 */
@Entity
public class PlaccountantandauditorsMeasure implements Serializable {

	private int id;
	private int idMeasure;
	private HashMap<String, String> BookKeepingAndAudit;
	private HashMap<String, String> managementAdvisory;
	private HashMap<String, String> ForcastProjection;
	private static final long serialVersionUID = 1L;

	public PlaccountantandauditorsMeasure() {
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

	public HashMap<String, String> getBookKeepingAndAudit() {
		return BookKeepingAndAudit;
	}

	public void setBookKeepingAndAudit(
			HashMap<String, String> bookKeepingAndAudit) {
		BookKeepingAndAudit = bookKeepingAndAudit;
	}

	public HashMap<String, String> getManagementAdvisory() {
		return managementAdvisory;
	}

	public void setManagementAdvisory(HashMap<String, String> managementAdvisory) {
		this.managementAdvisory = managementAdvisory;
	}

	public HashMap<String, String> getForcastProjection() {
		return ForcastProjection;
	}

	public void setForcastProjection(HashMap<String, String> forcastProjection) {
		ForcastProjection = forcastProjection;
	}

	public int getIdMeasure() {
		return idMeasure;
	}

	public void setIdMeasure(int idMeasure) {
		this.idMeasure = idMeasure;
	}

}
