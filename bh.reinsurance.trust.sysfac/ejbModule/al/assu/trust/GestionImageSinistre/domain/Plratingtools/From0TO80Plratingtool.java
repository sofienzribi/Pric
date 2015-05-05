package al.assu.trust.GestionImageSinistre.domain.Plratingtools;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: From0TO80Plratingtool
 *
 */
@Entity

public class From0TO80Plratingtool implements Serializable {

	
	private int id;
	private String practice;
	private String factor;
	private static final long serialVersionUID = 1L;

	public From0TO80Plratingtool() {
		super();
	}   
	@Id    
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getPractice() {
		return this.practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}   
	public String getFactor() {
		return this.factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}
   
}
