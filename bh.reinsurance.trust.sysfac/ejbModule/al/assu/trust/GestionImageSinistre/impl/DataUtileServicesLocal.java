package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.DataUtile;

@Local
public interface DataUtileServicesLocal {

	
	public List<String> GetClass();
	public List<String> GetOccupancies();
	public DataUtile GetUtile(String Class,String Occapancy);
	
}
