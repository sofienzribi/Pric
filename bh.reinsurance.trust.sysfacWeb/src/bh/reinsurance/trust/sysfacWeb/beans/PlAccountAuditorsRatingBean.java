package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.PIaccandAudit;
import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.PlaccandAuditServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean
@SessionScoped
public class PlAccountAuditorsRatingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Double, String> practice1;
	final Locale us = Locale.US;
	private Map<Double, String> practice2;
	private Map<Integer, String> Incuredlosseslist;
	private Map<Integer, String> NumberOfClaimsList;
	private Map<Integer, String> SpecialClientProfileList;
	private Map<Integer, String> CoverExtensionsList;
	private Map<Integer, String> CoverExtensionsList2;
	private Map<Integer, String> AggregateLimitList;

	private PIaccandAudit iaccandAudittosave;

	private double Bookingandauditfactor = 0;
	private double managementadvisoryfactor = 0;
	private double forecastsfactor = 0;
	private double payrollfactor = 0;
	private double fiduciaryfactor = 0;
	private double taxpreparationfactor = 0;
	private double securitiesrelatedfactor = 0;
	private double Financialplanningfactor = 0;
	private double CoverageExtensionsFactor = 0;
	private double RetrospectiveCovers = 0;
	private double ExtendedReporting = 0;
	private double AggregateLimitFactor = 1;
	private double DishonestFactor = 0;
	private double DefenceExpFactor = 0;
	private double LibelFactor = 0;
	private double LossOfDocumentsFactor = 0;
	private double Incuredlossesandnumberofclaimsfactor = 0;
	private double specialclientlistfactor = 0;

	private double totalpracticespeciality = 0;
	private double totalOtherLoadingFactors = 0;

	// reporting var
	private String URLDestination;
	private String URLJasperModel;
	private String ProjectName;
	private JasperPrint jasperPrint;
	private String TextToAddToreport = "";
	private boolean DisplayTextArea;
	private String Checkboxvalue;
	private String CheckBoxRatingDetailsValue;

	// summary var
	private double DeductibleFactor = 0;
	private float CoverageEnhancement = 0;
	private float BasePremium = 0;
	private float TotalBasePremium;
	private double TerritoryLoad = 1;
	private float TotalPremium = 0;
	private double AverageRate = 0;
	private double ExpensesLoading = 0;
	private float PremiumBeforeLoadingAnddeductible = 0;
	private float PremiumAfterenhancements = 0;
	private float PremiumAftercoverageextensions = 0;
	private float PremiumAfterLoadingAndDeductible = 0;
	private float IndemnityLimitPremium = 0;
	private double Loadings = 0;

	// managed prop

	@ManagedProperty("#{projectBean.getProject3()}")
	private Project project3;

	// EJB
	@EJB
	private PlaccandAuditServicesLocal auditServicesLocal;
	@EJB
	private UserServicesLocal userServicesLocal;

	// constr
	public PlAccountAuditorsRatingBean() {
		URLJasperModel = "/Users/zribisofien/Desktop/ModelReport/";
		URLDestination = "/Users/zribisofien/Desktop/PDFGEN/";
		FillLists();

	}

	@PostConstruct
	public void init() {
		DisplayTextArea = false;
		iaccandAudittosave = auditServicesLocal
				.GetByIdProject(project3.getId());
		OperationWhenopeningTool();
	}

	// methods
	public void FillLists() {
		Incuredlosseslist = new HashMap<Integer, String>();
		Incuredlosseslist.put(1, "0");
		Incuredlosseslist.put(2, "0-100,00");
		Incuredlosseslist.put(3, "100,00-500,00");
		Incuredlosseslist.put(4, "500,00-1,000,000");
		Incuredlosseslist.put(5, "1,000,000 or more");

		practice1 = new HashMap<Double, String>();
		practice1.put(0.0, "0-59%");
		practice1.put(0.1, "60-79%");
		practice1.put(0.15, "80% or more");

		practice2 = new HashMap<Double, String>();
		practice2.put(0.0, "0-24%");
		practice2.put(0.1, "25-49%");
		practice2.put(0.15, "50% or more");

		SpecialClientProfileList = new HashMap<Integer, String>();
		SpecialClientProfileList.put(1, "yes");
		SpecialClientProfileList.put(2, "false");

		NumberOfClaimsList = new HashMap<Integer, String>();
		NumberOfClaimsList.put(1, "0");
		NumberOfClaimsList.put(2, "0-2");
		NumberOfClaimsList.put(3, "2 or more");

		CoverExtensionsList = new HashMap<Integer, String>();
		CoverExtensionsList.put(0, "0");
		CoverExtensionsList.put(1, "1");
		CoverExtensionsList.put(2, "2");
		CoverExtensionsList.put(3, "3");
		CoverExtensionsList.put(4, "4");
		CoverExtensionsList.put(5, "5");
		CoverExtensionsList.put(6, "6");
		CoverExtensionsList.put(7, "7");
		CoverExtensionsList.put(8, "8");
		CoverExtensionsList.put(9, "9");
		CoverExtensionsList.put(10, "10");
		CoverExtensionsList2 = new HashMap<Integer, String>();
		CoverExtensionsList2.put(0, "0");
		CoverExtensionsList2.put(1, "1");
		CoverExtensionsList2.put(2, "2");
		CoverExtensionsList2.put(3, "3");
		CoverExtensionsList2.put(4, "4");
		CoverExtensionsList2.put(5, "5");
		CoverExtensionsList2.put(6, "6");
		CoverExtensionsList2.put(7, "7");
		CoverExtensionsList2.put(8, "8");
		CoverExtensionsList2.put(9, "9");
		CoverExtensionsList2.put(10, "10");

		AggregateLimitList = new HashMap<Integer, String>();
		AggregateLimitList.put(1, "1");
		AggregateLimitList.put(2, "2");
		AggregateLimitList.put(3, "3");
		AggregateLimitList.put(4, "4");

	}

	public void SaveRating() {
		iaccandAudittosave.setIdproj(project3.getId());
		auditServicesLocal.update(iaccandAudittosave);
		FacesContext.getCurrentInstance()
				.addMessage(
						"messages1",
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Rating Saved", ""));
	}

	public void OperationWhenopeningTool() {

		GetAllFactorsOnOpeing();
		GetterritoryLoad();

		GetTotalBasePremium();
		GetIndemnityLimitPremium();
		GetExpensesLoading();
		GetDeductibleFactor();
		GetBasePremium();
		todoall();
	}

	public void GetAllFactorsOnOpeing() {
		SpecialClientChange();
		incuredLossAndNoOfClaimsChange();
		bookingandauditChange();
		Manageadvisorychange();
		TaxChange();
		fiduciaryChange();
		payrollChange();
		forecastChange();
		securitieschanges();
		planningchange();
		retrocoverChange();
		GetEnhancements();
		extendedReportChange();

	}

	// calculation of the expenses loading begin

	public void GetExpensesLoading() {
		ExpensesLoading = (Double.parseDouble(iaccandAudittosave
				.getCommissionandaquisition()) / 100)
				+ (Double.parseDouble(iaccandAudittosave.getGeneralexpenses()) / 100)
				+ (Double
						.parseDouble(iaccandAudittosave.getProfitcontingency()) / 100);

	}

	// calculation of the expenses loading end

	// premium calculation begin

	// calculate the total premium and rate
	public void GetPremiumAndRate() {
		AverageRate = TotalPremium
				/ ((Double.parseDouble(iaccandAudittosave.getOccurlimit()) * Double
						.parseDouble(iaccandAudittosave.getAggregatelimit())));
	}

	// loadings calculation begin

	// caclculation of the Basepremium and totalbasepremium begin

	public String GetBasePremium() {
		if (Float.parseFloat(iaccandAudittosave.getGrossRevenue()) < 500000) {
			BasePremium = 0;
			GetTotalBasePremium();
			return null;
		}
		if (Float.parseFloat(iaccandAudittosave.getGrossRevenue()) < 1000000) {
			BasePremium = 1900;
			GetTotalBasePremium();
			return null;
		}

		if (Float.parseFloat(iaccandAudittosave.getGrossRevenue()) < 2000000) {
			BasePremium = 3650;
			GetTotalBasePremium();
			return null;
		}
		if (Float.parseFloat(iaccandAudittosave.getGrossRevenue()) < 4000000) {
			BasePremium = 6400;
			GetTotalBasePremium();
			return null;
		}
		if (Float.parseFloat(iaccandAudittosave.getGrossRevenue()) < 6500000) {
			BasePremium = 10300;
			GetTotalBasePremium();
			return null;
		}
		if (Float.parseFloat(iaccandAudittosave.getGrossRevenue()) < 1006500000) {
			BasePremium = 13450;
			GetTotalBasePremium();
			return null;
		}
		if (Float.parseFloat(iaccandAudittosave.getGrossRevenue()) >= 1006500000) {
			BasePremium = 763425;
			GetTotalBasePremium();
			return null;
		}
		return null;
	}

	public void GetTotalBasePremium() {
		TotalBasePremium = (float) (BasePremium * TerritoryLoad);

		todoall();
	}

	// calculate territory load
	public void GetterritoryLoad() {
		if (iaccandAudittosave.getTerritory().equals("GCC")) {
			TerritoryLoad = 1;
		} else {
			TerritoryLoad = 1.25;
		}
	}

	public void todoall() {
		PremiumBeforeLoadingAnddeductible = TotalBasePremium
				+ IndemnityLimitPremium;
		PremiumAfterLoadingAndDeductible = (float) (PremiumBeforeLoadingAnddeductible
				* (1 + Loadings) * (1 + DeductibleFactor));
		PremiumAfterenhancements = PremiumAfterLoadingAndDeductible
				+ CoverageEnhancement;
		PremiumAftercoverageextensions = (float) (PremiumAfterenhancements + CoverageExtensionsFactor);

		PremiumAftercoverageextensions = (float) (PremiumAfterenhancements
				* (1 + RetrospectiveCovers) * (1 + ExtendedReporting));

		if ((PremiumAftercoverageextensions / (1 - ExpensesLoading)) < 2500) {
			TotalPremium = 2500;
			GetPremiumAndRate();
		} else {
			TotalPremium = (float) (PremiumAftercoverageextensions / (1 - ExpensesLoading));
			GetPremiumAndRate();
		}

	}

	// Basepremium and totalbasepremium end

	// calculation of the premium end

	public void CalculateLoadings() {
		Loadings = totalpracticespeciality + totalOtherLoadingFactors;
		todoall();

	}

	// other loading factor events begin

	public void CalculateTotalOtherLoadings() {
		totalOtherLoadingFactors = specialclientlistfactor
				+ Incuredlossesandnumberofclaimsfactor;
		CalculateLoadings();

	}

	public void incuredLossAndNoOfClaimsChange() {
		if (iaccandAudittosave.getIncuredlosses().equals("0")
				&& iaccandAudittosave.getNoofclaims().equals("0")) {
			Incuredlossesandnumberofclaimsfactor = -0.05;
		} else if (iaccandAudittosave.getIncuredlosses().equals("0")
				|| iaccandAudittosave.getNoofclaims().equals("0")) {
			Incuredlossesandnumberofclaimsfactor = 0;
		} else if (iaccandAudittosave.getIncuredlosses().equals("0-100,00")) {
			if (iaccandAudittosave.getNoofclaims().equals("0-2")) {
				Incuredlossesandnumberofclaimsfactor = 0.2;
			} else {
				Incuredlossesandnumberofclaimsfactor = 0.3;
			}

		} else if (iaccandAudittosave.getIncuredlosses()
				.equals("100,00-500,00")) {
			if (iaccandAudittosave.getNoofclaims().equals("0-2")) {
				Incuredlossesandnumberofclaimsfactor = 0.3;
			} else {
				Incuredlossesandnumberofclaimsfactor = 0.5;
			}
		} else if (iaccandAudittosave.getIncuredlosses().equals(
				"500,00-1,000,000")) {
			if (iaccandAudittosave.getNoofclaims().equals("0-2")) {
				Incuredlossesandnumberofclaimsfactor = 0.4;
			} else {
				Incuredlossesandnumberofclaimsfactor = 0.7;
			}
		} else if (iaccandAudittosave.getIncuredlosses().equals(
				"1,000,000 or more")) {
			if (iaccandAudittosave.getNoofclaims().equals("0-2")) {
				Incuredlossesandnumberofclaimsfactor = 0.5;
			} else {
				Incuredlossesandnumberofclaimsfactor = 0.8;
			}
		}
		CalculateTotalOtherLoadings();
	}

	public void SpecialClientChange() {
		if (iaccandAudittosave.getSpecialClientProfileList().equals("yes")) {
			specialclientlistfactor = 0.25;
		} else {
			specialclientlistfactor = 0;
		}
		CalculateTotalOtherLoadings();

	}

	// other loading factor events end

	// PRactice speciality ajax events begin

	public void bookingandauditChange() {

		if (iaccandAudittosave.getBookkeepingandaudit().equals("0-59%")
				|| iaccandAudittosave.getBookkeepingandaudit().equals("null")) {

			Bookingandauditfactor = 0;

		}
		if (iaccandAudittosave.getBookkeepingandaudit().equals("60-79%")) {

			Bookingandauditfactor = 0.10;

		}

		if (iaccandAudittosave.getBookkeepingandaudit().equals("80% or more")) {

			Bookingandauditfactor = 0.15;

		}
		calcultotalpracticespeciality();

	}

	public void Manageadvisorychange() {
		if (iaccandAudittosave.getManagementAdvisory().equals("0-59%")
				|| iaccandAudittosave.getManagementAdvisory().equals("null")) {

			managementadvisoryfactor = 0;

		}
		if (iaccandAudittosave.getManagementAdvisory().equals("60-79%")) {

			managementadvisoryfactor = 0.10;

		}

		if (iaccandAudittosave.getManagementAdvisory().equals("80% or more")) {

			managementadvisoryfactor = 0.15;

		}
		calcultotalpracticespeciality();
	}

	public void forecastChange() {
		if (iaccandAudittosave.getForecast().equals("0-59%")
				|| iaccandAudittosave.getManagementAdvisory().equals("null")) {

			forecastsfactor = 0;

		}
		if (iaccandAudittosave.getForecast().equals("60-79%")) {

			forecastsfactor = 0.10;

		}

		if (iaccandAudittosave.getForecast().equals("80% or more")) {

			forecastsfactor = 0.15;

		}
		calcultotalpracticespeciality();
	}

	public void payrollChange() {
		if (iaccandAudittosave.getPayrollservices().equals("0-59%")
				|| iaccandAudittosave.getManagementAdvisory().equals("null")) {

			payrollfactor = 0;

		}
		if (iaccandAudittosave.getPayrollservices().equals("60-79%")) {

			payrollfactor = 0.10;

		}

		if (iaccandAudittosave.getPayrollservices().equals("80% or more")) {

			payrollfactor = 0.15;

		}
		calcultotalpracticespeciality();
	}

	public void fiduciaryChange() {
		if (iaccandAudittosave.getFiduciary().equals("0-24%")
				|| iaccandAudittosave.getManagementAdvisory().equals("null")) {

			fiduciaryfactor = 0;

		}
		if (iaccandAudittosave.getFiduciary().equals("25-49%")) {

			fiduciaryfactor = 0.10;

		}

		if (iaccandAudittosave.getFiduciary().equals("50% or more")) {

			fiduciaryfactor = 0.15;

		}
		calcultotalpracticespeciality();
	}

	public void TaxChange() {
		if (iaccandAudittosave.getTaxpreparation().equals("0-24%")
				|| iaccandAudittosave.getTaxpreparation().equals("null")) {

			taxpreparationfactor = 0;

		}
		if (iaccandAudittosave.getTaxpreparation().equals("25-49%")) {

			taxpreparationfactor = 0.10;

		}

		if (iaccandAudittosave.getTaxpreparation().equals("50% or more")) {

			taxpreparationfactor = 0.15;

		}
		calcultotalpracticespeciality();
	}

	public void securitieschanges() {
		if (iaccandAudittosave.getSecuritiesrelated().equals("0-24%")
				|| iaccandAudittosave.getSecuritiesrelated().equals("null")) {

			securitiesrelatedfactor = 0;

		}
		if (iaccandAudittosave.getSecuritiesrelated().equals("25-49%")) {

			securitiesrelatedfactor = 0.10;

		}

		if (iaccandAudittosave.getSecuritiesrelated().equals("50% or more")) {

			securitiesrelatedfactor = 0.15;

		}
		calcultotalpracticespeciality();
	}

	public void planningchange() {

		if (iaccandAudittosave.getFinancialPlanning().equals("0-24%")
				|| iaccandAudittosave.getFinancialPlanning().equals("null")) {

			Financialplanningfactor = 0;

		}
		if (iaccandAudittosave.getFinancialPlanning().equals("25-49%")) {

			Financialplanningfactor = 0.10;

		}

		if (iaccandAudittosave.getFinancialPlanning().equals("50% or more")) {

			Financialplanningfactor = 0.15;

		}
		calcultotalpracticespeciality();
	}

	public void calcultotalpracticespeciality() {

		totalpracticespeciality = (Bookingandauditfactor
				+ managementadvisoryfactor + forecastsfactor + payrollfactor
				+ fiduciaryfactor + taxpreparationfactor
				+ securitiesrelatedfactor + Financialplanningfactor);

		CalculateLoadings();

	}

	// PRactice speciality ajax events end

	// loadings calculation end

	// coverage extensions event begin
	public void retrocoverChange() {
		if (iaccandAudittosave.getRetrocover().equals("0")) {
			RetrospectiveCovers = 0;
		}
		if (iaccandAudittosave.getRetrocover().equals("1")) {
			RetrospectiveCovers = 0.075;
		}
		if (iaccandAudittosave.getRetrocover().equals("2")) {
			RetrospectiveCovers = 0.09;
		}
		if (iaccandAudittosave.getRetrocover().equals("3")) {
			RetrospectiveCovers = 0.105;
		}
		if (iaccandAudittosave.getRetrocover().equals("4")) {
			RetrospectiveCovers = 0.12;
		}
		if (iaccandAudittosave.getRetrocover().equals("5")) {
			RetrospectiveCovers = 0.135;
		}
		if (iaccandAudittosave.getRetrocover().equals("6")) {
			RetrospectiveCovers = 0.15;
		}
		if (iaccandAudittosave.getRetrocover().equals("7")) {
			RetrospectiveCovers = 0.165;
		}
		if (iaccandAudittosave.getRetrocover().equals("8")) {
			RetrospectiveCovers = 0.18;
		}
		if (iaccandAudittosave.getRetrocover().equals("9")) {
			RetrospectiveCovers = 0.195;
		}
		if (iaccandAudittosave.getRetrocover().equals("10")) {
			RetrospectiveCovers = 0.21;
		}
		calculateCoverageExtensions();
	}

	public void extendedReportChange() {
		if (iaccandAudittosave.getExtendedreporting().equals("0")) {
			ExtendedReporting = 0;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("1")) {
			ExtendedReporting = 0.1;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("2")) {
			ExtendedReporting = 0.15;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("3")) {
			ExtendedReporting = 0.225;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("4")) {
			ExtendedReporting = 0.325;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("5")) {
			ExtendedReporting = 0.425;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("6")) {
			ExtendedReporting = 0.5;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("7")) {
			ExtendedReporting = 0.625;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("8")) {
			ExtendedReporting = 0.65;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("9")) {
			ExtendedReporting = 0.70;
		}
		if (iaccandAudittosave.getExtendedreporting().equals("10")) {
			ExtendedReporting = 0.75;
		}
		calculateCoverageExtensions();
	}

	public void calculateCoverageExtensions() {

		todoall();

	}

	// coverage extensions event end

	// calculate the deductible factor begin
	public void GetDeductibleFactor() {
		if (Float.parseFloat(iaccandAudittosave.getDeductible()) <= 2500) {
			DeductibleFactor = 0.1;
		} else if (Float.parseFloat(iaccandAudittosave.getDeductible()) > 2500
				&& Float.parseFloat(iaccandAudittosave.getDeductible()) <= 5000) {
			DeductibleFactor = 0;
		} else if (Float.parseFloat(iaccandAudittosave.getDeductible()) > 5000
				&& Float.parseFloat(iaccandAudittosave.getDeductible()) <= 10000) {
			DeductibleFactor = -0.05;
		} else if (Float.parseFloat(iaccandAudittosave.getDeductible()) > 10000
				&& Float.parseFloat(iaccandAudittosave.getDeductible()) <= 20000) {
			DeductibleFactor = -0.1;
		} else if (Float.parseFloat(iaccandAudittosave.getDeductible()) > 20000
				&& Float.parseFloat(iaccandAudittosave.getDeductible()) <= 40000) {
			DeductibleFactor = -0.125;
		} else if (Float.parseFloat(iaccandAudittosave.getDeductible()) > 400000
				&& Float.parseFloat(iaccandAudittosave.getDeductible()) <= 50000) {
			DeductibleFactor = -0.15;
		} else if (Float.parseFloat(iaccandAudittosave.getDeductible()) > 50000) {
			DeductibleFactor = -0.175;
		}
		todoall();
	}

	// calculation of the deductible factor end

	// calculation of the indemnity limit premium begin
	public void AggregateChange() {
		if (iaccandAudittosave.getAggregatelimit().equals("1")) {
			AggregateLimitFactor = 1;
		} else {
			if (iaccandAudittosave.getAggregatelimit().equals("2")) {
				AggregateLimitFactor = 1.15;
			} else {
				if (iaccandAudittosave.getAggregatelimit().equals("3")) {
					AggregateLimitFactor = 1.25;
				} else {
					AggregateLimitFactor = 1.4;

				}
			}
		}
		GetIndemnityLimitPremium();
	}

	public void GetIndemnityLimitPremium() {
		IndemnityLimitPremium = 0;
		if (Float.parseFloat(iaccandAudittosave.getOccurlimit()) - 250000 > 0) {
			float a = Float.parseFloat(iaccandAudittosave.getOccurlimit()) - 250000;

			if (a > 250000) {
				IndemnityLimitPremium = (float) (250000 * 0.00562 * AggregateLimitFactor);
				a = a - 250000;
				if (a > 500000) {
					IndemnityLimitPremium = (float) (IndemnityLimitPremium + (500000 * 0.004875 * AggregateLimitFactor));
					a = a - 500000;
					if (a > 1000000) {
						IndemnityLimitPremium = (float) (IndemnityLimitPremium + (1000000 * 0.004250 * AggregateLimitFactor));
						a = a - 1000000;
						if (a > 2000000) {
							IndemnityLimitPremium = (float) (IndemnityLimitPremium + (2000000 * 0.003375 * AggregateLimitFactor));
							a = a - 2000000;
							if (a > 2000000) {
								IndemnityLimitPremium = (float) (IndemnityLimitPremium + (2000000 * 0.002850 * AggregateLimitFactor));
								a = a - 2000000;
								if (a > 4000000) {
									IndemnityLimitPremium = (float) (IndemnityLimitPremium + (4000000 * 0.002450 * AggregateLimitFactor));
									a = a - 4000000;
									if (a > 5000000) {
										IndemnityLimitPremium = (float) (IndemnityLimitPremium + (5000000 * 0.001715 * AggregateLimitFactor));
										a = a - 5000000;
										if (a > 5000000) {
											IndemnityLimitPremium = (float) (IndemnityLimitPremium + (5000000 * 0.001375 * AggregateLimitFactor));
											a = a - 5000000;
											if (a > 5000000) {
												IndemnityLimitPremium = (float) (IndemnityLimitPremium + (5000000 * 0.001100 * AggregateLimitFactor));
												a = a - 5000000;
												if (a > 10000000) {
													IndemnityLimitPremium = (float) (IndemnityLimitPremium + (10000000 * 0.000875 * AggregateLimitFactor));
													a = a - 10000000;
												} else {
													IndemnityLimitPremium = (float) (IndemnityLimitPremium + (a * 0.000875 * AggregateLimitFactor));

												}
											} else {
												IndemnityLimitPremium = (float) (IndemnityLimitPremium + (a * 0.001100 * AggregateLimitFactor));

											}
										} else {
											IndemnityLimitPremium = (float) (IndemnityLimitPremium + (a * 0.001375 * AggregateLimitFactor));

										}
									} else {
										IndemnityLimitPremium = (float) (IndemnityLimitPremium + (a * 0.001715 * AggregateLimitFactor));
									}
								} else {
									IndemnityLimitPremium = (float) (IndemnityLimitPremium + (a * 0.002450 * AggregateLimitFactor));
								}

							} else {
								IndemnityLimitPremium = (float) (IndemnityLimitPremium + (a * 0.002850 * AggregateLimitFactor));
							}

						} else {
							IndemnityLimitPremium = (float) (IndemnityLimitPremium + (a * 0.003375 * AggregateLimitFactor));
						}
					} else {
						IndemnityLimitPremium = (float) (IndemnityLimitPremium + (a * 0.004250 * AggregateLimitFactor));
					}

				} else {
					IndemnityLimitPremium = (float) (IndemnityLimitPremium + (a * 0.004875 * AggregateLimitFactor));
				}

			} else {

				IndemnityLimitPremium = (float) (a * 0.00562 * AggregateLimitFactor);
			}

		} else {
			IndemnityLimitPremium = 0;

		}
		todoall();

	}

	// calculation of the indemnity limit premium end

	// Calculation of the coverage enhancements begin
	public void GetEnhancements() {

		DishonestFactor = (Float.parseFloat(iaccandAudittosave
				.getDishonestofempl()) - (Float.parseFloat(iaccandAudittosave
				.getOccurlimit()) * 0.1)) * 0.0025;

		LossOfDocumentsFactor = (Float.parseFloat(iaccandAudittosave
				.getLossofdocument()) - (Float.parseFloat(iaccandAudittosave
				.getOccurlimit()) * 0.1)) * 0.005;

		LibelFactor = (Float.parseFloat(iaccandAudittosave.getLibel()) - (Float
				.parseFloat(iaccandAudittosave.getOccurlimit()) * 0.1)) * 0.005;

		DefenceExpFactor = (Float.parseFloat(iaccandAudittosave
				.getDefenseexpenses()) - (Float.parseFloat(iaccandAudittosave
				.getOccurlimit()) * 0.1)) * 0.05;

		CoverageEnhancement = (float) (DishonestFactor + LossOfDocumentsFactor
				+ LibelFactor + DefenceExpFactor);

	}

	// Calculation of the coverage enhancements end

	// format results begin
	public String FormatTotalPremium() {
		return NumberFormat.getCurrencyInstance(us).format(TotalPremium);
	}

	public String FormatBasePremium() {
		return NumberFormat.getCurrencyInstance(us).format(BasePremium);
	}

	public String FormatTotalBasePremium() {
		return NumberFormat.getCurrencyInstance(us).format(TotalBasePremium);
	}

	public String FormatIndemnityLimitPremium() {
		return NumberFormat.getCurrencyInstance(us).format(
				IndemnityLimitPremium);
	}

	public String FormatModifiedPremium() {
		return NumberFormat.getCurrencyInstance(us).format(
				PremiumAfterLoadingAndDeductible);
	}

	public String CoverageEnhacements() {
		return NumberFormat.getCurrencyInstance(us).format(CoverageEnhancement);
	}

	public String FormatPremiumBeforeLoading() {
		return NumberFormat.getCurrencyInstance(us).format(
				PremiumBeforeLoadingAnddeductible);
	}

	public String FormatPremiumAfterCoverageExtensions() {
		return NumberFormat.getCurrencyInstance(us).format(
				PremiumAftercoverageextensions);
	}

	public String FormatPremiumAfterEnhacement() {
		return NumberFormat.getCurrencyInstance(us).format(
				PremiumAfterenhancements);
	}

	public String FormatAverageRate() {
		NumberFormat formatter = new DecimalFormat("#0.00 %");
		return formatter.format(AverageRate);
	}

	public String FormatDeductibleFactor() {
		NumberFormat formatter = new DecimalFormat("#0.00 %");
		return formatter.format(DeductibleFactor);
	}

	public String FormatLoadings() {
		NumberFormat formatter = new DecimalFormat("#0.00 %");
		return formatter.format(Loadings);
	}

	public String FormatExpensesLoading() {
		NumberFormat formatter = new DecimalFormat("#0.00 %");
		return formatter.format(ExpensesLoading);
	}

	public String FormatTotalPracticeSpeciality() {
		NumberFormat formatter = new DecimalFormat("#0.00 %");
		return formatter.format(totalpracticespeciality);
	}

	public String FormatTotalOtherLoadingFactor() {
		NumberFormat formatter = new DecimalFormat("#0.00 %");
		return formatter.format(totalOtherLoadingFactors);
	}

	// format result end

	// Report Generation begin
	public void DisplayTextBox() {
		if (Checkboxvalue.equals("true")) {
			DisplayTextArea = true;
		} else {
			DisplayTextArea = false;
		}
	}

	/**
	 * @throws JRException
	 * @throws IOException
	 */
	public void ExportAccountantAndAuditorsSummary() throws JRException,
			IOException {
		File sourceFile;
		boolean testName;
		try {
			sourceFile = new File(URLDestination + ProjectName + ".pdf");
			FileInputStream fis = new FileInputStream(sourceFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			testName = true;

		} catch (Exception e) {
			testName = false;
		}
		if (testName == true) {
			RequestContext context = RequestContext.getCurrentInstance();

			FacesContext
					.getCurrentInstance()
					.addMessage(
							"messages1",
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"The name "
											+ ProjectName
											+ ".pdf already exists. Please choose another name.",
									""));
		} else {
			List<Object> projects = new ArrayList<Object>();
			projects.add(iaccandAudittosave);
			Map<String, Object> param = new HashMap<String, Object>();
			User user = new User();
			user = userServicesLocal.GetUserByid(project3.getUser());
			param.put("Title", project3.getNameOfTheProject());
			param.put("name", user.getFirst_Name() + " " + user.getLast_Name());
			param.put("insured", project3.getInsured());
			param.put("broker", project3.getBroker());
			param.put("basepremium", FormatBasePremium());
			param.put("territoryload", String.valueOf(TerritoryLoad));
			param.put("totalbasepremium", FormatTotalBasePremium());
			param.put("inception", project3.getDateCreation().toString());
			param.put("policy", project3.getPolicy());
			param.put("indemnitylimitpremium", FormatIndemnityLimitPremium());
			param.put("Prembeforeded", FormatPremiumBeforeLoading());
			param.put("dedfactor", FormatDeductibleFactor());
			param.put("loadings", FormatLoadings());
			param.put("modifiedpremium", FormatModifiedPremium());
			param.put("coverageenh", CoverageEnhacements());
			param.put("premafterenh", FormatPremiumAfterEnhacement());
			param.put("premaftercov", FormatPremiumAfterCoverageExtensions());
			param.put("exploadings", FormatExpensesLoading());
			param.put("avgrate", FormatAverageRate());
			param.put("totalpremium", FormatTotalPremium());
			System.out.println(TextToAddToreport);
			if (Checkboxvalue.equals("true")) {
				param.put("Comment", TextToAddToreport);
			} else {
				param.put("Comment", "No Comments");
			}
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
					projects);
			if (CheckBoxRatingDetailsValue.equals("true")) {
				jasperPrint = JasperFillManager.fillReport(URLJasperModel
						+ "AccountantsAndAuditorsModelwithrating.jasper",
						param, beanCollectionDataSource);
			} else {
				jasperPrint = JasperFillManager.fillReport(URLJasperModel
						+ "AccountantsAndAuditorsModel.jasper", param,
						beanCollectionDataSource);
			}

			JasperExportManager.exportReportToPdfFile(jasperPrint,
					URLDestination + ProjectName + ".pdf");

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('PopupPdf').hide();");
			FacesContext.getCurrentInstance().addMessage(
					"messages1",
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"File Created", ""));
			ProjectName = null;
			Checkboxvalue = "false";
			DisplayTextArea = false;
		}

	}

	// Report Generation ends

	// getters setters
	public Map<Double, String> getPractise1() {
		return practice1;
	}

	public void setPractise1(Map<Double, String> practise1) {
		this.practice1 = practise1;
	}

	public Map<Double, String> getPractise2() {
		return practice2;
	}

	public void setPractise2(Map<Double, String> practise2) {
		this.practice2 = practise2;
	}

	public PIaccandAudit getIaccandAudittosave() {
		return iaccandAudittosave;
	}

	public void setIaccandAudittosave(PIaccandAudit iaccandAudittosave) {
		this.iaccandAudittosave = iaccandAudittosave;
	}

	public double getBookingandauditfactor() {
		return Bookingandauditfactor;
	}

	public void setBookingandauditfactor(double bookingandauditfactor) {
		Bookingandauditfactor = bookingandauditfactor;
	}

	public double getManagementadvisoryfactor() {
		return managementadvisoryfactor;
	}

	public void setManagementadvisoryfactor(double managementadvisoryfactor) {
		this.managementadvisoryfactor = managementadvisoryfactor;
	}

	public double getForecastsfactor() {
		return forecastsfactor;
	}

	public void setForecastsfactor(double forecastsfactor) {
		this.forecastsfactor = forecastsfactor;
	}

	public double getPayrollfactor() {
		return payrollfactor;
	}

	public void setPayrollfactor(double payrollfactor) {
		this.payrollfactor = payrollfactor;
	}

	public double getFiduciaryfactor() {
		return fiduciaryfactor;
	}

	public void setFiduciaryfactor(double fiduciaryfactor) {
		this.fiduciaryfactor = fiduciaryfactor;
	}

	public double getTaxpreparationfactor() {
		return taxpreparationfactor;
	}

	public void setTaxpreparationfactor(double taxpreparationfactor) {
		this.taxpreparationfactor = taxpreparationfactor;
	}

	public double getSecuritiesrelatedfactor() {
		return securitiesrelatedfactor;
	}

	public void setSecuritiesrelatedfactor(double securitiesrelatedfactor) {
		this.securitiesrelatedfactor = securitiesrelatedfactor;
	}

	public double getFinancialplanningfactor() {
		return Financialplanningfactor;
	}

	public void setFinancialplanningfactor(double financialplanningfactor) {
		Financialplanningfactor = financialplanningfactor;
	}

	public double getTotalpracticespeciality() {
		return totalpracticespeciality;
	}

	public void setTotalpracticespeciality(double totalpracticespeciality) {
		this.totalpracticespeciality = totalpracticespeciality;
	}

	public void setProject3(Project project3) {
		this.project3 = project3;
	}

	public double getLoadings() {
		return Loadings;
	}

	public void setLoadings(double loadings) {
		Loadings = loadings;
	}

	public Map<Integer, String> getIncuredlosseslist() {
		return Incuredlosseslist;
	}

	public void setIncuredlosseslist(Map<Integer, String> incuredlosseslist) {
		Incuredlosseslist = incuredlosseslist;
	}

	public double getSpecialclientlistfactor() {
		return specialclientlistfactor;
	}

	public void setSpecialclientlistfactor(double specialclientlistfactor) {
		this.specialclientlistfactor = specialclientlistfactor;
	}

	public double getTotalOtherLoadingFactors() {
		return totalOtherLoadingFactors;
	}

	public void setTotalOtherLoadingFactors(double totalOtherLoadingFactors) {
		this.totalOtherLoadingFactors = totalOtherLoadingFactors;
	}

	public Map<Integer, String> getNumberOfClaimsList() {
		return NumberOfClaimsList;
	}

	public void setNumberOfClaimsList(Map<Integer, String> numberOfClaimsList) {
		NumberOfClaimsList = numberOfClaimsList;
	}

	public Map<Integer, String> getSpecialClientProfileList() {
		return SpecialClientProfileList;
	}

	public void setSpecialClientProfileList(
			Map<Integer, String> specialClientProfileList) {
		SpecialClientProfileList = specialClientProfileList;
	}

	public double getIncuredlossesandnumberofclaimsfactor() {
		return Incuredlossesandnumberofclaimsfactor;
	}

	public void setIncuredlossesandnumberofclaimsfactor(
			double incuredlossesandnumberofclaimsfactor) {
		Incuredlossesandnumberofclaimsfactor = incuredlossesandnumberofclaimsfactor;
	}

	public float getTotalPremium() {
		return TotalPremium;
	}

	public void setTotalPremium(float totalPremium) {
		TotalPremium = totalPremium;
	}

	public double getAverageRate() {
		return AverageRate;
	}

	public void setAverageRate(double averageRate) {
		AverageRate = averageRate;
	}

	public float getBasePremium() {
		return BasePremium;
	}

	public void setBasePremium(float basePremium) {
		BasePremium = basePremium;
	}

	public double getTerritoryLoad() {
		return TerritoryLoad;
	}

	public void setTerritoryLoad(double territoryLoad) {
		TerritoryLoad = territoryLoad;
	}

	public float getTotalBasePremium() {
		return TotalBasePremium;
	}

	public float getIndemnityLimitPremium() {
		return IndemnityLimitPremium;
	}

	public void setIndemnityLimitPremium(float indemnityLimitPremium) {
		IndemnityLimitPremium = indemnityLimitPremium;
	}

	public void setTotalBasePremium(float totalBasePremium) {
		TotalBasePremium = totalBasePremium;
	}

	public double getExpensesLoading() {
		return ExpensesLoading;
	}

	public void setExpensesLoading(double expensesLoading) {
		ExpensesLoading = expensesLoading;
	}

	public float getPremiumAfterenhancements() {
		return PremiumAfterenhancements;
	}

	public void setPremiumAfterenhancements(float premiumAfterenhancements) {
		PremiumAfterenhancements = premiumAfterenhancements;
	}

	public float getPremiumAftercoverageextensions() {
		return PremiumAftercoverageextensions;
	}

	public void setPremiumAftercoverageextensions(
			float premiumAftercoverageextensions) {
		PremiumAftercoverageextensions = premiumAftercoverageextensions;
	}

	public float getPremiumBeforeLoadingAnddeductible() {
		return PremiumBeforeLoadingAnddeductible;
	}

	public void setPremiumBeforeLoadingAnddeductible(
			float premiumBeforeLoadingAnddeductible) {
		PremiumBeforeLoadingAnddeductible = premiumBeforeLoadingAnddeductible;
	}

	public float getPremiumAfterLoadingAndDeductible() {
		return PremiumAfterLoadingAndDeductible;
	}

	public void setPremiumAfterLoadingAndDeductible(
			float premiumAfterLoadingAndDeductible) {
		PremiumAfterLoadingAndDeductible = premiumAfterLoadingAndDeductible;
	}

	public double getDeductibleFactor() {
		return DeductibleFactor;
	}

	public void setDeductibleFactor(double deductibleFactor) {
		DeductibleFactor = deductibleFactor;
	}

	public float getCoverageEnhancement() {
		return CoverageEnhancement;
	}

	public void setCoverageEnhancement(float coverageEnhancement) {
		CoverageEnhancement = coverageEnhancement;
	}

	public double getCoverageExtensionsFactor() {
		return CoverageExtensionsFactor;
	}

	public void setCoverageExtensionsFactor(double coverageExtensionsFactor) {
		CoverageExtensionsFactor = coverageExtensionsFactor;
	}

	public double getRetrospectiveCovers() {
		return RetrospectiveCovers;
	}

	public void setRetrospectiveCovers(double retrospectiveCovers) {
		RetrospectiveCovers = retrospectiveCovers;
	}

	public double getExtendedReporting() {
		return ExtendedReporting;
	}

	public void setExtendedReporting(double extendedReporting) {
		ExtendedReporting = extendedReporting;
	}

	public Map<Integer, String> getCoverExtensionsList() {
		return CoverExtensionsList;
	}

	public void setCoverExtensionsList(Map<Integer, String> coverExtensionsList) {
		CoverExtensionsList = coverExtensionsList;
	}

	public Map<Integer, String> getCoverExtensionsList2() {
		return CoverExtensionsList2;
	}

	public void setCoverExtensionsList2(
			Map<Integer, String> coverExtensionsList2) {
		CoverExtensionsList2 = coverExtensionsList2;
	}

	public Map<Integer, String> getAggregateLimitList() {
		return AggregateLimitList;
	}

	public void setAggregateLimitList(Map<Integer, String> aggregateLimitList) {
		AggregateLimitList = aggregateLimitList;
	}

	public double getAggregateLimitFactor() {
		return AggregateLimitFactor;
	}

	public void setAggregateLimitFactor(double aggregateLimitFactor) {
		AggregateLimitFactor = aggregateLimitFactor;
	}

	public double getDishonestFactor() {
		return DishonestFactor;
	}

	public void setDishonestFactor(double dishonestFactor) {
		DishonestFactor = dishonestFactor;
	}

	public double getDefenceExpFactor() {
		return DefenceExpFactor;
	}

	public void setDefenceExpFactor(double defenceExpFactor) {
		DefenceExpFactor = defenceExpFactor;
	}

	public double getLibelFactor() {
		return LibelFactor;
	}

	public void setLibelFactor(double libelFactor) {
		LibelFactor = libelFactor;
	}

	public double getLossOfDocumentsFactor() {
		return LossOfDocumentsFactor;
	}

	public void setLossOfDocumentsFactor(double lossOfDocumentsFactor) {
		LossOfDocumentsFactor = lossOfDocumentsFactor;
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	public String getURLDestination() {
		return URLDestination;
	}

	public void setURLDestination(String uRLDestination) {
		URLDestination = uRLDestination;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getURLJasperModel() {
		return URLJasperModel;
	}

	public void setURLJasperModel(String uRLJasperModel) {
		URLJasperModel = uRLJasperModel;
	}

	public Project getProject3() {
		return project3;
	}

	public String getTextToAddToreport() {
		return TextToAddToreport;
	}

	public void setTextToAddToreport(String textToAddToreport) {
		TextToAddToreport = textToAddToreport;
	}

	public boolean isDisplayTextArea() {
		return DisplayTextArea;
	}

	public void setDisplayTextArea(boolean displayTextArea) {
		DisplayTextArea = displayTextArea;
	}

	public String getCheckboxvalue() {
		return Checkboxvalue;
	}

	public void setCheckboxvalue(String checkboxvalue) {
		Checkboxvalue = checkboxvalue;
	}

	public String getCheckBoxRatingDetailsValue() {
		return CheckBoxRatingDetailsValue;
	}

	public void setCheckBoxRatingDetailsValue(String checkBoxRatingDetailsValue) {
		CheckBoxRatingDetailsValue = checkBoxRatingDetailsValue;
	}

	public void test() {
		System.out.println("yooopi");
	}

}
