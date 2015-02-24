package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
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

import al.assu.trust.GestionImageSinistre.domain.MailBox;
import al.assu.trust.GestionImageSinistre.domain.Offer;
import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.Summary;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.MailBoxServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.OfferServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.ProjectServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.SummaryServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean()
@SessionScoped
public class ProjectBean implements Serializable {
	/**
	 * 
	 */
	// var

	@ManagedProperty("#{login.getUser()}")
	private User user2;

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
	private MailBox box;
	private Offer offer;
	private Project project3;
	@EJB
	private OfferServicesLocal offerServicesLocal;
	@EJB
	private ProjectServicesLocal local;
	@EJB
	private UserServicesLocal local2;
	private List<User> SendToUsers;
	private Summary summary;
	@EJB
	private SummaryServicesLocal summaryServicesLocal;
	@EJB
	private MailBoxServicesLocal mailBoxServicesLocal;
	private User UserDestination;
	private MailBox mailBox;
	private List<MailBox> mailBoxs;
	private int NumberProjectReceived;
	private boolean DisplayButtonMailBox;
	private List<Project> projectsbyuser;
	private Project proojectbyuser;
	private boolean DisplayProjectManagButton;
	private String DisplayFacultativeDept;
	private String DisplayActuarial;
	private String DisplayRating;
	private boolean DiplayDeleteButton;
	private boolean DisabledButtonProject;
	private boolean DisabledButtonProjectSendClose;

	// methods

	public ProjectBean() {

		offer = new Offer();
		box = new MailBox();
		summary = new Summary();
		project3 = new Project();
		CheckboxDisplay = false;
		project2 = new Project();
	}

