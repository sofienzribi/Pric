package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.domain.Occupancies;
import al.assu.trust.GestionImageSinistre.impl.OccupanciesServicesLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class OccupanciesServices
 */
@Stateless
public class OccupanciesServices implements OccupanciesServicesLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager entityManager;
    public OccupanciesServices() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public List<String> GetCOB() {
			Query query = entityManager.createQuery("select Distinct a.cob from Occupancies a");
			return query.getResultList();
	}
	@Override
	public List<String> GetOccupanciesByCob(String COB) {
		Query query = entityManager.createQuery("select a.occupancy from Occupancies a where a.cob=:param1");
		query.setParameter("param1",COB);
		return query.getResultList();
	}
	@Override
	public int GetRiskGrade(String COB, String Occupancy) {
		Query query = entityManager.createQuery("select a.riskClass from Occupancies a where a.cob=:param1 AND a.occupancy=:param2");
		query.setParameter("param1",COB);
		query.setParameter("param2",Occupancy);
		return (Integer) query.getSingleResult();
	}
	@Override
	public Occupancies GetOccupByfilters(String COB, String Occupancy) {
		Query query = entityManager.createQuery("select a from Occupancies a where a.cob=:param1 AND a.occupancy=:param2");
		query.setParameter("param1",COB);
		query.setParameter("param2",Occupancy);
		return  (Occupancies) query.getSingleResult();
	}

}
