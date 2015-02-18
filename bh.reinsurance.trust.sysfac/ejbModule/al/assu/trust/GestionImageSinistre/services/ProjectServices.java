package al.assu.trust.GestionImageSinistre.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.ProjectServicesLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	static Locale locale=Locale.getDefault();
	static Date date=new Date();
	static DateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
	String datee ;
    public ProjectServices() {
    }
    

	@Override
	public void NewProject(Project project) {
		datee=dateFormat.format(date);
		project.setDateCreation(date);
	entityManager.persist(project);
		
	}

	@Override
	public List<Project> GetProjectsByUser(User user) {
		return entityManager
				.createQuery("select p from Project p where p.user=:c")
				.setParameter("c", user).getResultList();
	}

	@Override
	public List<Project> GetAllProjects() {
		
		Query query = entityManager.createQuery("select a from Project a");
		return query.getResultList();
	}

	@Override
	public void DeleteProject(Project project) {
		entityManager.remove(entityManager.merge(project));
		
	}

}
