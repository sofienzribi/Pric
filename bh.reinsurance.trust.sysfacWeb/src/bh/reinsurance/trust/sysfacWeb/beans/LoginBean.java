package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.jboss.security.Base64Encoder;
import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.domain.UserTrace;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserTraceServicesLocal;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	// Models
	private String Department;
	private User user;
	private boolean connected;
	private String theme = "redmond";
	private String password1;
	private String password2;
	private String CurrentPassword;
	private UserTrace userTrace;
	private Map<Integer, String> themes;
	// EJB
	@EJB
	private UserTraceServicesLocal userTraceServicesLocal;
	@EJB
	private UserServicesLocal userServicesLocal;

	private boolean Remember;

	// constructor
	public LoginBean() {
		user = new User();
	}

	// init methode
	@PostConstruct
	public void init() {
		userTrace = new UserTrace();
		themes = new HashMap<Integer, String>();
		themes.put(1, "afterwork");
		themes.put(2, "redmond");
		themes.put(3, "ui-lightness");
		themes.put(4, "blitzer");
		themes.put(5, "sunny");
		themes.put(6, "sam");
		themes.put(7, "cupertino");
		themes.put(8, "start");
		themes.put(9, "south-street");
		themes.put(10, "pepper-grinder");
		themes.put(11, "humanity");

		Remember = false;

		Map<String, Object> cookies = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestCookieMap();
		Cookie cookie;
		if ((Cookie) cookies.get("Remember") != null) {
			Remember = true;
			cookie = (Cookie) cookies.get("Remember");

			user.login = cookie.getValue();

		}
		connected = false;

	}

	// methods

	public void ChangeTheme() {
		userTrace = new UserTrace("changing theme ", user.getId(), " from "
				+ user.getTheme() + " to " + theme);
		userTraceServicesLocal.AddTrace(userTrace);
		user.setTheme(theme);
		userServicesLocal.UpdateUser(user);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Theme changed!",
						""));

	}

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
			return user.getFirst_Name();
		}

	}

	public void LogOut() throws IOException {

		if (user.getDepartment() != "admin") {
			userTrace = new UserTrace("Logout", user.getId(), "");
			userTraceServicesLocal.AddTrace(userTrace);
		}
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		user = new User();

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/bh.reinsurance.trust.sysfacWeb/");
	}

	public String login() throws IOException {

		String loginEncoded = new Base64Encoder().encode(user.getPassword()
				.getBytes());
		User userFound = userServicesLocal.login(user.getLogin(), loginEncoded);
		if (userFound != null) {

			user = userFound;

			if (userFound.getDepartment().equals("admin")) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("pages/admin/AdminHome.jsf");
				connected = true;

				if (user.getTheme().equals("null")) {
					theme = "ui-lightness";
				} else {
					theme = user.getTheme();
				}

				if (Remember == true) {
					HttpServletResponse response = (HttpServletResponse) FacesContext
							.getCurrentInstance().getExternalContext()
							.getResponse();
					Cookie cookie = new Cookie("Remember", user.login);
					cookie.setMaxAge(3600);
					response.addCookie(cookie);
				}
				return null;
			} else {
				if (user.isBlocked()) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL,
									"Account Blocked!",
									"Please contact a system administrator"));
					return null;

				} else {
					user.setLoginAttempts(5);
					userServicesLocal.UpdateUser(user);

					if (userFound.getDepartment().equals("actuarialandrisk")) {
						Department = "Actuarial & Risk";
						connected = true;

						if (user.getTheme().equals("null")) {
							theme = "redmond";

						} else {
							theme = user.getTheme();
						}

						userTrace = new UserTrace("Login", user.getId(), "");
						userTraceServicesLocal.AddTrace(userTrace);
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("pages/User/HomePage.jsf");
						if (Remember == true) {
							HttpServletResponse response = (HttpServletResponse) FacesContext
									.getCurrentInstance().getExternalContext()
									.getResponse();
							Cookie cookie = new Cookie("Remember", user.login);
							cookie.setMaxAge(3600);
							response.addCookie(cookie);
						}
						return null;
					} else {
						Department = "Facultative Department";
						connected = true;
						if (user.getTheme().equals("null")) {
							theme = "blitzer";
						} else {
							theme = user.getTheme();
						}
						if (Remember == true) {
							HttpServletResponse response = (HttpServletResponse) FacesContext
									.getCurrentInstance().getExternalContext()
									.getResponse();
							Cookie cookie = new Cookie("Remember", user.login);
							cookie.setMaxAge(3600);
							response.addCookie(cookie);
						}
						userTrace = new UserTrace("Login", user.getId(), "");
						userTraceServicesLocal.AddTrace(userTrace);
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("pages/User/HomePage.jsf");
						return null;
					}
				}
			}

		} else {

			if (userServicesLocal.GetUserByLogin(user.getLogin()) == null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"Bad Credentials!", ""));
			} else {
				userFound = userServicesLocal.GetUserByLogin(user.getLogin());
				if (userFound.getDepartment().equals("admin")) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL,
									"Bad Credentials!", ""));
				} else {
					if (userFound.isBlocked() == true) {
						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_FATAL,
												"Account Blocked!",
												"Please contact a system administrator"));
					} else {
						userFound
								.setLoginAttempts(userFound.getLoginAttempts() - 1);
						userServicesLocal.UpdateUser(userFound);
						if (userFound.getLoginAttempts() == 0) {

							userTrace = new UserTrace("Account Blocked",
									userFound.getId(), "Bad password");
							userTraceServicesLocal.AddTrace(userTrace);
							userFound.setBlocked(true);
							userServicesLocal.UpdateUser(userFound);
							FacesContext
									.getCurrentInstance()
									.addMessage(
											null,
											new FacesMessage(
													FacesMessage.SEVERITY_FATAL,
													"Account Blocked!",
													"Please contact a system administrator"));
						} else {
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_FATAL,
											"Bad Credentials!", userFound
													.getLoginAttempts()
													+ "attempt(s) remaining"));
						}
					}

				}

			}

			// setUser(new user());
			userFound = new User();
			return "";
		}
	}

	public void ChangePassword() throws IOException {
		String currentpasswordEncrypted = new Base64Encoder()
				.encode(CurrentPassword.getBytes());
		if (currentpasswordEncrypted.equals(user.getPassword())) {

			if (password1.equals(password2)) {
				user.setPassword(new Base64Encoder().encode(password1));
				userTrace = new UserTrace("Password Change", user.getId(), "");
				userTraceServicesLocal.AddTrace(userTrace);
				userServicesLocal.UpdateUser(user);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Profile Updated!",
								"Your Password has been changed"));
			} else {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Bad Password",
										"Please make sure that both passwords are the same"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Bad Password", "Wrong current password"));
		}
	}
	

	public void SetEmailPwd() throws MessagingException, IOException {
		if (ping()==false) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Connection Problems",
									"Please make sure that you are connected to the internet"));
		} else {

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('statusDialog').show();");
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
							return new PasswordAuthentication(username,
									password);
						}
					});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					user.getEmail()));
			message.setSubject("SYSFAC CONFIRMATION");
			message.setText("Your email password was set properly");
			try {
				Transport.send(message);
				user.setVerified(true);
				userServicesLocal.UpdateUser(user);
				userTrace = new UserTrace("changing email password",
						user.getId(), "");
				userTraceServicesLocal.AddTrace(userTrace);
				RequestContext context2 = RequestContext.getCurrentInstance();
				context2.execute("PF('statusDialog').hide();");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Profile Updated!",
								"An email was sent to your address"));

			} catch (Exception e) {
				RequestContext context2 = RequestContext.getCurrentInstance();
				context2.execute("PF('statusDialog').hide();");
				user.setEmailPwd("");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Bad Credentials",
								"Incorrect email address or password "));
			}

		}

	}

	public void ModifyButton() {
		user.setVerified(false);
	}

	public boolean Cancelbutton() {
		if (user.getEmailPwd().equals(null) || user.getEmailPwd().equals("")) {
			return false;
		}
		return true;
	}

	public void CancelButtonAct() {
		user.setVerified(true);
	}

	// test
	@PreDestroy
	public void destroy() {

	}
	
	public boolean ping() throws IOException {
		Socket socket = null;
		boolean reachable = false;
		try {
			socket = new Socket("google.com", 80);
			reachable = true;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return reachable;

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

	public boolean isRemember() {
		return Remember;
	}

	public void setRemember(boolean remember) {
		Remember = remember;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getCurrentPassword() {
		return CurrentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		CurrentPassword = currentPassword;
	}

	public Map<Integer, String> getThemes() {
		return themes;
	}

	public void setThemes(Map<Integer, String> themes) {
		this.themes = themes;
	}

	public UserTrace getUserTrace() {
		return userTrace;
	}

	public void setUserTrace(UserTrace userTrace) {
		this.userTrace = userTrace;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

}
