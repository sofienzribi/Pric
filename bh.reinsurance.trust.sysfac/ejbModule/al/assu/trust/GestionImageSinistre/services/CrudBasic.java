package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import al.assu.trust.GestionImageSinistre.impl.CrudBasicLocal;

/**
 * Session Bean implementation class CrudBasic
 */
@Stateless
public class CrudBasic implements CrudBasicLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	EntityManager entityManager;

	public CrudBasic() {

	}

	@Override
	public void Persist(Object object) {
		entityManager.merge(object);

	}

	@Override
	public void Delete(Object object) {
		entityManager.remove(entityManager.merge(object));

	}

	@Override
	public List<Object> FindAll(String type) {
		return entityManager.createQuery("select p from " + type + " p ")
				.getResultList();
	}

	@Override
	public Object FindById(String type, int id) {
		return entityManager
				.createQuery("select p from " + type + " p where p.id=:b ")
				.setParameter("b", id).getSingleResult();
	}

	@Override
	public Object FindByFilter(String type, String nameofparam, int param) {
		return entityManager
				.createQuery(
						"select p from " + type + " p where p." + nameofparam
								+ "=:b ").setParameter("b", param)
				.getSingleResult();
	}

}
