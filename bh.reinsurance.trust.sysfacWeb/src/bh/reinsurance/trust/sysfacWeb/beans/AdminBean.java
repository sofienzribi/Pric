package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.domain.UserTrace;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserTraceServicesLocal;

@ManagedBean(name = "admin")
@SessionScoped
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
	private List<User> users;
	private String SelectedValue = "all";
	@EJB
	private UserTraceServicesLocal userTraceServicesLocal;

	private List<UserTrace> userTraces;

	// const
	public AdminBean() {
	}

	@PostConstruct
	public void init() {

		users = local.GetAllUsers();
		getlistUsers();
		userTraces = userTraceServicesLocal.GetAllTraces();

		user = new User();
	}

	// mthods
	public List<User> getlistUsers() {
		List<User> list = new ArrayList<User>();
		for (User a : users) {
			if (!a.getDepartment().equals("admin")) {
				list.add(a);
			}
			users = list;
		}
		return users;
	}

	public String getUsername(int id) {
		return local.GetFirstAndLast(id);
	}

	public String OnFlowProcess(FlowEvent event) {

		return event.getNewStep();
	}

	public void refreshList() {
		OnUserChange();
	}

	public void addUser() {
		System.out.println(user.getEmail());
		local.AddUser(user);
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", " "));
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();

	}

	// notification to users
	public void SendNotif(ActionEvent actionEvent) {
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish("/NotifyUsers", new FacesMessage(title, messa));
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Notification Sent", " "));
	}

	// User trace change filteruser
	public void OnUserChange() {
		if (SelectedValue.equals("all")) {
			userTraces = userTraceServicesLocal.GetAllTraces();
		} else {
			System.out.println(SelectedValue);
			userTraces = userTraceServicesLocal.FindTracesByuser(local
					.GetUserByid(Integer.parseInt(SelectedValue)));

		}
	}

	// const

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addthis() {

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

	public List<UserTrace> getUserTraces() {
		return userTraces;
	}

	public void setUserTraces(List<UserTrace> userTraces) {
		this.userTraces = userTraces;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getSelectedValue() {
		return SelectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		SelectedValue = selectedValue;
	}

}
