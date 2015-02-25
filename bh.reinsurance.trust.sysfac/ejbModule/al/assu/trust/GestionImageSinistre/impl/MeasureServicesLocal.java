package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Measure;

@Local
public interface MeasureServicesLocal {
	public void NewMeasure(Measure measure);

	public Measure GetMeasure(int id);

	public void UpdateMeasure(Measure measure);

	public List<Measure> GetAllMeasures();

	public void DeleteMeasure(Measure measure);

	public boolean NameExist(String name);
	
	public Measure GetMeasureByName(String Name);
}
