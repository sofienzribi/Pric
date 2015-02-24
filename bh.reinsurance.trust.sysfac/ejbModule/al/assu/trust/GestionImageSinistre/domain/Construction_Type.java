package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Construction_Type
 * 
 */
@Entity
public class Construction_Type implements Serializable {

	private int id;
	private int load;
	private String category;
	private Factors factors;
	private static final long serialVersionUID = 1L;

	public Construction_Type() {
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

	public int getLoading() {
		return this.load;
	}

	public void setLoading(int loading) {
		this.load = loading;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(insertable=true,name="IdFacrors",referencedColumnName="id")
	public Factors getFactors() {
		return factors;
	}

	public void setFactors(Factors factors) {
		this.factors = factors;
	}

}
