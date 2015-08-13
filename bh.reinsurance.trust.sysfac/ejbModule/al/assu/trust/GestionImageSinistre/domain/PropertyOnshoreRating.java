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
	private double ModifiedPrice;
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
	private double LY1_Premium;
	private boolean LY1_Selected;
	private double LY1_Share;
	private double LY1_CommercialPrice;
	private double LY1_BasicRIskPrice;
	private double LY1_NatCatastrophe;
	private double LY1_InternalCostLoading;
	private double LY1_ProfitMargin;
	private double LY1_Brokerage;

	private double LY2_lim;
	private double LY2_ded;
	private double LY2_Premium;
	private boolean LY2_Selected;
	private double LY2_Share;
	private double LY2_CommercialPrice;
	private double LY2_BasicRIskPrice;
	private double LY2_NatCatastrophe;
	private double LY2_InternalCostLoading;
	private double LY2_ProfitMargin;
	private double LY2_Brokerage;

	private double LY3_lim;
	private double LY3_ded;
	private double LY3_Premium;
	private boolean LY3_Selected;
	private double LY3_Share;
	private double LY3_CommercialPrice;
	private double LY3_BasicRIskPrice;
	private double LY3_NatCatastrophe;
	private double LY3_InternalCostLoading;
	private double LY3_ProfitMargin;
	private double LY3_Brokerage;

	private double LY4_lim;
	private double LY4_ded;
	private double LY4_Premium;
	private boolean LY4_Selected;
	private double LY4_Share;
	private double LY4_CommercialPrice;
	private double LY4_BasicRIskPrice;
	private double LY4_NatCatastrophe;
	private double LY4_InternalCostLoading;
	private double LY4_ProfitMargin;
	private double LY4_Brokerage;

	private double LY5_lim;
	private double LY5_ded;
	private double LY5_Premium;
	private boolean LY5_Selected;
	private double LY5_Share;
	private double LY5_CommercialPrice;
	private double LY5_BasicRIskPrice;
	private double LY5_NatCatastrophe;
	private double LY5_InternalCostLoading;
	private double LY5_ProfitMargin;
	private double LY5_Brokerage;

	// REsults
	private double PD_BaseRate;
	private double PD_AdRate;
	private double PD_Disc;
	private double PD_Ded;
	private double PD_PML;
	private double PD_Premium;
	private double PD_TotalSumInsured;

	private double BI_BaseRate;
	private double BI_AdjRate;
	private double BI_Disc;
	private double BI_Ded;
	private double BI_PML;
	private double BI_Premium;
	private double BI_TotalSumInsured;

	private double MB_BaseRate;
	private double MB_AdjRate;
	private double MB_Discount;
	private double MB_Deductible;
	private double MB_PML;
	private double MB_Premium;
	private double MB_TotalSumInsure;

	private double Mlop_BaseRate;
	private double Mlop_AdjRate;
	private double Mlop_Discount;
	private double Mlop_Deductible;
	private double Mlop_PML;
	private double Mlop_Premium;
	private double Mlop_TotalSumInsured;

	private double Stock_BaseRate;
	private double Stock_AdjRate;
	private double Stock_Discount;
	private double Stock_Deductible;
	private double Stock_Premium;
	private double Stock_PML;
	private double Stock_TotalSumInsured;

	private double Other_BaseRate;
	private double Other_AdjRate;
	private double Other_Discount;
	private double Other_Deductible;
	private double Other_Premium;
	private double Other_PML;
	private double Other_TotalSumInsured;

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
		this.LY1_Selected = false;
		this.LY2_Selected = false;
		this.LY3_Selected = false;
		this.LY4_Selected = false;
		this.LY5_Selected = false;
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

	public double getModifiedPrice() {
		return ModifiedPrice;
	}

	public void setModifiedPrice(double modifiedPrice) {
		ModifiedPrice = modifiedPrice;
	}

	public double getPD_BaseRate() {
		return PD_BaseRate;
	}

	public void setPD_BaseRate(double pD_BaseRate) {
		PD_BaseRate = pD_BaseRate;
	}

	public double getPD_AdRate() {
		return PD_AdRate;
	}

	public void setPD_AdRate(double pD_AdRate) {
		PD_AdRate = pD_AdRate;
	}

	public double getPD_Disc() {
		return PD_Disc;
	}

	public void setPD_Disc(double pD_Disc) {
		PD_Disc = pD_Disc;
	}

	public double getPD_Ded() {
		return PD_Ded;
	}

	public void setPD_Ded(double pD_Ded) {
		PD_Ded = pD_Ded;
	}

	public double getPD_PML() {
		return PD_PML;
	}

	public void setPD_PML(double pD_PML) {
		PD_PML = pD_PML;
	}

	public double getPD_Premium() {
		return PD_Premium;
	}

	public void setPD_Premium(double pD_Premium) {
		PD_Premium = pD_Premium;
	}

	public double getPD_TotalSumInsured() {
		return PD_TotalSumInsured;
	}

	public void setPD_TotalSumInsured(double pD_TotalSumInsured) {
		PD_TotalSumInsured = pD_TotalSumInsured;
	}

	public double getBI_BaseRate() {
		return BI_BaseRate;
	}

	public void setBI_BaseRate(double bI_BaseRate) {
		BI_BaseRate = bI_BaseRate;
	}

	public double getBI_AdjRate() {
		return BI_AdjRate;
	}

	public void setBI_AdjRate(double bI_AdjRate) {
		BI_AdjRate = bI_AdjRate;
	}

	public double getBI_Disc() {
		return BI_Disc;
	}

	public void setBI_Disc(double bI_Disc) {
		BI_Disc = bI_Disc;
	}

	public double getBI_Ded() {
		return BI_Ded;
	}

	public void setBI_Ded(double bI_Ded) {
		BI_Ded = bI_Ded;
	}

	public double getBI_PML() {
		return BI_PML;
	}

	public void setBI_PML(double bI_PML) {
		BI_PML = bI_PML;
	}

	public double getBI_Premium() {
		return BI_Premium;
	}

	public void setBI_Premium(double bI_Premium) {
		BI_Premium = bI_Premium;
	}

	public double getBI_TotalSumInsured() {
		return BI_TotalSumInsured;
	}

	public void setBI_TotalSumInsured(double bI_TotalSumInsured) {
		BI_TotalSumInsured = bI_TotalSumInsured;
	}

	public double getMB_BaseRate() {
		return MB_BaseRate;
	}

	public void setMB_BaseRate(double mB_BaseRate) {
		MB_BaseRate = mB_BaseRate;
	}

	public double getMB_AdjRate() {
		return MB_AdjRate;
	}

	public void setMB_AdjRate(double mB_AdjRate) {
		MB_AdjRate = mB_AdjRate;
	}

	public double getMB_Discount() {
		return MB_Discount;
	}

	public void setMB_Discount(double mB_Discount) {
		MB_Discount = mB_Discount;
	}

	public double getMB_Deductible() {
		return MB_Deductible;
	}

	public void setMB_Deductible(double mB_Deductible) {
		MB_Deductible = mB_Deductible;
	}

	public double getMB_PML() {
		return MB_PML;
	}

	public void setMB_PML(double mB_PML) {
		MB_PML = mB_PML;
	}

	public double getMB_Premium() {
		return MB_Premium;
	}

	public void setMB_Premium(double mB_Premium) {
		MB_Premium = mB_Premium;
	}

	public double getMB_TotalSumInsure() {
		return MB_TotalSumInsure;
	}

	public void setMB_TotalSumInsure(double mB_TotalSumInsure) {
		MB_TotalSumInsure = mB_TotalSumInsure;
	}

	public double getMlop_BaseRate() {
		return Mlop_BaseRate;
	}

	public void setMlop_BaseRate(double mlop_BaseRate) {
		Mlop_BaseRate = mlop_BaseRate;
	}

	public double getMlop_AdjRate() {
		return Mlop_AdjRate;
	}

	public void setMlop_AdjRate(double mlop_AdjRate) {
		Mlop_AdjRate = mlop_AdjRate;
	}

	public double getMlop_Discount() {
		return Mlop_Discount;
	}

	public void setMlop_Discount(double mlop_Discount) {
		Mlop_Discount = mlop_Discount;
	}

	public double getMlop_Deductible() {
		return Mlop_Deductible;
	}

	public void setMlop_Deductible(double mlop_Deductible) {
		Mlop_Deductible = mlop_Deductible;
	}

	public double getMlop_PML() {
		return Mlop_PML;
	}

	public void setMlop_PML(double mlop_PML) {
		Mlop_PML = mlop_PML;
	}

	public double getMlop_Premium() {
		return Mlop_Premium;
	}

	public void setMlop_Premium(double mlop_Premium) {
		Mlop_Premium = mlop_Premium;
	}

	public double getMlop_TotalSumInsured() {
		return Mlop_TotalSumInsured;
	}

	public void setMlop_TotalSumInsured(double mlop_TotalSumInsured) {
		Mlop_TotalSumInsured = mlop_TotalSumInsured;
	}

	public double getStock_BaseRate() {
		return Stock_BaseRate;
	}

	public void setStock_BaseRate(double stock_BaseRate) {
		Stock_BaseRate = stock_BaseRate;
	}

	public double getStock_AdjRate() {
		return Stock_AdjRate;
	}

	public void setStock_AdjRate(double stock_AdjRate) {
		Stock_AdjRate = stock_AdjRate;
	}

	public double getStock_Discount() {
		return Stock_Discount;
	}

	public void setStock_Discount(double stock_Discount) {
		Stock_Discount = stock_Discount;
	}

	public double getStock_Deductible() {
		return Stock_Deductible;
	}

	public void setStock_Deductible(double stock_Deductible) {
		Stock_Deductible = stock_Deductible;
	}

	public double getStock_Premium() {
		return Stock_Premium;
	}

	public void setStock_Premium(double stock_Premium) {
		Stock_Premium = stock_Premium;
	}

	public double getStock_PML() {
		return Stock_PML;
	}

	public void setStock_PML(double stock_PML) {
		Stock_PML = stock_PML;
	}

	public double getStock_TotalSumInsured() {
		return Stock_TotalSumInsured;
	}

	public void setStock_TotalSumInsured(double stock_TotalSumInsured) {
		Stock_TotalSumInsured = stock_TotalSumInsured;
	}

	public double getOther_BaseRate() {
		return Other_BaseRate;
	}

	public void setOther_BaseRate(double other_BaseRate) {
		Other_BaseRate = other_BaseRate;
	}

	public double getOther_AdjRate() {
		return Other_AdjRate;
	}

	public void setOther_AdjRate(double other_AdjRate) {
		Other_AdjRate = other_AdjRate;
	}

	public double getOther_Discount() {
		return Other_Discount;
	}

	public void setOther_Discount(double other_Discount) {
		Other_Discount = other_Discount;
	}

	public double getOther_Deductible() {
		return Other_Deductible;
	}

	public void setOther_Deductible(double other_Deductible) {
		Other_Deductible = other_Deductible;
	}

	public double getOther_Premium() {
		return Other_Premium;
	}

	public void setOther_Premium(double other_Premium) {
		Other_Premium = other_Premium;
	}

	public double getOther_PML() {
		return Other_PML;
	}

	public void setOther_PML(double other_PML) {
		Other_PML = other_PML;
	}

	public double getOther_TotalSumInsured() {
		return Other_TotalSumInsured;
	}

	public void setOther_TotalSumInsured(double other_TotalSumInsured) {
		Other_TotalSumInsured = other_TotalSumInsured;
	}

	public double getLY1_Premium() {
		return LY1_Premium;
	}

	public void setLY1_Premium(double lY1_Premium) {
		LY1_Premium = lY1_Premium;
	}

	public boolean isLY1_Selected() {
		return LY1_Selected;
	}

	public void setLY1_Selected(boolean lY1_Selected) {
		LY1_Selected = lY1_Selected;
	}

	public double getLY2_Premium() {
		return LY2_Premium;
	}

	public void setLY2_Premium(double lY2_Premium) {
		LY2_Premium = lY2_Premium;
	}

	public boolean isLY2_Selected() {
		return LY2_Selected;
	}

	public void setLY2_Selected(boolean lY2_Selected) {
		LY2_Selected = lY2_Selected;
	}

	public double getLY3_Premium() {
		return LY3_Premium;
	}

	public void setLY3_Premium(double lY3_Premium) {
		LY3_Premium = lY3_Premium;
	}

	public boolean isLY3_Selected() {
		return LY3_Selected;
	}

	public void setLY3_Selected(boolean lY3_Selected) {
		LY3_Selected = lY3_Selected;
	}

	public double getLY4_Premium() {
		return LY4_Premium;
	}

	public void setLY4_Premium(double lY4_Premium) {
		LY4_Premium = lY4_Premium;
	}

	public boolean isLY4_Selected() {
		return LY4_Selected;
	}

	public void setLY4_Selected(boolean lY4_Selected) {
		LY4_Selected = lY4_Selected;
	}

	public double getLY5_Premium() {
		return LY5_Premium;
	}

	public void setLY5_Premium(double lY5_Premium) {
		LY5_Premium = lY5_Premium;
	}

	public boolean isLY5_Selected() {
		return LY5_Selected;
	}

	public void setLY5_Selected(boolean lY5_Selected) {
		LY5_Selected = lY5_Selected;
	}

	public double getLY1_Share() {
		return LY1_Share;
	}

	public void setLY1_Share(double lY1_Share) {
		LY1_Share = lY1_Share;
	}

	public double getLY2_Share() {
		return LY2_Share;
	}

	public void setLY2_Share(double lY2_Share) {
		LY2_Share = lY2_Share;
	}

	public double getLY3_Share() {
		return LY3_Share;
	}

	public void setLY3_Share(double lY3_Share) {
		LY3_Share = lY3_Share;
	}

	public double getLY4_Share() {
		return LY4_Share;
	}

	public void setLY4_Share(double lY4_Share) {
		LY4_Share = lY4_Share;
	}

	public double getLY5_Share() {
		return LY5_Share;
	}

	public void setLY5_Share(double lY5_Share) {
		LY5_Share = lY5_Share;
	}

	public double getLY1_CommercialPrice() {
		return LY1_CommercialPrice;
	}

	public void setLY1_CommercialPrice(double lY1_CommercialPrice) {
		LY1_CommercialPrice = lY1_CommercialPrice;
	}

	public double getLY2_CommercialPrice() {
		return LY2_CommercialPrice;
	}

	public void setLY2_CommercialPrice(double lY2_CommercialPrice) {
		LY2_CommercialPrice = lY2_CommercialPrice;
	}

	public double getLY3_CommercialPrice() {
		return LY3_CommercialPrice;
	}

	public void setLY3_CommercialPrice(double lY3_CommercialPrice) {
		LY3_CommercialPrice = lY3_CommercialPrice;
	}

	public double getLY4_CommercialPrice() {
		return LY4_CommercialPrice;
	}

	public void setLY4_CommercialPrice(double lY4_CommercialPrice) {
		LY4_CommercialPrice = lY4_CommercialPrice;
	}

	public double getLY5_CommercialPrice() {
		return LY5_CommercialPrice;
	}

	public void setLY5_CommercialPrice(double lY5_CommercialPrice) {
		LY5_CommercialPrice = lY5_CommercialPrice;
	}

	public double getLY1_BasicRIskPrice() {
		return LY1_BasicRIskPrice;
	}

	public void setLY1_BasicRIskPrice(double lY1_BasicRIskPrice) {
		LY1_BasicRIskPrice = lY1_BasicRIskPrice;
	}

	public double getLY1_ProfitMargin() {
		return LY1_ProfitMargin;
	}

	public void setLY1_ProfitMargin(double lY1_ProfitMargin) {
		LY1_ProfitMargin = lY1_ProfitMargin;
	}

	public double getLY1_Brokerage() {
		return LY1_Brokerage;
	}

	public void setLY1_Brokerage(double lY1_Brokerage) {
		LY1_Brokerage = lY1_Brokerage;
	}

	public double getLY1_NatCatastrophe() {
		return LY1_NatCatastrophe;
	}

	public void setLY1_NatCatastrophe(double lY1_NatCatastrophe) {
		LY1_NatCatastrophe = lY1_NatCatastrophe;
	}

	public double getLY1_InternalCostLoading() {
		return LY1_InternalCostLoading;
	}

	public void setLY1_InternalCostLoading(double lY1_InternalCostLoading) {
		LY1_InternalCostLoading = lY1_InternalCostLoading;
	}

	public double getLY2_BasicRIskPrice() {
		return LY2_BasicRIskPrice;
	}

	public void setLY2_BasicRIskPrice(double lY2_BasicRIskPrice) {
		LY2_BasicRIskPrice = lY2_BasicRIskPrice;
	}

	public double getLY2_NatCatastrophe() {
		return LY2_NatCatastrophe;
	}

	public void setLY2_NatCatastrophe(double lY2_NatCatastrophe) {
		LY2_NatCatastrophe = lY2_NatCatastrophe;
	}

	public double getLY2_InternalCostLoading() {
		return LY2_InternalCostLoading;
	}

	public void setLY2_InternalCostLoading(double lY2_InternalCostLoading) {
		LY2_InternalCostLoading = lY2_InternalCostLoading;
	}

	public double getLY2_ProfitMargin() {
		return LY2_ProfitMargin;
	}

	public void setLY2_ProfitMargin(double lY2_ProfitMargin) {
		LY2_ProfitMargin = lY2_ProfitMargin;
	}

	public double getLY2_Brokerage() {
		return LY2_Brokerage;
	}

	public void setLY2_Brokerage(double lY2_Brokerage) {
		LY2_Brokerage = lY2_Brokerage;
	}

	public double getLY3_BasicRIskPrice() {
		return LY3_BasicRIskPrice;
	}

	public void setLY3_BasicRIskPrice(double lY3_BasicRIskPrice) {
		LY3_BasicRIskPrice = lY3_BasicRIskPrice;
	}

	public double getLY3_NatCatastrophe() {
		return LY3_NatCatastrophe;
	}

	public void setLY3_NatCatastrophe(double lY3_NatCatastrophe) {
		LY3_NatCatastrophe = lY3_NatCatastrophe;
	}

	public double getLY3_InternalCostLoading() {
		return LY3_InternalCostLoading;
	}

	public void setLY3_InternalCostLoading(double lY3_InternalCostLoading) {
		LY3_InternalCostLoading = lY3_InternalCostLoading;
	}

	public double getLY3_ProfitMargin() {
		return LY3_ProfitMargin;
	}

	public void setLY3_ProfitMargin(double lY3_ProfitMargin) {
		LY3_ProfitMargin = lY3_ProfitMargin;
	}

	public double getLY3_Brokerage() {
		return LY3_Brokerage;
	}

	public void setLY3_Brokerage(double lY3_Brokerage) {
		LY3_Brokerage = lY3_Brokerage;
	}

	public double getLY4_BasicRIskPrice() {
		return LY4_BasicRIskPrice;
	}

	public void setLY4_BasicRIskPrice(double lY4_BasicRIskPrice) {
		LY4_BasicRIskPrice = lY4_BasicRIskPrice;
	}

	public double getLY4_NatCatastrophe() {
		return LY4_NatCatastrophe;
	}

	public void setLY4_NatCatastrophe(double lY4_NatCatastrophe) {
		LY4_NatCatastrophe = lY4_NatCatastrophe;
	}

	public double getLY4_InternalCostLoading() {
		return LY4_InternalCostLoading;
	}

	public void setLY4_InternalCostLoading(double lY4_InternalCostLoading) {
		LY4_InternalCostLoading = lY4_InternalCostLoading;
	}

	public double getLY4_ProfitMargin() {
		return LY4_ProfitMargin;
	}

	public void setLY4_ProfitMargin(double lY4_ProfitMargin) {
		LY4_ProfitMargin = lY4_ProfitMargin;
	}

	public double getLY4_Brokerage() {
		return LY4_Brokerage;
	}

	public void setLY4_Brokerage(double lY4_Brokerage) {
		LY4_Brokerage = lY4_Brokerage;
	}

	public double getLY5_BasicRIskPrice() {
		return LY5_BasicRIskPrice;
	}

	public void setLY5_BasicRIskPrice(double lY5_BasicRIskPrice) {
		LY5_BasicRIskPrice = lY5_BasicRIskPrice;
	}

	public double getLY5_NatCatastrophe() {
		return LY5_NatCatastrophe;
	}

	public void setLY5_NatCatastrophe(double lY5_NatCatastrophe) {
		LY5_NatCatastrophe = lY5_NatCatastrophe;
	}

	public double getLY5_InternalCostLoading() {
		return LY5_InternalCostLoading;
	}

	public void setLY5_InternalCostLoading(double lY5_InternalCostLoading) {
		LY5_InternalCostLoading = lY5_InternalCostLoading;
	}

	public double getLY5_ProfitMargin() {
		return LY5_ProfitMargin;
	}

	public void setLY5_ProfitMargin(double lY5_ProfitMargin) {
		LY5_ProfitMargin = lY5_ProfitMargin;
	}

	public double getLY5_Brokerage() {
		return LY5_Brokerage;
	}

	public void setLY5_Brokerage(double lY5_Brokerage) {
		LY5_Brokerage = lY5_Brokerage;
	}

}
