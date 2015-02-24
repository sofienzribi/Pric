package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Factors
 * 
 */
@Entity
public class Factors implements Serializable {

	private int id;
	private String Name;
	private List<Construction_Type> construction_Types;

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

	@OneToMany(mappedBy = "factors", cascade = CascadeType.ALL)
	public List<Construction_Type> getConstruction_Types() {
		return construction_Types;
	}

	public void setConstruction_Types(List<Construction_Type> construction_Types) {
		this.construction_Types = construction_Types;
	}

}
