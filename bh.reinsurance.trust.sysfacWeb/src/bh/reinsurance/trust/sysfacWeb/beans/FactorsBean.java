package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import al.assu.trust.GestionImageSinistre.domain.Construction_Type;
import al.assu.trust.GestionImageSinistre.domain.Factors;
import al.assu.trust.GestionImageSinistre.impl.FactorsServicesLocal;

@ManagedBean
@SessionScoped
public class FactorsBean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	// models
	@EJB
	FactorsServicesLocal factorsServicesLocal;
	private Factors factors;
	private Construction_Type construction_Type;

	// const

	public FactorsBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		factors = new Factors();
		construction_Type = new Construction_Type();
	}

	// methods
	public void CreateConstructionType() {

		factors.setId(1);
		
		factorsServicesLocal.Delete(factors);

	}

	// get set

	public Construction_Type getConstruction_Type() {
		return construction_Type;
	}

	public void setConstruction_Type(Construction_Type construction_Type) {
		this.construction_Type = construction_Type;
	}

	public Factors getFactors() {
		return factors;
	}

	public void setFactors(Factors factors) {
		this.factors = factors;
	}

}
