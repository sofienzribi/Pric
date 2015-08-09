package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: PropertyOnshoreRating
 * 
 */
@Entity
public class PropertyOnshoreRating implements Serializable {

	private int id;
	private int idproj;
	private double ProposedShare;
	private double MaxLiability;
	private double RatingFactor;
	private double Nat_Catastrophe;
	private double TotalRiskPrice;
	private double Expenses;
	private double CostOfCapital;
	private double ReinsuranceCosts;
	private double TotalCosts;
	private double ProfitMargin;
	private double Brokerage;
	private double CommercialPrice;
	private double PD_Deductible_amount;
	private double PD_deductible_perc;
	private double BI_Deductible_amount;
	private double BI_deductible_perc;
	private double Stock_amount;
	private double Other_amount;

	private String Classof;
	private String Occupancy;
	private String RiskGrade;
	private String BasisOfAcceptance;
	private String BiIndemnityPeriod;
	private String MlopIndemnityPeriod;
	private String CBI;
	private String PMLerror;

	// riskasses
	private String LossRatio;
	private String ConstructionClass;
	private String SecurityGuard;
	private String ManagementRisk;
	private String PhysicalBarriers;
	private String AccessControl;
	private String SmokeControl;
	private String WeldingControl;
	private String FirePrevention;
	private String StockStorage;
	private String MechanicalMaintenance;
	private String ElectricalMaintenance;
	private String FullFilment;
	private String RegularMaintenance;
	private String BackupFacilities;
	private String BussinessPlanContinuity;
	private String FireDetectionInstallaion;
	private String Link;
	private String EstinguishmentInstallation;
	private String Coverage;
	private String PlantFireBrigadeType;
	private String FamiliarityWithPremises;
	private String ResponseTime;
	private String MachineMake;
	private String Capacity;
	private String PlantAge;
	private String MaintenanceQuality;
	private String AvailabilityOfParts;
	private String BottleneckLossMinimised;

	// XL layers var
	private double OPD_lim;
	private double OPD_ded;
	private double LY1_lim;
	private double LY1_ded;
	private double LY2_lim;
	private double LY2_ded;
	private double LY3_lim;
	private double LY3_ded;
	private double LY4_lim;
	private double LY4_ded;
	private double LY5_lim;
	private double LY5_ded;

	private static final long serialVersionUID = 1L;

	public PropertyOnshoreRating() {
		super();
		this.BasisOfAcceptance = "qs";
		this.LossRatio = "New Risk";
		this.ConstructionClass = "B";
		this.SecurityGuard = "Good";
		this.ManagementRisk = "Good";
		this.PhysicalBarriers = "Good";
		this.AccessControl = "Good";
		this.SmokeControl = "Good";
		this.WeldingControl = "Good";
		this.FirePrevention = "Good";
		this.StockStorage = "Good";
		this.MechanicalMaintenance = "Good";
		this.ElectricalMaintenance = "Good";
		this.FullFilment = "Good";
		this.RegularMaintenance = "Good";
		this.BackupFacilities = "Good";
		this.BussinessPlanContinuity = "Good";
		this.BottleneckLossMinimised = "Good";
		this.setPMLerror("0%");

		this.EstinguishmentInstallation = "None";
		this.Coverage = ">=0%";
		this.FireDetectionInstallaion = "autowith";
		this.Link = "linkedtoplant";
		this.PlantFireBrigadeType = "None";
		this.FamiliarityWithPremises = "no";
		this.ResponseTime = "more15";
		this.MachineMake = "Siemens";
		this.Capacity = "<50";
		this.PlantAge = "<10 years";
		this.MaintenanceQuality = "Poor";
		this.AvailabilityOfParts = "Low";
		this.Classof = "Food/ Beverage and Tobacco";
		this.Occupancy = "Bakery/biscuits mfg";
		this.BiIndemnityPeriod = "0-5";
		this.MlopIndemnityPeriod = "0-5";
		

	}

	public String getOccupancy() {
		return Occupancy;
	}

	public void setOccupancy(String occupancy) {
		Occupancy = occupancy;
	}

	public String getRiskGrade() {
		return RiskGrade;
	}

	public void setRiskGrade(String riskGrade) {
		RiskGrade = riskGrade;
	}

	public String getLossRatio() {
		return LossRatio;
	}

	public void setLossRatio(String lossRatio) {
		LossRatio = lossRatio;
	}

	public String getConstructionClass() {
		return ConstructionClass;
	}

	public void setConstructionClass(String constructionClass) {
		ConstructionClass = constructionClass;
	}

	public String getSecurityGuard() {
		return SecurityGuard;
	}

	public void setSecurityGuard(String securityGuard) {
		SecurityGuard = securityGuard;
	}

	public String getManagementRisk() {
		return ManagementRisk;
	}

