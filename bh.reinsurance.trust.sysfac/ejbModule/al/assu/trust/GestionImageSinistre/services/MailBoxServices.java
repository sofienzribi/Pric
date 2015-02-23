package al.assu.trust.GestionImageSinistre.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	static Locale locale = Locale.getDefault();
	static Date date = new Date();
	static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	String datee;

	public MailBoxServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CreateMailBox(MailBox box) {

		datee = dateFormat.format(date);
		box.setSentDate(date);
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

	@Override
	public void UpdateMailBox(MailBox box) {
		entityManager.merge(box);

	}

	@Override
	public void DeleteMailBox(int box) {

		entityManager.remove(entityManager.find(MailBox.class, box));

	}

}
