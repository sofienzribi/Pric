package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import al.assu.trust.GestionImageSinistre.domain.PropertyOnshoreMeasure;
import al.assu.trust.GestionImageSinistre.impl.PropertyOnshoreMeasureServicesLocal;

/**
 * Session Bean implementation class PropertyOnshoreMeasureServices
 */
@Stateless
public class PropertyOnshoreMeasureServices implements
		PropertyOnshoreMeasureServicesLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	EntityManager entityManager;

	public PropertyOnshoreMeasureServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void AddMeasure(PropertyOnshoreMeasure onshoreMeasure) {
		entityManager.merge(onshoreMeasure);

	}

	@Override
	public List<PropertyOnshoreMeasure> FindAll() {
		return entityManager.createQuery(
				"select p from PropertyOnshoreMeasure p ").getResultList();
	}

	@Override
	public PropertyOnshoreMeasure FindById(int Id) {
		return entityManager.find(PropertyOnshoreMeasure.class, Id);
	}

	@Override
	public PropertyOnshoreMeasure FindByIdMeasure(int id) {
		return (PropertyOnshoreMeasure) entityManager
				.createQuery(
						"select p from PropertyOnshoreMeasure p where p.idMeasure=:c")
				.setParameter("c", id).getSingleResult();
	}

	@Override
	public void DeletePropMeasure(PropertyOnshoreMeasure measure) {
		entityManager.remove(entityManager.merge(measure));
		
	}

	

}
