package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.ExposureScale;

@Local
public interface ExposureScaleServicesLocal {
	public List<String> GetAllCountries();
	public List<String> GetSeverity();
	public ExposureScale GetExposureScale(String country,String Severity);
}
