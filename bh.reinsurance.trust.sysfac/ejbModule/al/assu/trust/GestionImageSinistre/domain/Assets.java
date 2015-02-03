package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Assets
 *
 */
@Entity

public class Assets implements Serializable {

	
	private int id;
	private int loc;
	private String include;
	private String PD_MB_split;
	private String Rate_Adj;
	private int Curve;
	private int PD_SI;
	private int PD_PML;
	private int PD_Rate;
	private int MB_SI;
	private int MB_PML;
	private int MB_Rate;
	private int BI_SI;
	private int BI_PML;
	private int BI_Rate;
	private int OTHER_SI;
	private int OTHER_PML;
	private int OTHER_Rate;
	private Project project;
	private static final long serialVersionUID = 1L;

	public Assets() {
		super();
	}   
	@Id    
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getLoc() {
		return this.loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}   
	public String getInclude() {
		return this.include;
	}

	public void setInclude(String include) {
		this.include = include;
	}   
	public String getPD_MB_split() {
		return this.PD_MB_split;
	}

	public void setPD_MB_split(String PD_MB_split) {
		this.PD_MB_split = PD_MB_split;
	}   
	public String getRate_Adj() {
		return this.Rate_Adj;
	}

	public void setRate_Adj(String Rate_Adj) {
		this.Rate_Adj = Rate_Adj;
	}   
	public int getCurve() {
		return this.Curve;
	}

	public void setCurve(int Curve) {
		this.Curve = Curve;
	}   
	public int getPD_SI() {
		return this.PD_SI;
	}

	public void setPD_SI(int PD_SI) {
		this.PD_SI = PD_SI;
	}   
	public int getPD_PML() {
		return this.PD_PML;
	}

	public void setPD_PML(int PD_PML) {
		this.PD_PML = PD_PML;
	}   
	public int getPD_Rate() {
		return this.PD_Rate;
	}

	public void setPD_Rate(int PD_Rate) {
		this.PD_Rate = PD_Rate;
	}   
	public int getMB_SI() {
		return this.MB_SI;
	}

	public void setMB_SI(int MB_SI) {
		this.MB_SI = MB_SI;
	}   
	public int getMB_PML() {
		return this.MB_PML;
	}

	public void setMB_PML(int MB_PML) {
		this.MB_PML = MB_PML;
	}   
	public int getMB_Rate() {
		return this.MB_Rate;
	}

	public void setMB_Rate(int MB_Rate) {
		this.MB_Rate = MB_Rate;
	}   
	public int getBI_SI() {
		return this.BI_SI;
	}

	public void setBI_SI(int BI_SI) {
		this.BI_SI = BI_SI;
	}   
	public int getBI_PML() {
		return this.BI_PML;
	}

	public void setBI_PML(int BI_PML) {
		this.BI_PML = BI_PML;
	}   
	public int getBI_Rate() {
		return this.BI_Rate;
	}

	public void setBI_Rate(int BI_Rate) {
		this.BI_Rate = BI_Rate;
	}   
	public int getOTHER_SI() {
		return this.OTHER_SI;
	}

	public void setOTHER_SI(int OTHER_SI) {
		this.OTHER_SI = OTHER_SI;
	}   
	public int getOTHER_PML() {
		return this.OTHER_PML;
	}

	public void setOTHER_PML(int OTHER_PML) {
		this.OTHER_PML = OTHER_PML;
	}   
	public int getOTHER_Rate() {
		return this.OTHER_Rate;
	}

	public void setOTHER_Rate(int OTHER_Rate) {
		this.OTHER_Rate = OTHER_Rate;
	}
	@ManyToOne
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
   
}
