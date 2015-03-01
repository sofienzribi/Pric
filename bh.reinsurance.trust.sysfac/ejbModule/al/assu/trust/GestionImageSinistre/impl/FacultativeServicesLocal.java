package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Facultative;

@Local
public interface FacultativeServicesLocal {

	public List<Facultative> facultatives();

	public Facultative getfacbyid(int id);

	public List<Facultative> findfacbyregion(String region);

	public List<String> getRegions();

	public List<String> GetCountries(String region);

	public List<String> GetOcuupencies();

	public List<String> GetOcuupenciesbyChoice(String Region, String Country);

	public List<Facultative> GetFacBychoice(String region, String country,
			String occupancy);

	public List<Facultative> GetOurLiability();

	public long getlil();
}
