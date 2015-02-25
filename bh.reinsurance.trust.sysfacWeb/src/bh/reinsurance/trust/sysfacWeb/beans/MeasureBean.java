package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.Factors;
import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.FactorsServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.MeasureServicesLocal;

@ManagedBean(name = "measure")
@SessionScoped
public class MeasureBean implements Serializable {

	/**
	 * 
	 */
	// Models
	@ManagedProperty("#{login.getUser()}")
	private User user;
	private Measure measure;
	private Measure Newmeasure;
	private Measure WorkingMeasure;
	private boolean DisplayButtons;
	private List<Measure> measures;
	@EJB
	MeasureServicesLocal measureServicesLocal;
	private static final long serialVersionUID = 1L;
	private Factors factors;
	@EJB
	private FactorsServicesLocal factorsServicesLocal;
	private boolean DisableButtonMeasure;
	private boolean disableButtonCloseSet;
	private String DisplayMeasureMenu;

	private String PasswordCheck;

	// const
	public MeasureBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		PasswordCheck = null;
		DisplayMeasureMenu = "none";
		DisableButtonMeasure = false;
		disableButtonCloseSet = true;
		DisplayButtons = false;
		Newmeasure = new Measure();
		WorkingMeasure = new Measure();
		measure = new Measure();
		measures = measureServicesLocal.GetAllMeasures();
	}

	public boolean isDisableButtonMeasure() {
		return DisableButtonMeasure;
	}

	public void setDisableButtonMeasure(boolean disableButtonMeasure) {
		DisableButtonMeasure = disableButtonMeasure;
	}

	public boolean isDisableButtonCloseSet() {
		return disableButtonCloseSet;
	}

	public void setDisableButtonCloseSet(boolean disableButtonCloseSet) {
		this.disableButtonCloseSet = disableButtonCloseSet;
	}

	// methods
	public void CreateMeasure() throws IOException {

		if (measureServicesLocal.NameExist(Newmeasure.getName())) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Name already exists!!", "Name Exist"));

		} else {
			factors = new Factors();

			Newmeasure.setUserId(user.getId());
			measureServicesLocal.NewMeasure(Newmeasure);
			measures = measureServicesLocal.GetAllMeasures();

			factors.setIdMeasure(measureServicesLocal.GetMeasureByName(
					Newmeasure.getName()).getId());
			factors.setName(Newmeasure.getName());

			factorsServicesLocal.Persist(factors);
			RequestContext context = RequestContext.getCurrentInstance();
			WorkingMeasure = measureServicesLocal.GetMeasureByName(Newmeasure
					.getName());
			DisplayMeasureMenu = "true";
			DisableButtonMeasure = true;
			disableButtonCloseSet = false;

			Newmeasure = new Measure();
			context.execute("popup.hide();");
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("Factors.jsf");

		}
	}

	public void OnRowSelected() {
		DisplayButtons = true;
	}

	public String OpenMeasure() {
		WorkingMeasure = measure;
		DisplayMeasureMenu = "true";
		DisableButtonMeasure = true;
		disableButtonCloseSet = false;
		return "Factors?faces-redirect=true";

	}

	public void MakeWorkingMeasure() throws IOException {
		if (!PasswordCheck.equals("sofien")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Wrong password!!", "wrong password"));
		} else {

			WorkingMeasure.setActive(true);
			measureServicesLocal.UpdateMeasure(WorkingMeasure);
			measures = measureServicesLocal.GetAllMeasures();
			for (int i = 0; i < measures.size(); i++) {
				if (measures.get(i).getId() != WorkingMeasure.getId()) {
					Measure measuree = measures.get(i);
					measuree.setActive(false);
					measureServicesLocal.UpdateMeasure(measuree);
				}

			}
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("POPSET.hide();");

		}
	}

	public String testactive(boolean a) {
		if (a == true)
			return "Yes";
		else {
			return "No";
		}
	}

	public void DeleteMeasure() {
		measureServicesLocal.DeleteMeasure(measure);
		measure = new Measure();
		measures = measureServicesLocal.GetAllMeasures();
		DisplayButtons = false;
	}

	public void CloseMeasure() throws IOException {
		DisplayMeasureMenu = "none";
		DisableButtonMeasure = false;
		disableButtonCloseSet = true;
		WorkingMeasure = new Measure();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Measures.jsf");
	}

	// getters and setters
	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public List<Measure> getMeasures() {
		return measures;
	}

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}

	public Measure getNewmeasure() {
		return Newmeasure;
	}

	public void setNewmeasure(Measure newmeasure) {
		Newmeasure = newmeasure;
	}

	public Measure getWorkingMeasure() {
		return WorkingMeasure;
	}

	public void setWorkingMeasure(Measure workingMeasure) {
		WorkingMeasure = workingMeasure;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDisplayButtons() {
		return DisplayButtons;
	}

	public void setDisplayButtons(boolean displayButtons) {
		DisplayButtons = displayButtons;
	}

	public Factors getFactors() {
		return factors;
	}

	public void setFactors(Factors factors) {
		this.factors = factors;
	}

	public String getDisplayMeasureMenu() {
		return DisplayMeasureMenu;
	}

	public void setDisplayMeasureMenu(String displayMeasureMenu) {
		DisplayMeasureMenu = displayMeasureMenu;
	}

	public String getPasswordCheck() {
		return PasswordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		PasswordCheck = passwordCheck;
	}
}
