package al.assu.trust.GestionImageSinistre.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import al.assu.trust.GestionImageSinistre.domain.PropertyOnshoreRating;
import al.assu.trust.GestionImageSinistre.impl.PropertyOnshoreRatingServicesLocal;

/**
 * Session Bean implementation class PropertyOnshoreRatingServices
 */
@Stateless
public class PropertyOnshoreRatingServices implements
		PropertyOnshoreRatingServicesLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	EntityManager entityManager;

	public PropertyOnshoreRatingServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Add(PropertyOnshoreRating onshoreRating) {
		entityManager.merge(onshoreRating);
	}

	@Override
	public PropertyOnshoreRating GetByIdProject(int id) {
		return (PropertyOnshoreRating) entityManager
				.createQuery(
						"select p from PropertyOnshoreRating p where p.idproj=:c")
				.setParameter("c", id).getSingleResult();
	}

	@Override
	public void Delete(PropertyOnshoreRating onshoreRating) {
		entityManager.remove(onshoreRating);
	}

	@Override
	public PropertyOnshoreRating FindById(int id) {
		return entityManager.find(PropertyOnshoreRating.class, id);
	}

}
