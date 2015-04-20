package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import javax.ejb.AccessTimeout;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnits;

import al.assu.trust.GestionImageSinistre.domain.DataUtile;
import al.assu.trust.GestionImageSinistre.impl.DataUtileServicesLocal;

/**
 * Session Bean implementation class DataUtileServices
 */
@Stateless
public class DataUtileServices implements DataUtileServicesLocal {

	/**
	 * Default constructor.
	 */
	public DataUtileServices() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<String> GetClass() {
		return entityManager.createQuery(
				"select DISTINT p.classs from DataUtile p where p.id=349")
				.getResultList();
	}

	@Override
	public List<String> GetOccupancies() {
		return entityManager.createQuery(
				"select DISTINCT p.occupancy from DataUtile p").getResultList();
	}

	@Override
	public DataUtile GetUtile(String Class, String Occapancy) {
		return (DataUtile) entityManager
				.createQuery(
						"select p from DataUtile p where p.classs=:c and p.occupancy=:d 	")
				.setParameter("c", Class).setParameter("d", Occapancy)
				.getSingleResult();
	}

}
