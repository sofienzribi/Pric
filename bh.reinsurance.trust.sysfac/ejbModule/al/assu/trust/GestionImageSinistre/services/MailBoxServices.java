package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import al.assu.trust.GestionImageSinistre.domain.MailBox;
import al.assu.trust.GestionImageSinistre.impl.MailBoxServicesLocal;

/**
 * Session Bean implementation class MailBoxServices
 */
@Stateless
public class MailBoxServices implements MailBoxServicesLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	EntityManager entityManager;

	public MailBoxServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CreateMailBox(MailBox box) {
		entityManager.merge(box);

	}

	@Override
	public List<MailBox> GetMailBoxByUserId(int User_Id) {
		
		
		return entityManager
				.createQuery("select u from MailBox u where u.user_id =:param1")
				.setParameter("param1", User_Id).getResultList();

	}

	@Override
	public MailBox GetMailBox(int id) {
		return entityManager.find(MailBox.class, id);
	}

}
