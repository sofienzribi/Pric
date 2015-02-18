package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.Summary;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.ProjectServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.SummaryServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean
@SessionScoped
public class ProjectBean implements Serializable {
	/**
	 * 
	 */
	// var
	@ManagedProperty("#{login.getUser()}")
	private User user;
	private boolean passwordmsg;
	private String pwdcheck;
	private String Checkbox;
	private static final long serialVersionUID = 1L;
	private List<Project> projects = new ArrayList<Project>();
	private Project project;
	private Project project2;
	private String Test;
	private boolean PopDisplayed;
	private boolean CheckboxDisplay;
	private String priv;
	
	
	private Summary summary;
	@EJB
	private SummaryServicesLocal local3;

	
	
	
	
	@EJB
	private ProjectServicesLocal local;
	@EJB
	private UserServicesLocal local2;
	private List<User> SendToUsers;

	// methods

	public ProjectBean() {
		CheckboxDisplay = false;
		project2 = new Project();
	}

	@PostConstruct
	public void init() {
		
		System.out.println(user.getId());

		passwordmsg = true;
		PopDisplayed = false;
		projects = local.GetAllProjects();

	}

	public void displaypasswordmsg() {
		if (priv.equals("true")) {
			passwordmsg = true;
			CheckboxDisplay = true;

		} else {
			passwordmsg = false;
			CheckboxDisplay = false;
		}
	}

	public String OpenProject() {

		if (priv.equals("false")) {
			project2.setPrivacy(false);
			if (Checkbox.equals("true")) {
				project2.setPassword(user.getPassword());
			}
		} else {
			project2.setPrivacy(true);

		}

		project2.setUser(user.getId());
		System.out.println(user.getId());
		local.NewProject(project2);
		projects = local.GetAllProjects();
		return "Fac_info?faces-redirect=true";
	}

	public String verifypassword() {
		System.out.println(project.getPassword());
		System.out.println(pwdcheck);
		if (pwdcheck.equals(project.getPassword())) {
			summary=local3.GetSummary(project.getId());
			return "Fac_info?faces-redirect=true";
		} else {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Bad Credentials!", "Bad Credentials"));
			return null;
		}
	}

	public void DisablePasswordUsingCheckbox() {
		if (Checkbox.equals("true")) {
			passwordmsg = true;
		} else {
			passwordmsg = false;
		}

	}

	public String getPrivacy(boolean a) {
		if (a == true) {
			return "Public";
		} else {
			return "Private";
		}
	}

	public String getLogin(int id) {
		return local2.GetUserByid(id).login;
	}

	public void DisplayPop() {
		PopDisplayed = true;
	}

	public String openingproject() {

		if (project.getPrivacy() == true) {
			summary=local3.GetSummary(project.getId());
			return "Fac_info?faces-redirect=true";
		} else {
			if (project.getUser() == user.getId()) {
				summary=local3.GetSummary(project.getId());
				return "Fac_info?faces-redirect=true";
			} else {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("popup1.show();");
				return null;
			}
		}

	}

	public List<User> completeTheme(String query) {
		List<User> allThemes = local2.GetAllUsers();
		List<User> filteredThemes = new ArrayList<User>();

		for (int i = 0; i < allThemes.size(); i++) {
			User user1 = allThemes.get(i);
			if (user1.getLogin().toLowerCase().contains(query)) {
				filteredThemes.add(user1);
			}
		}

		return filteredThemes;
	}

	// get set

	public UserServicesLocal getLocal2() {
		return local2;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLocal2(UserServicesLocal local2) {
		this.local2 = local2;
	}

	public ProjectServicesLocal getLocal() {
		return local;
	}

	public void setLocal(ProjectServicesLocal local) {
		this.local = local;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public boolean isPopDisplayed() {
		return PopDisplayed;
	}

	public void setPopDisplayed(boolean popDisplayed) {
		PopDisplayed = popDisplayed;
	}

	public String getPriv() {
		return priv;
	}

	public void setPriv(String priv) {
		this.priv = priv;
	}

	public String getTest() {
		return Test;
	}

	public void setTest(String test) {
		Test = test;
	}

	public Project getProject2() {
		return project2;
	}

	public void setProject2(Project project2) {
		this.project2 = project2;
	}

	public boolean getPasswordmsg() {
		return passwordmsg;
	}

	public void setPasswordmsg(boolean passwordmsg) {
		this.passwordmsg = passwordmsg;
	}

	public String getPwdcheck() {
		return pwdcheck;
	}

	public void setPwdcheck(String pwdcheck) {
		this.pwdcheck = pwdcheck;
	}

	public String getCheckbox() {
		return Checkbox;
	}

	public void setCheckbox(String checkbox) {
		Checkbox = checkbox;
	}

	public boolean isCheckboxDisplay() {
		return CheckboxDisplay;
	}

	public void setCheckboxDisplay(boolean checkboxDisplay) {
		CheckboxDisplay = checkboxDisplay;
	}

	public List<User> getSendToUsers() {
		return SendToUsers;
	}

	public void setSendToUsers(List<User> sendToUsers) {
		SendToUsers = sendToUsers;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

}
