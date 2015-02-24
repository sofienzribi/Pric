package al.assu.trust.GestionImageSinistre.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public void Delete(Factors object) {

		entityManager.remove(entityManager.merge(object));

	}

}
