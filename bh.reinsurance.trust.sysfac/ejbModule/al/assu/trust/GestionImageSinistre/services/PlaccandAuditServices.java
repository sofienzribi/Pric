package al.assu.trust.GestionImageSinistre.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import al.assu.trust.GestionImageSinistre.domain.PIaccandAudit;
import al.assu.trust.GestionImageSinistre.impl.PlaccandAuditServicesLocal;

/**
 * Session Bean implementation class PlaccandAuditServices
 */
@Stateless
public class PlaccandAuditServices implements PlaccandAuditServicesLocal {

	/**
	 * Default constructor.
	 */
	public PlaccandAuditServices() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void add(PIaccandAudit audit) {
		entityManager.persist(audit);

	}

	@Override
	public void update(PIaccandAudit audit) {
		entityManager.merge(audit);

	}

	@Override
	public PIaccandAudit searchById(int id) {

		return entityManager.find(PIaccandAudit.class, id);
	}

	@Override
	public void delete(PIaccandAudit audit) {
		entityManager.remove(entityManager.merge(audit));

	}

}
