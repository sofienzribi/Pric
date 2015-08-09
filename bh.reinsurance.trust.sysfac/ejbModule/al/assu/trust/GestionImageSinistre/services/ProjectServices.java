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

import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.ProjectServicesLocal;

/**
 * Session Bean implementation class ProjectServices
 */
@Stateless
public class ProjectServices implements ProjectServicesLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	EntityManager entityManager;
	static Locale locale = Locale.getDefault();
	static Date date = new Date();
	static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	String datee;

	public ProjectServices() {
	}

	@Override
	public void NewProject(Project project) {

		datee = dateFormat.format(date);
		project.setDateCreation(date);

		entityManager.merge(project);

	}

	@Override
	public List<Project> GetProjectsByUser(User user) {
		return entityManager
				.createQuery("select DISTINCT p from Project p where p.user=:c ORDER BY p.dateCreation DESC")
				.setParameter("c", user.getId()).getResultList();
	}

	@Override
	public List<Project> GetAllProjects() {

		Query query = entityManager.createQuery("select a from Project a GROUP BY a.nameOfTheProject  ORDER BY a.dateCreation DESC ");
		return query.getResultList();
	}
	@Override
	public List<Project> GetAllDistinctProjects() {

		Query query = entityManager.createQuery("select DISTINCT a from Project a GROUP BY a.nameOfTheProject ORDER BY a.dateCreation DESC");
		return query.getResultList();
	}
	
	@Override
	public List<Project> GetProjectsByName(String Name) {
		String jpql = "select u from Project u where u.nameOfTheProject =:param1 ORDER BY u.dateCreation DESC ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", Name);
		return query.getResultList();
	}

	@Override
	public void DeleteProject(int project) {
		entityManager.remove(entityManager.find(Project.class, project));

	}

	@Override
	public Project GetProjectByName(String Name) {
		return (Project) entityManager
				.createQuery(
						"select p from Project p where p.nameOfTheProject=:c")
				.setParameter("c", Name).getSingleResult();
	}

	@Override
	public boolean Nameexist(String Name) {
		String jpql = "select u from Project u where u.nameOfTheProject =:param1 ";
		Query query = entityManager.createQuery(jpql);
		Project projectFound;
		query.setParameter("param1", Name);

		try {
			projectFound = (Project) query.getSingleResult();

		} catch (Exception e) {
			projectFound = null;

		}

		if (projectFound != null)
			return true;
		else
			return false;
	}

	@Override
	public Project GetProjectById(int id) {
		try {
			return entityManager.find(Project.class, id);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void UpdateProject(Project project) {
		entityManager.merge(project);

	}
}
