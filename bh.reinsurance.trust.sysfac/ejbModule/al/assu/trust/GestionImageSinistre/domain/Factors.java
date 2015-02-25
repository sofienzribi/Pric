package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Factors
 * 
 */
@Entity
public class Factors implements Serializable {

	private int id;
	private String Name;
	private int IdMeasure;
	private static final long serialVersionUID = 1L;

	public Factors() {
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

	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public int getIdMeasure() {
		return IdMeasure;
	}

	public void setIdMeasure(int idMeasure) {
		IdMeasure = idMeasure;
	}

}
