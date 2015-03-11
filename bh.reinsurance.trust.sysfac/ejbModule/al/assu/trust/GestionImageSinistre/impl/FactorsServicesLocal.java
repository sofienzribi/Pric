package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Construction_Type;
import al.assu.trust.GestionImageSinistre.domain.Factors;
import al.assu.trust.GestionImageSinistre.domain.Loss_Frequency;

@Local
public interface FactorsServicesLocal {
	public void Persist(Object object);

	public void Delete(Object object);

	public List<Loss_Frequency> getloss(int facors);

	public List<Construction_Type> GetConsttype(int factors);

	public Factors GetFactorByIdMeasure(int id);

	public boolean CategoryExists(String Category, int IdFactor, String Type);

	public Object FindConstructionTypeByCategory(String cat,String cls,
			int idFactor);

	public Construction_Type FindConstById(int id);

}