	public void setManagementRisk(String managementRisk) {
		ManagementRisk = managementRisk;
	}

	public String getPhysicalBarriers() {
		return PhysicalBarriers;
	}

	public void setPhysicalBarriers(String physicalBarriers) {
		PhysicalBarriers = physicalBarriers;
	}

	public String getAccessControl() {
		return AccessControl;
	}

	public void setAccessControl(String accessControl) {
		AccessControl = accessControl;
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

	public String getStockStorage() {
		return StockStorage;
	}

	public void setStockStorage(String stockStorage) {
		StockStorage = stockStorage;
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

	public String getBussinessPlanContinuity() {
		return BussinessPlanContinuity;
	}

	public void setBussinessPlanContinuity(String bussinessPlanContinuity) {
		BussinessPlanContinuity = bussinessPlanContinuity;
	}

	public String getBottleneckLossMinimised() {
		return BottleneckLossMinimised;
	}

	public void setBottleneckLossMinimised(String bottleneckLossMinimised) {
		BottleneckLossMinimised = bottleneckLossMinimised;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdproj() {
		return this.idproj;
	}

	public void setIdproj(int idproj) {
		this.idproj = idproj;
	}

	public String getClassof() {
		return Classof;
	}

	public void setClassof(String classof) {
		Classof = classof;
	}

	public String getBasisOfAcceptance() {
		return BasisOfAcceptance;
	}

	public void setBasisOfAcceptance(String basisOfAcceptance) {
		BasisOfAcceptance = basisOfAcceptance;
	}

	public String getBiIndemnityPeriod() {
		return BiIndemnityPeriod;
	}

	public void setBiIndemnityPeriod(String biIndemnityPeriod) {
		BiIndemnityPeriod = biIndemnityPeriod;
	}

	public String getMlopIndemnityPeriod() {
		return MlopIndemnityPeriod;
	}

	public void setMlopIndemnityPeriod(String mlopIndemnityPeriod) {
		MlopIndemnityPeriod = mlopIndemnityPeriod;
	}

	public String getCBI() {
		return CBI;
	}

	public void setCBI(String cBI) {
		CBI = cBI;
	}

	public String getFireDetectionInstallaion() {
		return FireDetectionInstallaion;
	}

	public void setFireDetectionInstallaion(String fireDetectionInstallaion) {
		FireDetectionInstallaion = fireDetectionInstallaion;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	public String getEstinguishmentInstallation() {
		return EstinguishmentInstallation;
	}

	public void setEstinguishmentInstallation(String estinguishmentInstallation) {
		EstinguishmentInstallation = estinguishmentInstallation;
	}

	public String getCoverage() {
		return Coverage;
	}

	public void setCoverage(String coverage) {
		Coverage = coverage;
	}

	public String getPlantFireBrigadeType() {
		return PlantFireBrigadeType;
	}

	public void setPlantFireBrigadeType(String plantFireBrigadeType) {
		PlantFireBrigadeType = plantFireBrigadeType;
	}

	public String getFamiliarityWithPremises() {
		return FamiliarityWithPremises;
	}

	public void setFamiliarityWithPremises(String familiarityWithPremises) {
		FamiliarityWithPremises = familiarityWithPremises;
	}

	public String getResponseTime() {
		return ResponseTime;
	}

	public void setResponseTime(String responseTime) {
		ResponseTime = responseTime;
	}

	public String getMachineMake() {
		return MachineMake;
	}

	public void setMachineMake(String machineMake) {
		MachineMake = machineMake;
	}

	public String getCapacity() {
		return Capacity;
	}

	public void setCapacity(String capacity) {
		Capacity = capacity;
	}

	public String getPlantAge() {
		return PlantAge;
	}

	public void setPlantAge(String plantAge) {
		PlantAge = plantAge;
	}

	public String getMaintenanceQuality() {
		return MaintenanceQuality;
	}

	public void setMaintenanceQuality(String maintenanceQuality) {
		MaintenanceQuality = maintenanceQuality;
	}

	public String getAvailabilityOfParts() {
		return AvailabilityOfParts;
	}

	public void setAvailabilityOfParts(String availabilityOfParts) {
		AvailabilityOfParts = availabilityOfParts;
	}

	public String getPMLerror() {
		return PMLerror;
	}

	public void setPMLerror(String pMLerror) {
		PMLerror = pMLerror;
	}

	public double getProposedShare() {
		return ProposedShare;
	}

	public void setProposedShare(double proposedShare) {
		ProposedShare = proposedShare;
	}

	public double getMaxLiability() {
		return MaxLiability;
	}

	public void setMaxLiability(double maxLiability) {
		MaxLiability = maxLiability;
	}

	public double getRatingFactor() {
		return RatingFactor;
	}

	public void setRatingFactor(double ratingFactor) {
		RatingFactor = ratingFactor;
	}

	public double getNat_Catastrophe() {
		return Nat_Catastrophe;
	}

	public void setNat_Catastrophe(double nat_Catastrophe) {
		Nat_Catastrophe = nat_Catastrophe;
	}

	public double getTotalRiskPrice() {
		return TotalRiskPrice;
	}

	public void setTotalRiskPrice(double totalRiskPrice) {
		TotalRiskPrice = totalRiskPrice;
	}

	public double getExpenses() {
		return Expenses;
	}

	public void setExpenses(double expenses) {
		Expenses = expenses;
	}

	public double getCostOfCapital() {
		return CostOfCapital;
	}

	public void setCostOfCapital(double costOfCapital) {
		CostOfCapital = costOfCapital;
	}

	public double getReinsuranceCosts() {
		return ReinsuranceCosts;
	}

	public void setReinsuranceCosts(double reinsuranceCosts) {
		ReinsuranceCosts = reinsuranceCosts;
	}

	public double getTotalCosts() {
		return TotalCosts;
	}

	public void setTotalCosts(double totalCosts) {
		TotalCosts = totalCosts;
	}

	public double getProfitMargin() {
		return ProfitMargin;
	}

	public void setProfitMargin(double profitMargin) {
		ProfitMargin = profitMargin;
	}

	public double getBrokerage() {
		return Brokerage;
	}

	public void setBrokerage(double brokerage) {
		Brokerage = brokerage;
	}

	public double getCommercialPrice() {
		return CommercialPrice;
	}

	public void setCommercialPrice(double commercialPrice) {
		CommercialPrice = commercialPrice;
	}

	public double getPD_Deductible_amount() {
		return PD_Deductible_amount;
	}

	public void setPD_Deductible_amount(double pD_Deductible_amount) {
		PD_Deductible_amount = pD_Deductible_amount;
	}

	public double getPD_deductible_perc() {
		return PD_deductible_perc;
	}

	public void setPD_deductible_perc(double pD_deductible_perc) {
		PD_deductible_perc = pD_deductible_perc;
	}

	public double getBI_Deductible_amount() {
		return BI_Deductible_amount;
	}

	public void setBI_Deductible_amount(double bI_Deductible_amount) {
		BI_Deductible_amount = bI_Deductible_amount;
	}

	public double getBI_deductible_perc() {
		return BI_deductible_perc;
	}

	public void setBI_deductible_perc(double bI_deductible_perc) {
		BI_deductible_perc = bI_deductible_perc;
	}

	public double getStock_amount() {
		return Stock_amount;
	}

	public void setStock_amount(double stock_amount) {
		Stock_amount = stock_amount;
	}

	public double getOther_amount() {
		return Other_amount;
	}

	public void setOther_amount(double other_amount) {
		Other_amount = other_amount;
	}

	public double getOPD_lim() {
		return OPD_lim;
	}

	public void setOPD_lim(double oPD_lim) {
		OPD_lim = oPD_lim;
	}

	public double getLY1_lim() {
		return LY1_lim;
	}

	public void setLY1_lim(double lY1_lim) {
		LY1_lim = lY1_lim;
	}

	public double getLY2_lim() {
		return LY2_lim;
	}

	public void setLY2_lim(double lY2_lim) {
		LY2_lim = lY2_lim;
	}

	public double getLY3_lim() {
		return LY3_lim;
	}

	public void setLY3_lim(double lY3_lim) {
		LY3_lim = lY3_lim;
	}

	public double getLY4_lim() {
		return LY4_lim;
	}

	public void setLY4_lim(double lY4_lim) {
		LY4_lim = lY4_lim;
	}

	public double getLY5_lim() {
		return LY5_lim;
	}

	public void setLY5_lim(double lY5_lim) {
		LY5_lim = lY5_lim;
	}

	public double getOPD_ded() {
		return OPD_ded;
	}

	public void setOPD_ded(double oPD_ded) {
		OPD_ded = oPD_ded;
	}

	public double getLY1_ded() {
		return LY1_ded;
	}

	public void setLY1_ded(double lY1_ded) {
		LY1_ded = lY1_ded;
	}

	public double getLY2_ded() {
		return LY2_ded;
	}

	public void setLY2_ded(double lY2_ded) {
		LY2_ded = lY2_ded;
	}

	public double getLY3_ded() {
		return LY3_ded;
	}

	public void setLY3_ded(double lY3_ded) {
		LY3_ded = lY3_ded;
	}

	public double getLY4_ded() {
		return LY4_ded;
	}

	public void setLY4_ded(double lY4_ded) {
		LY4_ded = lY4_ded;
	}

	public double getLY5_ded() {
		return LY5_ded;
	}

	public void setLY5_ded(double lY5_ded) {
		LY5_ded = lY5_ded;
	}

}
