package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.PropertyOnshoreMeasure;

@Local
public interface PropertyOnshoreMeasureServicesLocal {
	public void AddMeasure(PropertyOnshoreMeasure onshoreMeasure);
	public List<PropertyOnshoreMeasure> FindAll();
	public PropertyOnshoreMeasure FindById(int Id);
	public PropertyOnshoreMeasure FindByIdMeasure(int id);
	public void DeletePropMeasure(PropertyOnshoreMeasure onshoreMeasure);
}
