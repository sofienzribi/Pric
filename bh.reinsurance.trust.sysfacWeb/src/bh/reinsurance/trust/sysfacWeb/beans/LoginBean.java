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

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	// Models
	private String Department;
	private User user;
	private boolean connected;
	private String theme = "redmond";
	// EJB
	@EJB
	private UserServicesLocal userServicesLocal;

	// constructor
	public LoginBean() {

		user = new User();
	}

	// init methode
	@PostConstruct
	public void init() {
		connected = false;

	}

	// methods

	public boolean loggedin() {
		if (user != null) {
			return true;
		} else {
			return false;
		}
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

	public String login() throws IOException {

		User userFound = userServicesLocal.login(user.getLogin(),
				user.getPassword());
		if (userFound != null) {

			user = userFound;
			if (userFound.getDepartment().equals("admin")) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("pages/admin/AdminHome.jsf");
				connected = true;
				theme = "ui-lightness";
				return null;
			} else {

				if (userFound.getDepartment().equals("actuarialandrisk")) {
					Department = "Actuarial & Risk";
					connected = true;
					theme = "redmond";
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("pages/User/Fac_info.jsf");
					
					return null;

				} else {
					Department = "Facultative Department";
					connected = true;
					theme = "blitzer";

					return "pages/User/Fac_info?faces-redirect=true";
				}
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

	// getters stters

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

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public UserServicesLocal getUserServicesLocal() {
		return userServicesLocal;
	}

	public void setUserServicesLocal(UserServicesLocal userServicesLocal) {
		this.userServicesLocal = userServicesLocal;
	}

}
