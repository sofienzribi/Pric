package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import al.assu.trust.GestionImageSinistre.domain.Facultative;
import al.assu.trust.GestionImageSinistre.impl.FacultativeServicesLocal;

/**
 * Session Bean implementation class FacultativeServices
 */
@Stateless
public class FacultativeServices implements FacultativeServicesLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	EntityManager entityManager;

	public FacultativeServices() {
		// TODO Auto-generated constructor stub
	}

	public List<Facultative> facultatives() {
		Query query = entityManager.createQuery("select a from Facultative a");
		return query.getResultList();

	}

	@Override
	public Facultative getfacbyid(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Facultative.class, id);
	}

	@Override
	public List<Facultative> findfacbyregion(String region) {
		// TODO Auto-generated method stub

		return entityManager
				.createQuery("select p from Facultative p where p.regions=:c")
				.setParameter("c", region).getResultList();
	}

	@Override
	public List<String> getRegions() {

		Query query = entityManager
				.createQuery("select DISTINCT a.regions from Facultative a");
		return query.getResultList();

	}

	@Override
	public List<String> GetCountries(String region) {
		if (region == null || region == "") {
			return entityManager.createQuery(
					"select DISTINCT p.countries from Facultative p ")
					.getResultList();

		} else {
			return entityManager
					.createQuery(
							"select DISTINCT p.countries from Facultative p where p.regions=:c")
					.setParameter("c", region).getResultList();
		}

	}

	@Override
	public List<String> GetOcuupencies() {

		Query query = entityManager
				.createQuery("select DISTINCT a.occupencies from Facultative a");
		return query.getResultList();
	}

	@Override
	public List<Facultative> GetFacBychoice(String region, String country,
			String occupancy) {

		if (region != null && country != null && occupancy != null) {

			Query query = entityManager
					.createQuery("select  p from Facultative p where p.regions=:c AND p.countries=:a AND p.occupencies=:d");
			query.setParameter("c", region);
			query.setParameter("a", country);
			query.setParameter("d", occupancy);
			return query.getResultList();
		}
		if (region != null && country != null && occupancy == null) {
			Query query = entityManager
					.createQuery("select  p from Facultative p where p.regions=:c AND p.countries=:a");
			query.setParameter("c", region);
			query.setParameter("a", country);

			return query.getResultList();
		}
		if (region != null && country == null && occupancy == null) {
			Query query = entityManager
					.createQuery("select  p from Facultative p where p.regions=:c ");
			query.setParameter("c", region);

			return query.getResultList();
		}
		if (region == null && country == null && occupancy == null) {
			Query query = entityManager
					.createQuery("select  p from Facultative p  ");

			return query.getResultList();
		}
		if (region != null && country == null && occupancy != null) {
			Query query = entityManager
					.createQuery("select  p from Facultative p where p.regions=:c AND p.occupencies=:d ");
			query.setParameter("c", region);

			query.setParameter("d", occupancy);
			return query.getResultList();
		}
		if (region == null && country != null && occupancy == null) {
			Query query = entityManager
					.createQuery("select  p from Facultative p where p.countries=:a");
			query.setParameter("a", country);
			return query.getResultList();
		}
		if (region == null && country == null && occupancy != null) {
			Query query = entityManager
					.createQuery("select  p from Facultative p where p.occupencies=:d ");
			query.setParameter("d", occupancy);
			return query.getResultList();
		}
		if (region == null && country != null && occupancy != null) {
			Query query = entityManager
					.createQuery("select  p from Facultative p where p.occupencies=:d AND p.countries=:a");
			query.setParameter("a", country);
			query.setParameter("d", occupancy);
			return query.getResultList();
		}

		return null;
	}

	@Override
	public List<Facultative> GetOurLiability() {

		Query query = entityManager.createQuery("select p from Facultative p");

		return query.getResultList();
	}

	@Override
	public long getlil() {
		Query query = entityManager
				.createQuery("SELECT SUM(f.our_liability) FROM Facultative f");

		return (Long) query.getSingleResult();
	}

	@Override
	public List<String> GetOcuupenciesbyChoice(String Region, String Country) {
		if (Country == null) {

			Query query = entityManager
					.createQuery("select DISTINCT  p.occupencies from Facultative p where p.regions=:a");
			query.setParameter("a", Region);

			return query.getResultList();
		} else if (Country == null && Region == null) {
			Query query = entityManager
					.createQuery("select DISTINCT a.occupencies from Facultative a");
			return query.getResultList();
		} else {

			Query query = entityManager
					.createQuery("select DISTINCT p.occupencies from Facultative p where p.countries=:a AND p.regions=:b");
			query.setParameter("a", Country);
			query.setParameter("b", Region);
			return query.getResultList();
		}
	}

}
