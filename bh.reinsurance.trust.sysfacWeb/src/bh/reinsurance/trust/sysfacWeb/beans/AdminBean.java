package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean(name = "admin")
@SessionScoped
public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7461654699564286956L;
	private User user;
	@EJB
	private UserServicesLocal local;
	private String title;
	private String messa;
	private String b = "info";
	private Map<String, String> Severi;
	public FacesMessage.Severity op = FacesMessage.SEVERITY_INFO;

	public AdminBean() {
	}

	@PostConstruct
	public void init() {
		Severi = new HashMap<String, String>();
		Severi.put("Info", "");
		Severi.put("Warning", "");
		Severi.put("Error", "");
		Severi.put("Fatal", "");
		user = new User();
	}

	public String OnFlowProcess(FlowEvent event) {

		return event.getNewStep();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addUser() {
		System.out.println(user.getEmail());
		local.AddUser(user);
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", " "));
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();

	}

	public void ff(ActionEvent actionEvent) {
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish("/rr", new FacesMessage(title, messa));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessa() {
		return messa;
	}

	public void setMessa(String messa) {
		this.messa = messa;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public Map<String, String> getSeverity() {
		return Severi;
	}

	public void setSeverity(Map<String, String> severity) {
		Severi = severity;
	}

}