	@PostConstruct
	public void init() {
		DisabledButtonProject=false;
		DisabledButtonProjectSendClose=true;
		DisplayRating = "none";
		if (user2.getDepartment().equals("actuarialandrisk")) {
			DisplayActuarial = "true";
			DisplayFacultativeDept = "none";
		} else {
			DisplayFacultativeDept = "true";
			DisplayActuarial = "none";
		}
		DiplayDeleteButton = false;
		proojectbyuser = new Project();
		DisplayProjectManagButton = false;
		projectsbyuser = local.GetProjectsByUser(user2);
		DisplayButtonMailBox = false;
		setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2.getId()));
		NumberProjectReceived = GetMails();
		passwordmsg = true;
		PopDisplayed = false;
		projects = local.GetAllProjects();

	}

	public void UpdateProject() {

		local.UpdateProject(proojectbyuser);
		projectsbyuser = local.GetProjectsByUser(user2);
		DisplayProjectManagButton = false;
		projects = local.GetAllProjects();

	}
	
	public void DeleteProject() {
		local.DeleteProject(proojectbyuser.getId());
		Offer offer2 = offerServicesLocal.GetOffer(proojectbyuser.getId());
		Summary summary2 = summaryServicesLocal.GetSummary(proojectbyuser
				.getId());
		summaryServicesLocal.DeleteSummary(summary2);
		offerServicesLocal.DeleteOfferByIdProject(offer2);
		projectsbyuser = local.GetProjectsByUser(user2);
		DisplayProjectManagButton = false;
		DisplayRating = "none";
		setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2.getId()));
		projects = local.GetAllProjects();
	}

	public void OnProjManSelect() {
		System.out.println("L id est " + proojectbyuser.getNameOfTheProject());
		DisplayProjectManagButton = true;

	}

	public int GetMails() {
		int a = 0;

		for (int i = 0; i < mailBoxs.size(); i++) {
			if (mailBoxs.get(i).getState().equals("NOT SEEN")) {
				a++;
			}
		}
		return a;
	}

	public void OnRowSelect() {

		if (local.GetProjectById(mailBox.getId_project()) == null) {
			DisplayButtonMailBox = false;
			DiplayDeleteButton = true;
		} else {
			DiplayDeleteButton = true;
			DisplayButtonMailBox = true;
		}

	}

	public void DeleteMailBox() {
		mailBoxServicesLocal.DeleteMailBox(mailBox.getId());
		setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2.getId()));
		DisplayButtonMailBox = false;
		DiplayDeleteButton = false;

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

	public String OpenProject()

	{
		if (local.Nameexist(project2.getNameOfTheProject())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Name already exists!!", "Name Exist"));
			return null;
		} else {

			if (priv.equals("false")) {
				project2.setPrivacy(false);
				if (Checkbox.equals("true")) {
					project2.setPassword(user2.getPassword());
				}
			} else {
				project2.setPrivacy(true);

			}

			project2.setUser(user2.getId());
			local.NewProject(project2);
			project3 = local.GetProjectByName(project2.getNameOfTheProject());
			summary.setIdProj(project3.getId());
			offer.setId_project(project3.getId());
			System.out.println("L id de ce con est" + user2.getDepartment());
			offer.setId_underwriter(user2.getId());
			summaryServicesLocal.CreateSummary(summary);
			offerServicesLocal.AddOffer(offer);
			project2 = new Project();
			DisplayRating = "true";
			projects = local.GetAllProjects();
			projectsbyuser = local.GetProjectsByUser(user2);
			DisabledButtonProject=true;
			DisabledButtonProjectSendClose=false;
			return "Fac_info?faces-redirect=true";
		}
	}

	public String GetUserName(int id) {

		User user2 = local2.GetUserByid(id);
		return user2.getFirst_Name() + " " + user2.getLast_Name();
	}

	public String GetNameOfTheProject(int id) {

		Project proj = local.GetProjectById(id);
		if (proj == null) {
			return "The project was deleted by his owner";
		} else {
			return proj.getNameOfTheProject();
		}

	}

	public void SendProject() {
		box.setId_project(project3.getId());
		box.setUser_sending_id(user2.getId());
		box.setState("NOT SEEN");
		for (int i = 0; i < SendToUsers.size(); i++) {

			box.setUser_id(SendToUsers.get(i).getId());
			mailBoxServicesLocal.CreateMailBox(box);
		}

		setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2.getId()));
		NumberProjectReceived = GetMails();
		box = new MailBox();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "project Sent!",
						"Bad Credentials"));

	}

	public String verifypassword() {

		if (pwdcheck.equals(project.getPassword())) {
			project3 = project;
			project = new Project();
			PopDisplayed = false;
			summary = summaryServicesLocal.GetSummary(project3.getId());
			DisplayRating = "true";
			DisabledButtonProject=true;
			DisabledButtonProjectSendClose=false;
			return "Summary2?faces-redirect=true";
		} else {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Bad Credentials!", "Bad Credentials"));
			return null;
		}
	}

	public List<User> completeName(String query) {
		List<User> allThemes = local2.GetAllUsers();
		List<User> filteredThemes = new ArrayList<User>();

		for (int i = 0; i < allThemes.size(); i++) {
			User skin = allThemes.get(i);
			if (skin.getFirst_Name().toLowerCase().startsWith(query)
					|| skin.getLast_Name().toLowerCase().startsWith(query)) {
				filteredThemes.add(skin);
			}
		}

		return filteredThemes;
	}

	public void DisablePasswordUsingCheckbox() {
		if (Checkbox.equals("true")) {
			passwordmsg = true;
		} else {
			passwordmsg = false;
		}

	}

	public void refreshtable() {
		setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2.getId()));
	}

	public void refreshtable2() {
		projects = local.GetAllProjects();

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
			project3 = project;
			project = new Project();
			PopDisplayed = false;
			DisplayRating = "true";
			DisabledButtonProject=true;
			DisabledButtonProjectSendClose=false;
			summary = summaryServicesLocal.GetSummary(project3.getId());
			return "Summary2?faces-redirect=true";
		} else {
			if (project.getUser() == user2.getId()) {
				project3 = project;
				project = new Project();
				PopDisplayed = false;
				DisplayRating = "true";
				DisabledButtonProject=true;
				DisabledButtonProjectSendClose=false;
				summary = summaryServicesLocal.GetSummary(project3.getId());
				return "Summary2?faces-redirect=true";
			} else {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("popup1.show();");
				return null;
			}
		}

	}

	public String OpeningSentProject() {

		project = local.GetProjectById(mailBox.getId_project());
		project3 = project;
		summary = summaryServicesLocal.GetSummary(project3.getId());
		MailBox box2 = mailBoxServicesLocal.GetMailBox(mailBox.getId());
		box2.setState("SEEN");

		mailBoxServicesLocal.UpdateMailBox(box2);
		setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2.getId()));
		NumberProjectReceived = GetMails();
		DisplayButtonMailBox = false;
		mailBox = new MailBox();
		DisabledButtonProject=true;
		DisabledButtonProjectSendClose=false;
		DisplayRating = "true";
		return "Summary2?faces-redirect=true";
	}

	public void CloseDProject() throws IOException {

		project3 = new Project();
		DisplayRating = "none";
		DisabledButtonProject=false;
		DisabledButtonProjectSendClose=true;
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Project_Screen.jsf");
	}

	// get set

	public UserServicesLocal getLocal2() {
		return local2;
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

	public MailBox getBox() {
		return box;
	}

	public void setBox(MailBox box) {
		this.box = box;
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

	public Project getProject3() {
		return project3;
	}

	public void setProject3(Project project3) {
		this.project3 = project3;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public List<MailBox> getMailBoxs() {
		return mailBoxs;
	}

	public void setMailBoxs(List<MailBox> mailBoxs) {
		this.mailBoxs = mailBoxs;
	}

	public MailBox getMailBox() {
		return mailBox;
	}

	public void setMailBox(MailBox mailBox) {
		this.mailBox = mailBox;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public int getNumberProjectReceived() {
		return NumberProjectReceived;
	}

	public void setNumberProjectReceived(int numberProjectReceived) {
		NumberProjectReceived = numberProjectReceived;
	}

	public User getUserDestination() {
		return UserDestination;
	}

	public void setUserDestination(User userDestination) {
		UserDestination = userDestination;
	}

	public boolean isDisplayButtonMailBox() {
		return DisplayButtonMailBox;
	}

	public void setDisplayButtonMailBox(boolean displayButtonMailBox) {
		DisplayButtonMailBox = displayButtonMailBox;
	}

	public List<Project> getProjectsbyuser() {
		return projectsbyuser;
	}

	public void setProjectsbyuser(List<Project> projectsbyuser) {
		this.projectsbyuser = projectsbyuser;
	}

	public Project getProojectbyuser() {
		return proojectbyuser;
	}

	public void setProojectbyuser(Project proojectbyuser) {
		this.proojectbyuser = proojectbyuser;
	}

	public boolean isDisplayProjectManagButton() {
		return DisplayProjectManagButton;
	}

	public void setDisplayProjectManagButton(boolean displayProjectManagButton) {
		DisplayProjectManagButton = displayProjectManagButton;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public String getDisplayFacultativeDept() {
		return DisplayFacultativeDept;
	}

	public void setDisplayFacultativeDept(String displayFacultativeDept) {
		DisplayFacultativeDept = displayFacultativeDept;
	}

	public String getDisplayActuarial() {
		return DisplayActuarial;
	}

	public void setDisplayActuarial(String displayActuarial) {
		DisplayActuarial = displayActuarial;
	}

	public String getDisplayRating() {
		return DisplayRating;
	}

	public void setDisplayRating(String displayRating) {
		DisplayRating = displayRating;
	}

	public boolean isDiplayDeleteButton() {
		return DiplayDeleteButton;
	}

	public void setDiplayDeleteButton(boolean diplayDeleteButton) {
		DiplayDeleteButton = diplayDeleteButton;
	}

	public boolean isDisabledButtonProject() {
		return DisabledButtonProject;
	}

	public void setDisabledButtonProject(boolean disabledButtonProject) {
		DisabledButtonProject = disabledButtonProject;
	}

	public boolean isDisabledButtonProjectSendClose() {
		return DisabledButtonProjectSendClose;
	}

	public void setDisabledButtonProjectSendClose(
			boolean disabledButtonProjectSendClose) {
		DisabledButtonProjectSendClose = disabledButtonProjectSendClose;
	}

}
