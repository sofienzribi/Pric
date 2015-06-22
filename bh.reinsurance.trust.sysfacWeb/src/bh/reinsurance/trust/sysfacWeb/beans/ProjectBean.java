package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import al.assu.trust.GestionImageSinistre.domain.Assets;
import al.assu.trust.GestionImageSinistre.domain.MailBox;
import al.assu.trust.GestionImageSinistre.domain.PIaccandAudit;
import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.domain.UserTrace;
import al.assu.trust.GestionImageSinistre.impl.AssetsServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.MailBoxServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.PlaccandAuditServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.ProjectServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserTraceServicesLocal;

@ManagedBean()
@SessionScoped
public class ProjectBean implements Serializable {

	// models

	@ManagedProperty("#{login.getUser()}")
	private User user2;

	private boolean passwordmsg;
	private String pwdcheck;
	private String DisplayProjectSelectionByuser = "all";
	private String DisplayProjectByTool = "all";
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
	private Project project3;
	@EJB
	private AssetsServicesLocal assetsServicesLocal;
	@EJB
	private ProjectServicesLocal local;

	@EJB
	private UserServicesLocal local2;
	private List<User> SendToUsers;

	@EJB
	private MailBoxServicesLocal mailBoxServicesLocal;
	@EJB
	private PlaccandAuditServicesLocal auditServicesLocal;
	@EJB
	private UserTraceServicesLocal userTraceServicesLocal;

	private UserTrace userTrace;

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
	private boolean DisplayMailSubj;
	private String DisplaySelection = "all";
	private List<MailBox> mailBoxs2;
	private Map<String, String> Tool;
	private List<Assets> assets2;
	private Map<String, String> Territory;
	private String TerritoryChoice;

	// const

	public ProjectBean() {

		box = new MailBox();
		project3 = new Project();
		CheckboxDisplay = false;
		project2 = new Project();
	}

	@PostConstruct
	public void init() {
		Tool = new HashMap<String, String>();
		Territory = new HashMap<String, String>();
		Territory.put("1", "GCC");
		Territory.put("1.25", "Outside GCC");
		Tool.put("account", "PI accountants and auditors");
		Tool.put("property", "Property and Onshore");
		DisplayMailSubj = false;
		DisabledButtonProject = false;
		DisabledButtonProjectSendClose = true;
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
		setMailBoxs2(mailBoxServicesLocal.GetSentMailBox(user2.getId()));
		NumberProjectReceived = GetMails();
		passwordmsg = true;
		PopDisplayed = false;
		projects = local.GetAllProjects();
	}

	// methods
	public void UpdateProject() {

		local.UpdateProject(proojectbyuser);
		projectsbyuser = local.GetProjectsByUser(user2);
		DisplayProjectManagButton = false;
		projects = local.GetAllProjects();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('popup').hide();");

	}

