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

import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.impl.MeasureServicesLocal;

/**
 * Session Bean implementation class MeasureServices
 */
@Stateless
public class MeasureServices implements MeasureServicesLocal {
	@PersistenceContext
	EntityManager entityManager;
	static Locale locale = Locale.getDefault();
	static Date date = new Date();
	static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	String datee;

	public MeasureServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void NewMeasure(Measure measure) {
		datee = dateFormat.format(date);
		measure.setCreationDate(date);
		entityManager.merge(measure);

	}

	@Override
	public Measure GetMeasure(int id) {

		return entityManager.find(Measure.class, id);
	}

	@Override
	public List<Measure> GetAllMeasures() {
		Query query = entityManager.createQuery("select a from Measure a");
		return query.getResultList();
	}

	@Override
	public void UpdateMeasure(Measure measure) {
		entityManager.merge(measure);

	}

	@Override
	public void DeleteMeasure(Measure measure) {
		entityManager.remove(entityManager.merge(measure));

	}

	@Override
	public boolean NameExist(String name) {
		String jpql = "select u from Measure u where u.name =:param1 ";
		Query query = entityManager.createQuery(jpql);
		Measure MeasureFound;
		query.setParameter("param1", name);

		try {
			MeasureFound = (Measure) query.getSingleResult();

		} catch (Exception e) {
			MeasureFound = null;

		}

		if (MeasureFound != null)
			return true;
		else
			return false;
	}

	@Override
	public Measure GetMeasureByName(String Name) {
		return (Measure) entityManager
				.createQuery("select p from Measure p where p.name=:c")
				.setParameter("c", Name).getSingleResult();
	}

	@Override
	public Measure GetWorkingMeasure() {
		String jpql = "select u from Measure u where u.active =:param1 ";
		Query query = entityManager.createQuery(jpql).setParameter("param1",
				true);
		try {
			return (Measure) query.getSingleResult();
		} catch (Exception e) {

			System.out.println("No working Measure");
			return null;
		}

	}

	@Override
	public Measure GetTestingMeasure() {
		String jpql = "select u from Measure u where u.activeTest =:param1 ";
		Query query = entityManager.createQuery(jpql).setParameter("param1",
				true);
		try {
			return (Measure) query.getSingleResult();
		} catch (Exception e) {

			System.out.println("No Testing Measure");
			return null;
		}
	}
}
