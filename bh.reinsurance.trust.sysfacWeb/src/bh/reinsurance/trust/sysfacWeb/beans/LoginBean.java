package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServlet;

import al.assu.trust.GestionImageSinistre.domain.MailBox;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.MailBoxServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	// Models

	private MailBox mailBox;
	private List<MailBox> mailBoxs;
	private User user;
	// EJB
	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private MailBoxServicesLocal mailBoxServicesLocal;

	public UserServicesLocal getUserServicesLocal() {
		return userServicesLocal;
	}

	public String Connected() {
		System.out.println(user.login);
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
			System.out.println(user.getId());
			System.out.println("acces granted");
			mailBoxs = mailBoxServicesLocal.GetMailBoxByUserId(user.getId());
			return "Project_Screen?faces-redirect=true";

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Bad Credentials!", "Bad Credentials"));
			// setUser(new user());
			return "";
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MailBox getMailBox() {
		return mailBox;
	}

	public void setMailBox(MailBox mailBox) {
		this.mailBox = mailBox;
	}

	public List<MailBox> getMailBoxs() {
		return mailBoxs;
	}

	public void setMailBoxs(List<MailBox> mailBoxs) {
		this.mailBoxs = mailBoxs;
	}

}
