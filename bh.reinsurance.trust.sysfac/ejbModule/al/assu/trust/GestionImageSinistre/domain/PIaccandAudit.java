package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: PIaccandAudit
 * 
 */
@Entity
public class PIaccandAudit implements Serializable {

	private int id;
	private int idproj;
	private String bookkeepingandaudit;
	private String managementAdvisory;
	private String Territory;
	private String forecast;
	private String payrollservices;
	private String fiduciary;
	private String Taxpreparation;
	private String securitiesrelated;
	private String financialPlanning;
	private String incuredlosses;
	private String noofclaims;
	private String specialClientProfileList;
	private String retrocover;
	private String extendedreporting;
	private String occurlimit;
	private String aggregatelimit;
	private String Deductible;
	private String lossofdocument;
	private String dishonestofempl;
	private String libel;
	private String defenseexpenses;
	private String commissionandaquisition;
	private String Generalexpenses;
	private String profitcontingency;
	private String Averagerate;
	private String TotalPremium;
	private String GrossRevenue;

	private static final long serialVersionUID = 1L;

	public PIaccandAudit() {
		this.bookkeepingandaudit = "null";
		this.managementAdvisory = "null";
		this.forecast = "null";
		this.payrollservices = "null";
		this.fiduciary = "null";
		this.securitiesrelated = "null";
		this.incuredlosses = "null";
		this.noofclaims = "null";
		this.financialPlanning = "null";
		this.specialClientProfileList = "null";
		this.Taxpreparation = "null";
		this.commissionandaquisition = "0";
		this.profitcontingency = "0";
		this.Generalexpenses = "0";
		this.retrocover = "0";
		this.extendedreporting = "0";
		this.Deductible = "0";
		this.GrossRevenue = "0";
		this.aggregatelimit = "0";
		this.occurlimit = "1";
		this.lossofdocument = "0";
		this.dishonestofempl = "0";
		this.libel = "0";
		this.defenseexpenses = "0";

	}

	public int getIdproj() {
		return idproj;
	}

	public PIaccandAudit(int idproj, String bookkeepingandaudit,
			String managementAdvisory, String forecast, String payrollservices,
			String fiduciary, String taxpreparation, String securitiesrelated,
			String financialPlanning, String incuredlosses, String noofclaims,
			String specialClientProfileList, String retrocover,
			String extendedreporting, String occurlimit, String aggregatelimit,
			String deductible, String lossofdocument, String dishonestofempl,
			String libel, String defenseexpenses,
			String commissionandaquisition, String generalexpenses,
			String profitcontingency, String averagerate, String totalPremium) {
		super();
		this.idproj = idproj;
		this.bookkeepingandaudit = bookkeepingandaudit;
		this.managementAdvisory = managementAdvisory;
		this.forecast = forecast;
		this.payrollservices = payrollservices;
		this.fiduciary = fiduciary;
		Taxpreparation = taxpreparation;
		this.securitiesrelated = securitiesrelated;
		this.financialPlanning = financialPlanning;
		this.incuredlosses = incuredlosses;
		this.noofclaims = noofclaims;
		this.specialClientProfileList = specialClientProfileList;
		this.retrocover = retrocover;
		this.extendedreporting = extendedreporting;
		this.occurlimit = occurlimit;
		this.aggregatelimit = aggregatelimit;
		Deductible = deductible;
		this.lossofdocument = lossofdocument;
		this.dishonestofempl = dishonestofempl;
		this.libel = libel;
		this.defenseexpenses = defenseexpenses;
		this.commissionandaquisition = commissionandaquisition;
		Generalexpenses = generalexpenses;
		this.profitcontingency = profitcontingency;
		Averagerate = averagerate;
		TotalPremium = totalPremium;
	}

	public void setIdproj(int idproj) {
		this.idproj = idproj;
	}

	public String getBookkeepingandaudit() {
		return bookkeepingandaudit;
	}

	public void setBookkeepingandaudit(String bookkeepingandaudit) {
		this.bookkeepingandaudit = bookkeepingandaudit;
	}

	public String getManagementAdvisory() {
		return managementAdvisory;
	}

