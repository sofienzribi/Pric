package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServlet;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean(name ="login")
@SessionScoped
public class LoginBean extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	// Models
	private String Department;
	private User user;
	

	// EJB
	@EJB
	private UserServicesLocal userServicesLocal;

	public UserServicesLocal getUserServicesLocal() {
		return userServicesLocal;
	}

	public String Connected() {

		if (user.getLogin() == null) {
			return "Nobody !!!";
		} else {
			return user.getLogin();
		}

	}

	public void doGet() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		user = new User();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/bh.reinsurance.trust.sysfacWeb/");
	}

	public void setUserServicesLocal(UserServicesLocal userServicesLocal) {
		this.userServicesLocal = userServicesLocal;
	}

	// constructor
	public LoginBean() {

		user = new User();
	}

	// init methode
	@PostConstruct
	public void init() {
	
	}

	// methods

	public boolean loggedin() {
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	public String login() {

		User userFound = userServicesLocal.login(user.getLogin(),
				user.getPassword());
		if (userFound != null) {

			user = userFound;
			if (userFound.getDepartment().equals("actuarialandrisk")) {
				Department = "Actuarial & Risk";
				return "Measures?faces-redirect=true";
			} else {
				Department = "Facultative Department";
				return "Project_Screen?faces-redirect=true";
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Bad Credentials!", "Bad Credentials"));
			// setUser(new user());
			return "";
		}
	}

	

	
	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
