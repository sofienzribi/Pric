package al.assu.trust.GestionImageSinistre.impl;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Factors;

@Local
public interface FactorsServicesLocal {
	public void Persist(Object object);

	public void Delete(Factors object);
}
