package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import al.assu.trust.GestionImageSinistre.domain.Construction_Type;
import al.assu.trust.GestionImageSinistre.domain.Factors;
import al.assu.trust.GestionImageSinistre.impl.FactorsServicesLocal;

/**
 * Session Bean implementation class FactorsServices
 */
@Stateless
public class FactorsServices implements FactorsServicesLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	EntityManager entityManager;

	public FactorsServices() {
		// TODO Auto-generated constructor stub
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
	public List<Construction_Type> GetConsttype(int factors) {
		return entityManager
				.createQuery(
						"select p from Construction_Type p where p.idFactor=:c")
				.setParameter("c", factors).getResultList();
	}

	@Override
	public Factors GetFactorByIdMeasure(int id) {
		return (Factors) entityManager
				.createQuery("select p from Factors p where p.idMeasure=:c")
				.setParameter("c", id).getSingleResult();
	}

	@Override
	public boolean CategoryExists(String Category, int IdFactor, String Type) {

		String jpql = "select u from "+Type+" u where u.category=:param1 and u.idFactor=:param3  ";
		Query query = entityManager.createQuery(jpql);

		query.setParameter("param1", Category);
		query.setParameter("param3", IdFactor);
		try {
			query.getSingleResult();

		} catch (Exception e) {
			return false;

		}

		return true;
	}

}
