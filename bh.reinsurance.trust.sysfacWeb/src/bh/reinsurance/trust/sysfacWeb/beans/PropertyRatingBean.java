package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import al.assu.trust.GestionImageSinistre.domain.Assets;
import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.PropertyOnshoreMeasure;
import al.assu.trust.GestionImageSinistre.domain.PropertyOnshoreRating;
import al.assu.trust.GestionImageSinistre.impl.AssetsServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.ExposureScaleServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.MeasureServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.OccupanciesServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.PropertyOnshoreMeasureServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.PropertyOnshoreRatingServicesLocal;

@ManagedBean(name = "PropertyRating")
@ViewScoped
public class PropertyRatingBean implements Serializable {

	/**
	 * 
	 */
	final Locale us = Locale.US;
	// param
	private PropertyOnshoreRating propertyOnshoreRating;
	private PropertyOnshoreMeasure propertyOnshoreMeasure;
	private List<String> COB;
	private List<String> Occupancies;
	private List<String> Countries;
	private List<String> Severity;
	private Double PMLErrorLoad;
	// Assets Mang
	private Assets assets;
	private List<Assets> assetsList;
	private boolean forrmDisplayed;
	private List<String> COB2;
	private List<String> Occupancies2;
	// Risk Asses
	private double FireMeasureTotal = 0;
	private double PublicFireBrigadeLoad = 0;
	private double PlantFireBrigadeLoad = 0;
	private double EstingSystemLoad = 0;
	private double FireDetectionLoad = 0;
	private double ConstructionClassLoad;
	private double lossRatioLoad;
	private double FireFighting_Discount = 0;
	private double PD_Prevention_Discount = 0;
	private double BI_Prevention_Discount = 0;
	private double SafetyAndSecurityAssesment = 0;
	private double HousekeepingAssesment = 0;
	private double MaintenanceAssesment = 0;
	private double BILossPrevenstionAssesment = 0;
	private double MBandMlOPLoad = 0;
	private double BIload = 0;

	// MLOP AND BI var
	private double BiindemnityLoad = 0;
	private double MlopindemnityLoad = 0;

	// SI section breakdown calculation VAR
	private double BasePropertyDamageRate = 0;

	// ejb and managed prop
	@EJB
	ExposureScaleServicesLocal exposureScaleServicesLocal;
	@EJB
	AssetsServicesLocal assetsServicesLocal;
	@EJB
	OccupanciesServicesLocal occupanciesServicesLocal;

	@EJB
	PropertyOnshoreRatingServicesLocal propertyOnshoreRatingServicesLocal;

	@EJB
	PropertyOnshoreMeasureServicesLocal propertyOnshoreMeasureServicesLocal;

	@EJB
	private MeasureServicesLocal measureServicesLocal;

	@ManagedProperty("#{projectBean.getProject3()}")
	private Project project3;

	// visual disp param
	private boolean DispalyExcess;
	private boolean DisplayQs;

	private static final long serialVersionUID = 1L;

	public PropertyRatingBean() {
		forrmDisplayed = false;
		setAssets(new Assets());
		assets.setId(0);
	}

	// init method
	@PostConstruct
	public void init() {
		Countries = exposureScaleServicesLocal.GetAllCountries();
		Severity = exposureScaleServicesLocal.GetSeverity();
		COB2 = occupanciesServicesLocal.GetCOB();
		COB = occupanciesServicesLocal.GetCOB();

		propertyOnshoreRating = propertyOnshoreRatingServicesLocal
				.GetByIdProject(project3.getId());

		propertyOnshoreMeasure = propertyOnshoreMeasureServicesLocal
				.FindByIdMeasure(measureServicesLocal.GetWorkingMeasure(
						"Property").getId());
		DisplayByBasis();
		LoadBIMLOPstart();
		PMLErrorLoad = Double.parseDouble(propertyOnshoreMeasure.getPMLerror()
				.get(propertyOnshoreRating.getPMLerror()));

		assetsList = assetsServicesLocal.GetAssetsByIdProject(project3.getId());

		LossRatioChange();
		ConstructionClassChange();
		loadOnOpenForAssesment();

	}

	// change display with basis of acceptance
	public void DisplayByBasis() {
		if (propertyOnshoreRating.getBasisOfAcceptance().equals("qs")) {
			DisplayQs = true;
			DispalyExcess = false;
		} else {
			DispalyExcess = true;
			DisplayQs = false;
		}

	}

	// Getifthe Rating is quoted or not
	public boolean DisplayNotQuotedRating() {
		if (project3.getQuoted_Date() != null) {
			return false;
		} else {
			return true;
		}
	}

