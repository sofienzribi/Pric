package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean(name = "admin")
@ViewScoped
public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7461654699564286956L;
	private User user;
	@EJB
	private UserServicesLocal local;

	public AdminBean() {
	}

	@PostConstruct
	public void init() {
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

		context.addMessage(null, new FacesMessage("Successful",
				" "));
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();

	}

	public void tt() {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage("growl", new FacesMessage("Successful",
				"Your message: "));
	}

	public void ff(ActionEvent actionEvent) {
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish("/rr", new FacesMessage("zz", "ss"));
		System.out.println("sdf");
	}
}