	public void setDisplayMSG() {

		List<MailBox> boxs = new ArrayList<MailBox>();
		List<MailBox> boxs2 = new ArrayList<MailBox>();
		boxs = mailBoxServicesLocal.GetMailBoxByUserId(user2.getId());
		if (DisplaySelection.equals("all")) {
			setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2.getId()));

		} else if (DisplaySelection.equals("SEEN")) {

			for (MailBox a : boxs) {
				if (a.getState().equals("SEEN")) {
					boxs2.add(a);
				}
			}
			setMailBoxs(boxs2);
		} else {
			for (MailBox a : boxs) {
				if (a.getState().equals("NOT SEEN")) {
					boxs2.add(a);
				}
			}
			setMailBoxs(boxs2);
		}

	}

	public void Displayprojectbyfilters() {
		if (DisplayProjectSelectionByuser.equals("all")
				&& DisplayProjectByTool.equals("all")) {
			projects = local.GetAllProjects();
		} else {
			if (DisplayProjectSelectionByuser.equals("Mine")
					&& DisplayProjectByTool.equals("all")) {
				projects = local.GetProjectsByUser(user2);
			} else {
				if (DisplayProjectSelectionByuser.equals("Mine")) {
					List<Project> a = local.GetProjectsByUser(user2);
					List<Project> b = new ArrayList<Project>();
					for (int i = 0; i < a.size(); i++) {
						if (a.get(i).getTool().equals(DisplayProjectByTool)) {
							b.add(a.get(i));
						}

					}
					projects = b;
				} else {
					List<Project> a = local.GetAllProjects();
					List<Project> b = new ArrayList<Project>();
					for (int i = 0; i < a.size(); i++) {
						if (a.get(i).getTool().equals(DisplayProjectByTool)) {
							b.add(a.get(i));
						}

					}
					projects = b;
				}
			}
		}

	}

	public void DeleteProjectTest() {
		assets2 = assetsServicesLocal.GetAssetsByIdProject(proojectbyuser
				.getId());
		if (proojectbyuser.getTool().equals("Property and Onshore")) {
			for (int i = 0; i < assets2.size(); i++) {
				assetsServicesLocal.DeleteAsset(assets2.get(i));
			}
		}

		if (proojectbyuser.getTool().equals("PI accountants and auditors")) {
			PIaccandAudit audit1 = auditServicesLocal
					.GetByIdProject(proojectbyuser.getId());
			auditServicesLocal.delete(audit1);
		}
		userTrace = new UserTrace("Delete Project", user2.getId(),
				"Project Name : " + proojectbyuser.getNameOfTheProject());
		userTraceServicesLocal.AddTrace(userTrace);
		local.DeleteProject(proojectbyuser.getId());

		projectsbyuser = local.GetProjectsByUser(user2);
		DisplayProjectManagButton = false;
		DisplayRating = "none";
		setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2.getId()));
		projects = local.GetAllProjects();
	}

	public void OnProjManSelect() {
		DisplayProjectManagButton = true;

	}

	public String FormatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(date);
	}

	public String GetCalendarMinDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		return dateFormat.format(new Date());

	}

	private List<Project> ProjectExpringThisMonth = new ArrayList<Project>();

	public void GoToProjectExpiringThisMonth() {

		List<Project> proj = new ArrayList<Project>();
		for (Project a : projectsbyuser) {
			if ((a.getExpiry_date().getMonth() == new Date().getMonth())
					&& (a.getExpiry_date().getYear() == new Date().getYear())) {
				proj.add(a);
			}
		}

		
		
		
		ProjectExpringThisMonth = proj;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('diag1').show();");
	}

	private List<Project> ProjectExpired = new ArrayList<Project>();

	public void GoToExpiredProjects() {

		List<Project> proj = new ArrayList<Project>();
		for (Project a : projectsbyuser) {
			if ((a.getExpiry_date().before(new Date()))
					&& (a.getQuoted_Date() == null)) {
				proj.add(a);
			}
		}

		ProjectExpired = proj;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('diag2').show();");
	}

	public void GoToReceived() throws IOException {
		if (GetMails() > 0) {
			DisplaySelection = "NOT SEEN";
		}
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ProjectBox.jsf");
	}

	public int GetProjectExpThisMonth() {
		int a = 0;

		for (Project b : projectsbyuser) {
			if ((b.getExpiry_date().getMonth() == new Date().getMonth())
					&& (b.getExpiry_date().getYear() == new Date().getYear())) {
				a++;
			}
		}

		return a;
	}

	public int GetExpiredProjects() {
		int a = 0;

		for (Project b : projectsbyuser) {
			if ((b.getExpiry_date().before(new Date()))
					&& (b.getQuoted_Date() == null)) {
				a++;
			}
		}

		return a;
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

	public String openprojecttest() {

		if (project.getPrivacy() == true) {
			project3 = project;
			project = new Project();
			PopDisplayed = false;
			DisplayRating = "true";
			DisabledButtonProject = true;
			DisabledButtonProjectSendClose = false;
			if (project3.getTool().equals("PI accountants and auditors")) {

				userTrace = new UserTrace("Open project", user2.getId(),
						"project name:" + project3.getNameOfTheProject());
				userTraceServicesLocal.AddTrace(userTrace);
				return "PIAccountantandandAuditorsTool?faces-redirect=true";
			}
			if (project3.getTool().equals("Property and Onshore")) {
				return "test2?faces-redirect=true";
			}

			return null;
		} else {
			if (project.getUser() == user2.getId()) {
				project3 = project;
				project = new Project();
				PopDisplayed = false;
				DisplayRating = "true";
				DisabledButtonProject = true;
				DisabledButtonProjectSendClose = false;

				if (project3.getTool().equals("PI accountants and auditors")) {
					return "PIAccountantandandAuditorsTool?faces-redirect=true";
				}
				if (project3.getTool().equals("Property and Onshore")) {
					return "test2?faces-redirect=true";
				}

				return null;

			} else {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('popup1').show();");
				return null;
			}
		}

	}

	public String createprojtest() {
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
			if (project2.getTool().equals("Property and Onshore")) {
				project2.setUser(user2.getId());
				local.NewProject(project2);
				project3 = local.GetProjectByName(project2
						.getNameOfTheProject());
				project2 = new Project();
				DisplayRating = "true";
				projects = local.GetAllProjects();

				projectsbyuser = local.GetProjectsByUser(user2);
				DisabledButtonProject = true;
				DisabledButtonProjectSendClose = false;
				return "test2?faces-redirect=true";
			}
			if (project2.getTool().equals("PI accountants and auditors")) {
				project2.setUser(user2.getId());
				local.NewProject(project2);
				project3 = local.GetProjectByName(project2
						.getNameOfTheProject());
				project2 = new Project();
				projects = local.GetAllProjects();
				projectsbyuser = local.GetProjectsByUser(user2);
				PIaccandAudit audit = new PIaccandAudit();
				audit.setTerritory(TerritoryChoice);
				audit.setIdproj(project3.getId());
				auditServicesLocal.add(audit);
				DisabledButtonProject = true;
				DisabledButtonProjectSendClose = false;
				userTrace = new UserTrace("Create project", user2.getId(),
						"project name:" + project3.getNameOfTheProject());
				userTraceServicesLocal.AddTrace(userTrace);
				return "PIAccountantandandAuditorsTool?faces-redirect=true";
			}
			return null;

		}
	}

	public String GetUserName(int id) {

		User user2 = local2.GetUserByid(id);
		return user2.getFirst_Name() + " " + user2.getLast_Name();
	}

	public String GetNameOfTheProject(int id) {

		Project proj = local.GetProjectById(id);
		if (proj == null) {
			return "The project has been deleted by his owner";
		} else {
			return proj.getNameOfTheProject();
		}

	}

	// sending proj : displaymailsubj means if sendbyemail checked or not

	private UploadedFile uploadedFile;

	public void CopyPDFifDontExist(FileUploadEvent event) throws IOException {
		uploadedFile = event.getFile();

		File sourceFile;
		boolean testName;
		try {
			sourceFile = new File("/Users/zribisofien/Desktop/PDFGEN/"
					+ uploadedFile.getFileName());
			FileInputStream fis = new FileInputStream(sourceFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			testName = true;

		} catch (Exception e) {
			testName = false;
		}
		if (testName == true) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('UploadPDF').hide()");
		} else {

			String filename = FilenameUtils.getName(uploadedFile.getFileName());
			InputStream input = uploadedFile.getInputstream();
			OutputStream output = new FileOutputStream(new File(
					"/Users/zribisofien/Desktop/PDFGEN/", filename));
			try {
				IOUtils.copy(input, output);
			} finally {
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(output);
			}

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('UploadPDF').hide()");
		}

	}

	public void SendProject() throws MessagingException, UnknownHostException {

		RequestContext context = RequestContext.getCurrentInstance();
		if (DisplayMailSubj == false) {
			box.setId_project(project3.getId());
			box.setUser_sending_id(user2.getId());
			box.setState("NOT SEEN");
			for (int i = 0; i < SendToUsers.size(); i++) {
				box.setUser_id(SendToUsers.get(i).getId());
				mailBoxServicesLocal.CreateMailBox(box);
				setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2
						.getId()));
				userTrace = new UserTrace("Send project", user2.getId(),
						"project name:" + project3.getNameOfTheProject());
				userTraceServicesLocal.AddTrace(userTrace);
				NumberProjectReceived = GetMails();
				box = new MailBox();
				SendToUsers = new ArrayList<User>();

				context.execute("PF('statusDialog').hide();");
				FacesContext.getCurrentInstance().addMessage(
						"e",
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"project Sent!", ""));

			}

		} else {
			// Internet check !!!
			if ("127.0.0.1".equals(InetAddress.getLocalHost().getHostAddress()
					.toString())) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Internet Problems",
										"Please make sure that you are connected to the internet"));
			} else {

				String from = user2.getEmail();
				final String username = user2.getEmail();
				final String password = user2.getEmailPwd();
				Properties props = new Properties();
				// GMAIL SMTP
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
				message.setSubject(box.getSubj());
				box.setId_project(project3.getId());
				box.setUser_sending_id(user2.getId());
				box.setState("NOT SEEN");
				for (int i = 0; i < SendToUsers.size(); i++) {
					if (DisplayMailSubj == true) {
						message.addRecipient(Message.RecipientType.TO,
								new InternetAddress(SendToUsers.get(i)
										.getEmail()));
					}

					box.setUser_id(SendToUsers.get(i).getId());
					mailBoxServicesLocal.CreateMailBox(box);
				}

				BodyPart messageBodyPart = new MimeBodyPart();

				// Fill the message
				messageBodyPart.setText(box.getMessage());

				// Create a multipar message
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);

				// Part two is attachment

				messageBodyPart = new MimeBodyPart();
				String filename = "/Users/zribisofien/Desktop/PDFGEN/"
						+ uploadedFile.getFileName();
				DataSource source = new FileDataSource(filename);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename);
				multipart.addBodyPart(messageBodyPart);

				message.setContent(multipart);
				try {

					// Send message

					Transport.send(message);
					userTrace = new UserTrace("Send project", user2.getId(),
							"project name:" + project3.getNameOfTheProject()
									+ " sent via email");
					userTraceServicesLocal.AddTrace(userTrace);
					setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2
							.getId()));
					NumberProjectReceived = GetMails();
					box = new MailBox();
					context.execute("PF('statusDialog').hide();");
					FacesContext.getCurrentInstance().addMessage(
							"e",
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"project Sent!", ""));
					System.out.println("message sent successfully....");
				} catch (MessagingException e) {
					System.out.println("Problem while sending email");

					e.printStackTrace();
				}

			}

		}

	}

	public void VerifyMail() {
		if (DisplayMailSubj == true) {

			if (user2.isVerified() == false) {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('verif').show()");
			} else {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('UploadPDF').show()");
			}

		}

	}

	public void CancelButtonPDF() {
		DisplayMailSubj = false;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('UploadPDF').hide()");
	}

	public void cancelbutton() {
		DisplayMailSubj = false;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('verif').hide()");
	}

	// send proj ends

	public void CancelNewProj() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('popup').hide()");
	}

	public void Doitnow() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("UserProfile.jsf");
		DisplayMailSubj = false;
	}

	// verify password for project
	public String verifypassword() {

		if (pwdcheck.equals(project.getPassword())) {
			project3 = project;
			project = new Project();
			PopDisplayed = false;

			DisplayRating = "true";
			DisabledButtonProject = true;
			DisabledButtonProjectSendClose = false;
			if (project3.getTool().equals("PI accountants and auditors")) {
				userTrace = new UserTrace("Open project", user2.getId(),
						"project name:" + project3.getNameOfTheProject());
				userTraceServicesLocal.AddTrace(userTrace);
				return "PIAccountantandandAuditorsTool?faces-redirect=true";
			} else {
				if (project3.getTool().equals("Property and Onshore")) {
					return "test2?faces-redirect=true";
				}
			}
			return null;
		} else {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Bad Credentials!", "Bad Credentials"));
			return null;
		}
	}

	// complete name
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

	// Opening a received project

	public String OpeningSentProject() {

		project = local.GetProjectById(mailBox.getId_project());
		project3 = project;
		MailBox box2 = mailBoxServicesLocal.GetMailBox(mailBox.getId());
		box2.setState("SEEN");

		mailBoxServicesLocal.UpdateMailBox(box2);
		setMailBoxs(mailBoxServicesLocal.GetMailBoxByUserId(user2.getId()));
		NumberProjectReceived = GetMails();
		DisplayButtonMailBox = false;
		mailBox = new MailBox();
		DisabledButtonProject = true;
		DisabledButtonProjectSendClose = false;
		DisplayRating = "true";
		userTrace = new UserTrace("Open project", user2.getId(),
				"project name :" + project3.getNameOfTheProject());
		userTraceServicesLocal.AddTrace(userTrace);
		DisplaySelection = "all";
		if (project3.getTool().equals("PI accountants and auditors")) {
			return "PIAccountantandandAuditorsTool?faces-redirect=true";
		} else {
			if (project3.getTool().equals("Property and Onshore")) {
				return "test2?faces-redirect=true";
			}
		}
		return null;
	}

	public void CloseDProject() throws IOException {
		userTrace = new UserTrace("Close project", user2.getId(),
				"project name:" + project3.getNameOfTheProject());
		userTraceServicesLocal.AddTrace(userTrace);
		project3 = new Project();
		DisplayRating = "none";
		DisabledButtonProject = false;
		DisabledButtonProjectSendClose = true;
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Project_Screen.jsf");
	}

	// redirection when pressing the rating tool button
	public void GoToRatingTool() {
		if (project3.getTool().equals("PI accountants and auditors")) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("PIAccountantandandAuditorsTool.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());

			}
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("test2.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	// making the project quoted
	public void MakeThisProjectQuoted() {
		if (project3.getQuoted_Date() != null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Project Already quoted!", ""));
		} else {
			project3.setQuoted_Date(new Date());
			local.UpdateProject(project3);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Project Quoted!", ""));
		}
	}

	public String GetQuotedDate() {
		if (project3.getQuoted_Date() == null) {
			return "";
		} else {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			return dateFormat.format(project3.getQuoted_Date());
		}
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

	public boolean isDisplayMailSubj() {
		return DisplayMailSubj;
	}

	public void setDisplayMailSubj(boolean displayMailSubj) {
		DisplayMailSubj = displayMailSubj;
	}

	public String getDisplaySelection() {
		return DisplaySelection;
	}

	public void setDisplaySelection(String displaySelection) {
		DisplaySelection = displaySelection;
	}

	public List<MailBox> getMailBoxs2() {
		return mailBoxs2;
	}

	public void setMailBoxs2(List<MailBox> mailBoxs2) {
		this.mailBoxs2 = mailBoxs2;
	}

	public Map<String, String> getTool() {
		return Tool;
	}

	public void setTool(Map<String, String> tool) {
		Tool = tool;
	}

	public List<Assets> getAssets2() {
		return assets2;
	}

	public void setAssets2(List<Assets> assets2) {
		this.assets2 = assets2;
	}

	public String getDisplayProjectSelectionByuser() {
		return DisplayProjectSelectionByuser;
	}

	public void setDisplayProjectSelectionByuser(
			String displayProjectSelectionByuser) {
		DisplayProjectSelectionByuser = displayProjectSelectionByuser;
	}

	public String getDisplayProjectByTool() {
		return DisplayProjectByTool;
	}

	public void setDisplayProjectByTool(String displayProjectByTool) {
		DisplayProjectByTool = displayProjectByTool;
	}

	public Map<String, String> getTerritory() {
		return Territory;
	}

	public void setTerritory(Map<String, String> territory) {
		Territory = territory;
	}

	public String getTerritoryChoice() {
		return TerritoryChoice;
	}

	public void setTerritoryChoice(String territoryChoice) {
		TerritoryChoice = territoryChoice;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public UserTrace getUserTrace() {
		return userTrace;
	}

	public void setUserTrace(UserTrace userTrace) {
		this.userTrace = userTrace;
	}

	public List<Project> getProjectExpringThisMonth() {
		return ProjectExpringThisMonth;
	}

	public void setProjectExpringThisMonth(List<Project> projectExpringThisMonth) {
		ProjectExpringThisMonth = projectExpringThisMonth;
	}

	public List<Project> getProjectExpired() {
		return ProjectExpired;
	}

	public void setProjectExpired(List<Project> projectExpired) {
		ProjectExpired = projectExpired;
	}

}
