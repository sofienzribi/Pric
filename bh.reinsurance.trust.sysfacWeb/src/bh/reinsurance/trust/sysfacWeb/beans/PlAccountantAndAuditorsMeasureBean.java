package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.HashMap;
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

	public void AddBookKeepingAndAudit() {
		HashMap<String, String> ee;
		placcmeasure = (PlaccountantandauditorsMeasure) basicLocal
				.FindByFilter("PlaccountantandauditorsMeasure", "idMeasure",
						workingMeasure.getId());
		if (placcmeasure.getBookKeepingAndAudit() == null) {
			ee = new HashMap<String, String>();

		} else {
			ee = placcmeasure.getBookKeepingAndAudit();
		}
		ee.put(Key, Value);
		placcmeasure.setBookKeepingAndAudit(ee);
		basicLocal.Persist(placcmeasure);
		Key = null;
		Value = null;
		System.out.println(Key);
		System.out.println(Value);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('addbook').hide();");

	}

	public void DisplaymodifyBookKeepandaudit() {
		String ssd[] = null;

		ssd = SelectionPlaccandaudit.split("=");
		System.out.println(SelectionPlaccandaudit);
		Key = ssd[0];
		Value = ssd[1];
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('popmodifbkaa').show();");
		System.out.println(Key);

	}

	public void ModifyBKAA() {

		placcmeasure.getBookKeepingAndAudit().put(Key, Value);

		basicLocal.Persist(placcmeasure);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('popmodifbkaa').hide();");

	}

	public void DeleteBKAA() {
		String ssd[];

		ssd = SelectionPlaccandaudit.split("=");
		Key = ssd[0];
		Value = ssd[1];
		placcmeasure.getBookKeepingAndAudit().remove(Key);
		basicLocal.Persist(placcmeasure);
	}

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
}