	public void setManagementAdvisory(String managementAdvisory) {
		this.managementAdvisory = managementAdvisory;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getPayrollservices() {
		return payrollservices;
	}

	public void setPayrollservices(String payrollservices) {
		this.payrollservices = payrollservices;
	}

	public String getFiduciary() {
		return fiduciary;
	}

	public void setFiduciary(String fiduciary) {
		this.fiduciary = fiduciary;
	}

	public String getTaxpreparation() {
		return Taxpreparation;
	}

	public void setTaxpreparation(String taxpreparation) {
		Taxpreparation = taxpreparation;
	}

	public String getSecuritiesrelated() {
		return securitiesrelated;
	}

	public void setSecuritiesrelated(String securitiesrelated) {
		this.securitiesrelated = securitiesrelated;
	}

	public String getFinancialPlanning() {
		return financialPlanning;
	}

	public void setFinancialPlanning(String financialPlanning) {
		this.financialPlanning = financialPlanning;
	}

	public String getIncuredlosses() {
		return incuredlosses;
	}

	public void setIncuredlosses(String incuredlosses) {
		this.incuredlosses = incuredlosses;
	}

	public String getNoofclaims() {
		return noofclaims;
	}

	public void setNoofclaims(String noofclaims) {
		this.noofclaims = noofclaims;
	}

	public String getSpecialClientProfileList() {
		return specialClientProfileList;
	}

	public void setSpecialClientProfileList(String specialClientProfileList) {
		this.specialClientProfileList = specialClientProfileList;
	}

	public String getRetrocover() {
		return retrocover;
	}

	public void setRetrocover(String retrocover) {
		this.retrocover = retrocover;
	}

	public String getExtendedreporting() {
		return extendedreporting;
	}

	public void setExtendedreporting(String extendedreporting) {
		this.extendedreporting = extendedreporting;
	}

	public String getOccurlimit() {
		return occurlimit;
	}

	public void setOccurlimit(String occurlimit) {
		this.occurlimit = occurlimit;
	}

	public String getAggregatelimit() {
		return aggregatelimit;
	}

	public void setAggregatelimit(String aggregatelimit) {
		this.aggregatelimit = aggregatelimit;
	}

	public String getDeductible() {
		return Deductible;
	}

	public void setDeductible(String deductible) {
		Deductible = deductible;
	}

	public String getLossofdocument() {
		return lossofdocument;
	}

	public void setLossofdocument(String lossofdocument) {
		this.lossofdocument = lossofdocument;
	}

	public String getDishonestofempl() {
		return dishonestofempl;
	}

	public void setDishonestofempl(String dishonestofempl) {
		this.dishonestofempl = dishonestofempl;
	}

	public String getLibel() {
		return libel;
	}

	public void setLibel(String libel) {
		this.libel = libel;
	}

	public String getDefenseexpenses() {
		return defenseexpenses;
	}

	public void setDefenseexpenses(String defenseexpenses) {
		this.defenseexpenses = defenseexpenses;
	}

	public String getCommissionandaquisition() {
		return commissionandaquisition;
	}

	public void setCommissionandaquisition(String commissionandaquisition) {
		this.commissionandaquisition = commissionandaquisition;
	}

	public String getGeneralexpenses() {
		return Generalexpenses;
	}

	public void setGeneralexpenses(String generalexpenses) {
		Generalexpenses = generalexpenses;
	}

	public String getProfitcontingency() {
		return profitcontingency;
	}

	public void setProfitcontingency(String profitcontingency) {
		this.profitcontingency = profitcontingency;
	}

	public String getAveragerate() {
		return Averagerate;
	}

	public void setAveragerate(String averagerate) {
		Averagerate = averagerate;
	}

	public String getTotalPremium() {
		return TotalPremium;
	}

	public void setTotalPremium(String totalPremium) {
		TotalPremium = totalPremium;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTerritory() {
		return Territory;
	}

	public void setTerritory(String territory) {
		Territory = territory;
	}

	public String getGrossRevenue() {
		return GrossRevenue;
	}

	public void setGrossRevenue(String grossRevenue) {
		GrossRevenue = grossRevenue;
	}

}
