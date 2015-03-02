package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.Facultative;
import al.assu.trust.GestionImageSinistre.domain.Sysfacus;
import al.assu.trust.GestionImageSinistre.impl.FacultativeServicesLocal;

@ManagedBean
@ViewScoped
public class FacultativeBean implements Serializable {
	@EJB
	FacultativeServicesLocal facultativeServicesLocal;
	final Locale us = Locale.US;
	private List<Facultative> facbychoice;

	private boolean testaff;
	private static final long serialVersionUID = 1L;
	private List<String> countr;
	private String countr1;
	private Facultative ComparaisonFacultative;
	private String occup1;
	private Facultative facultative;
	private boolean DisplayDeatils;
	private List<String> occup;
	private List<String> reg;
	private String reg1;
	private List<Facultative> facultatives;
	private Facultative facbychoice1;
	private List<Facultative> filtredfac;
	private List<Facultative> facultatives2;
	private boolean DisplayComparaisonButton;
	private boolean DisplayFacCompare;
	private String liab = null;
	private int NBRfac = 0;
	private String Suminsured = null;
	private String AvgLiability;
	private List<Sysfacus> sysfacus2;
	private int TotalNumbreFac = 0;
	private String TotalLiability = null;
	private String TotalSumInsured = null;
	private String AVgLiability;
	private Map<String, String> Years = new HashMap<String, String>();
	private List<Integer> years1;
	private int LiabilityBand = 0;
	private int PasLiability = 1000000;
	private String RadioValue = "Liablity";
	private int SumInsuredPas = 10000000;

	public FacultativeBean() {
	}

	@PostConstruct
	public void init() {
		years1 = new ArrayList<Integer>();
		years1.add(2010);
		years1.add(2011);
		years1.add(2012);
		years1.add(2014);
		Years = new HashMap<String, String>();
		Years.put("2010", "2010");
		Years.put("2011", "2011");
		Years.put("2012", "2012");
		Years.put("2013", "2013");
		Years.put("2014", "2014");
		Years.put("2015", "2015");
		sysfacus2 = new ArrayList<Sysfacus>();
		DisplayFacCompare = false;
		DisplayComparaisonButton = true;
		DisplayDeatils = false;
		testaff = false;
		setFacultative(new Facultative());
		occup = facultativeServicesLocal.GetOcuupencies();
		reg = facultativeServicesLocal.getRegions();
		setFacultatives(facultativeServicesLocal.facultatives());
	}

	public void OnRegionChange() {
		countr1 = null;
		occup1 = null;
		setCountr(facultativeServicesLocal.GetCountries(reg1));
		setOccup(facultativeServicesLocal.GetOcuupenciesbyChoice(reg1, countr1));
		setFacbychoice(facultativeServicesLocal.GetFacBychoice(reg1, countr1,
				occup1));

		Filllist();

		testaff = true;
	}

	public void affichselec() {
		setCountr(facultativeServicesLocal.GetCountries(reg1));
		setOccup(facultativeServicesLocal.GetOcuupenciesbyChoice(reg1, countr1));
		setFacbychoice(facultativeServicesLocal.GetFacBychoice(reg1, countr1,
				occup1));
		Filllist();
		testaff = true;
	}

	public void DisplayFacCompare() {
		DisplayFacCompare = true;
		DisplayComparaisonButton = false;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('popup').hide();");
	}

	public void OnRowSelect4() {
		DisplayDeatils = true;
	}

	public void plusLiabilityBand() {
		if (RadioValue.equals("Liability")) {
			PasLiability = PasLiability * 2;
			Filllist();
		} else {
			SumInsuredPas = SumInsuredPas * 2;
			Filllist2();
		}

	}

	public void minusLiabilityBand() {
		if (RadioValue.equals("Liability")) {
			PasLiability = PasLiability / 2;
			Filllist();
		} else {
			SumInsuredPas = SumInsuredPas / 2;
			Filllist2();
		}
	}

	public void ChangeTableTypeToSI() {
		RadioValue = "Sum Insured";
		Filllist2();
	}

	public void ChangeTableTypeToLiab() {
		RadioValue = "Liability";
		Filllist();
	}

