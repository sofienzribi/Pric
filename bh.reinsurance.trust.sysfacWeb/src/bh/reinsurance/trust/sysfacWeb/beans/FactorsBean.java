package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.Construction_Type;
import al.assu.trust.GestionImageSinistre.domain.Factors;
import al.assu.trust.GestionImageSinistre.domain.Loss_Frequency;
import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.impl.FactorsServicesLocal;

@ManagedBean
@RequestScoped
public class FactorsBean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	// models
	@EJB
	FactorsServicesLocal factorsServicesLocal;
	@ManagedProperty("#{measure.workingMeasure}")
	private Measure workingMeasure;
	private Factors factors;
	private Construction_Type construction_Type;
	private List<Construction_Type> construction_Types;
	private Construction_Type construction_Type2;
	private List<Loss_Frequency> loss_Frequencies;
	private Loss_Frequency loss_Frequency;
	private Loss_Frequency loss_Frequency2;

	// const

	public FactorsBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		factors = factorsServicesLocal.GetFactorByIdMeasure(workingMeasure
				.getId());
		loss_Frequencies = factorsServicesLocal.getloss(factors.getId());
		loss_Frequency = new Loss_Frequency();
		loss_Frequency2 = new Loss_Frequency();
		construction_Type = new Construction_Type();
		construction_Type2 = new Construction_Type();
		construction_Types = factorsServicesLocal.GetConsttype(factors.getId());
	}

	// methods
	public void CreateConstructionType() {

		if (factorsServicesLocal.CategoryExists(
				construction_Type2.getCategory(), factors.getId(),
				"Construction_Type")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Category already exists!!", "Name Exist"));

		} else {
			construction_Type2.setIdFactor(factors.getId());
			factorsServicesLocal.Persist(construction_Type2);
			construction_Types = factorsServicesLocal.GetConsttype(factors
					.getId());
			construction_Type2 = new Construction_Type();
			RequestContext context = RequestContext.getCurrentInstance();

			context.execute("PF('Popup').hide();");
		}

	}

	public void createLossRtaino() {
		loss_Frequency2.setIdFactor(factors.getId());
		factorsServicesLocal.Persist(loss_Frequency2);
		loss_Frequencies = factorsServicesLocal.getloss(factors.getId());
		RequestContext context = RequestContext.getCurrentInstance();

		context.execute("PF('Popup2').hide();");

	}

	public void UpdateConstructionType() {
		factorsServicesLocal.Persist(construction_Type);
		construction_Types = factorsServicesLocal.GetConsttype(factors.getId());
		RequestContext context = RequestContext.getCurrentInstance();

		context.execute("PF('Popup2').hide();");
	}

	public void DeleteConstructionType() {
		factorsServicesLocal.Delete(construction_Type);
		construction_Types = factorsServicesLocal.GetConsttype(factors.getId());
	}

	public void deleteLoss() {
		factorsServicesLocal.Delete(loss_Frequency);
		loss_Frequencies = factorsServicesLocal.getloss(factors.getId());
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

	public List<Construction_Type> getConstruction_Types() {
		return construction_Types;
	}

	public void setConstruction_Types(List<Construction_Type> construction_Types) {
		this.construction_Types = construction_Types;
	}

	public Construction_Type getConstruction_Type2() {
		return construction_Type2;
	}

	public void setConstruction_Type2(Construction_Type construction_Type2) {
		this.construction_Type2 = construction_Type2;
	}

	public Measure getWorkingMeasure() {
		return workingMeasure;
	}

	public void setWorkingMeasure(Measure workingMeasure) {
		this.workingMeasure = workingMeasure;
	}

	public List<Loss_Frequency> getLoss_Frequencies() {
		return loss_Frequencies;
	}

	public void setLoss_Frequencies(List<Loss_Frequency> loss_Frequencies) {
		this.loss_Frequencies = loss_Frequencies;
	}

	public Loss_Frequency getLoss_Frequency() {
		return loss_Frequency;
	}

	public void setLoss_Frequency(Loss_Frequency loss_Frequency) {
		this.loss_Frequency = loss_Frequency;
	}

	public Loss_Frequency getLoss_Frequency2() {
		return loss_Frequency2;
	}

	public void setLoss_Frequency2(Loss_Frequency loss_Frequency2) {
		this.loss_Frequency2 = loss_Frequency2;
	}

}
