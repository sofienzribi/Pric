package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.PlaccountantandauditorsMeasure;
import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.impl.CrudBasicLocal;

@javax.faces.bean.ManagedBean(name = "applicationbean")
@ApplicationScoped
public class ApplicationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlaccountantandauditorsMeasure measure;
	private Map<Integer, List<Project>> ListProj;
	private String s;
	private String ssd[];
	private String Field;
	private String ValueOfTheField;

	@EJB
	private CrudBasicLocal basicLocal;

	public ApplicationBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		testima2();

		ListProj = new HashMap<Integer, List<Project>>();
	}

	public void testima3() {

		ssd = s.split("=");
		Field = ssd[0];
		ValueOfTheField = ssd[1];
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('pop').show();");
	}

	public void testima2() {

		measure = (PlaccountantandauditorsMeasure) basicLocal.FindById(
				"PlaccountantandauditorsMeasure", 1);

	}

	public void testima5() {
		ssd = s.split("=");
		Field = ssd[0];
		ValueOfTheField = ssd[1];
	}

	public void testima4() {

		measure.getBookKeepingAndAudit().put(Field, ValueOfTheField);
		basicLocal.Persist(measure);
		testima2();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('pop').hide();");

	}

	public List<Project> getit(int id) {

		return ListProj.get(id);
	}

	// test
	public Map<Integer, List<Project>> getListProj() {
		return ListProj;
	}

	public void setListProj(Map<Integer, List<Project>> listProj) {
		ListProj = listProj;
	}

	public PlaccountantandauditorsMeasure getMeasure() {
		return measure;
	}

	public void setMeasure(PlaccountantandauditorsMeasure measure) {
		this.measure = measure;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String[] getSsd() {
		return ssd;
	}

	public void setSsd(String[] ssd) {
		this.ssd = ssd;
	}

	public String getValueOfTheField() {
		return ValueOfTheField;
	}

	public void setValueOfTheField(String valueOfTheField) {
		ValueOfTheField = valueOfTheField;
	}

	public String getField() {
		return Field;
	}

	public void setField(String field) {
		Field = field;
	}

}
