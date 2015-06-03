package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.domain.PlaccountantandauditorsMeasure;
import al.assu.trust.GestionImageSinistre.impl.CrudBasicLocal;
import al.assu.trust.GestionImageSinistre.impl.MeasureServicesLocal;

@ManagedBean(name = "accountantandauditorsmeasure")
@ViewScoped
public class PlAccountantAndAuditorsMeasureBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Measure measureToCopy = new Measure();

	private String Key;
	private String Value;

	private List<Measure> measures;

	private PlaccountantandauditorsMeasure placcmeasure = new PlaccountantandauditorsMeasure();

	private String SelectionPlaccandaudit;
	@ManagedProperty("#{measure.workingMeasure}")
	private Measure workingMeasure;

	@EJB
	private CrudBasicLocal basicLocal;
	@EJB
	private MeasureServicesLocal measureServicesLocal;

	// constructor
	public PlAccountantAndAuditorsMeasureBean() {
		// TODO Auto-generated constructor stub
	}

	// init meth
	@PostConstruct
	public void init() {
		placcmeasure = (PlaccountantandauditorsMeasure) basicLocal
				.FindByFilter("PlaccountantandauditorsMeasure", "idMeasure",
						workingMeasure.getId());
		setMeasures(measureServicesLocal
				.GetMeasuresByClass("PI accountants and auditors"));
	}

	// methods

	// Copy factors from another measure begin *******************************
	public void DisplayCopyMeasurePOP() {

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('Copy').show();");

	}

	public void CopyMeasure() {
		PlaccountantandauditorsMeasure factorstocopy = (PlaccountantandauditorsMeasure) basicLocal
				.FindByFilter("PlaccountantandauditorsMeasure", "idMeasure",
						measureToCopy.getId());

		factorstocopy.setId(placcmeasure.getId());
		factorstocopy.setIdMeasure(workingMeasure.getId());
		basicLocal.Persist(factorstocopy);

		placcmeasure = factorstocopy;

		measureToCopy = new Measure();

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('Copy').hide();");
		FacesContext.getCurrentInstance().addMessage(
				"messages1",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Measure Copied",
						""));

	}

	// Copy factors from another measure end *******************************

	// *********************************************** add factor begin

	private String TypeToAdd;

	public void AddButtonClicked(String fact) {
		TypeToAdd = fact;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('Addfactor').show();");
	}

	// Sorting PROB HERE
	public void AddFactorToBase() {
		basicLocal.Persist(placcmeasure);
		Key = null;
		Value = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('Addfactor').hide();");
	}

	public String FillFactorToAdd() {
		HashMap<String, String> ee = new LinkedHashMap<String, String>();

		if (TypeToAdd.equals("bookkeeping")) {

			if (placcmeasure.getBookKeepingAndAudit() == null) {
				placcmeasure
						.setBookKeepingAndAudit(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			placcmeasure.getBookKeepingAndAudit().put(Key, Value);
			AddFactorToBase();
			return null;
		}
		if (TypeToAdd.equals("forecast")) {

			if (placcmeasure.getForcastProjection() == null) {
				placcmeasure
						.setForcastProjection(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getForcastProjection().put(Key, Value);
			AddFactorToBase();
			return null;
		}

		if (TypeToAdd.equals("management")) {

			if (placcmeasure.getManagementAdvisory() == null) {
				placcmeasure
						.setManagementAdvisory(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getManagementAdvisory().put(Key, Value);
			AddFactorToBase();
			return null;
		}
		if (TypeToAdd.equals("payrollServices")) {

			if (placcmeasure.getPayrollServices() == null) {
				placcmeasure
						.setPayrollServices(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getPayrollServices().put(Key, Value);
			AddFactorToBase();
			return null;
		}
		if (TypeToAdd.equals("fiduciary")) {

			if (placcmeasure.getFiduciary() == null) {
				placcmeasure.setFiduciary(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getFiduciary().put(Key, Value);
			AddFactorToBase();
			return null;
		}
		if (TypeToAdd.equals("taxpreparation")) {

			if (placcmeasure.getTaxPreparation() == null) {
				placcmeasure
						.setTaxPreparation(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getTaxPreparation().put(Key, Value);
			AddFactorToBase();
			return null;
		}
		if (TypeToAdd.equals("securitiesrelated")) {

			if (placcmeasure.getSecuritiesRelated() == null) {
				placcmeasure
						.setSecuritiesRelated(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getSecuritiesRelated().put(Key, Value);
			AddFactorToBase();
			return null;
		}
		if (TypeToAdd.equals("financialplanning")) {

			if (placcmeasure.getFinancialPlanning() == null) {
				placcmeasure
						.setFinancialPlanning(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getFinancialPlanning().put(Key, Value);
			AddFactorToBase();
			return null;
		}
		if (TypeToAdd.equals("specialclient")) {

			if (placcmeasure.getSpecialClientProfileList() == null) {
				placcmeasure
						.setSpecialClientProfileList(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getSpecialClientProfileList().put(Key, Value);
			AddFactorToBase();
			return null;
		}
		if (TypeToAdd.equals("extendedreport")) {

			if (placcmeasure.getExtendedReport() == null) {
				placcmeasure
						.setExtendedReport(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getExtendedReport().put(Key, Value);
			AddFactorToBase();
			return null;
		}

		if (TypeToAdd.equals("retrocover")) {

			if (placcmeasure.getRetrospectiveCovers() == null) {
				placcmeasure
						.setRetrospectiveCovers(new LinkedHashMap<String, String>());

			}
			ee.put(Key, Value);
			placcmeasure.getRetrospectiveCovers().put(Key, Value);
			AddFactorToBase();
			return null;
		}
		System.out.println("Error in add no type found");
		return null;

	}

	// ************************************************************Add factor
	// end

	// Modif Factor begin *********************************
	private String TypeToModify;

	public void Displaymodifyfactor(String type) {
		String ssd[] = null;
		ssd = SelectionPlaccandaudit.split("=");
		Key = ssd[0];
		Value = ssd[1];
		TypeToModify = type;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('modiffactor').show();");

	}

	public String ModifyFactor() {
		if (TypeToModify.equals("bookkeeping")) {
			placcmeasure.getBookKeepingAndAudit().clear();
			placcmeasure.getBookKeepingAndAudit().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("forecast")) {
			placcmeasure.getForcastProjection().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("management")) {
			placcmeasure.getManagementAdvisory().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("payrollServices")) {
			placcmeasure.getPayrollServices().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("fiduciary")) {
			placcmeasure.getFiduciary().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("taxpreparation")) {
			placcmeasure.getTaxPreparation().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("securitiesrelated")) {
			placcmeasure.getSecuritiesRelated().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("financialplanning")) {
			placcmeasure.getFinancialPlanning().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("specialclient")) {
			placcmeasure.getSpecialClientProfileList().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("extendedreport")) {
			placcmeasure.getExtendedReport().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToModify.equals("retrocover")) {
			placcmeasure.getRetrospectiveCovers().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		System.out.println("Error in modify no type found");
		return null;

	}

	public void UpdateAfterModify() {
		basicLocal.Persist(placcmeasure);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('modiffactor').hide();");
		FacesContext.getCurrentInstance().addMessage(
				"messages1",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Factor Modified",
						""));
	}

	// modif Factor end *********************************

	// Delete Factor begin *********************************
	public String DeleteFactor(String type) {
		String ssd[];
		ssd = SelectionPlaccandaudit.split("=");
		Key = ssd[0];
		Value = ssd[1];
		if (type.equals("bookkeeping")) {
			placcmeasure.getBookKeepingAndAudit().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("forecast")) {
			placcmeasure.getForcastProjection().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("management")) {
			placcmeasure.getManagementAdvisory().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("payrollServices")) {
			placcmeasure.getPayrollServices().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("fiduciary")) {
			placcmeasure.getFiduciary().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("taxpreparation")) {
			placcmeasure.getTaxPreparation().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("managsecuritiesrelatedement")) {
			placcmeasure.getSecuritiesRelated().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("financialplanning")) {
			placcmeasure.getFinancialPlanning().remove(Key);
			UpdateAfterDelete();
			return null;
		}

		if (type.equals("specialclient")) {
			placcmeasure.getSpecialClientProfileList().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("extendedreport")) {
			placcmeasure.getExtendedReport().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("retrocover")) {
			placcmeasure.getRetrospectiveCovers().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		System.out.println("Error in delete no type found");
		return null;
	}

	public void UpdateAfterDelete() {
		basicLocal.Persist(placcmeasure);
		FacesContext.getCurrentInstance().addMessage(
				"messages1",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Factor Deleted",
						""));
	}

	// Delete Factor end *********************************

	// Loss measure begin

	// Loss measure end

	// Getters and Setters
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

	public List<Measure> getMeasures() {
		return measures;
	}

	public Measure getWorkingMeasure() {
		return workingMeasure;
	}

	public void setWorkingMeasure(Measure workingMeasure) {
		this.workingMeasure = workingMeasure;
	}

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}

	public String getTypeToAdd() {
		return TypeToAdd;
	}

	public void setTypeToAdd(String typeToAdd) {
		TypeToAdd = typeToAdd;
	}

	public String getTypeToModify() {
		return TypeToModify;
	}

	public void setTypeToModify(String typeToModify) {
		TypeToModify = typeToModify;
	}

}
