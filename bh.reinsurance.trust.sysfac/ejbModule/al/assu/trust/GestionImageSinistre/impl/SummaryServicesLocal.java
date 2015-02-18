package al.assu.trust.GestionImageSinistre.impl;


import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Summary;

@Local
public interface SummaryServicesLocal {

	public Summary GetSummary(int IdProject);
	
	public void CreateSummary(Summary summary);
	
}
