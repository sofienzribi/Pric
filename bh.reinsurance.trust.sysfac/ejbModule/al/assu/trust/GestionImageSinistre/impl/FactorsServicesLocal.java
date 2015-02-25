package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Construction_Type;
import al.assu.trust.GestionImageSinistre.domain.Factors;

@Local
public interface FactorsServicesLocal {
	public void Persist(Object object);

	public void Delete(Object object);

	public List<Construction_Type> GetConsttype(int factors);

	public Factors GetFactorByIdMeasure(int id);

	public boolean CategoryExists(String Category, int IdFactor, String Type);
}
