package bh.reinsurance.trust.sysfacWeb.beans;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import al.assu.trust.GestionImageSinistre.domain.Construction_Type;
import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.impl.FactorsServicesLocal;

@ManagedBean
@ViewScoped
public class AssesmentBean {
	//models
	private Map<String, String> Quality;
	private Map<String, String> LossRatio;
	private Map<String, String> ConsClass;
	private Map<String, String> ResponseTime;
	@ManagedProperty("#{measure.getFacWorkingMeasure()}")
	private Measure facWorkingMeasure;
	private List<Construction_Type> construction_Types;
	@EJB
	FactorsServicesLocal factorsServicesLocal;
	private Construction_Type construction_Type;
	private int a;
//const
	public AssesmentBean() {

	}

	@PostConstruct
	public void init() {
		a = 0;
		construction_Type = new Construction_Type();
		System.out.println("work fac" + facWorkingMeasure.getId());
		construction_Types = factorsServicesLocal
				.GetConsttype(factorsServicesLocal.GetFactorByIdMeasure(
						facWorkingMeasure.getId()).getId());
		Quality = new HashMap<String, String>();
		LossRatio = new HashMap<String, String>();
		ConsClass = new HashMap<String, String>();
		ResponseTime = new HashMap<String, String>();
		ResponseTime.put("<=5 min", "5-");
		ResponseTime.put("<=10 min", "10-");
		ResponseTime.put("<=15 min", "15-");
		ResponseTime.put(">15 min", "15+");

		ConsClass.put("A", "A");
		ConsClass.put("B", "B");
		ConsClass.put("C", "C");

		LossRatio.put("<20%", "<20%");
		LossRatio.put("<50%", "<50%");
		LossRatio.put("<100%", "<100%");
		LossRatio.put(">100%", ">100%");
		LossRatio.put("New Risk", "new");

		Quality.put("Excellent", "excellent");
		Quality.put("Good", "good");
		Quality.put("Standard", "standard");
		Quality.put("Substandard", "substandard");
	}

	// methods
	public void test() {
		System.out.println("Le type est " + a);
	}

	public Construction_Type FindConstById(int id) {
		return factorsServicesLocal.FindConstById(id);
	}

	public Construction_Type Findbycat(String Cat) {
		return factorsServicesLocal.FindConstructionTypeByCategory(
				Cat,
				factorsServicesLocal.GetFactorByIdMeasure(
						facWorkingMeasure.getId()).getId());
	}

	// get set
	public Map<String, String> getQuality() {
		return Quality;
	}

	public void setQuality(Map<String, String> quality) {
		Quality = quality;
	}

	public Map<String, String> getLossRatio() {
		return LossRatio;
	}

	public void setLossRatio(Map<String, String> lossRatio) {
		LossRatio = lossRatio;
	}

	public Map<String, String> getConsClass() {
		return ConsClass;
	}

	public void setConsClass(Map<String, String> consClass) {
		ConsClass = consClass;
	}

	public Map<String, String> getResponseTime() {
		return ResponseTime;
	}

	public void setResponseTime(Map<String, String> responseTime) {
		ResponseTime = responseTime;
	}

	public Measure getFacWorkingMeasure() {
		return facWorkingMeasure;
	}

	public void setFacWorkingMeasure(Measure facWorkingMeasure) {
		this.facWorkingMeasure = facWorkingMeasure;
	}

	public List<Construction_Type> getConstruction_Types() {
		return construction_Types;
	}

	public void setConstruction_Types(List<Construction_Type> construction_Types) {
		this.construction_Types = construction_Types;
	}

	public Construction_Type getConstruction_Type() {
		return construction_Type;
	}

	public void setConstruction_Type(Construction_Type construction_Type) {
		this.construction_Type = construction_Type;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

}
