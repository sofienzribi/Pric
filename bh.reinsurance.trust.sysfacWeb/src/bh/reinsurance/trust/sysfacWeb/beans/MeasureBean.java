package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import al.assu.trust.GestionImageSinistre.domain.PlaccountantandauditorsMeasure;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.CrudBasicLocal;
import al.assu.trust.GestionImageSinistre.impl.FactorsServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.MeasureServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean(name = "measure")
@SessionScoped
public class MeasureBean implements Serializable {

	// Models
	@ManagedProperty("#{login.getUser()}")
	private User user;
	private String DisplayMeasuresByClass;
	private Measure measure;
	private Measure Newmeasure;
	private Measure WorkingMeasure;
	private boolean DisplayButtons;
	private List<Measure> measures;
	private List<Measure> searchmeasure;
	private Measure PLAccountantAndAuditorsMeasure;
	private boolean HideActiveMeasureButton;
	@EJB
	MeasureServicesLocal measureServicesLocal;
	private static final long serialVersionUID = 1L;
	private Factors factors;
	private Map<String, String> Tool;
	@EJB
	private FactorsServicesLocal factorsServicesLocal;
	private boolean DisableButtonMeasure;
	private boolean disableButtonCloseSet;
	private String DisplayMeasureMenu;
	private Measure FacWorkingMeasure;
	private String PasswordCheck;
	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private CrudBasicLocal basicLocal;

	// const
	public MeasureBean() {
	}

	@PostConstruct
	public void init() {
		searchmeasure = new ArrayList<Measure>();
		Tool = new HashMap<String, String>();
		Tool.put("account", "PI accountants and auditors");
		Tool.put("property", "Property and Onshore");

		PLAccountantAndAuditorsMeasure = measureServicesLocal
				.GetWorkingMeasure("PI accountants and auditors");

		// for the property
		FacWorkingMeasure = measureServicesLocal.GetWorkingMeasure("Property");
		PasswordCheck = null;
		DisplayMeasureMenu = "none";
		HideActiveMeasureButton = true;
		DisableButtonMeasure = false;
		disableButtonCloseSet = true;
		DisplayButtons = false;
		Newmeasure = new Measure();
		WorkingMeasure = new Measure();
		measure = new Measure();
		measures = measureServicesLocal.GetAllMeasures();
	}

	// methods
	public void SetTestMeasure() throws IOException {
		measure.setActiveTest(true);
		measureServicesLocal.UpdateMeasure(measure);
		measures = measureServicesLocal.GetAllMeasures();
		for (int i = 0; i < measures.size(); i++) {
			if (measures.get(i).getId() != measure.getId()) {
				Measure measuree = measures.get(i);
				measuree.setActiveTest(false);
				measureServicesLocal.UpdateMeasure(measuree);
			}
		}
		DisplayButtons = false;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('POPMeasure').hide();");
		measure = new Measure();
		FacWorkingMeasure = measureServicesLocal.GetTestingMeasure("");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Summary2.jsf");
	}

	public void DisplayMeasureByFilter() {
		List<Measure> list = new ArrayList<Measure>();
		if (DisplayMeasuresByClass.equals("all")) {
			measures = measureServicesLocal.GetAllMeasures();
		} else {
			measures = measureServicesLocal.GetAllMeasures();
			for (Measure a : measures) {
				if (a.getClassofbusiness().equals(DisplayMeasuresByClass)) {
					list.add(a);
				}
			}
			measures = list;
		}

	}

	public void ResetMeasure() throws IOException {
		measures = measureServicesLocal.GetAllMeasures();
		for (int i = 0; i < measures.size(); i++) {

			Measure measuree = measures.get(i);
			measuree.setActiveTest(false);
			measureServicesLocal.UpdateMeasure(measuree);

		}
		measure = new Measure();
		FacWorkingMeasure = measureServicesLocal.GetWorkingMeasure("Property");
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('POPMeasure').hide();");
		DisplayButtons = false;

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Summary2.jsf");

	}

