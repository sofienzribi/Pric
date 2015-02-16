package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.Facultative;
import al.assu.trust.GestionImageSinistre.impl.FacultativeServicesLocal;

@ManagedBean
@ViewScoped
public class FacultativeBean implements Serializable {
	@EJB
	FacultativeServicesLocal facultativeServicesLocal;

	private List<Facultative> facbychoice;

	private boolean testaff;
	private static final long serialVersionUID = 1L;
	private List<String> countr;
	private String countr1;
	private Facultative ComparaisonFacultative;
	private String occup1;
	private Facultative facultative;
	private boolean DisplayDeatils;
	private List<String> occup;
	private List<String> reg;
	private String reg1;
	private List<Facultative> facultatives;
	private Facultative facbychoice1;
	private List<Facultative> filtredfac;
	private List<Facultative> facultatives2;
	private boolean DisplayComparaisonButton;
	private boolean DisplayFacCompare;

	public FacultativeBean() {
	}

	@PostConstruct
	public void init() {
		DisplayFacCompare = false;
		DisplayComparaisonButton = true;
		DisplayDeatils = false;
		testaff = false;
		setFacultative(new Facultative());
		occup = facultativeServicesLocal.GetOcuupencies();
		reg = facultativeServicesLocal.getRegions();
		setFacultatives(facultativeServicesLocal.facultatives());
	}

	public void OnRegionChange() {
		setCountr(facultativeServicesLocal.GetCountries(reg1));
		setFacbychoice(facultativeServicesLocal.GetFacBychoice(reg1, countr1,
				occup1));
		testaff = true;
	}

	public void DisplayFacCompare() {
		DisplayFacCompare = true;
		DisplayComparaisonButton = false;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("popup.hide();");
	}

	public void OnRowSelect4() {
		DisplayDeatils = true;
	}

	public void affichselec() {
		setFacbychoice(facultativeServicesLocal.GetFacBychoice(reg1, countr1,
				occup1));
		testaff = true;
	}

	public boolean isTestaff() {
		return testaff;
	}

	public void setTestaff(boolean testaff) {
		this.testaff = testaff;
	}

	public List<Facultative> getFacbychoice() {
		return facbychoice;
	}

	public void setFacbychoice(List<Facultative> facbychoice) {
		this.facbychoice = facbychoice;
	}

	public List<String> getCountr() {
		return countr;
	}

	public void setCountr(List<String> countr) {
		this.countr = countr;
	}

	public List<String> getOccup() {
		return occup;
	}

	public void setOccup(List<String> occup) {
		this.occup = occup;
	}

	public FacultativeServicesLocal getFacultativeServicesLocal() {
		return facultativeServicesLocal;
	}

	public void setFacultativeServicesLocal(
			FacultativeServicesLocal facultativeServicesLocal) {
		this.facultativeServicesLocal = facultativeServicesLocal;
	}

	public String getCountr1() {
		return countr1;
	}

	public void setCountr1(String countr1) {
		this.countr1 = countr1;
	}

	public String getOccup1() {
		return occup1;
	}

	public void setOccup1(String occup1) {
		this.occup1 = occup1;
	}

	public List<String> getReg() {
		return reg;
	}

	public void setReg(List<String> reg) {
		this.reg = reg;
	}

	public String getReg1() {
		return reg1;
	}

	public void setReg1(String reg1) {
		this.reg1 = reg1;
	}

	public List<Facultative> getFacultatives() {
		return facultatives;
	}

	public void setFacultatives(List<Facultative> facultatives) {
		this.facultatives = facultatives;
	}

	public Facultative getFacbychoice1() {
		return facbychoice1;
	}

	public void setFacbychoice1(Facultative facbychoice1) {
		this.facbychoice1 = facbychoice1;
	}

	public List<Facultative> getFacultatives2() {
		return facultatives2;
	}

	public void setFacultatives2(List<Facultative> facultatives2) {
		this.facultatives2 = facultatives2;
	}

	public List<Facultative> getFiltredfac() {
		return filtredfac;
	}

	public void setFiltredfac(List<Facultative> filtredfac) {
		this.filtredfac = filtredfac;
	}

	public Facultative getFacultative() {
		return facultative;
	}

	public void setFacultative(Facultative facultative) {
		this.facultative = facultative;
	}

	public boolean isDisplayDeatils() {
		return DisplayDeatils;
	}

	public void setDisplayDeatils(boolean displayDeatils) {
		DisplayDeatils = displayDeatils;
	}

	public boolean isDisplayComparaisonButton() {
		return DisplayComparaisonButton;
	}

	public void setDisplayComparaisonButton(boolean displayComparaisonButton) {
		DisplayComparaisonButton = displayComparaisonButton;
	}

	public boolean isDisplayFacCompare() {
		return DisplayFacCompare;
	}

	public void setDisplayFacCompare(boolean displayFacCompare) {
		DisplayFacCompare = displayFacCompare;
	}

	public Facultative getComparaisonFacultative() {
		return ComparaisonFacultative;
	}

	public void setComparaisonFacultative(Facultative comparaisonFacultative) {
		ComparaisonFacultative = comparaisonFacultative;
	}

}
