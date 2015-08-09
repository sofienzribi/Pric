package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.domain.PropertyOnshoreMeasure;
import al.assu.trust.GestionImageSinistre.impl.PropertyOnshoreMeasureServicesLocal;

@ManagedBean(name = "PropertyMeasure")
@ViewScoped
public class PropertyMeasureBean implements Serializable {

	private PropertyOnshoreMeasure propertyOnshoreMeasure;
	private String SelectionPropertyOnshore;
	private String TypeToAdd;
	private String Key;
	private String Value;

	@ManagedProperty("#{measure.workingMeasure}")
	private Measure workingMeasure;

	@EJB
	PropertyOnshoreMeasureServicesLocal propertyOnshoreMeasureServicesLocal;

	private static final long serialVersionUID = 1L;

	public PropertyMeasureBean() {
		// TODO Auto-generated constructor stub
	}

	// init method
	@PostConstruct
	public void init() {
		propertyOnshoreMeasure = propertyOnshoreMeasureServicesLocal
				.FindByIdMeasure(workingMeasure.getId());
	}

	// Add Factors begin
	public void AddButtonClicked(String fact) {
		TypeToAdd = fact;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('Addfactor').show();");
	}

	public String FillFactorToAdd() {
		HashMap<String, String> ee = new LinkedHashMap<String, String>();

		if (TypeToAdd.equals("ConstructionClass")) {
			if (propertyOnshoreMeasure.getConstructionClass() == null) {
				propertyOnshoreMeasure
						.setConstructionClass(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getConstructionClass().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("LossRatio")) {
			if (propertyOnshoreMeasure.getLossRatio() == null) {
				propertyOnshoreMeasure
						.setLossRatio(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getLossRatio().put(Key, Value);
			AddFactorToBase();

			return null;
		}

		if (TypeToAdd.equals("Capacity")) {
			if (propertyOnshoreMeasure.getCapacity() == null) {
				propertyOnshoreMeasure
						.setCapacity(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getCapacity().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("PlantAge")) {
			if (propertyOnshoreMeasure.getPlantAge() == null) {
				propertyOnshoreMeasure
						.setPlantAge(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getPlantAge().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("MachnineMake")) {
			if (propertyOnshoreMeasure.getMachineMake() == null) {
				propertyOnshoreMeasure
						.setMachineMake(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getMachineMake().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("MaintenanceQuality")) {
			if (propertyOnshoreMeasure.getMaintenanceQuality() == null) {
				propertyOnshoreMeasure
						.setMaintenanceQuality(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getMaintenanceQuality().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("AvaibilityOfParts")) {
			if (propertyOnshoreMeasure.getAvaibilityOfParts() == null) {
				propertyOnshoreMeasure
						.setAvaibilityOfParts(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getAvaibilityOfParts().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("Link")) {
			if (propertyOnshoreMeasure.getLink() == null) {
				propertyOnshoreMeasure
						.setLink(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getLink().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("Coverage")) {
			if (propertyOnshoreMeasure.getCoverage() == null) {
				propertyOnshoreMeasure
						.setCoverage(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getCoverage().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("InstallationEstinguishment")) {
			if (propertyOnshoreMeasure.getInstallationEstinguishment() == null) {
				propertyOnshoreMeasure
						.setInstallationEstinguishment(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getInstallationEstinguishment().put(Key,
					Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("InstallationFire")) {
			if (propertyOnshoreMeasure.getInstallationFire() == null) {
				propertyOnshoreMeasure
						.setInstallationFire(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getInstallationFire().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("ResponseTime")) {
			if (propertyOnshoreMeasure.getResponseTime() == null) {
				propertyOnshoreMeasure
						.setResponseTime(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getResponseTime().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("FamiliarityWithPremises")) {
			if (propertyOnshoreMeasure.getFamiliarityWithPremises() == null) {
				propertyOnshoreMeasure
						.setFamiliarityWithPremises(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getFamiliarityWithPremises().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("PlantFireBrigadeType")) {
			if (propertyOnshoreMeasure.getPlantFireBrigadeType() == null) {
				propertyOnshoreMeasure
						.setPlantFireBrigadeType(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getPlantFireBrigadeType().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("MLoPIndemnityPeriod")) {
			if (propertyOnshoreMeasure.getMLoPIndemnityPeriod() == null) {
				propertyOnshoreMeasure
						.setMLoPIndemnityPeriod(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getMLoPIndemnityPeriod().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("BIIndemnityPeriod")) {
			if (propertyOnshoreMeasure.getBiIndemnityPeriod() == null) {
				propertyOnshoreMeasure
						.setBiIndemnityPeriod(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getBiIndemnityPeriod().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("CBI")) {
			if (propertyOnshoreMeasure.getCBI() == null) {
				propertyOnshoreMeasure
						.setCBI(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getCBI().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("Assesment")) {
			if (propertyOnshoreMeasure.getAssesment() == null) {
				propertyOnshoreMeasure
						.setAssesment(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getAssesment().put(Key, Value);
			AddFactorToBase();

			return null;
		}
		if (TypeToAdd.equals("PMLerror")) {
			if (propertyOnshoreMeasure.getPMLerror() == null) {
				propertyOnshoreMeasure
						.setPMLerror(new LinkedHashMap<String, String>());
			}
			ee.put(Key, Value);
			propertyOnshoreMeasure.getPMLerror().put(Key, Value);
			AddFactorToBase();

			return null;
		}

		return null;
	}

	public void AddFactorToBase() {
		propertyOnshoreMeasureServicesLocal.AddMeasure(propertyOnshoreMeasure);
		Key = null;
		Value = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('Addfactor').hide();");
	}

	// Add factor End

	// Modify factor Begin
	public void Displaymodifyfactor(String type) {
		String ssd[] = null;
		ssd = SelectionPropertyOnshore.split("=");
		Key = ssd[0];
		Value = ssd[1];
		TypeToAdd = type;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('modiffactor').show();");

	}

	public String ModifyFactor() {
		if (TypeToAdd.equals("ConstructionClass")) {
			propertyOnshoreMeasure.getConstructionClass().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("LossRatio")) {
			propertyOnshoreMeasure.getLossRatio().put(Key, Value);
			UpdateAfterModify();
			return null;
		}

		if (TypeToAdd.equals("Capacity")) {
			propertyOnshoreMeasure.getCapacity().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("PlantAge")) {
			propertyOnshoreMeasure.getPlantAge().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("MachnineMake")) {
			propertyOnshoreMeasure.getMachineMake().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("MaintenanceQuality")) {
			propertyOnshoreMeasure.getMaintenanceQuality().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("AvaibilityOfParts")) {
			propertyOnshoreMeasure.getAvaibilityOfParts().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("InstallationEstinguishment")) {
			propertyOnshoreMeasure.getInstallationEstinguishment().put(Key,
					Value);
			UpdateAfterModify();
			return null;
		}

		if (TypeToAdd.equals("Coverage")) {
			propertyOnshoreMeasure.getCoverage().put(Key, Value);
			UpdateAfterModify();
			return null;
		}

		if (TypeToAdd.equals("Link")) {
			propertyOnshoreMeasure.getLink().put(Key, Value);
			UpdateAfterModify();
			return null;
		}

		if (TypeToAdd.equals("InstallationFire")) {
			propertyOnshoreMeasure.getInstallationFire().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("ResponseTime")) {
			propertyOnshoreMeasure.getResponseTime().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("FamiliarityWithPremises")) {
			propertyOnshoreMeasure.getFamiliarityWithPremises().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("PlantFireBrigadeType")) {
			propertyOnshoreMeasure.getPlantFireBrigadeType().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("CBI")) {
			propertyOnshoreMeasure.getCBI().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("BIIndemnityPeriod")) {
			propertyOnshoreMeasure.getBiIndemnityPeriod().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("MLoPIndemnityPeriod")) {
			propertyOnshoreMeasure.getMLoPIndemnityPeriod().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("Assesment")) {
			propertyOnshoreMeasure.getAssesment().put(Key, Value);
			UpdateAfterModify();
			return null;
		}
		if (TypeToAdd.equals("PMLerror")) {
			propertyOnshoreMeasure.getPMLerror().put(Key, Value);
			UpdateAfterModify();
			return null;
		}

		return null;
	}

	public void UpdateAfterModify() {
		propertyOnshoreMeasureServicesLocal.AddMeasure(propertyOnshoreMeasure);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('modiffactor').hide();");
		FacesContext.getCurrentInstance().addMessage(
				"messages1",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Factor Modified",
						""));
	}

	// Modify factor End

	// Delete Factor begin
	public String DeleteFactor(String type) {
		String ssd[];
		ssd = SelectionPropertyOnshore.split("=");
		Key = ssd[0];
		Value = ssd[1];
		if (type.equals("ConstructionClass")) {
			propertyOnshoreMeasure.getConstructionClass().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("LossRatio")) {
			propertyOnshoreMeasure.getLossRatio().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("LossRatio")) {
			propertyOnshoreMeasure.getLossRatio().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("LossRatio")) {
			propertyOnshoreMeasure.getLossRatio().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("LossRatio")) {
			propertyOnshoreMeasure.getLossRatio().remove(Key);
			UpdateAfterDelete();
			return null;
		}

		if (type.equals("Capacity")) {
			propertyOnshoreMeasure.getCapacity().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("PlantAge")) {
			propertyOnshoreMeasure.getPlantAge().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("MachnineMake")) {
			propertyOnshoreMeasure.getMachineMake().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("MaintenanceQuality")) {
			propertyOnshoreMeasure.getMaintenanceQuality().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("AvaibilityOfParts")) {
			propertyOnshoreMeasure.getAvaibilityOfParts().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("InstallationEstinguishment")) {
			propertyOnshoreMeasure.getInstallationEstinguishment().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("Coverage")) {
			propertyOnshoreMeasure.getCoverage().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("Link")) {
			propertyOnshoreMeasure.getLink().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("InstallationFire")) {
			propertyOnshoreMeasure.getInstallationFire().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("ResponseTime")) {
			propertyOnshoreMeasure.getResponseTime().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("FamiliarityWithPremises")) {
			propertyOnshoreMeasure.getFamiliarityWithPremises().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("PlantFireBrigadeType")) {
			propertyOnshoreMeasure.getPlantFireBrigadeType().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("MLoPIndemnityPeriod")) {
			propertyOnshoreMeasure.getMLoPIndemnityPeriod().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("BIIndemnityPeriod")) {
			propertyOnshoreMeasure.getBiIndemnityPeriod().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("CBI")) {
			propertyOnshoreMeasure.getCBI().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("Assesment")) {
			propertyOnshoreMeasure.getAssesment().remove(Key);
			UpdateAfterDelete();
			return null;
		}
		if (type.equals("PMLerror")) {
			propertyOnshoreMeasure.getPMLerror().remove(Key);
			UpdateAfterDelete();
			return null;
		}

		return null;
	}

	public void UpdateAfterDelete() {
		propertyOnshoreMeasureServicesLocal.AddMeasure(propertyOnshoreMeasure);
		FacesContext.getCurrentInstance().addMessage(
				"messages1",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Factor Deleted",
						""));
	}

	// Delete FActor End

	// get set

	public Measure getWorkingMeasure() {
		return workingMeasure;
	}

	public void setWorkingMeasure(Measure workingMeasure) {
		this.workingMeasure = workingMeasure;
	}

	public PropertyOnshoreMeasure getPropertyOnshoreMeasure() {
		return propertyOnshoreMeasure;
	}

	public void setPropertyOnshoreMeasure(
			PropertyOnshoreMeasure propertyOnshoreMeasure) {
		this.propertyOnshoreMeasure = propertyOnshoreMeasure;
	}

	public String getSelectionPropertyOnshore() {
		return SelectionPropertyOnshore;
	}

	public void setSelectionPropertyOnshore(String selectionPropertyOnshore) {
		SelectionPropertyOnshore = selectionPropertyOnshore;
	}

	public String getTypeToAdd() {
		return TypeToAdd;
	}

	public void setTypeToAdd(String typeToAdd) {
		TypeToAdd = typeToAdd;
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
}
