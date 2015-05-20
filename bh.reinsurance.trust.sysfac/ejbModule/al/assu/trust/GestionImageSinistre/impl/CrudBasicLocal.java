package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CrudBasicLocal {
	public void Persist(Object object);

	public void Delete(Object object);

	public List<Object> FindAll(String type);

	public Object FindById(String type, int id);
	

	public Object FindByFilter(String type, String nameofparam, int param);
}
