package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.User;

@Local
public interface ProjectServicesLocal {
	
	public void NewProject(Project project);
	public List<Project> GetProjectsByUser(User user);
	public Project GetProjectByName(String Name);
	public List<Project> GetAllProjects();
	public void DeleteProject(Project project);
	
public boolean Nameexist(String Name);
	
	
}
