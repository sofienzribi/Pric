package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Loss_Frequency
 * 
 */
@Entity
public class Loss_Frequency implements Serializable {

	@Id
	private int id;
	private String category;
	private float Charge;
	private int IdFactor;
	private static final long serialVersionUID = 1L;

	public Loss_Frequency() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public float getCharge() {
		return this.Charge;
	}

	public void setCharge(float Charge) {
		this.Charge = Charge;
	}

	public int getIdFactor() {
		return this.IdFactor;
	}

	public void setIdFactor(int IdFactor) {
		this.IdFactor = IdFactor;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
