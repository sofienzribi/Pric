package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: PlaccountantandauditorsMeasure
 * 
 */
@Entity
public class PlaccountantandauditorsMeasure implements Serializable {

	private int id;
	private int idMeasure;
	private HashMap<String, String> BookKeepingAndAudit;
	private HashMap<String, String> managementAdvisory;
	private HashMap<String, String> ForcastProjection;
	private HashMap<String, String> PayrollServices;
	private HashMap<String, String> Fiduciary;
	private HashMap<String, String> TaxPreparation;
	private HashMap<String, String> SecuritiesRelated;
	private HashMap<String, String> FinancialPlanning;
	private HashMap<String, String> SpecialClientProfileList;
	private HashMap<String, String> ExtendedReport;
	private HashMap<String, String> RetrospectiveCovers;
	private HashMap<String, String> AggregateLimit;
	private HashMap<Integer, String> LossHistoryIncured;
	private double LossofDocumentFactor;
	private double DishonestFactor;
	private double LibelSlanderFactor;
	private double DefenceExpenses;

	private static final long serialVersionUID = 1L;

	public PlaccountantandauditorsMeasure() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashMap<String, String> getBookKeepingAndAudit() {
		return BookKeepingAndAudit;
	}

	public void setBookKeepingAndAudit(
			HashMap<String, String> bookKeepingAndAudit) {
		BookKeepingAndAudit = bookKeepingAndAudit;
	}

	public HashMap<String, String> getManagementAdvisory() {
		return managementAdvisory;
	}

	public void setManagementAdvisory(HashMap<String, String> managementAdvisory) {
		this.managementAdvisory = managementAdvisory;
	}

	public HashMap<String, String> getForcastProjection() {
		return ForcastProjection;
	}

	public void setForcastProjection(HashMap<String, String> forcastProjection) {
		ForcastProjection = forcastProjection;
	}

	public int getIdMeasure() {
		return idMeasure;
	}

	public void setIdMeasure(int idMeasure) {
		this.idMeasure = idMeasure;
	}

	public HashMap<String, String> getPayrollServices() {
		return PayrollServices;
	}

	public void setPayrollServices(HashMap<String, String> payrollServices) {
		PayrollServices = payrollServices;
	}

	public HashMap<String, String> getFiduciary() {
		return Fiduciary;
	}

	public void setFiduciary(HashMap<String, String> fiduciary) {
		Fiduciary = fiduciary;
	}

	public HashMap<String, String> getSecuritiesRelated() {
		return SecuritiesRelated;
	}

	public void setSecuritiesRelated(HashMap<String, String> securitiesRelated) {
		SecuritiesRelated = securitiesRelated;
	}

	public HashMap<String, String> getFinancialPlanning() {
		return FinancialPlanning;
	}

	public void setFinancialPlanning(HashMap<String, String> financialPlanning) {
		FinancialPlanning = financialPlanning;
	}

	public HashMap<String, String> getTaxPreparation() {
		return TaxPreparation;
	}

	public void setTaxPreparation(HashMap<String, String> taxPreparation) {
		TaxPreparation = taxPreparation;
	}

	public HashMap<String, String> getSpecialClientProfileList() {
		return SpecialClientProfileList;
	}

	public void setSpecialClientProfileList(
			HashMap<String, String> specialClientProfileList) {
		SpecialClientProfileList = specialClientProfileList;
	}

	public HashMap<String, String> getExtendedReport() {
		return ExtendedReport;
	}

	public void setExtendedReport(HashMap<String, String> extendedReport) {
		ExtendedReport = extendedReport;
	}

	public HashMap<String, String> getRetrospectiveCovers() {
		return RetrospectiveCovers;
	}

	public void setRetrospectiveCovers(
			HashMap<String, String> retrospectiveCovers) {
		RetrospectiveCovers = retrospectiveCovers;
	}

	public double getLossofDocumentFactor() {
		return LossofDocumentFactor;
	}

	public void setLossofDocumentFactor(double lossofDocumentFactor) {
		LossofDocumentFactor = lossofDocumentFactor;
	}

	public double getDishonestFactor() {
		return DishonestFactor;
	}

	public void setDishonestFactor(double dishonestFactor) {
		DishonestFactor = dishonestFactor;
	}

	public double getLibelSlanderFactor() {
		return LibelSlanderFactor;
	}

	public void setLibelSlanderFactor(double libelSlanderFactor) {
		LibelSlanderFactor = libelSlanderFactor;
	}

	public double getDefenceExpenses() {
		return DefenceExpenses;
	}

	public void setDefenceExpenses(double defenceExpenses) {
		DefenceExpenses = defenceExpenses;
	}

	public HashMap<String, String> getAggregateLimit() {
		return AggregateLimit;
	}

	public void setAggregateLimit(HashMap<String, String> aggregateLimit) {
		AggregateLimit = aggregateLimit;
	}

	public HashMap<Integer, String> getLossHistoryIncured() {
		return LossHistoryIncured;
	}

	public void setLossHistoryIncured(
			HashMap<Integer, String> lossHistoryIncured) {
		LossHistoryIncured = lossHistoryIncured;
	}

}
