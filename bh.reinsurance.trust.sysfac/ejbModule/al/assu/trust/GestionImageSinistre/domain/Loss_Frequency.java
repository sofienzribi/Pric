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
	private String Loss_Ratio;
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

	public String getLoss_Ratio() {
		return this.Loss_Ratio;
	}

	public void setLoss_Ratio(String Loss_Ratio) {
		this.Loss_Ratio = Loss_Ratio;
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

}
