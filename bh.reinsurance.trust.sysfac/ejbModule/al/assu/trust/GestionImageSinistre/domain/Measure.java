package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Measure
 * 
 */
@Entity
public class Measure implements Serializable {

	private int id;
	private Date creationDate;
	private int userId;
	private String Name;
	private boolean active;
	private boolean activeTest;
	private String description;
	private static final long serialVersionUID = 1L;
	public Measure() {
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

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getActiveTest() {
		return this.activeTest;
	}

	public void setActiveTest(boolean activeTest) {
		this.activeTest = activeTest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
