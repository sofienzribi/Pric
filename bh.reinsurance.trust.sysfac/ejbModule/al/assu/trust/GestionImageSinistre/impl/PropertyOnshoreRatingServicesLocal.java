package al.assu.trust.GestionImageSinistre.impl;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.PropertyOnshoreRating;

@Local
public interface PropertyOnshoreRatingServicesLocal {

	public void Add(PropertyOnshoreRating onshoreRating);

	public PropertyOnshoreRating GetByIdProject(int id);

	public void Delete(PropertyOnshoreRating onshoreRating);
	 public PropertyOnshoreRating FindById(int id);
}