	// Table 1 By Liability
	public void Filllist() {
		sysfacus2 = new ArrayList<Sysfacus>();
		TotalNumbreFac = 0;
		int i = 1;
		if (facbychoice.size() != 0) {
			i = facbychoice.size();
		}

		int f = 0;
		float c = 0;
		float totalSI = 0;
		int totalLia = 0;
		List<Facultative> facultatives2 = new ArrayList<Facultative>();
		for (int j = 0; j <= 9 * PasLiability; j = j + PasLiability) {
			Sysfacus sysfacus = new Sysfacus();
			f = 0;
			c = 0;
			facultatives2 = new ArrayList<Facultative>();
			for (Facultative a : facbychoice) {
				if (j == 9 * PasLiability && a.getOur_liability() > j) {
					facultatives2.add(a);
					totalLia = totalLia + a.getOur_liability();
					totalSI = totalSI + Float.parseFloat(a.getSuminsured());
					f = f + a.getOur_liability();
					TotalNumbreFac++;
					c = c + Float.parseFloat(a.getSuminsured());
				} else {

					if (a.getOur_liability() <= j + PasLiability
							&& a.getOur_liability() > j) {
						facultatives2.add(a);
						totalLia = totalLia + a.getOur_liability();
						totalSI = totalSI + Float.parseFloat(a.getSuminsured());
						f = f + a.getOur_liability();
						TotalNumbreFac++;
						c = c + Float.parseFloat(a.getSuminsured());
					}
				}
			}
			if (TotalNumbreFac == 0) {
				TotalNumbreFac = 1;
			}
			sysfacus.setLia(liab = NumberFormat.getCurrencyInstance(us).format(
					f));
			AVgLiability = NumberFormat.getCurrencyInstance(us).format(
					totalLia / TotalNumbreFac);
			TotalSumInsured = NumberFormat.getCurrencyInstance(us).format(
					totalSI);
			TotalLiability = NumberFormat.getCurrencyInstance(us).format(
					totalLia);

			sysfacus.setAvglia(AvgLiability = NumberFormat.getCurrencyInstance(
					us).format((f / i)));
			sysfacus.setNbr(facultatives2.size());
			sysfacus.setSum(NumberFormat.getCurrencyInstance(us).format(c));
			sysfacus.setFrom(NumberFormat.getCurrencyInstance(us).format(j));
			if (j == 9 * PasLiability) {
				sysfacus.setTo("&&More");
			} else {
				sysfacus.setTo(NumberFormat.getCurrencyInstance(us).format(
						j + PasLiability));
			}

			sysfacus2.add(sysfacus);
		}

	}

	// table 2 By SI
	public void Filllist2() {
		sysfacus2 = new ArrayList<Sysfacus>();
		TotalNumbreFac = 0;
		int i = facbychoice.size();
		int f = 0;
		float c = 0;
		float totalSI = 0;
		int totalLia = 0;
		List<Facultative> facultatives2 = new ArrayList<Facultative>();
		for (int j = 0; j <= 9 * SumInsuredPas; j = j + SumInsuredPas) {
			Sysfacus sysfacus = new Sysfacus();
			f = 0;
			c = 0;
			facultatives2 = new ArrayList<Facultative>();
			for (Facultative a : facbychoice) {
				if (j == 9 * SumInsuredPas
						&& Float.parseFloat(a.getSuminsured()) > j) {
					facultatives2.add(a);
					totalLia = totalLia + a.getOur_liability();
					totalSI = totalSI + Float.parseFloat(a.getSuminsured());
					f = f + a.getOur_liability();
					TotalNumbreFac++;
					c = c + Float.parseFloat(a.getSuminsured());
				} else {

					if (Float.parseFloat(a.getSuminsured()) <= j
							+ SumInsuredPas
							&& Float.parseFloat(a.getSuminsured()) > j) {
						facultatives2.add(a);
						totalLia = totalLia + a.getOur_liability();
						totalSI = totalSI + Float.parseFloat(a.getSuminsured());
						f = f + a.getOur_liability();
						TotalNumbreFac++;
						c = c + Float.parseFloat(a.getSuminsured());
					}
				}
			}

			sysfacus.setLia(liab = NumberFormat.getCurrencyInstance(us).format(
					f));
			AVgLiability = NumberFormat.getCurrencyInstance(us).format(
					totalLia / TotalNumbreFac);
			TotalSumInsured = NumberFormat.getCurrencyInstance(us).format(
					totalSI);
			TotalLiability = NumberFormat.getCurrencyInstance(us).format(
					totalLia);

			sysfacus.setAvglia(AvgLiability = NumberFormat.getCurrencyInstance(
					us).format((f / i)));
			sysfacus.setNbr(facultatives2.size());
			sysfacus.setSum(NumberFormat.getCurrencyInstance(us).format(c));
			sysfacus.setFrom(NumberFormat.getCurrencyInstance(us).format(j));
			if (j == 9 * SumInsuredPas) {
				sysfacus.setTo("&&More");
			} else {
				sysfacus.setTo(NumberFormat.getCurrencyInstance(us).format(
						j + SumInsuredPas));
			}

			sysfacus2.add(sysfacus);
		}

	}

	public boolean isTestaff() {
		return testaff;
	}

	public void setTestaff(boolean testaff) {
		this.testaff = testaff;
	}

	public List<Facultative> getFacbychoice() {
		return facbychoice;
	}

	public void setFacbychoice(List<Facultative> facbychoice) {
		this.facbychoice = facbychoice;
	}

	public List<String> getCountr() {
		return countr;
	}

	public void setCountr(List<String> countr) {
		this.countr = countr;
	}

	public List<String> getOccup() {
		return occup;
	}

	public void setOccup(List<String> occup) {
		this.occup = occup;
	}

	public FacultativeServicesLocal getFacultativeServicesLocal() {
		return facultativeServicesLocal;
	}