	// PML error Change
	public void PmlerrorChange() {
		PMLErrorLoad = Double.parseDouble(propertyOnshoreMeasure.getPMLerror()
				.get(propertyOnshoreRating.getPMLerror()));
		for (Assets as : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					as.getCountry(), as.getPD_Curve()).getExposure();
			double aa = (as.getPD_PML_RATE() / 100) * (1 + PMLErrorLoad);
			double cc = (as.getBI_PML_RATE() / 100) * (1 + PMLErrorLoad);
			double bb = (as.getMB_PML_RATE() / 100) * (1 + PMLErrorLoad);
			double dd = (as.getOTHER_PML_RATE() / 100) * (1 + PMLErrorLoad);
			double ee = (as.getStock_PML_RATE() / 100) * (1 + PMLErrorLoad);
			double ff = (as.getMlop_PML_RATE() / 100) * (1 + PMLErrorLoad);

			double a = Math.min(aa, 1);
			double b = Math.min(bb, 1);
			double c = Math.min(cc, 1);
			double d = Math.min(dd, 1);
			double e = Math.min(ee, 1);
			double f = Math.min(ff, 1);

			as.setPD_PML(a * as.getPD_SI());
			as.setMB_PML(b * as.getMB_SI());
			as.setBI_PML(c * as.getBI_SI());
			as.setOTHER_PML(d * as.getOTHER_SI());
			as.setStock_PML(e * as.getStock_SI());
			as.setMlop_PML(f * as.getMlop_SI());

			as.setPD_Base_Premium(as.getPD_Rate() * as.getPD_SI() / 100);

			as.setPD_PML_Adjusted_Premium(MBBEFD(
					(as.getPD_PML() / as.getPD_SI()), Exposure)
					* as.getPD_Base_Premium());

			Double Discount = (1 + FireMeasureTotal)
					* (1 + (lossRatioLoad / 100)) - 1;

			as.setPD_Discount_premium((1 + Discount)
					* as.getPD_PML_Adjusted_Premium());

		}

	}

	// BI MLOP indemnity period result begin
	public void BIIndemnityPeriodChange() {
		BiindemnityLoad = Double.parseDouble(propertyOnshoreMeasure
				.getBiIndemnityPeriod().get(
						propertyOnshoreRating.getBiIndemnityPeriod()));

		for (Assets as : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					as.getCountry(), as.getBI_Curve()).getExposure();

			as.setBI_Rate(((occupanciesServicesLocal.GetOccupByfilters(
					as.getCOB(), as.getOccupancy())).getBI())
					* (1 + BiindemnityLoad * 0.01));
			as.setBI_Base_Premium(as.getBI_Rate() * as.getBI_SI() / 100);
			as.setBI_PML_Adjusted_Premium(MBBEFD(
					(as.getBI_PML() / as.getBI_SI()), Exposure)
					* as.getBI_Base_Premium());
			as.setBI_Discount_premium((1 + BIload)
					* as.getBI_PML_Adjusted_Premium());
			as.setBI_Dedpremium(as.getBI_Discount_premium());
		}

	}

	public void MLOPIndemnityPeriodChange() {
		MlopindemnityLoad = Double.parseDouble(propertyOnshoreMeasure
				.getMLoPIndemnityPeriod().get(
						propertyOnshoreRating.getMlopIndemnityPeriod()));

		for (Assets a : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getBI_Curve()).getExposure();

			a.setMlop_Rate(((occupanciesServicesLocal.GetOccupByfilters(
					a.getCOB(), a.getOccupancy())).getMLOP())
					* (1 + MlopindemnityLoad * 0.01));

			a.setMlop_Base_Premium(a.getMlop_Rate() * a.getMlop_SI() / 100);

			a.setMlop_PML_Adjusted_Premium(MBBEFD(
					(a.getMlop_PML() / a.getMlop_SI()), Exposure)
					* a.getMlop_Base_Premium());

			a.setMlop_Discount_premium((1 + MBandMlOPLoad)
					* a.getMlop_PML_Adjusted_Premium());

			a.setMlop_Dedpremium(a.getMlop_Discount_premium());
		}

	}

	public void LoadBIMLOPstart() {
		BiindemnityLoad = Double.parseDouble(propertyOnshoreMeasure
				.getBiIndemnityPeriod().get(
						propertyOnshoreRating.getBiIndemnityPeriod()));
		MlopindemnityLoad = Double.parseDouble(propertyOnshoreMeasure
				.getMLoPIndemnityPeriod().get(
						propertyOnshoreRating.getMlopIndemnityPeriod()));

	}

	// BI MLOP indemnity period result end

	// change Risk grade and occup when COB changes
	public List<String> COBChangeByParam2(String bf) {
		Occupancies = occupanciesServicesLocal.GetOccupanciesByCob(bf);
		return Occupancies;
	}

	public void GetRiskGrade() {
		propertyOnshoreRating.setRiskGrade(String
				.valueOf(occupanciesServicesLocal.GetRiskGrade(
						propertyOnshoreRating.getClassof(),
						propertyOnshoreRating.getOccupancy())));
	}

	// Risk Assesment Begin

	public void loadOnOpenForAssesment() {

		CountPDPreventionDiscount();
		CountBIPreventionDiscount();
		CountFireDetectionLoad();
		CountPublicFirebrigadeLoad();
		CountPlantFireBrigadeLoad();
		CountEstingSystemLoad();
		CountMbandMloploead();

	}

	public void UpdateAssetsWhenRiskAssesmentChanges() {
		for (Assets as : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					as.getCountry(), as.getPD_Curve()).getExposure();
			Double Discount = ((1 + FireMeasureTotal)
					* (1 + (lossRatioLoad / 100)) * (1 + (ConstructionClassLoad / 100))) - 1;

			as.setPD_Discount_premium((1 + Discount)
					* as.getPD_PML_Adjusted_Premium());
			double DedBLY1 = (1 - MBBEFD(
					(propertyOnshoreRating.getPD_Deductible_amount()
							/ (propertyOnshoreRating.getPD_deductible_perc() / 100) / as
							.getPD_PML()), Exposure))
					* as.getPD_Discount_premium()
					* (1 - (propertyOnshoreRating.getPD_deductible_perc() / 100));

			double DedBLY2 = ((MBBEFD(
					propertyOnshoreRating.getPD_Deductible_amount()
							/ (propertyOnshoreRating.getPD_deductible_perc() / 100)
							/ as.getPD_PML(), Exposure)) - (MBBEFD(
					(propertyOnshoreRating.getPD_Deductible_amount() / as
							.getPD_PML()),
					Exposure)))
					* as.getPD_Discount_premium();

			as.setPD_DeductibleB((DedBLY1 + DedBLY2));

			as.setOTHER_Discount_premium((1 + Discount)
					* as.getOTHER_PML_Adjusted_Premium());

			as.setStock_Discount_premium((1 + Discount)
					* as.getStock_PML_Adjusted_Premium());

			as.setStock_Dedpremium(as.getStock_Discount_premium()
					* (1 - MBBEFD((propertyOnshoreRating.getStock_amount() / as
							.getStock_PML()), Exposure)));

			as.setOTHER_Dedpremium(as.getOTHER_Discount_premium()
					* (1 - MBBEFD((propertyOnshoreRating.getOther_amount() / as
							.getOTHER_PML()), Exposure)));

		}

	}

	// Property Damage
	public void CountPDPreventionDiscount() {
		double Achieved;
		double min;
		SafetyAndSecurityAssesment = Double.parseDouble(propertyOnshoreMeasure
				.getAssesment().get(propertyOnshoreRating.getSecurityGuard()))
				* (Double
						.parseDouble(propertyOnshoreMeasure.getSecurityGuard()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getManagementRisk()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getManagementRiskAwareness()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getPhysicalBarriers()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getPhysicalBarriers()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getAccessControl()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getAccessControlAndAlarmSystem()) / 100);

		HousekeepingAssesment = Double.parseDouble(propertyOnshoreMeasure
				.getAssesment().get(propertyOnshoreRating.getSmokeControl()))
				* (Double.parseDouble(propertyOnshoreMeasure.getSmokeControl()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getWeldingControl()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getWeldingControl()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getFirePrevention()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getFirePrevention()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getStockStorage()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getStockStoragePractise()) / 100);

		MaintenanceAssesment = Double.parseDouble(propertyOnshoreMeasure
				.getAssesment().get(
						propertyOnshoreRating.getMechanicalMaintenance()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getMechanicalMaintenance()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getElectricalMaintenance()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getElectricalMaintenance()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getFullFilment()))
				* (Double.parseDouble(propertyOnshoreMeasure.getFullFilment()) / 100)

				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getRegularMaintenance()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getRegularMaintenance()) / 100);

		Achieved = MaintenanceAssesment + SafetyAndSecurityAssesment
				+ HousekeepingAssesment;
		double a = Math.min(HousekeepingAssesment, SafetyAndSecurityAssesment);
		min = Math.min(a, MaintenanceAssesment);

		PD_Prevention_Discount = ((Achieved - min) * -0.3) / (6 - min);
		CountFireFightingDiscount();

	}

	public void LossRatioChange() {
		lossRatioLoad = Double.parseDouble(propertyOnshoreMeasure
				.getLossRatio().get(propertyOnshoreRating.getLossRatio()));
		CountBIPreventionDiscount();
		UpdateAssetsWhenRiskAssesmentChanges();

	}

	public void ConstructionClassChange() {
		ConstructionClassLoad = Double.parseDouble(propertyOnshoreMeasure
				.getConstructionClass().get(
						propertyOnshoreRating.getConstructionClass()));
		CountBIPreventionDiscount();
		UpdateAssetsWhenRiskAssesmentChanges();
	}

	public void CountFireFightingDiscount() {

		double M1 = Math.max(EstingSystemLoad, PlantFireBrigadeLoad);
		double M2 = Math.max(M1, PublicFireBrigadeLoad);

		FireFighting_Discount = Math
				.max(-0.6,
						-((M2 * 0.5)
								+ ((EstingSystemLoad + PlantFireBrigadeLoad + PublicFireBrigadeLoad) * 0.5) + FireDetectionLoad));

		FireMeasureTotal = Math.max(-0.82,
				(FireFighting_Discount + PD_Prevention_Discount));
		CountBIPreventionDiscount();

		UpdateAssetsWhenRiskAssesmentChanges();

	}

	public void CountEstingSystemLoad() {
		double ESI = Double.parseDouble(propertyOnshoreMeasure
				.getInstallationEstinguishment().get(
						propertyOnshoreRating.getEstinguishmentInstallation()));
		double ESC = Double.parseDouble(propertyOnshoreMeasure.getCoverage()
				.get(propertyOnshoreRating.getCoverage()));
		double ES = ESI * ESC;
		EstingSystemLoad = ES;
		CountFireFightingDiscount();
	}

	public void CountPlantFireBrigadeLoad() {
		double PFB = Double.parseDouble(propertyOnshoreMeasure
				.getPlantFireBrigadeType().get(
						propertyOnshoreRating.getPlantFireBrigadeType()));
		PlantFireBrigadeLoad = PFB;
		CountFireFightingDiscount();
	}

	public void CountPublicFirebrigadeLoad() {
		String a = propertyOnshoreRating.getFamiliarityWithPremises();
		String b = propertyOnshoreRating.getResponseTime();
		if (a.equals("yes")) {
			if (b.equals("5")) {
				PublicFireBrigadeLoad = 0.12;
			} else {
				if (b.equals("10")) {
					PublicFireBrigadeLoad = 0.08;
				} else {
					if (b.equals("15")) {
						PublicFireBrigadeLoad = 0.04;
					} else {
						PublicFireBrigadeLoad = 0;
					}
				}
			}

		} else {
			if (b.equals("5")) {
				PublicFireBrigadeLoad = 0.08;
			} else {
				if (b.equals("10")) {
					PublicFireBrigadeLoad = 0.05;
				} else {
					if (b.equals("15")) {
						PublicFireBrigadeLoad = 0.02;
					} else {
						PublicFireBrigadeLoad = 0;
					}
				}
			}
		}
		CountFireFightingDiscount();
	}

	public void CountFireDetectionLoad() {

		String a = propertyOnshoreRating.getFireDetectionInstallaion();
		String b = propertyOnshoreRating.getLink();

		if (a.equals("autowith")) {
			if (b.equals("linkedtoplant")) {
				FireDetectionLoad = 0.2;
			} else {
				if (b.equals("linkedtopublic")) {
					FireDetectionLoad = 0.15;
				} else {
					if (b.equals("linkedtoother")) {
						FireDetectionLoad = 0.1;
					} else {
						if (b.equals("public")) {
							FireDetectionLoad = 0.02;
						} else {
							if (b.equals("none")) {
								FireDetectionLoad = 0;
							} else {
								System.out.println("missmatch type");
							}
						}
					}
				}
			}
		} else {
			if (a.equals("autowithout")) {
				if (b.equals("linkedtoplant")) {
					FireDetectionLoad = 0.15;
				} else {
					if (b.equals("linkedtopublic")) {
						FireDetectionLoad = 0.1;
					} else {
						if (b.equals("linkedtoother")) {
							FireDetectionLoad = 0.05;
						} else {
							if (b.equals("public")) {
								FireDetectionLoad = 0.02;
							} else {
								if (b.equals("none")) {
									FireDetectionLoad = 0;
								} else {
									System.out.println("missmatch type");
								}
							}
						}
					}
				}

			} else {
				if (a.equals("partautomated")) {
					if (b.equals("linkedtoplant")) {
						FireDetectionLoad = 0.05;
					} else {
						if (b.equals("linkedtopublic")) {
							FireDetectionLoad = 0.0375;
						} else {
							if (b.equals("linkedtoother")) {
								FireDetectionLoad = 0.025;
							} else {
								if (b.equals("public")) {
									FireDetectionLoad = 0.02;
								} else {
									if (b.equals("none")) {
										FireDetectionLoad = 0;
									} else {
										System.out.println("missmatch type");
									}
								}
							}
						}
					}
				} else {
					if (a.equals("none")) {
						if (b.equals("linkedtoplant")) {
							FireDetectionLoad = 0;
						} else {
							if (b.equals("linkedtopublic")) {
								FireDetectionLoad = 0;
							} else {
								if (b.equals("linkedtoother")) {
									FireDetectionLoad = 0;
								} else {
									if (b.equals("public")) {
										FireDetectionLoad = 0;
									} else {
										if (b.equals("none")) {
											FireDetectionLoad = 0;
										} else {
											System.out
													.println("missmatch type");
										}
									}
								}
							}
						}
					} else {
						System.out.println("error type not found");
					}
				}
			}
		}
		CountFireFightingDiscount();
	}

	// BI
	public void CountBIPreventionDiscount() {
		double Achieved;
		BILossPrevenstionAssesment = Double.parseDouble(propertyOnshoreMeasure
				.getAssesment()
				.get(propertyOnshoreRating.getBackupFacilities()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getBackupFacilities()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getBussinessPlanContinuity()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getBusinessContinuityPlan()) / 100)
				+ Double.parseDouble(propertyOnshoreMeasure.getAssesment().get(
						propertyOnshoreRating.getBottleneckLossMinimised()))
				* (Double.parseDouble(propertyOnshoreMeasure
						.getBottleneckLossMinimised()) / 100);
		BI_Prevention_Discount = (BILossPrevenstionAssesment / 2) * -0.15;
		BIload = ((1 + BI_Prevention_Discount) * (1 + FireMeasureTotal)
				* (1 + (lossRatioLoad / 100)) * (1 + (ConstructionClassLoad / 100))) - 1;
		for (Assets as : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					as.getCountry(), as.getBI_Curve()).getExposure();
			as.setBI_Base_Premium(as.getBI_Rate() * as.getBI_SI() / 100);
			as.setBI_PML_Adjusted_Premium(MBBEFD(
					(as.getBI_PML() / as.getBI_SI()), Exposure)
					* as.getBI_Base_Premium());

			as.setBI_Discount_premium((1 + BIload)
					* as.getBI_PML_Adjusted_Premium());
			as.setBI_Dedpremium(as.getBI_Discount_premium());
		}

	}

	// MBandMLOP
	public void CountMbandMloploead() {
		MBandMlOPLoad = ((1 + Double.parseDouble((propertyOnshoreMeasure
				.getMachineMake().get(propertyOnshoreRating.getMachineMake()))) / 100)
				* (1 + Double.parseDouble(propertyOnshoreMeasure.getPlantAge()
						.get(propertyOnshoreRating.getPlantAge())) / 100)
				* (1 + Double.parseDouble(propertyOnshoreMeasure.getCapacity()
						.get(propertyOnshoreRating.getCapacity())) / 100)
				* (1 + Double.parseDouble(propertyOnshoreMeasure
						.getMaintenanceQuality().get(
								propertyOnshoreRating.getMaintenanceQuality())) / 100) * (1 + Double
				.parseDouble(propertyOnshoreMeasure.getAvaibilityOfParts().get(
						propertyOnshoreRating.getAvailabilityOfParts())) / 100)) - 1;

		for (Assets as : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					as.getCountry(), as.getPD_Curve()).getExposure();

			as.setMB_Discount_premium((1 + MBandMlOPLoad)
					* as.getMB_PML_Adjusted_Premium());

			double DedBLY1MB = (1 - MBBEFD(
					(propertyOnshoreRating.getPD_Deductible_amount()
							/ (propertyOnshoreRating.getPD_deductible_perc() / 100) / as
							.getMB_PML()), Exposure))
					* as.getMB_Discount_premium()
					* (1 - (propertyOnshoreRating.getPD_deductible_perc() / 100));

			double DedBLY2MB = ((MBBEFD(
					propertyOnshoreRating.getPD_Deductible_amount()
							/ (propertyOnshoreRating.getPD_deductible_perc() / 100)
							/ as.getMB_PML(), Exposure)) - (MBBEFD(
					(propertyOnshoreRating.getPD_Deductible_amount() / as
							.getPD_PML()),
					Exposure)))
					* as.getMB_Discount_premium();

			as.setMB_Dedpremium((DedBLY1MB + DedBLY2MB));

			as.setMlop_Discount_premium((1 + MBandMlOPLoad)
					* as.getMlop_PML_Adjusted_Premium());

			as.setMlop_Dedpremium(as.getMlop_Discount_premium());
		}

	}

	// Risk Assesment End

	// Save Rating begin
	public void SaveRating() {
		propertyOnshoreRatingServicesLocal.Add(propertyOnshoreRating);
		FacesMessage msg = new FacesMessage("Rating Saved", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Save Rating end

	// SI section breakdown calculation begin

	// PD
	public double calculateBasePropertyDamage() {
		if (calculatePropertyTotalSumInsured() == 0) {
			return 0;
		}
		double result = 0;
		for (Assets a : assetsList) {
			result = (result + a.getPD_Base_Premium());

		}
		return result / (calculatePropertyTotalSumInsured()) * 100;

	}

	public double calculatePropertyTotalSumInsured() {

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getPD_SI();
		}
		return result;
	}

	public double calculatePropertyTotalPML() {
		if (calculatePropertyTotalSumInsured() == 0) {
			return 0;
		}

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getPD_PML();
		}
		return result;
	}

	public double CalculatePDPMLAdjustedRate() {
		if (calculatePropertyTotalSumInsured() == 0) {
			return 0;
		}

		double resultMBD = 0;
		double totSI = 0;
		for (Assets a : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getPD_Curve()).getExposure();
			double r = a.getPD_PML() / a.getPD_SI();
			double t = MBBEFD(r, Exposure) * (a.getPD_Rate() * a.getPD_SI());
			resultMBD = resultMBD + t;
			totSI = totSI + a.getPD_SI();
		}
		return resultMBD / totSI;
	}

	public double CalculateTotalPDDiscount() {
		if (calculatePropertyTotalSumInsured() == 0) {
			return 0;
		}
		double totSI = 0;
		double result = 0;

		for (Assets a : assetsList) {
			result = result + Math.round(a.getPD_Discount_premium());
			totSI = totSI + Math.round(a.getPD_SI());

		}
		double toti = (result / totSI) * 100;
		return toti;
	}

	public double CalculateTotalPDPremium() {
		if (calculatePropertyTotalSumInsured() == 0) {
			return 0;
		}

		double result = 0;

		for (Assets a : assetsList) {
			if (propertyOnshoreRating.getBasisOfAcceptance().equals("qs")) {
				result = result + a.getPD_DeductibleB();
			} else {
				result = result + a.getPD_Discount_premium();
			}

		}

		return result;

	}

	// BI
	public double calculateBaseBI() {
		if (calculateBITotalSumInsured() == 0) {
			return 0;
		}

		double result = 0;

		for (Assets a : assetsList) {
			result = result + a.getBI_Base_Premium();

		}
		return (result / calculateBITotalSumInsured()) * 100;

	}

	public double calculateBITotalPML() {
		if (calculateBITotalSumInsured() == 0) {
			return 0;
		}

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getBI_PML();
		}
		return result;
	}

	public double calculateBITotalSumInsured() {

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getBI_SI();
		}
		return result;
	}

	public double CalculateBIPMLAdjustedRate() {
		if (calculateBITotalSumInsured() == 0) {
			return 0;
		}

		double resultMBD = 0;
		double totSI = 0;
		for (Assets a : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getBI_Curve()).getExposure();
			double r = a.getBI_PML() / a.getBI_SI();
			double t = MBBEFD(r, Exposure) * (a.getBI_Rate() * a.getBI_SI());
			resultMBD = resultMBD + t;
			totSI = totSI + a.getBI_SI();
		}
		return resultMBD / totSI;
	}

	public double CalculateTotalBIDiscount() {
		if (calculateBITotalSumInsured() == 0) {
			return 0;
		}
		double totSI = 0;
		double result = 0;

		for (Assets a : assetsList) {
			result = result + Math.round(a.getBI_Discount_premium());
			totSI = totSI + Math.round(a.getBI_SI());

		}
		double toti = (result / totSI) * 100;
		return toti;
	}

	public double CalculateTotalBIPremium() {
		if (calculateBITotalSumInsured() == 0) {
			return 0;
		}
		double result = 0;

		for (Assets a : assetsList) {
			if (propertyOnshoreRating.getBasisOfAcceptance().equals("qs")) {
				result = result + a.getBI_Dedpremium();
			} else {
				result = result + a.getBI_Discount_premium();
			}

		}

		return result;
	}

	// MB
	public double calculateMBTotalSumInsured() {

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getMB_SI();
		}
		return result;
	}

	public double calculateMBTotalPML() {
		if (calculateMBTotalSumInsured() == 0) {
			return 0;
		}

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getMB_PML();
		}
		return result;
	}

	public double calculateBaseMB() {
		if (calculateMBTotalSumInsured() == 0) {
			return 0;
		}

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getMB_Base_Premium();
		}
		return (result / calculateMBTotalSumInsured()) * 100;
	}

	public double CalculateMBPMLAdjustedRate() {
		if (calculateMBTotalSumInsured() == 0) {
			return 0;
		}

		double resultMBD = 0;
		double totSI = 0;
		for (Assets a : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					assets.getCountry(), assets.getBI_Curve()).getExposure();
			double r = a.getMB_PML() / a.getMB_SI();
			double t = MBBEFD(r, Exposure) * (a.getMB_Rate() * a.getMB_SI());
			resultMBD = resultMBD + t;
			totSI = totSI + a.getMB_SI();
		}
		return resultMBD / totSI;
	}

	public double CalculateTotalMBDiscount() {
		if (calculateMBTotalSumInsured() == 0) {
			return 0;
		}
		double totSI = 0;
		double result = 0;

		for (Assets a : assetsList) {
			result = result + Math.round(a.getMB_Discount_premium());
			totSI = totSI + Math.round(a.getMB_SI());

		}
		double toti = (result / totSI) * 100;
		return toti;
	}

	public double CalculateTotalMBPremium() {
		if (calculateMBTotalSumInsured() == 0) {
			return 0;
		}
		double result = 0;

		for (Assets a : assetsList) {
			if (propertyOnshoreRating.getBasisOfAcceptance().equals("qs")) {
				result = result + a.getMB_Dedpremium();
			} else {
				result = result + a.getMB_Discount_premium();
			}

		}

		return result;
	}

	// MLOP
	public double calculateBaseMLop() {
		if (calculateMlopTotalSI() == 0) {

			return 0;
		}

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getMlop_Base_Premium();
		}
		return (result / calculateMlopTotalSI()) * 100;
	}

	public double calculateMlopTotalPML() {
		if (calculateMlopTotalSI() == 0) {
			return 0;
		}

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getMlop_PML();
		}
		return result;
	}

	public double calculateMlopTotalSI() {

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getMlop_SI();
		}
		return result;
	}

	public double CalculateMlopPMLAdjustedRate() {
		if (calculateMlopTotalSI() == 0) {
			return 0;
		}

		double resultMBD = 0;
		double totSI = 0;
		for (Assets a : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getBI_Curve()).getExposure();
			double r = a.getMlop_PML() / a.getMlop_SI();
			double t = MBBEFD(r, Exposure)
					* (a.getMlop_Rate() * a.getMlop_SI());
			resultMBD = resultMBD + t;
			totSI = totSI + a.getMlop_SI();
		}
		return resultMBD / totSI;
	}

	public double CalculateTotalMlopDiscount() {
		if (calculateMlopTotalSI() == 0) {
			return 0;
		}
		double totSI = 0;
		double result = 0;

		for (Assets a : assetsList) {

			result = result + Math.round(a.getMlop_Discount_premium());
			totSI = totSI + Math.round(a.getMlop_SI());

		}
		double toti = (result / totSI) * 100;
		return toti;
	}

	public double CalculateTotalMlopPremium() {
		if (calculateMlopTotalSI() == 0) {
			return 0;
		}
		double result = 0;

		for (Assets a : assetsList) {
			result = result + a.getMlop_Dedpremium();

		}

		return result;
	}

	// STOCK
	public double calculateStockTotalSI() {

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getStock_SI();
		}
		return result;
	}

	public double calculateBaseStock() {
		if (calculateStockTotalSI() == 0) {
			return 0;
		}

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getStock_Base_Premium();
		}
		return (result / calculateStockTotalSI()) * 100;
	}

	public double calculateStockTotalPML() {
		if (calculateStockTotalSI() == 0) {
			return 0;
		}

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getStock_PML();
		}
		return result;
	}

	public double CalculateStockPMLAdjustedRate() {
		if (calculateStockTotalSI() == 0) {
			return 0;
		}
		double resultMBD = 0;
		double totSI = 0;
		for (Assets a : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getBI_Curve()).getExposure();
			double r = a.getStock_PML() / a.getStock_SI();
			double t = MBBEFD(r, Exposure)
					* (a.getStock_RATE() * a.getStock_SI());
			resultMBD = resultMBD + t;
			totSI = totSI + a.getStock_SI();
		}
		return resultMBD / totSI;
	}

	public double CalculateTotalStockDiscount() {
		if (calculateMlopTotalSI() == 0) {
			return 0;
		}
		double totSI = 0;
		double result = 0;

		for (Assets a : assetsList) {
			result = result + Math.round(a.getStock_Discount_premium());
			totSI = totSI + Math.round(a.getStock_SI());

		}
		double finalres = (result / totSI) * 100;
		return finalres;
	}

	public double CalculateTotalStockPremium() {
		if (calculateStockTotalSI() == 0) {
			return 0;
		}
		double result = 0;

		for (Assets a : assetsList) {
			if (propertyOnshoreRating.getBasisOfAcceptance().equals("qs")) {
				result = result + a.getStock_Dedpremium();
			} else {
				result = result + a.getStock_Discount_premium();
			}

		}

		return result;
	}

	// OTHER

	public double calculateOtherTotalSI() {

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getOTHER_SI();
		}
		return result;
	}

	public double calculateBaseOther() {
		if (calculateOtherTotalSI() == 0) {
			return 0;
		}

		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getOTHER_Base_Premium();
		}

		return (result / calculateOtherTotalSI()) * 100;
	}

	public double calculateOtherTotalPML() {
		if (calculateOtherTotalSI() == 0) {
			return 0;
		}
		double result = 0;
		for (Assets a : assetsList) {
			result = result + a.getOTHER_PML();
		}
		return result;
	}

	public double CalculateOtherPMLAdjustedRate() {
		if (calculateOtherTotalSI() == 0) {
			return 0;
		}
		double resultMBD = 0;
		double totSI = 0;
		for (Assets a : assetsList) {
			double Exposure = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getBI_Curve()).getExposure();
			double r = a.getOTHER_PML() / a.getOTHER_SI();
			double t = MBBEFD(r, Exposure)
					* (a.getOTHER_Rate() * a.getOTHER_SI());
			resultMBD = resultMBD + t;
			totSI = totSI + a.getOTHER_SI();
		}

		return resultMBD / totSI;
	}

	public double CalculateTotalOtherDiscount() {
		if (calculateMlopTotalSI() == 0) {
			return 0;
		}
		double totSI = 0;
		double result = 0;

		for (Assets a : assetsList) {
			result = result + Math.round(a.getOTHER_Discount_premium());
			totSI = totSI + Math.round(a.getOTHER_SI());

		}
		double toti = (result / totSI) * 100;
		return toti;
	}

	public double CalculateTotalOtherPremium() {
		if (calculateOtherTotalSI() == 0) {
			return 0;
		}
		double result = 0;

		for (Assets a : assetsList) {
			if (propertyOnshoreRating.getBasisOfAcceptance().equals("qs")) {
				result = result + a.getOTHER_Dedpremium();
			} else {
				result = result + a.getOTHER_Discount_premium();
			}

		}

		return result;
	}

	// tolal

	public double GetTotalPml() {
		return calculateBITotalPML() + calculateMBTotalPML()
				+ calculateOtherTotalPML() + calculateStockTotalPML()
				+ calculatePropertyTotalPML() + calculateMlopTotalPML();
	}

	public double GetTotalBaseRAtes() {
		return (calculateBaseBI() + calculateBaseMB() + calculateBaseMLop()
				+ calculateBaseOther() + calculateBasePropertyDamage() + calculateBaseStock()) / 6;
	}

	public double GetTotalAdjustedRAtes() {
		return (CalculateBIPMLAdjustedRate() + CalculateMBPMLAdjustedRate()
				+ CalculateMlopPMLAdjustedRate()
				+ CalculateOtherPMLAdjustedRate()
				+ CalculatePDPMLAdjustedRate() + CalculateStockPMLAdjustedRate()) / 6;
	}

	public double GetTotalSI() {
		return calculateBITotalSumInsured() + calculateMBTotalSumInsured()
				+ calculateOtherTotalSI() + calculateStockTotalSI()
				+ calculatePropertyTotalSumInsured() + calculateMlopTotalSI();
	}

	public double GetTotalPremium() {
		return CalculateTotalBIPremium() + CalculateTotalPDPremium()
				+ CalculateTotalOtherPremium() + CalculateTotalStockPremium()
				+ CalculateTotalMBPremium() + CalculateTotalMlopPremium();
	}

	// SI section breakdown calculation end

	// ASSETS MANAGEMENT BEGIN
	public void AddAsset() {
		if (project3.getQuoted_Date() != null) {
			FacesMessage msg = new FacesMessage("Cant Modify a quoted rating");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			assets = new Assets();
			assets.setIdproject(project3.getId());
			if (!propertyOnshoreRating.getClassof().equals(null)) {

				assets.setCOB(propertyOnshoreRating.getClassof());
				assets.setOccupancy(propertyOnshoreRating.getOccupancy());
			}

			assetsServicesLocal.AddAsset(assets);
			forrmDisplayed = false;
			assetsList = assetsServicesLocal.GetAssetsByIdProject(project3
					.getId());
		}

	}

	public void onRowEdit(RowEditEvent event) {
		assetChange();
		FacesMessage msg = new FacesMessage("Asset Modified", "Asset id :"
				+ assets.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void assetChange() {
		double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
				assets.getCountry(), assets.getPD_Curve()).getExposure();
		double ExposureBI = exposureScaleServicesLocal.GetExposureScale(
				assets.getCountry(), assets.getBI_Curve()).getExposure();

		// PD
		double aa = (assets.getPD_PML_RATE() / 100) * (1 + PMLErrorLoad);
		double a = Math.min(aa, 1);
		Double Discount = (1 + FireMeasureTotal) * (1 + (lossRatioLoad / 100))
				* (1 + (ConstructionClassLoad / 100)) - 1;
		if (assets.getPD_SI() == 0) {
			assets.setPD_PML(0);
			assets.setPD_Rate((1 + assets.getRate_Adj())
					* (occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getPD());
		} else {
			assets.setPD_PML(a * assets.getPD_SI());

			assets.setPD_Rate((1 + assets.getRate_Adj())
					* (occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getPD());

			assets.setPD_Base_Premium(assets.getPD_Rate() * assets.getPD_SI()
					/ 100);

			assets.setPD_PML_Adjusted_Premium(MBBEFD(
					(assets.getPD_PML() / assets.getPD_SI()), ExposurePD)
					* assets.getPD_Base_Premium());

			assets.setPD_Discount_premium((1 + Discount)
					* assets.getPD_PML_Adjusted_Premium());

			if (propertyOnshoreRating.getPD_deductible_perc() == 0) {
				double somme1 = MBBEFD(
						propertyOnshoreRating.getPD_Deductible_amount()
								/ assets.getPD_PML(), ExposurePD);
				double DedBLY1MB = (1 - somme1)
						* assets.getPD_Discount_premium();
				assets.setPD_DeductibleB(DedBLY1MB);
			} else {

				double DedBLY1 = (1 - MBBEFD(
						(propertyOnshoreRating.getPD_Deductible_amount()
								/ (propertyOnshoreRating.getPD_deductible_perc() / 100) / assets
								.getPD_PML()), ExposurePD))
						* assets.getPD_Discount_premium()
						* (1 - (propertyOnshoreRating.getPD_deductible_perc() / 100));

				double DedBLY2 = ((MBBEFD(
						propertyOnshoreRating.getPD_Deductible_amount()
								/ (propertyOnshoreRating.getPD_deductible_perc() / 100)
								/ assets.getPD_PML(), ExposurePD)) - (MBBEFD(
						(propertyOnshoreRating.getPD_Deductible_amount() / assets
								.getPD_PML()), ExposurePD)))
						* assets.getPD_Discount_premium();
				assets.setPD_DeductibleB((DedBLY1 + DedBLY2));
			}
		}

		// MB
		double bb = (assets.getMB_PML_RATE() / 100) * (1 + PMLErrorLoad);
		double b = Math.min(bb, 1);
		if (assets.getMB_SI() == 0) {
			assets.setMB_PML(0);
			assets.setMB_Rate((1 + assets.getRate_Adj())
					* (occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getMB());
		} else {
			assets.setMB_PML(b * assets.getMB_SI());
			assets.setMB_Rate((1 + assets.getRate_Adj())
					* (occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getMB());

			assets.setMB_Base_Premium(assets.getMB_Rate() * assets.getMB_SI()
					/ 100);
			assets.setMB_PML_Adjusted_Premium(MBBEFD(
					(assets.getMB_PML() / assets.getMB_SI()), ExposureBI)
					* assets.getMB_Base_Premium());

			assets.setMB_Discount_premium((1 + MBandMlOPLoad)
					* assets.getMB_PML_Adjusted_Premium());

			double DedBLY1MB = (1 - MBBEFD(
					(propertyOnshoreRating.getPD_Deductible_amount()
							/ (propertyOnshoreRating.getPD_deductible_perc() / 100) / assets
							.getMB_PML()), ExposurePD))
					* assets.getMB_Discount_premium()
					* (1 - (propertyOnshoreRating.getPD_deductible_perc() / 100));

			double DedBLY2MB = ((MBBEFD(
					propertyOnshoreRating.getPD_Deductible_amount()
							/ (propertyOnshoreRating.getPD_deductible_perc() / 100)
							/ assets.getMB_PML(), ExposurePD)) - (MBBEFD(
					(propertyOnshoreRating.getPD_Deductible_amount() / assets
							.getPD_PML()),
					ExposurePD)))
					* assets.getMB_Discount_premium();

			assets.setMB_Dedpremium((DedBLY1MB + DedBLY2MB));
		}

		// BI
		double cc = (assets.getBI_PML_RATE() / 100) * (1 + PMLErrorLoad);
		double c = Math.min(cc, 1);

		if (assets.getBI_SI() == 0) {
			assets.setBI_PML(0);
			assets.setBI_Rate((1 + assets.getRate_Adj())
					* ((occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getBI())
					* (1 + BiindemnityLoad * 0.01));
		} else {
			assets.setBI_PML(c * assets.getBI_SI());
			assets.setBI_Rate((1 + assets.getRate_Adj())
					* ((occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getBI())
					* (1 + BiindemnityLoad * 0.01));

			assets.setBI_Base_Premium(assets.getBI_Rate() * assets.getBI_SI()
					/ 100);

			assets.setBI_PML_Adjusted_Premium(MBBEFD(
					(assets.getBI_PML() / assets.getBI_SI()), ExposureBI)
					* assets.getBI_Base_Premium());

			assets.setBI_Discount_premium((1 + BIload)
					* assets.getBI_PML_Adjusted_Premium());

			assets.setBI_Dedpremium(assets.getBI_Discount_premium());
		}

		// MLOP
		double ff = (assets.getMlop_PML_RATE() / 100) * (1 + PMLErrorLoad);
		double f = Math.min(ff, 1);
		if (assets.getMlop_SI() == 0) {
			assets.setMlop_PML(0);
			assets.setMlop_Rate((1 + assets.getRate_Adj())
					* ((occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getMLOP())
					* (1 + MlopindemnityLoad * 0.01));
		} else {
			assets.setMlop_PML(f * assets.getMlop_SI());
			assets.setMlop_Rate((1 + assets.getRate_Adj())
					* ((occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getMLOP())
					* (1 + MlopindemnityLoad * 0.01));
			assets.setMlop_Base_Premium(assets.getMlop_Rate()
					* assets.getMlop_SI() / 100);

			assets.setMlop_PML_Adjusted_Premium(MBBEFD(
					(assets.getMlop_PML() / assets.getMlop_SI()), ExposureBI)
					* assets.getMlop_Base_Premium());

			assets.setMlop_Discount_premium((1 + MBandMlOPLoad)
					* assets.getMlop_PML_Adjusted_Premium());

			assets.setMlop_Dedpremium(assets.getMlop_Discount_premium());
		}

		// STOCK
		double ee = (assets.getStock_PML_RATE() / 100) * (1 + PMLErrorLoad);
		double e = Math.min(ee, 1);
		if (assets.getStock_SI() == 0) {

			assets.setStock_PML(0);
			assets.setStock_RATE((1 + assets.getRate_Adj())
					* (occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getPD());
		} else {

			assets.setStock_PML(e * assets.getStock_SI());

			assets.setStock_RATE((1 + assets.getRate_Adj())
					* (occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getPD());
			assets.setStock_Base_Premium(assets.getStock_RATE()
					* assets.getStock_SI() / 100);
			assets.setStock_PML_Adjusted_Premium(MBBEFD(
					(assets.getStock_PML() / assets.getStock_SI()), ExposureBI)
					* assets.getStock_Base_Premium());

			assets.setStock_Discount_premium((1 + Discount)
					* assets.getStock_PML_Adjusted_Premium());

			assets.setStock_Dedpremium(assets.getStock_Discount_premium()
					* (1 - MBBEFD(
							(propertyOnshoreRating.getStock_amount() / assets
									.getStock_PML()), ExposurePD)));
		}

		// OTHER
		double dd = (assets.getOTHER_PML_RATE() / 100) * (1 + PMLErrorLoad);
		double d = Math.min(dd, 1);
		if (assets.getOTHER_SI() == 0) {
			assets.setOTHER_PML(0);
			assets.setOTHER_Rate((1 + assets.getRate_Adj())
					* (occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getPD());
		} else {
			assets.setOTHER_PML(d * assets.getOTHER_SI());
			assets.setOTHER_Rate((1 + assets.getRate_Adj())
					* (occupanciesServicesLocal.GetOccupByfilters(
							assets.getCOB(), assets.getOccupancy())).getPD());

			assets.setOTHER_Base_Premium(assets.getOTHER_Rate()
					* assets.getOTHER_SI() / 100);
			assets.setOTHER_PML_Adjusted_Premium(MBBEFD(
					(assets.getOTHER_PML() / assets.getOTHER_SI()), ExposureBI)
					* assets.getOTHER_Base_Premium());

			assets.setOTHER_Discount_premium((1 + Discount)
					* assets.getOTHER_PML_Adjusted_Premium());

			assets.setOTHER_Dedpremium(assets.getOTHER_Discount_premium()
					* (1 - MBBEFD(
							(propertyOnshoreRating.getOther_amount() / assets
									.getOTHER_PML()), ExposurePD)));

			assets.setEML(assets.getPD_PML() + assets.getBI_PML()
					+ assets.getStock_PML() + assets.getOTHER_PML());
		}

		// OPD
		double min1 = Math.min(1,
				((propertyOnshoreRating.getOPD_lim() + propertyOnshoreRating
						.getOPD_ded()) / assets.getEML()));
		double min2 = Math.min(1,
				propertyOnshoreRating.getOPD_ded() / assets.getEML());

		assets.setOPDFac(MBBEFD(min1, ExposurePD) - MBBEFD(min2, ExposurePD));
		// LY1
		double min1LY1 = Math.min(1,
				((propertyOnshoreRating.getLY1_lim() + propertyOnshoreRating
						.getLY1_ded()) / assets.getEML()));
		double min2LY1 = Math.min(1, propertyOnshoreRating.getLY1_ded()
				/ assets.getEML());
		assets.setLY1Fac(MBBEFD(min1LY1, ExposurePD)
				- MBBEFD(min2LY1, ExposurePD));
		// LY2
		double min1LY2 = Math.min(1,
				((propertyOnshoreRating.getLY2_lim() + propertyOnshoreRating
						.getLY2_ded()) / assets.getEML()));
		double min2LY2 = Math.min(1, propertyOnshoreRating.getLY2_ded()
				/ assets.getEML());
		assets.setLY2Fac(MBBEFD(min1LY2, ExposurePD)
				- MBBEFD(min2LY2, ExposurePD));
		// LY3
		double min1LY3 = Math.min(1,
				((propertyOnshoreRating.getLY3_lim() + propertyOnshoreRating
						.getLY3_ded()) / assets.getEML()));
		double min2LY3 = Math.min(1, propertyOnshoreRating.getLY3_ded()
				/ assets.getEML());
		assets.setLY3Fac(MBBEFD(min1LY3, ExposurePD)
				- MBBEFD(min2LY3, ExposurePD));
		// LY4
		double min1LY4 = Math.min(1,
				((propertyOnshoreRating.getLY4_lim() + propertyOnshoreRating
						.getLY4_ded()) / assets.getEML()));
		double min2LY4 = Math.min(1, propertyOnshoreRating.getLY4_ded()
				/ assets.getEML());
		assets.setLY4Fac(MBBEFD(min1LY4, ExposurePD)
				- MBBEFD(min2LY4, ExposurePD));
		// LY5
		double min1LY5 = Math.min(1,
				((propertyOnshoreRating.getLY5_lim() + propertyOnshoreRating
						.getLY5_ded()) / assets.getEML()));
		double min2LY5 = Math.min(1, propertyOnshoreRating.getLY5_ded()
				/ assets.getEML());
		assets.setLY5Fac(MBBEFD(min1LY5, ExposurePD)
				- MBBEFD(min2LY5, ExposurePD));
		assetsServicesLocal.AddAsset(assets);
		assetsList = assetsServicesLocal.GetAssetsByIdProject(project3.getId());
		GetCommPrice();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", "ff");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void DoDelete() {
		if (project3.getQuoted_Date() != null) {
			FacesMessage msg = new FacesMessage("Cant Modify a quoted rating");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			assetsServicesLocal.DeleteAsset(assets);
			assetsList = assetsServicesLocal.GetAssetsByIdProject(project3
					.getId());
			FacesMessage msg = new FacesMessage("Asset Deleted", "Asset id :"
					+ assets.getId());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void DoCancel() {
		assets = new Assets();
		forrmDisplayed = false;
	}

	public void OnRowSelected() {
		forrmDisplayed = true;
	}

	public List<String> COBChangeByParam(String COBz) {
		Occupancies2 = occupanciesServicesLocal.GetOccupanciesByCob(COBz);
		return Occupancies2;
	}

	// ASSETS MANAGEMENT END
	// RATING MAIN FUNCTION START
	public double MBBEFD(double X, double c) {
		double b;
		double g;
		double z;
		double MBDEFD1 = 0;
		b = Math.exp(3.1 - 0.15 * (1 + c) * c);
		g = Math.exp((0.78 + 0.12 * c) * c);
		z = b * g;
		if (X >= 1) {
			MBDEFD1 = 1;
		} else {
			if (g == 1 || b == 0) {
				MBDEFD1 = X;
			} else {
				if (g > 1 && b == 1) {
					MBDEFD1 = (Math.log(1 + (g - 1) * X)) / (Math.log(g));
				} else {
					if (z == 1 && g > 1) {
						MBDEFD1 = (1 - Math.pow(b, X)) / (1 - b);
					} else {
						if (b > 0 && b != 1 && z != 1 && g > 1) {

							MBDEFD1 = Math.log(((g - 1) * b + ((1 - z) * Math
									.pow(b, X))) / (1 - b))
									/ (Math.log(z));

						}
					}
				}
			}
		}
		if (MBDEFD1 < 0) {
			return (MBDEFD1 * (-1));
		} else {
			return MBDEFD1;
		}

	}

	// RATING FUNCTION END

	// Summary Calculation start
	public double GetCommPrice() {
		if (propertyOnshoreRating.getProposedShare() == 0) {
			propertyOnshoreRating.setMaxLiability(0);
		}

		propertyOnshoreRating.setMaxLiability((propertyOnshoreRating
				.getProposedShare() / 100) * GetTotalPml());

		if (propertyOnshoreRating.getBasisOfAcceptance().equals("qs")) {
			propertyOnshoreRating.setRatingFactor(propertyOnshoreRating
					.getProposedShare() / 100 * GetTotalPremium());
		} else {
			propertyOnshoreRating.setRatingFactor(propertyOnshoreRating
					.getProposedShare() / 100 * CalculateXLTotalPremium());
		}
		propertyOnshoreRating.setNat_Catastrophe(propertyOnshoreRating
				.getRatingFactor() * 0.1);

		GetRiskPrice();

		propertyOnshoreRating.setExpenses(0.08 * propertyOnshoreRating
				.getTotalRiskPrice());
		propertyOnshoreRating.setCostOfCapital(0.03 * propertyOnshoreRating
				.getTotalRiskPrice());
		propertyOnshoreRating.setReinsuranceCosts(0.05 * propertyOnshoreRating
				.getTotalRiskPrice());

		propertyOnshoreRating.setTotalCosts(propertyOnshoreRating
				.getReinsuranceCosts()
				+ propertyOnshoreRating.getCostOfCapital()
				+ propertyOnshoreRating.getExpenses());

		propertyOnshoreRating.setCommercialPrice((propertyOnshoreRating
				.getTotalCosts() + propertyOnshoreRating.getTotalRiskPrice())
				/ (1 - propertyOnshoreRating.getProfitMargin() / 100)
				/ (1 - propertyOnshoreRating.getBrokerage() / 100));
		return propertyOnshoreRating.getCommercialPrice();

	}

	public void GetRiskPrice() {

		propertyOnshoreRating
				.setTotalRiskPrice(propertyOnshoreRating.getNat_Catastrophe()
						+ propertyOnshoreRating.getRatingFactor());
	}

	// Summary Calculation end
	// Deductible rating start
	public void PD_dedChange() {

		for (Assets as : assetsList) {
			double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
					as.getCountry(), as.getPD_Curve()).getExposure();
			double ExposureBI = exposureScaleServicesLocal.GetExposureScale(
					as.getCountry(), as.getBI_Curve()).getExposure();
			if (propertyOnshoreRating.getPD_deductible_perc() == 0) {
				double somme1 = MBBEFD(
						propertyOnshoreRating.getPD_Deductible_amount()
								/ as.getPD_PML(), ExposurePD);
				double DedBLY1MB = (1 - somme1) * as.getPD_Discount_premium();
				as.setPD_DeductibleB(DedBLY1MB);
			} else {
				// PD
				double DedBLY1 = (1 - MBBEFD(
						(propertyOnshoreRating.getPD_Deductible_amount()
								/ (propertyOnshoreRating.getPD_deductible_perc() / 100) / as
								.getPD_PML()), ExposurePD))
						* as.getPD_Discount_premium()
						* (1 - (propertyOnshoreRating.getPD_deductible_perc() / 100));

				double DedBLY2 = ((MBBEFD(
						propertyOnshoreRating.getPD_Deductible_amount()
								/ (propertyOnshoreRating.getPD_deductible_perc() / 100)
								/ as.getPD_PML(), ExposurePD)) - (MBBEFD(
						(propertyOnshoreRating.getPD_Deductible_amount() / as
								.getPD_PML()),
						ExposurePD)))
						* as.getPD_Discount_premium();
				as.setPD_DeductibleB((DedBLY1 + DedBLY2));
				// MB
				double DedBLY1MB = (1 - MBBEFD(
						(propertyOnshoreRating.getPD_Deductible_amount()
								/ (propertyOnshoreRating.getPD_deductible_perc() / 100) / as
								.getMB_PML()), ExposureBI))
						* as.getMB_Discount_premium()
						* (1 - (propertyOnshoreRating.getPD_deductible_perc() / 100));

				double DedBLY2MB = ((MBBEFD(
						propertyOnshoreRating.getPD_Deductible_amount()
								/ (propertyOnshoreRating.getPD_deductible_perc() / 100)
								/ as.getMB_PML(), ExposureBI)) - (MBBEFD(
						(propertyOnshoreRating.getPD_Deductible_amount() / as
								.getPD_PML()),
						ExposureBI)))
						* as.getMB_Discount_premium();

				as.setMB_Dedpremium((DedBLY1MB + DedBLY2MB));

			}

		}

	}

	public void Stock_Deductible_Change() {

		for (Assets r : assetsList) {
			double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
					r.getCountry(), r.getPD_Curve()).getExposure();
			r.setStock_Dedpremium(r.getStock_Discount_premium()
					* (1 - MBBEFD((propertyOnshoreRating.getStock_amount() / r
							.getStock_PML()), ExposurePD)));

		}

	}

	public void Other_Deductible_Change() {

		for (Assets r : assetsList) {
			double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
					r.getCountry(), r.getPD_Curve()).getExposure();
			r.setOTHER_Dedpremium(r.getOTHER_Discount_premium()
					* (1 - MBBEFD((propertyOnshoreRating.getOther_amount() / r
							.getOTHER_PML()), ExposurePD)));

		}

	}

	// Deductible rating end
	// XL layers management start
	public double GetLayer1ded() {
		if (propertyOnshoreRating.getLY1_lim() == 0) {
			return 0;
		}
		double a = (propertyOnshoreRating.getOPD_ded() + propertyOnshoreRating
				.getOPD_lim());
		propertyOnshoreRating.setLY1_ded(a);
		return a;
	}

	public double GetLayer2ded() {
		if (propertyOnshoreRating.getLY2_lim() == 0) {
			return 0;
		}
		double a = (propertyOnshoreRating.getLY1_ded() + propertyOnshoreRating
				.getLY1_lim());
		propertyOnshoreRating.setLY2_ded(a);
		return a;
	}

	public double GetLayer3ded() {
		if (propertyOnshoreRating.getLY3_lim() == 0) {
			return 0;
		}
		double a = (propertyOnshoreRating.getLY2_ded() + propertyOnshoreRating
				.getLY2_lim());
		propertyOnshoreRating.setLY3_ded(a);
		return a;
	}

	public double GetLayer4ded() {
		if (propertyOnshoreRating.getLY4_lim() == 0) {
			return 0;
		}
		double a = (propertyOnshoreRating.getLY3_ded() + propertyOnshoreRating
				.getLY3_lim());
		propertyOnshoreRating.setLY4_ded(a);
		return a;
	}

	public double GetLayer5ded() {
		if (propertyOnshoreRating.getLY5_lim() == 0) {
			return 0;
		}
		double a = (propertyOnshoreRating.getLY4_ded() + propertyOnshoreRating
				.getLY4_lim());
		propertyOnshoreRating.setLY5_ded(a);
		return a;
	}

	public void CalculateOBDfac() {
		for (Assets a : assetsList) {
			double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getPD_Curve()).getExposure();
			double min1 = Math
					.min(1,
							((propertyOnshoreRating.getOPD_lim() + propertyOnshoreRating
									.getOPD_ded()) / a.getEML()));
			double min2 = Math.min(1,
					propertyOnshoreRating.getOPD_ded() / a.getEML());
			a.setOPDFac(MBBEFD(min1, ExposurePD) - MBBEFD(min2, ExposurePD));
		}

	}

	public double CalculateOBDPremium() {
		double totp = 0;
		double totfac = 0;
		for (Assets a : assetsList) {
			totfac = totfac + a.getOPDFac();
			totp = totp
					+ (a.getBI_Discount_premium() + a.getMB_Discount_premium()
							+ a.getPD_Discount_premium()
							+ a.getMlop_Discount_premium()
							+ a.getStock_Discount_premium() + a
								.getOTHER_Discount_premium());
		}
		return (totfac * totp);
	}

	public void CalculateLY1fac() {
		for (Assets a : assetsList) {
			double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getPD_Curve()).getExposure();

			double min1 = Math
					.min(1,
							((propertyOnshoreRating.getLY1_lim() + propertyOnshoreRating
									.getLY1_ded()) / a.getEML()));
			double min2 = Math.min(1,
					propertyOnshoreRating.getLY1_ded() / a.getEML());
			a.setLY1Fac(MBBEFD(min1, ExposurePD) - MBBEFD(min2, ExposurePD));
		}

	}

	public double CalculateLY1Premium() {
		double totp = 0;
		double totfac = 0;
		for (Assets a : assetsList) {
			totfac = totfac + a.getLY1Fac();
			totp = totp
					+ (a.getBI_Discount_premium() + a.getMB_Discount_premium()
							+ a.getPD_Discount_premium()
							+ a.getMlop_Discount_premium()
							+ a.getStock_Discount_premium() + a
								.getOTHER_Discount_premium());
		}
		return (totfac * totp);
	}

	public void CalculateLY2fac() {
		for (Assets a : assetsList) {
			double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getPD_Curve()).getExposure();
			double min1 = Math
					.min(1,
							((propertyOnshoreRating.getLY2_lim() + propertyOnshoreRating
									.getLY2_ded()) / a.getEML()));
			double min2 = Math.min(1,
					propertyOnshoreRating.getLY2_ded() / a.getEML());
			a.setLY2Fac(MBBEFD(min1, ExposurePD) - MBBEFD(min2, ExposurePD));
		}

	}

	public double CalculateLY2Premium() {
		double totp = 0;
		double totfac = 0;
		for (Assets a : assetsList) {
			totfac = totfac + a.getLY2Fac();
			totp = totp
					+ (a.getBI_Discount_premium() + a.getMB_Discount_premium()
							+ a.getPD_Discount_premium()
							+ a.getMlop_Discount_premium()
							+ a.getStock_Discount_premium() + a
								.getOTHER_Discount_premium());
		}
		return (totfac * totp);
	}

	public void CalculateLY3fac() {
		for (Assets a : assetsList) {
			double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getPD_Curve()).getExposure();
			double min1 = Math
					.min(1,
							((propertyOnshoreRating.getLY3_lim() + propertyOnshoreRating
									.getLY3_ded()) / a.getEML()));
			double min2 = Math.min(1,
					propertyOnshoreRating.getLY3_ded() / a.getEML());
			a.setLY3Fac(MBBEFD(min1, ExposurePD) - MBBEFD(min2, ExposurePD));
		}

	}

	public double CalculateLY3Premium() {
		double totp = 0;
		double totfac = 0;
		for (Assets a : assetsList) {

			totfac = totfac + a.getLY3Fac();
			totp = totp
					+ (a.getBI_Discount_premium() + a.getMB_Discount_premium()
							+ a.getPD_Discount_premium()
							+ a.getMlop_Discount_premium()
							+ a.getStock_Discount_premium() + a
								.getOTHER_Discount_premium());
		}
		return (totfac * totp);
	}

	public void CalculateLY4fac() {
		for (Assets a : assetsList) {
			double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getPD_Curve()).getExposure();
			double min1 = Math
					.min(1,
							((propertyOnshoreRating.getLY4_lim() + propertyOnshoreRating
									.getLY4_ded()) / a.getEML()));
			double min2 = Math.min(1,
					propertyOnshoreRating.getLY4_ded() / a.getEML());
			a.setLY4Fac(MBBEFD(min1, ExposurePD) - MBBEFD(min2, ExposurePD));
		}

	}

	public double CalculateLY4Premium() {
		double totp = 0;
		double totfac = 0;
		for (Assets a : assetsList) {
			totfac = totfac + a.getLY4Fac();
			totp = totp
					+ (a.getBI_Discount_premium() + a.getMB_Discount_premium()
							+ a.getPD_Discount_premium()
							+ a.getMlop_Discount_premium()
							+ a.getStock_Discount_premium() + a
								.getOTHER_Discount_premium());
		}
		return (totfac * totp);
	}

	public void CalculateLY5fac() {
		for (Assets a : assetsList) {
			double ExposurePD = exposureScaleServicesLocal.GetExposureScale(
					a.getCountry(), a.getPD_Curve()).getExposure();
			double min1 = Math
					.min(1,
							((propertyOnshoreRating.getLY5_lim() + propertyOnshoreRating
									.getLY5_ded()) / a.getEML()));
			double min2 = Math.min(1,
					propertyOnshoreRating.getLY5_ded() / a.getEML());
			a.setLY5Fac(MBBEFD(min1, ExposurePD) - MBBEFD(min2, ExposurePD));
		}

	}

	public double CalculateLY5Premium() {
		double totp = 0;
		double totfac = 0;
		for (Assets a : assetsList) {
			totfac = totfac + a.getLY5Fac();
			totp = totp
					+ (a.getBI_Discount_premium() + a.getMB_Discount_premium()
							+ a.getPD_Discount_premium()
							+ a.getMlop_Discount_premium()
							+ a.getStock_Discount_premium() + a
								.getOTHER_Discount_premium());
		}
		return (totfac * totp);
	}

	public double CalculateXLTotalPremium() {

		return CalculateLY1Premium() + CalculateLY2Premium()
				+ CalculateLY3Premium() + CalculateLY4Premium()
				+ CalculateLY5Premium();
	}

	public double CalculateTotalLimit() {
		return propertyOnshoreRating.getLY1_lim()
				+ propertyOnshoreRating.getLY2_lim()
				+ propertyOnshoreRating.getLY3_lim()
				+ propertyOnshoreRating.getLY4_lim()
				+ propertyOnshoreRating.getLY5_lim();
	}

	public double CalculateTotalded() {
		return propertyOnshoreRating.getLY1_ded()
				+ propertyOnshoreRating.getLY2_ded()
				+ propertyOnshoreRating.getLY3_ded()
				+ propertyOnshoreRating.getLY4_ded()
				+ propertyOnshoreRating.getLY5_ded();
	}

	// XL layers management end

	// Format method begin
	public String FormatToDollar(double toformat) {
		return NumberFormat.getCurrencyInstance(us).format(toformat);
	}

	public String FormatPerc(Double toformat) {

		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(toformat);
	}

	// Format method end

	// set get
	public PropertyOnshoreRating getPropertyOnshoreRating() {
		return propertyOnshoreRating;
	}

	public void setPropertyOnshoreRating(
			PropertyOnshoreRating propertyOnshoreRating) {
		this.propertyOnshoreRating = propertyOnshoreRating;
	}

	public PropertyOnshoreMeasure getPropertyOnshoreMeasure() {
		return propertyOnshoreMeasure;
	}

	public void setPropertyOnshoreMeasure(
			PropertyOnshoreMeasure propertyOnshoreMeasure) {
		this.propertyOnshoreMeasure = propertyOnshoreMeasure;
	}

	public boolean isDispalyExcess() {
		return DispalyExcess;
	}

	public void setDispalyExcess(boolean dispalyExcess) {
		DispalyExcess = dispalyExcess;
	}

	public boolean isDisplayQs() {
		return DisplayQs;
	}

	public void setDisplayQs(boolean displayQs) {
		DisplayQs = displayQs;
	}

	public Project getProject3() {
		return project3;
	}

	public void setProject3(Project project3) {
		this.project3 = project3;
	}

	public List<String> getCOB() {
		return COB;
	}

	public void setCOB(List<String> cOB) {
		COB = cOB;
	}

	public List<String> getOccupancies() {
		return Occupancies;
	}

	public void setOccupancies(List<String> occupancies) {
		Occupancies = occupancies;
	}

	public double getPD_Prevention_Discount() {
		return PD_Prevention_Discount;
	}

	public void setPD_Prevention_Discount(double pD_Prevention_Discount) {
		PD_Prevention_Discount = pD_Prevention_Discount;
	}

	public double getBI_Prevention_Discount() {
		return BI_Prevention_Discount;
	}

	public void setBI_Prevention_Discount(double bI_Prevention_Discount) {
		BI_Prevention_Discount = bI_Prevention_Discount;
	}

	public double getSafetyAndSecurityAssesment() {
		return SafetyAndSecurityAssesment;
	}

	public void setSafetyAndSecurityAssesment(double safetyAndSecurityAssesment) {
		SafetyAndSecurityAssesment = safetyAndSecurityAssesment;
	}

	public double getHousekeepingAssesment() {
		return HousekeepingAssesment;
	}

	public void setHousekeepingAssesment(double housekeepingAssesment) {
		HousekeepingAssesment = housekeepingAssesment;
	}

	public double getMaintenanceAssesment() {
		return MaintenanceAssesment;
	}

	public void setMaintenanceAssesment(double maintenanceAssesment) {
		MaintenanceAssesment = maintenanceAssesment;
	}

	public double getBILossPrevenstionAssesment() {
		return BILossPrevenstionAssesment;
	}

	public void setBILossPrevenstionAssesment(double bILossPrevenstionAssesment) {
		BILossPrevenstionAssesment = bILossPrevenstionAssesment;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public List<Assets> getAssetsList() {
		return assetsList;
	}

	public void setAssetsList(List<Assets> assetsList) {
		this.assetsList = assetsList;
	}

	public boolean isForrmDisplayed() {
		return forrmDisplayed;
	}

	public void setForrmDisplayed(boolean forrmDisplayed) {
		this.forrmDisplayed = forrmDisplayed;
	}

	public List<String> getOccupancies2() {
		return Occupancies2;
	}

	public void setOccupancies2(List<String> occupancies2) {
		Occupancies2 = occupancies2;
	}

	public List<String> getCOB2() {
		return COB2;
	}

	public void setCOB2(List<String> cOB2) {
		COB2 = cOB2;
	}

	public double getBiindemnityLoad() {
		return BiindemnityLoad;
	}

	public void setBiindemnityLoad(double biindemnityLoad) {
		BiindemnityLoad = biindemnityLoad;
	}

	public double getMlopindemnityLoad() {
		return MlopindemnityLoad;
	}

	public void setMlopindemnityLoad(double mlopindemnityLoad) {
		MlopindemnityLoad = mlopindemnityLoad;
	}

	public double getBasePropertyDamageRate() {
		return BasePropertyDamageRate;
	}

	public void setBasePropertyDamageRate(double basePropertyDamageRate) {
		BasePropertyDamageRate = basePropertyDamageRate;
	}

	public Double getPMLErrorLoad() {
		return PMLErrorLoad;
	}

	public void setPMLErrorLoad(Double pMLErrorLoad) {
		PMLErrorLoad = pMLErrorLoad;
	}

	public double getConstructionClassLoad() {
		return ConstructionClassLoad;
	}

	public void setConstructionClassLoad(double constructionClassLoad) {
		ConstructionClassLoad = constructionClassLoad;
	}

	public double getLossRatioLoad() {
		return lossRatioLoad;
	}

	public void setLossRatioLoad(double lossRatioLoad) {
		this.lossRatioLoad = lossRatioLoad;
	}

	public double getFireFighting_Discount() {
		return FireFighting_Discount;
	}

	public void setFireFighting_Discount(double fireFighting_Discount) {
		FireFighting_Discount = fireFighting_Discount;
	}

	public double getPublicFireBrigadeLoad() {
		return PublicFireBrigadeLoad;
	}

	public void setPublicFireBrigadeLoad(double publicFireBrigadeLoad) {
		PublicFireBrigadeLoad = publicFireBrigadeLoad;
	}

	public double getFireDetectionLoad() {
		return FireDetectionLoad;
	}

	public void setFireDetectionLoad(double fireDetectionLoad) {
		FireDetectionLoad = fireDetectionLoad;
	}

	public double getPlantFireBrigadeLoad() {
		return PlantFireBrigadeLoad;
	}

	public void setPlantFireBrigadeLoad(double plantFireBrigadeLoad) {
		PlantFireBrigadeLoad = plantFireBrigadeLoad;
	}

	public double getEstingSystemLoad() {
		return EstingSystemLoad;
	}

	public void setEstingSystemLoad(double estingSystemLoad) {
		EstingSystemLoad = estingSystemLoad;
	}

	public double getFireMeasureTotal() {
		return FireMeasureTotal;
	}

	public void setFireMeasureTotal(double fireMeasureTotal) {
		FireMeasureTotal = fireMeasureTotal;
	}

	public double getMBandMlOPLoad() {
		return MBandMlOPLoad;
	}

	public void setMBandMlOPLoad(double mBandMlOPLoad) {
		MBandMlOPLoad = mBandMlOPLoad;
	}

	public double getBIload() {
		return BIload;
	}

	public void setBIload(double bIload) {
		BIload = bIload;
	}

	public List<String> getCountries() {
		return Countries;
	}

	public void setCountries(List<String> countries) {
		Countries = countries;
	}

	public List<String> getSeverity() {
		return Severity;
	}

	public void setSeverity(List<String> severity) {
		Severity = severity;
	}
}
