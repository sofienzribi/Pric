package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import al.assu.trust.GestionImageSinistre.domain.Construction_Type;
import al.assu.trust.GestionImageSinistre.domain.Factors;
import al.assu.trust.GestionImageSinistre.domain.Loss_Frequency;
import al.assu.trust.GestionImageSinistre.impl.FactorsServicesLocal;

/**s
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
	public List<Construction_Type> GetConsttype(int object) {
		return entityManager
				.createQuery(
						"select p from Construction_Type p where p.idFactor=:c")
				.setParameter("c", object).getResultList();
	}

	/*
	 * @Override public List<Object> GetConsttype(int factors) { return
	 * entityManager .createQuery(
	 * "select p from Construction_Type p where p.idFactor=:c")
	 * .setParameter("c", factors).getResultList(); }
	 */

	@Override
	public Factors GetFactorByIdMeasure(int id) {
		return (Factors) entityManager
				.createQuery("select p from Factors p where p.idMeasure=:c")
				.setParameter("c", id).getSingleResult();
	}

	@Override
	public boolean CategoryExists(String Category, int IdFactor, String Type) {
		
		
		String jpql = "select u from " + Type
				+ " u where u.category=:param1 and u.idFactor=:param3  ";
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

	@Override
	public Object FindConstructionTypeByCategory(String cat,String cls,int idFactor) {

		return (Construction_Type) entityManager
				.createQuery(
						"select p from "+cls+" p where p.idFactor=:c and category:=f")
				.setParameter("c", idFactor).setParameter("f", cat)
				.getSingleResult();

	}

	@Override
	public Construction_Type FindConstById(int id) {
		return entityManager.find(Construction_Type.class, id);
	}

	@Override
	public List<Loss_Frequency> getloss(int facors) {
		Query query = entityManager.createQuery(
				"select p from Loss_Frequency p where p.IdFactor=:c")
				.setParameter("c", facors);

		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
