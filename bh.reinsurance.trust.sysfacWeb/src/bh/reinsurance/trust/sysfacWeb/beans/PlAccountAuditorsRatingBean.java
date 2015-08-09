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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;

import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.domain.PIaccandAudit;
import al.assu.trust.GestionImageSinistre.domain.PlaccountantandauditorsMeasure;
import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.TestMeasureResult;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.domain.UserTrace;
import al.assu.trust.GestionImageSinistre.impl.CrudBasicLocal;
import al.assu.trust.GestionImageSinistre.impl.MeasureServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.PlaccandAuditServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.ProjectServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserTraceServicesLocal;

@ManagedBean
@ViewScoped
public class PlAccountAuditorsRatingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Locale us = Locale.US;
	private Map<Integer, String> Incuredlosseslist;
	private Map<Integer, String> NumberOfClaimsList;
	private Map<Integer, String> CoverExtensionsList;
	private Map<Integer, String> CoverExtensionsList2;


	private PIaccandAudit iaccandAudittosave;
	private TestMeasureResult test1 = new TestMeasureResult();
	private TestMeasureResult test2 = new TestMeasureResult();
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

	private PlaccountantandauditorsMeasure measureFactors;
	private Measure measure;
	private Measure testMeasure;
	private List<Measure> measures;

	// reporting var
	private String URLDestination;
	private String URLJasperModel;
	private String ProjectName;
	private JasperPrint jasperPrint;
	private JasperPrint jasperPrint2;
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
	private BarChartModel barChartModel;
	private BarChartModel barChartModelrate;

	// managed prop

	@ManagedProperty("#{projectBean.getProject3()}")
	private Project project3;

	// EJB
	@EJB
	private PlaccandAuditServicesLocal auditServicesLocal;
	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private MeasureServicesLocal measureServicesLocal;
	@EJB
	private CrudBasicLocal crudBasicLocal;
	// Measure var
	private String Selection;
	// Tracking users
	private UserTrace userTrace;
	@EJB
	private UserTraceServicesLocal userTraceServicesLocal;

	// constr
	public PlAccountAuditorsRatingBean() {
		testMeasure = new Measure();
		URLJasperModel = "/Users/zribisofien/Desktop/ModelReport/";
		URLDestination = "/Users/zribisofien/Desktop/PDFGEN/";
		FillLists();

	}

	@PostConstruct
	public void init() {
		barChartModel = new BarChartModel();
		barChartModelrate = new BarChartModel();
		initModel();
		if (project3.getId() != 0) {

			resetTestMeasureOnOPeningBean();

			measures = measureServicesLocal
					.GetMeasuresByClass("PI accountants and auditors");
			measure = measureServicesLocal
					.GetWorkingMeasure("PI accountants and auditors");
			measureFactors = (PlaccountantandauditorsMeasure) crudBasicLocal
					.FindByFilter("PlaccountantandauditorsMeasure",
							"idMeasure", measure.getId());
			DisplayTextArea = false;
			iaccandAudittosave = auditServicesLocal.GetByIdProject(project3
					.getId());

			OperationWhenopeningTool();
		}

	}

	// methods
	public void FillLists() {
		Incuredlosseslist = new HashMap<Integer, String>();
		Incuredlosseslist.put(1, "0");
		Incuredlosseslist.put(2, "0-100,00");
		Incuredlosseslist.put(3, "100,00-500,00");
		Incuredlosseslist.put(4, "500,00-1,000,000");
		Incuredlosseslist.put(5, "1,000,000 or more");

		NumberOfClaimsList = new HashMap<Integer, String>();
		NumberOfClaimsList.put(1, "0");
		NumberOfClaimsList.put(2, "0-2");
		NumberOfClaimsList.put(3, "2 or more");

	}

	public void SaveRating() {
		if ((measure.getActiveTest() == true)
				|| (project3.getQuoted_Date() != null)) {
			if ((measure.getActiveTest() == true)) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								"messages1",
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"You cant save this rating because you are not using the working measure",
										""));
			}
			FacesContext.getCurrentInstance().addMessage(
					"messages1",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"You cant modify a quoted rating", ""));
		} else {

			iaccandAudittosave.setIdproj(project3.getId());
			auditServicesLocal.update(iaccandAudittosave);
			User user = new User();
			user = userServicesLocal.GetUserByid(project3.getUser());
			userTrace = new UserTrace("Save project", user.getId(),
					"project name:" + project3.getNameOfTheProject());
			userTraceServicesLocal.AddTrace(userTrace);
			FacesContext.getCurrentInstance().addMessage(
					"messages1",
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Rating Saved", ""));
		}
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

	// Testing measure management begin
	public void resetTestMeasureOnOPeningBean() {
		measures = measureServicesLocal
				.GetMeasuresByClass("PI accountants and auditors");
		for (int i = 0; i < measures.size(); i++) {
			Measure measuree = measures.get(i);
			measuree.setActiveTest(false);
			measureServicesLocal.UpdateMeasure(measuree);
		}

	}

	public void ResetMeasureOnClick() {
		resetTestMeasureOnOPeningBean();
		measures = measureServicesLocal
				.GetMeasuresByClass("PI accountants and auditors");
		measure = measureServicesLocal
				.GetWorkingMeasure("PI accountants and auditors");
		measureFactors = (PlaccountantandauditorsMeasure) crudBasicLocal
				.FindByFilter("PlaccountantandauditorsMeasure", "idMeasure",
						measure.getId());
		iaccandAudittosave = auditServicesLocal
				.GetByIdProject(project3.getId());
		OperationWhenopeningTool();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('POPMeasure').hide();");
		FacesContext.getCurrentInstance().addMessage(
				"messages1",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Measure Reset",
						""));
	}

	public void TestingOtherMeasure() throws IOException {
		testMeasure.setActiveTest(true);
		measureServicesLocal.UpdateMeasure(testMeasure);
		measures = measureServicesLocal
				.GetMeasuresByClass("PI accountants and auditors");
		for (int i = 0; i < measures.size(); i++) {
			if (measures.get(i).getId() != testMeasure.getId()) {
				Measure measuree = measures.get(i);
				measuree.setActiveTest(false);
				measureServicesLocal.UpdateMeasure(measuree);
			}
		}

		measure = new Measure();
		measure = measureServicesLocal
				.GetTestingMeasure("PI accountants and auditors");
		measureFactors = (PlaccountantandauditorsMeasure) crudBasicLocal
				.FindByFilter("PlaccountantandauditorsMeasure", "idMeasure",
						measure.getId());
		try {
			GetAllFactorsOnOpeing();
			FacesContext.getCurrentInstance().addMessage(
					"messages1",
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Test measure Set", ""));
		} catch (Exception e) {
			iaccandAudittosave = new PIaccandAudit();
			GetAllFactorsOnOpeing();
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"messages1",
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									"Parameters for the test mesure doesnt fit with the working measure",
									"All the factors will be reinitialized"));
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('POPMeasure').hide();");

	}

	// Testing Measure management end

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

		if (iaccandAudittosave.getSpecialClientProfileList().equals("null")) {

		} else {
			specialclientlistfactor = Double.parseDouble(measureFactors
					.getSpecialClientProfileList().get(
							iaccandAudittosave.getSpecialClientProfileList())) / 100;
		}
		CalculateTotalOtherLoadings();

	}

	// other loading factor events end

	// PRactice speciality ajax events begin

	public void bookingandauditChange() {

		if (iaccandAudittosave.getBookkeepingandaudit().equals("null")) {

			Bookingandauditfactor = 0;

		} else {
			Bookingandauditfactor = Double.parseDouble(measureFactors
					.getBookKeepingAndAudit().get(
							iaccandAudittosave.getBookkeepingandaudit())) / 100;
		}
		calcultotalpracticespeciality();

	}

	public void Manageadvisorychange() {
		if (iaccandAudittosave.getManagementAdvisory().equals("null")) {

			managementadvisoryfactor = 0;

		} else {
			managementadvisoryfactor = Double.parseDouble(measureFactors
					.getManagementAdvisory().get(
							iaccandAudittosave.getManagementAdvisory())) / 100;
		}

		calcultotalpracticespeciality();
	}

	public void forecastChange() {
		if (iaccandAudittosave.getForecast().equals("null")
				|| iaccandAudittosave.getForecast().equals("")) {

			forecastsfactor = 0;

		} else {
			forecastsfactor = Double.parseDouble(measureFactors
					.getForcastProjection().get(
							iaccandAudittosave.getForecast())) / 100;
		}

		calcultotalpracticespeciality();
	}

	public void payrollChange() {
		if (iaccandAudittosave.getManagementAdvisory().equals("null")) {

			payrollfactor = 0;

		} else {
			payrollfactor = Double.parseDouble(measureFactors
					.getPayrollServices().get(
							iaccandAudittosave.getPayrollservices())) / 100;
		}

		calcultotalpracticespeciality();
	}

	public void fiduciaryChange() {
		if (iaccandAudittosave.getManagementAdvisory().equals("null")) {

			fiduciaryfactor = 0;

		} else {
			fiduciaryfactor = Double.parseDouble(measureFactors.getFiduciary()
					.get(iaccandAudittosave.getFiduciary())) / 100;
		}

		calcultotalpracticespeciality();
	}

	public void TaxChange() {
		if (iaccandAudittosave.getTaxpreparation().equals("null")) {

			taxpreparationfactor = 0;

		} else {
			taxpreparationfactor = Double.parseDouble(measureFactors
					.getTaxPreparation().get(
							iaccandAudittosave.getTaxpreparation())) / 100;
		}
		calcultotalpracticespeciality();
	}

	public void securitieschanges() {
		if (iaccandAudittosave.getSecuritiesrelated().equals("null")) {
			securitiesrelatedfactor = 0;
		} else {
			securitiesrelatedfactor = Double.parseDouble(measureFactors
					.getSecuritiesRelated().get(
							iaccandAudittosave.getSecuritiesrelated())) / 100;
		}

		calcultotalpracticespeciality();
	}

	public void planningchange() {

		if (iaccandAudittosave.getFinancialPlanning().equals("null")) {
			Financialplanningfactor = 0;
		} else {
			Financialplanningfactor = Double.parseDouble(measureFactors
					.getFinancialPlanning().get(
							iaccandAudittosave.getFinancialPlanning())) / 100;
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
		if (iaccandAudittosave.getRetrocover().equals("null")) {

			RetrospectiveCovers = 0;
		} else {
			RetrospectiveCovers = Double.parseDouble(measureFactors
					.getRetrospectiveCovers().get(
							iaccandAudittosave.getRetrocover())) / 100;
		}

		calculateCoverageExtensions();
	}

	public void extendedReportChange() {
		if (iaccandAudittosave.getExtendedreporting().equals("null")) {
			ExtendedReporting = 0;
		} else {
			ExtendedReporting = Double.parseDouble(measureFactors
					.getExtendedReport().get(
							iaccandAudittosave.getExtendedreporting())) / 100;
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
		if (iaccandAudittosave.getAggregatelimit() == null) {
			AggregateLimitFactor = 1;
		} else {
			AggregateLimitFactor = Double.parseDouble(measureFactors
					.getAggregateLimit().get(
							iaccandAudittosave.getAggregatelimit()));

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
				.getOccurlimit()) * 0.1))
				* measureFactors.getDishonestFactor();

		LossOfDocumentsFactor = (Float.parseFloat(iaccandAudittosave
				.getLossofdocument()) - (Float.parseFloat(iaccandAudittosave
				.getOccurlimit()) * 0.1))
				* measureFactors.getLossofDocumentFactor();

		LibelFactor = (Float.parseFloat(iaccandAudittosave.getLibel()) - (Float
				.parseFloat(iaccandAudittosave.getOccurlimit()) * 0.1))
				* measureFactors.getLibelSlanderFactor();

		DefenceExpFactor = (Float.parseFloat(iaccandAudittosave
				.getDefenseexpenses()) - (Float.parseFloat(iaccandAudittosave
				.getOccurlimit()) * 0.1))
				* measureFactors.getDefenceExpenses();

		CoverageEnhancement = (float) (DishonestFactor + LossOfDocumentsFactor
				+ LibelFactor + DefenceExpFactor);
		todoall();

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

	public String FormatTotalCoverageExtensions() {
		NumberFormat formatter = new DecimalFormat("#0.00 %");
		return formatter.format((ExtendedReporting + RetrospectiveCovers));
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

	public void ExportAccountantAndAuditorsSummary() throws JRException,
			IOException {
		File sourceFile;
		boolean testName;
		try {
			sourceFile = new File(URLDestination + ProjectName + ".pdf");
			FileInputStream fis = new FileInputStream(sourceFile);
			new BufferedInputStream(fis);
			testName = true;

		} catch (Exception e) {
			testName = false;
		}
		if (testName == true) {
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

	public void ExportTestMeasureSummary(Measure measure3) throws JRException {
		NumberFormat formatte2 = new DecimalFormat("#0.00 %");

		DecimalFormat formatter = (DecimalFormat) NumberFormat
				.getCurrencyInstance(us);
		formatter.setNegativePrefix("$-");
		formatter.setNegativeSuffix("");

		List<Project> projects = new ArrayList<Project>();
		Project project = new Project();
		projects.add(project);
		File sourceFile;
		boolean testName;

		Map<String, Object> param = new HashMap<String, Object>();
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				projects);
		param.put("OldPremium", formatter.format(test1.getPremium()));
		param.put("NewPremium", formatter.format(test2.getPremium()));
		param.put("OldRate", formatte2.format(test1.getRate()));
		param.put("NewRate", formatte2.format(test2.getRate()));
		param.put("WorkingName", measure3.getName());
		param.put(
				"TestingName",
				measureServicesLocal.GetWorkingMeasure(
						measure3.getClassofbusiness()).getName());

		jasperPrint2 = JasperFillManager.fillReport(URLJasperModel
				+ "/TestMeasureSummmary.jasper", param,
				beanCollectionDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint2, URLDestination
				+ ProjectName + ".pdf");

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('PopupPdf').hide();");
		FacesContext.getCurrentInstance()
				.addMessage(
						"messages1",
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"File Created", ""));
		ProjectName = null;

	}

	// Report Generation ends

	// Calculate total premium by rating begin
	public int GetWorkingMeasureId(String LOB) {
		Measure measure1 = new Measure();
		measure1 = measureServicesLocal.GetWorkingMeasure(LOB);
		System.out.println();
		return measure1.getId();

	}

	// $$$$$$$$$$$$$$$$$$$$$$$ WORK HERE
	// *********************************************************************************************

	@EJB
	private ProjectServicesLocal projectServicesLocal;

	public void GetTheTotalPremium(int WorkingID, int TestingID)
			throws IOException {
		List<Project> list = projectServicesLocal.GetAllProjects();
		List<Project> list2 = new ArrayList<Project>();

		double avg = 0;
		double total = 0.0;
		for (Project a : list) {
			if ((a.getTool().equals("PI accountants and auditors"))
					&& (a.getQuoted_Date() != null)) {
				list2.add(a);
			}
		}

		measure = measureServicesLocal.GetMeasure((WorkingID));
		measureFactors = (PlaccountantandauditorsMeasure) crudBasicLocal
				.FindByFilter("PlaccountantandauditorsMeasure", "idMeasure",
						measure.getId());

		for (Project b : list2) {
			iaccandAudittosave = auditServicesLocal.GetByIdProject(b.getId());
			OperationWhenopeningTool();
			total = total + TotalPremium;
			avg = avg + AverageRate;

		}
		test1.setPremium(total);
		test1.setRate(avg / list2.size());
		total = 0;
		avg = 0;
		measure = measureServicesLocal.GetMeasure((TestingID));
		measureFactors = (PlaccountantandauditorsMeasure) crudBasicLocal
				.FindByFilter("PlaccountantandauditorsMeasure", "idMeasure",
						measure.getId());

		for (Project b : list2) {
			iaccandAudittosave = auditServicesLocal.GetByIdProject(b.getId());
			OperationWhenopeningTool();
			total = total + TotalPremium;
			avg = avg + AverageRate;

		}
		test2.setPremium(total);
		test2.setRate(avg / list2.size());
		initModel();
	}

	public void initModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries Test = new ChartSeries();
		Test.setLabel("Test Measure");

		Test.set("", test2.getPremium());

		ChartSeries working = new ChartSeries();
		working.setLabel("Working measure");
		working.set("", test1.getPremium());

		model.addSeries(Test);
		model.addSeries(working);
		barChartModel = model;
		barChartModel.setTitle("Premium");
		barChartModel.setAnimate(true);
		barChartModel.setLegendPosition("ne");
		barChartModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		model = new BarChartModel();
		ChartSeries TestRate = new ChartSeries();
		TestRate.setLabel("Test Measure");

		TestRate.set("", test2.getRate());
		ChartSeries WorkingRate = new ChartSeries();
		WorkingRate.setLabel("Working Measure");

		WorkingRate.set("", test1.getRate());

		model.addSeries(TestRate);
		model.addSeries(WorkingRate);
		barChartModelrate = model;
		barChartModelrate.setTitle("Average Rate");
		barChartModelrate.setLegendPosition("ne");
		barChartModelrate.setAnimate(true);
		barChartModelrate.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
	}

	public String GetFormatresult(double total) {
		DecimalFormat formatter = (DecimalFormat) NumberFormat
				.getCurrencyInstance(us);
		formatter.setNegativePrefix("$-");
		formatter.setNegativeSuffix("");
		return formatter.format(total);
	}

	// Calculate total premium by rating end
	//Format method
		public String FormatToDollar(double toformat) {
			return NumberFormat.getCurrencyInstance(us).format(toformat);
		}

		
	// getters setters

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

	public Measure getMeasure() {
		return measure;
	}

	public PlaccountantandauditorsMeasure getMeasureFactors() {
		return measureFactors;
	}

	public void setMeasureFactors(PlaccountantandauditorsMeasure measureFactors) {
		this.measureFactors = measureFactors;
	}

	public Measure getTestMeasure() {
		return testMeasure;
	}

	public void setTestMeasure(Measure TestMeasure) {
		testMeasure = TestMeasure;
	}

	public List<Measure> getMeasures() {
		return measures;
	}

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}

	public String getSelection() {
		return Selection;
	}

	public void setSelection(String selection) {
		Selection = selection;
	}

	public UserTrace getUserTrace() {
		return userTrace;
	}

	public void setUserTrace(UserTrace userTrace) {
		this.userTrace = userTrace;
	}

	public TestMeasureResult getTest1() {
		return test1;
	}

	public void setTest1(TestMeasureResult test1) {
		this.test1 = test1;
	}

	public TestMeasureResult getTest2() {
		return test2;
	}

	public void setTest2(TestMeasureResult test2) {
		this.test2 = test2;
	}

	

	public BarChartModel getBarChartModel() {
		return barChartModel;
	}

	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}

	public BarChartModel getBarChartModelrate() {
		return barChartModelrate;
	}

	public void setBarChartModelrate(BarChartModel barChartModelrate) {
		this.barChartModelrate = barChartModelrate;
	}

	public JasperPrint getJasperPrint2() {
		return jasperPrint2;
	}

	public void setJasperPrint2(JasperPrint jasperPrint2) {
		this.jasperPrint2 = jasperPrint2;
	}

}
