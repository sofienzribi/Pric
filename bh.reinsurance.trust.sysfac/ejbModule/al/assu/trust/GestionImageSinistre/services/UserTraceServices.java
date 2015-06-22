package al.assu.trust.GestionImageSinistre.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.domain.UserTrace;
import al.assu.trust.GestionImageSinistre.impl.UserTraceServicesLocal;

/**
 * Session Bean implementation class UserTraceServices
 */
@Stateless
public class UserTraceServices implements UserTraceServicesLocal {
	static Date date = new Date();

	static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	static DateFormat dateFormat2 = new SimpleDateFormat("H:mm:ss");

	/**
	 * Default constructor.
	 */
	public UserTraceServices() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<UserTrace> GetAllTraces() {
		return entityManager.createQuery("select u from UserTrace u ORDER BY u.date DESC ")
				.getResultList();
	}

	@Override
	public void AddTrace(UserTrace trace) {
		trace.setDate(new Date());
		trace.setTime(dateFormat2.format(date));
		// TODO Auto-generated method stub
		entityManager.merge(trace);
	}

	@Override
	public List<UserTrace> FindTracesByuser(User user) {
		return entityManager
				.createQuery(
						"select u from UserTrace u where u.userId =:param1")
				.setParameter("param1", user.getId()).getResultList();
	}

	@Override
	public UserTrace FindTrace(int id) {
		return entityManager.find(UserTrace.class, id);
	}

}
