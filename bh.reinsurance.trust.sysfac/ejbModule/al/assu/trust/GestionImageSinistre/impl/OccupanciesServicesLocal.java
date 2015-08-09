package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Occupancies;

@Local
public interface OccupanciesServicesLocal {

	public List<String> GetCOB();
	public List<String> GetOccupanciesByCob(String COB);
	public int GetRiskGrade(String COB,String Occupancy);
	public Occupancies GetOccupByfilters(String COB,String Occupancy);
	
}