	// Create new measure
	public void CreateMeasure() throws IOException {

		if (measureServicesLocal.NameExist(Newmeasure.getName())) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Name already exists!!", "Name Exist"));

		} else {
			if (Newmeasure.getClassofbusiness().equals(
					"PI accountants and auditors")) {

				Createplaccountantandauditorsmeasure();

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
				WorkingMeasure = measureServicesLocal
						.GetMeasureByName(Newmeasure.getName());
				DisplayMeasureMenu = "true";
				DisableButtonMeasure = true;
				disableButtonCloseSet = false;

				Newmeasure = new Measure();
				context.execute("PF('popup').hide();");
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("Factors.jsf");

			}
		}
	}

	public void OnRowSelected() {
		DisplayButtons = true;
	}

	public String OpenMeasure() {
		if (measure.getClassofbusiness().equals("PI accountants and auditors")) {
			if (measure.getActive() == true) {
				//*****************************************************change to false or true if dev mode
				HideActiveMeasureButton = false;
			}else{
				HideActiveMeasureButton = true;
			}
			return Openplaccountantandauditorsmeasure();

		} else {
			WorkingMeasure = measure;
			DisplayMeasureMenu = "true";
			DisableButtonMeasure = true;
			disableButtonCloseSet = false;
			return "Factors?faces-redirect=true";
		}

	}

	public void MakeWorkingMeasure() throws IOException, InterruptedException {
		if (!PasswordCheck.equals("TRUST")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Wrong password!!", "wrong password"));
		} else {

			WorkingMeasure.setActive(true);

			measureServicesLocal.UpdateMeasure(WorkingMeasure);

			measures = measureServicesLocal.GetMeasuresByClass(WorkingMeasure
					.getClassofbusiness());

			for (int i = 0; i < measures.size(); i++) {
				if (measures.get(i).getId() != WorkingMeasure.getId()
						&& measures.get(i).getClassofbusiness()
								.equals(WorkingMeasure.getClassofbusiness())) {
					Measure measuree = measures.get(i);
					measuree.setActive(false);
					measureServicesLocal.UpdateMeasure(measuree);
				}

			}
			PLAccountantAndAuditorsMeasure = WorkingMeasure;
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('POPSET').hide();");

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"The Working measure was changed", ""));

		}
	}

	public String testactive(boolean a) {
		if (a == true)
			return "Yes";
		else {
			return "No";
		}
	}

	public String GetUserName(int id) {
		return userServicesLocal.GetFirstAndLast(id);
	}

	public void DeleteMeasure() {
		if (measure.getActive() == true) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"messages1",
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Impossible to delete this measure because it's the active measure",
									""));
		} else {
			if (measure.getClassofbusiness().equals(
					"PI accountants and auditors")) {
				placcmeasure = (PlaccountantandauditorsMeasure) basicLocal
						.FindByFilter("PlaccountantandauditorsMeasure",
								"idMeasure", measure.getId());

				basicLocal.Delete(placcmeasure);
			}

			measureServicesLocal.DeleteMeasure(measure);
			measure = new Measure();
			measures = measureServicesLocal.GetAllMeasures();
			DisplayButtons = false;
			FacesContext.getCurrentInstance().addMessage(
					"messages1",
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							" Measure Deleted", ""));
		}

	}

	public void CloseMeasure() throws IOException {
		DisplayMeasureMenu = "none";
		DisableButtonMeasure = false;
		disableButtonCloseSet = true;
		HideActiveMeasureButton=true;
		WorkingMeasure = new Measure();
		measure = new Measure();
		placcmeasure = new PlaccountantandauditorsMeasure();

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Measures.jsf");
	}

	// pl accountant and auditors measure management start

	private Measure measureToCopy = new Measure();
	private String Key;
	private String Value;
	private PlaccountantandauditorsMeasure placcmeasure = new PlaccountantandauditorsMeasure();
	private String SelectionPlaccandaudit;
	private List<Measure> AccAndAuditMeasureList = new ArrayList<Measure>();

	public void Createplaccountantandauditorsmeasure() throws IOException {
		Newmeasure.setUserId(user.getId());
		measureServicesLocal.NewMeasure(Newmeasure);
		placcmeasure = new PlaccountantandauditorsMeasure();
		placcmeasure.setIdMeasure(measureServicesLocal.GetMeasureByName(
				Newmeasure.getName()).getId());
		basicLocal.Persist(placcmeasure);
		measures = measureServicesLocal.GetAllMeasures();
		DisplayMeasureMenu = "true";
		DisableButtonMeasure = true;
		disableButtonCloseSet = false;
		WorkingMeasure = measureServicesLocal.GetMeasureByName(Newmeasure
				.getName());
		measure = WorkingMeasure;
		Newmeasure = new Measure();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('popup').hide();");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("PIAccountantandandAuditorsMeasurel.jsf");

	}

	public String Openplaccountantandauditorsmeasure() {
		placcmeasure = (PlaccountantandauditorsMeasure) basicLocal
				.FindByFilter("PlaccountantandauditorsMeasure", "idMeasure",
						measure.getId());

		WorkingMeasure = measure;
		DisplayMeasureMenu = "true";
		DisableButtonMeasure = true;
		disableButtonCloseSet = false;
		return "PIAccountantandandAuditorsMeasurel?faces-redirect=true";

	}

	// pl accountant and auditors measure management end
	public void GoToMeasure() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("PIAccountantandandAuditorsMeasurel.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}
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

	public Measure getFacWorkingMeasure() {
		return FacWorkingMeasure;
	}

	public void setFacWorkingMeasure(Measure facWorkingMeasure) {
		FacWorkingMeasure = facWorkingMeasure;
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

	public List<Measure> getSearchmeasure() {
		return searchmeasure;
	}

	public void setSearchmeasure(List<Measure> searchmeasure) {
		this.searchmeasure = searchmeasure;
	}

	public Map<String, String> getTool() {
		return Tool;
	}

	public void setTool(Map<String, String> tool) {
		Tool = tool;
	}

	public PlaccountantandauditorsMeasure getPlaccmeasure() {
		return placcmeasure;
	}

	public void setPlaccmeasure(PlaccountantandauditorsMeasure placcmeasure) {
		this.placcmeasure = placcmeasure;
	}

	public String getSelectionPlaccandaudit() {
		return SelectionPlaccandaudit;
	}

	public void setSelectionPlaccandaudit(String selectionPlaccandaudit) {
		SelectionPlaccandaudit = selectionPlaccandaudit;
	}

	public List<Measure> getAccAndAuditMeasureList() {
		return AccAndAuditMeasureList;
	}

	public void setAccAndAuditMeasureList(List<Measure> accAndAuditMeasureList) {
		AccAndAuditMeasureList = accAndAuditMeasureList;
	}

	public Measure getMeasureToCopy() {
		return measureToCopy;
	}

	public void setMeasureToCopy(Measure measureToCopy) {
		this.measureToCopy = measureToCopy;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getDisplayMeasuresByClass() {
		return DisplayMeasuresByClass;
	}

	public void setDisplayMeasuresByClass(String displayMeasuresByClass) {
		DisplayMeasuresByClass = displayMeasuresByClass;
	}

	public Measure getPLAccountantAndAuditorsMeasure() {
		return PLAccountantAndAuditorsMeasure;
	}

	public void setPLAccountantAndAuditorsMeasure(
			Measure pLAccountantAndAuditorsMeasure) {
		PLAccountantAndAuditorsMeasure = pLAccountantAndAuditorsMeasure;
	}

	public boolean isHideActiveMeasureButton() {
		return HideActiveMeasureButton;
	}

	public void setHideActiveMeasureButton(boolean hideActiveMeasureButton) {
		HideActiveMeasureButton = hideActiveMeasureButton;
	}

}
