package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
@javax.enterprise.context.SessionScoped
public class AdminBean implements Serializable {

	/**
	 * 
	 */
	// models
	private static final long serialVersionUID = 7461654699564286956L;
	private User user;
	@EJB
	private UserServicesLocal local;
	private String title;
	private String messa;
	private String b = "info";

	// const
	public AdminBean() {
	}

	@PostConstruct
	public void init() {

		user = new User();
	}

	// mthods
	public String OnFlowProcess(FlowEvent event) {

		return event.getNewStep();
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
		eventBus.publish("/NotifyUsers", new FacesMessage(title, messa));
	}

	// const

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
