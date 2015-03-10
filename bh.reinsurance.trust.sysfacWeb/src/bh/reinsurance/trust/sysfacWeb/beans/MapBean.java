package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;


@ManagedBean(name = "MapBean", eager = true)
@SessionScoped
public class MapBean implements Serializable {
	//models
	private MapModel simpleModel;
	private Marker marker;
	private static final long serialVersionUID = 1L;
	//const
	public MapBean() {
		user2 = new User();

		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		simpleModel = new DefaultMapModel();

		// Shared coordinates
		LatLng coord1 = new LatLng(26.0275, 50.5500);
		LatLng coord2 = new LatLng(36.883707, 30.689216);
		LatLng coord3 = new LatLng(40.7127, 74.0059);
		LatLng coord4 = new LatLng(34.0000, 9.0000);

		// Basic marker
		simpleModel.addOverlay(new Marker(coord1, "Risk1"));
		simpleModel.addOverlay(new Marker(coord2, " Risk2"));
		simpleModel.addOverlay(new Marker(coord3, " Risk 3"));
		simpleModel.addOverlay(new Marker(coord4, "Risk 4"));
	}
	
	//methods

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}
	
	//get set
	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	// tests

	public void tryGrowl() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful",
				"Your message: azerty "));
	}

	@EJB
	private UserServicesLocal local;

	private User user2;

	public List<User> completeTheme(String query) {
		List<User> allThemes = local.GetAllUsers();
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

	public User Finduserbyname(int id) {
		return local.GetUserByid(id);
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public void addField() {

	}

	private UploadedFile uploadedFile;

	public void sendMail() {
		String to = "sofien.zribi@esprit.tn";// change accordingly
		String from = "sofien.zribi@esprit.tn";
		final String username = "sofien.zribi@esprit.tn";
		final String password = "azertycode0000";
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

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("Ping");
			message.setText("Hello, this is example of sending email  ");
			System.out.println("well done sofien");
			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");
		} catch (MessagingException e) {
			System.out.println("ff sofien");
			e.printStackTrace();
		}

	}

	public void sendMailWithAtta() {
		String to = "omar.azzabi@esprit.tn";// change accordingly
		String from = "sofien.zribi@esprit.tn";
		final String username = "sofien.zribi@esprit.tn";
		final String password = "azertycode0000";
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

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("PING");
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText("try 1 try 1");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "/Users/zribisofien/Desktop/PDFGEN/liab.pdf";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);
			System.out.println("message created successfully....");
			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");
		} catch (MessagingException e) {
			System.out.println("message Failed....");
			e.printStackTrace();
		}

	}

	public void retfile() {
		
		if (uploadedFile != null) {

			System.out.println(uploadedFile.getFileName());
		} else {

			System.out.println("nulyŽwi");
		}
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
