package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import al.assu.trust.GestionImageSinistre.domain.ExposureScale;
import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.impl.ExposureScaleServicesLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ExposureScaleServices
 */
@Stateless
public class ExposureScaleServices implements ExposureScaleServicesLocal {

    /**
     * Default constructor. 
     */
    public ExposureScaleServices() {
        // TODO Auto-generated constructor stub
    }
    @PersistenceContext
    EntityManager entityManager;

	@Override
	public List<String> GetAllCountries() {
		Query query = entityManager.createQuery("select DISTINCT a.country from ExposureScale a");
		return query.getResultList();
	}

	@Override
	public List<String> GetSeverity() {
		Query query = entityManager.createQuery("select DISTINCT a.severity from ExposureScale a");
		return query.getResultList();
	}

	@Override
	public ExposureScale GetExposureScale(String country, String Severity) {
		return  (ExposureScale) entityManager
				.createQuery("select  a from ExposureScale a WHERE a.severity=:c and a.country=:b")
				.setParameter("c", Severity).setParameter("b", country).getSingleResult();
		
	}

}
