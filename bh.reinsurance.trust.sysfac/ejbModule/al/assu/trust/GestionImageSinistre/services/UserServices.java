package al.assu.trust.GestionImageSinistre.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

/**
 * Session Bean implementation class UserServices
 */
@Stateless
public class UserServices implements UserServicesLocal {
	static Locale locale = Locale.getDefault();
	static Date date = new Date();
	static DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	String datee;

	/**
	 * Default constructor.
	 */

	public UserServices() {

	}

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public User login(String login, String password) {
		String jpql = "select u from User u where u.login =:param1 and u.password=:param2";
		Query query = entityManager.createQuery(jpql);
		User userFound = new User();
		query.setParameter("param1", login);
		query.setParameter("param2", password);

		try {
			userFound = (User) query.getSingleResult();

		} catch (Exception e) {
			userFound = null;
			System.out.println("Acces granted" + datee);
		}

		return userFound;
	}

	@Override
	public User GetUserByid(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class, id);
	}

	@Override
	public void AddUser(User user) {
		entityManager.persist(user);

	}

	@Override
	public List<User> GetAllUsers() {
		return entityManager.createQuery("select p from User p ")
				.getResultList();

	}

	@Override
	public String GetFirstAndLast(int id) {
		User user3 = entityManager.find(User.class, id);
		return user3.getFirst_Name() + "  " + user3.getLast_Name();
	}

	@Override
	public void tryz() {
		entityManager.createNativeQuery(
				"ALTER TABLE ee ADD column_namef varchar(225)").executeUpdate();

	}

}
