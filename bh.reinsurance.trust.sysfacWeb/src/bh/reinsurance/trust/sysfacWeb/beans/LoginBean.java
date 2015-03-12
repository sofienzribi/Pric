package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;

import org.primefaces.context.RequestContext;

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

	public void SetEmailPwd() throws MessagingException{
		
			String from = user.getEmail();
			final String username = user.getEmail();
			final String password = user.getEmailPwd();
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(user.getEmail()));
			message.setSubject("SYSFAC CONFIRAMTION");
			message.setText("Your email password was set properly");
		try {
			Transport.send(message);
			userServicesLocal.UpdateUser(user);
			userServicesLocal.UpdateUser(user);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('statusDialog').hide();");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Profile Updated!", "An email was sent to your address"));
		} catch (Exception e) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('statusDialog').hide();");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Bad Credentials", "Incorrect email address or password "));
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