	public void setFacultativeServicesLocal(
			FacultativeServicesLocal facultativeServicesLocal) {
		this.facultativeServicesLocal = facultativeServicesLocal;
	}

	public String getCountr1() {
		return countr1;
	}

	public void setCountr1(String countr1) {
		this.countr1 = countr1;
	}

	public String getOccup1() {
		return occup1;
	}

	public void setOccup1(String occup1) {
		this.occup1 = occup1;
	}

	public List<String> getReg() {
		return reg;
	}

	public void setReg(List<String> reg) {
		this.reg = reg;
	}

	public String getReg1() {
		return reg1;
	}

	public void setReg1(String reg1) {
		this.reg1 = reg1;
	}

	public List<Facultative> getFacultatives() {
		return facultatives;
	}

	public void setFacultatives(List<Facultative> facultatives) {
		this.facultatives = facultatives;
	}

	public Facultative getFacbychoice1() {
		return facbychoice1;
	}

	public void setFacbychoice1(Facultative facbychoice1) {
		this.facbychoice1 = facbychoice1;
	}

	public List<Facultative> getFacultatives2() {
		return facultatives2;
	}

	public void setFacultatives2(List<Facultative> facultatives2) {
		this.facultatives2 = facultatives2;
	}

	public List<Facultative> getFiltredfac() {
		return filtredfac;
	}

	public void setFiltredfac(List<Facultative> filtredfac) {
		this.filtredfac = filtredfac;
	}

	public Facultative getFacultative() {
		return facultative;
	}

	public void setFacultative(Facultative facultative) {
		this.facultative = facultative;
	}

	public boolean isDisplayDeatils() {
		return DisplayDeatils;
	}

	public List<Sysfacus> getSysfacus2() {
		return sysfacus2;
	}

	public void setSysfacus2(List<Sysfacus> sysfacus2) {
		this.sysfacus2 = sysfacus2;
	}

	public void setDisplayDeatils(boolean displayDeatils) {
		DisplayDeatils = displayDeatils;
	}

	public boolean isDisplayComparaisonButton() {
		return DisplayComparaisonButton;
	}

	public void setDisplayComparaisonButton(boolean displayComparaisonButton) {
		DisplayComparaisonButton = displayComparaisonButton;
	}

	public boolean isDisplayFacCompare() {
		return DisplayFacCompare;
	}

	public void setDisplayFacCompare(boolean displayFacCompare) {
		DisplayFacCompare = displayFacCompare;
	}

	public Facultative getComparaisonFacultative() {
		return ComparaisonFacultative;
	}

	public void setComparaisonFacultative(Facultative comparaisonFacultative) {
		ComparaisonFacultative = comparaisonFacultative;
	}

	public int getNBRfac() {
		return NBRfac;
	}

	public void setNBRfac(int nBRfac) {
		NBRfac = nBRfac;
	}

	public String getLiab() {
		return liab;
	}

	public void setLiab(String liab) {
		this.liab = liab;
	}

	public String getSuminsured() {
		return Suminsured;
	}

	public void setSuminsured(String suminsured) {
		Suminsured = suminsured;
	}

	public String getAvgLiability() {
		return AvgLiability;
	}

	public void setAvgLiability(String avgLiability) {
		AvgLiability = avgLiability;
	}

	public int getTotalNumbreFac() {
		return TotalNumbreFac;
	}

	public void setTotalNumbreFac(int totalNumbreFac) {
		TotalNumbreFac = totalNumbreFac;
	}

	public String getTotalLiability() {
		return TotalLiability;
	}

	public void setTotalLiability(String totalLiability) {
		TotalLiability = totalLiability;
	}

	public String getTotalSumInsured() {
		return TotalSumInsured;
	}

	public void setTotalSumInsured(String totalSumInsured) {
		TotalSumInsured = totalSumInsured;
	}

	public String getAVgLiability() {
		return AVgLiability;
	}

	public void setAVgLiability(String aVgLiability) {
		AVgLiability = aVgLiability;
	}

	public Map<String, String> getYears() {
		return Years;
	}

	public void setYears(Map<String, String> years) {
		Years = years;
	}

	public List<Integer> getYears1() {
		return years1;
	}

	public void setYears1(List<Integer> years1) {
		this.years1 = years1;
	}

	public int getLiabilityBand() {
		return LiabilityBand;
	}

	public void setLiabilityBand(int liabilityBand) {
		LiabilityBand = liabilityBand;
	}

	public int getPasLiability() {
		return PasLiability;
	}

	public void setPasLiability(int pasLiability) {
		PasLiability = pasLiability;
	}

	public String getRadioValue() {
		return RadioValue;
	}

	public void setRadioValue(String radioValue) {
		RadioValue = radioValue;
	}

	public int getSumInsuredPas() {
		return SumInsuredPas;
	}

	public void setSumInsuredPas(int sumInsuredPas) {
		SumInsuredPas = sumInsuredPas;
	}

}
