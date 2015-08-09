package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: PropertyOnshoreMeasure
 * 
 */
@Entity
public class PropertyOnshoreMeasure implements Serializable {

	private int id;
	private int idMeasure;
	private HashMap<String, String> LossRatio;
	private HashMap<String, String> ConstructionClass;

	public PropertyOnshoreMeasure() {
		super();
		this.SecurityGuard = "0";
		this.ManagementRiskAwareness = "0";
		this.PhysicalBarriers = "0";

		this.AccessControlAndAlarmSystem = "0";
		this.SmokeControl = "0";
		this.WeldingControl = "0";
		this.FirePrevention = "0";
		this.StockStoragePractise = "0";
		this.MechanicalMaintenance = "0";
		this.ElectricalMaintenance = "0";

		this.FullFilment = "0";
		this.RegularMaintenance = "0";
		this.BackupFacilities = "0";

		this.BusinessContinuityPlan = "0";
		this.BottleneckLossMinimised = "0";

	}

	private String SecurityGuard;
	private String ManagementRiskAwareness;
	private String PhysicalBarriers;
	private String AccessControlAndAlarmSystem;
	private String SmokeControl;
	private String WeldingControl;
	private String FirePrevention;
	private String StockStoragePractise;
	private String MechanicalMaintenance;
	private String ElectricalMaintenance;
	private String FullFilment;
	private String RegularMaintenance;
	private String BackupFacilities;
	private String BusinessContinuityPlan;
	private String BottleneckLossMinimised;
	private HashMap<String, String> Assesment;
	private HashMap<String, String> MachineMake;
	private HashMap<String, String> Capacity;
	private HashMap<String, String> PlantAge;
	private HashMap<String, String> MaintenanceQuality;
	private HashMap<String, String> AvaibilityOfParts;
	private HashMap<String, String> FamiliarityWithPremises;
	private HashMap<String, String> ResponseTime;

	private HashMap<String, String> PlantFireBrigadeType;
	private HashMap<String, String> InstallationEstinguishment;
	private HashMap<String, String> Coverage;
	private HashMap<String, String> InstallationFire;
	private HashMap<String, String> Link;
	private HashMap<String, String> BiIndemnityPeriod;
	private HashMap<String, String> MLoPIndemnityPeriod;
	private HashMap<String, String> CBI;
	private HashMap<String, String> PMLerror;

	public HashMap<String, String> getMachineMake() {
		return MachineMake;
	}

	public void setMachineMake(HashMap<String, String> machineMake) {
		MachineMake = machineMake;
	}

	public HashMap<String, String> getCapacity() {
		return Capacity;
	}

	public void setCapacity(HashMap<String, String> capacity) {
		Capacity = capacity;
	}

	public HashMap<String, String> getPlantAge() {
		return PlantAge;
	}

	public void setPlantAge(HashMap<String, String> plantAge) {
		PlantAge = plantAge;
	}

	public HashMap<String, String> getMaintenanceQuality() {
		return MaintenanceQuality;
	}

	public void setMaintenanceQuality(HashMap<String, String> maintenanceQuality) {
		MaintenanceQuality = maintenanceQuality;
	}

	public HashMap<String, String> getAvaibilityOfParts() {
		return AvaibilityOfParts;
	}

	public void setAvaibilityOfParts(HashMap<String, String> avaibilityOfParts) {
		AvaibilityOfParts = avaibilityOfParts;
	}

	public HashMap<String, String> getFamiliarityWithPremises() {
		return FamiliarityWithPremises;
	}

	public void setFamiliarityWithPremises(
			HashMap<String, String> familiarityWithPremises) {
		FamiliarityWithPremises = familiarityWithPremises;
	}

	public HashMap<String, String> getResponseTime() {
		return ResponseTime;
	}

	public void setResponseTime(HashMap<String, String> responseTime) {
		ResponseTime = responseTime;
	}

	public HashMap<String, String> getPlantFireBrigadeType() {
		return PlantFireBrigadeType;
	}

	public void setPlantFireBrigadeType(
			HashMap<String, String> plantFireBrigadeType) {
		PlantFireBrigadeType = plantFireBrigadeType;
	}

	public HashMap<String, String> getInstallationEstinguishment() {
		return InstallationEstinguishment;
	}

	public void setInstallationEstinguishment(
			HashMap<String, String> installationEstinguishment) {
		InstallationEstinguishment = installationEstinguishment;
	}

	public HashMap<String, String> getCoverage() {
		return Coverage;
	}

	public void setCoverage(HashMap<String, String> coverage) {
		Coverage = coverage;
	}

	public HashMap<String, String> getInstallationFire() {
		return InstallationFire;
	}

	public void setInstallationFire(HashMap<String, String> installationFire) {
		InstallationFire = installationFire;
	}

