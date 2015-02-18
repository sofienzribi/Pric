package al.assu.trust.GestionImageSinistre.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import al.assu.trust.GestionImageSinistre.domain.Summary;
import al.assu.trust.GestionImageSinistre.impl.SummaryServicesLocal;

/**
 * Session Bean implementation class SummaryServices
 */
@Stateless
public class SummaryServices implements SummaryServicesLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	EntityManager entityManager;

	public SummaryServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Summary GetSummary(int IdProject) {
		return (Summary) entityManager
				.createQuery("select p from Summary p where p.idProj=:c")
				.setParameter("c", IdProject).getSingleResult();
	}

	@Override
	public void CreateSummary(Summary summary) {
		entityManager.persist(summary);

	}

}