	public HashMap<String, String> getLink() {
		return Link;
	}

	public void setLink(HashMap<String, String> link) {
		Link = link;
	}

	public String getSecurityGuard() {
		return SecurityGuard;
	}

	public void setSecurityGuard(String securityGuard) {
		SecurityGuard = securityGuard;
	}

	public String getManagementRiskAwareness() {
		return ManagementRiskAwareness;
	}

	public void setManagementRiskAwareness(String managementRiskAwareness) {
		ManagementRiskAwareness = managementRiskAwareness;
	}

	public String getPhysicalBarriers() {
		return PhysicalBarriers;
	}

	public void setPhysicalBarriers(String physicalBarriers) {
		PhysicalBarriers = physicalBarriers;
	}

	public String getAccessControlAndAlarmSystem() {
		return AccessControlAndAlarmSystem;
	}

	public void setAccessControlAndAlarmSystem(
			String accessControlAndAlarmSystem) {
		AccessControlAndAlarmSystem = accessControlAndAlarmSystem;
	}

	public String getSmokeControl() {
		return SmokeControl;
	}

	public void setSmokeControl(String smokeControl) {
		SmokeControl = smokeControl;
	}

	public String getWeldingControl() {
		return WeldingControl;
	}

	public void setWeldingControl(String weldingControl) {
		WeldingControl = weldingControl;
	}

	public String getFirePrevention() {
		return FirePrevention;
	}

	public void setFirePrevention(String firePrevention) {
		FirePrevention = firePrevention;
	}

	public String getStockStoragePractise() {
		return StockStoragePractise;
	}

	public void setStockStoragePractise(String stockStoragePractise) {
		StockStoragePractise = stockStoragePractise;
	}

	public String getMechanicalMaintenance() {
		return MechanicalMaintenance;
	}

	public void setMechanicalMaintenance(String mechanicalMaintenance) {
		MechanicalMaintenance = mechanicalMaintenance;
	}

	public String getElectricalMaintenance() {
		return ElectricalMaintenance;
	}

	public void setElectricalMaintenance(String electricalMaintenance) {
		ElectricalMaintenance = electricalMaintenance;
	}

	public String getFullFilment() {
		return FullFilment;
	}

	public void setFullFilment(String fullFilment) {
		FullFilment = fullFilment;
	}

	public String getRegularMaintenance() {
		return RegularMaintenance;
	}

	public void setRegularMaintenance(String regularMaintenance) {
		RegularMaintenance = regularMaintenance;
	}

	public String getBackupFacilities() {
		return BackupFacilities;
	}

	public void setBackupFacilities(String backupFacilities) {
		BackupFacilities = backupFacilities;
	}

	public String getBusinessContinuityPlan() {
		return BusinessContinuityPlan;
	}

	public void setBusinessContinuityPlan(String businessContinuityPlan) {
		BusinessContinuityPlan = businessContinuityPlan;
	}

	public String getBottleneckLossMinimised() {
		return BottleneckLossMinimised;
	}

	public void setBottleneckLossMinimised(String bottleneckLossMinimised) {
		BottleneckLossMinimised = bottleneckLossMinimised;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMeasure() {
		return this.idMeasure;
	}

	public void setIdMeasure(int idMeasure) {
		this.idMeasure = idMeasure;
	}

	public HashMap<String, String> getLossRatio() {
		return LossRatio;
	}

	public void setLossRatio(HashMap<String, String> lossRatio) {
		LossRatio = lossRatio;
	}

	public HashMap<String, String> getConstructionClass() {
		return ConstructionClass;
	}

	public void setConstructionClass(HashMap<String, String> constructionClass) {
		ConstructionClass = constructionClass;
	}

	public HashMap<String, String> getBiIndemnityPeriod() {
		return BiIndemnityPeriod;
	}

	public void setBiIndemnityPeriod(HashMap<String, String> biIndemnityPeriod) {
		BiIndemnityPeriod = biIndemnityPeriod;
	}

	public HashMap<String, String> getMLoPIndemnityPeriod() {
		return MLoPIndemnityPeriod;
	}

	public void setMLoPIndemnityPeriod(
			HashMap<String, String> mLoPIndemnityPeriod) {
		MLoPIndemnityPeriod = mLoPIndemnityPeriod;
	}

	public HashMap<String, String> getCBI() {
		return CBI;
	}

	public void setCBI(HashMap<String, String> cBI) {
		CBI = cBI;
	}

	public HashMap<String, String> getAssesment() {
		return Assesment;
	}

	public void setAssesment(HashMap<String, String> assesment) {
		Assesment = assesment;
	}

	public HashMap<String, String> getPMLerror() {
		return PMLerror;
	}

	public void setPMLerror(HashMap<String, String> pMLerror) {
		PMLerror = pMLerror;
	}

}
