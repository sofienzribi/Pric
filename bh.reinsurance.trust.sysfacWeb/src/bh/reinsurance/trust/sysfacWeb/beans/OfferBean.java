package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import al.assu.trust.GestionImageSinistre.domain.Offer;
import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.OfferServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.ProjectServicesLocal;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean
@RequestScoped
public class OfferBean implements Serializable {

	/**
	 * 
	 */
	//models
	private static final long serialVersionUID = 1L;
	private Offer offer;
	

	@ManagedProperty("#{projectBean.getProject3()}")
	private Project project;
	private User user;
	@EJB
	private OfferServicesLocal offerServicesLocal;
	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private ProjectServicesLocal projectServicesLocal;
	//const
	public OfferBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		if(project.getNameOfTheProject()!=null){
			offer = offerServicesLocal.GetOffer(project.getId());
		}
		
	}
	//methods

	public void SaveOffer() {
		offerServicesLocal.AddOffer(offer);
		offer = offerServicesLocal.GetOffer(project.getId());
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Information saved !!", "offer saved"));
	}
	
	//getters setters
	public String getunder(int id){
		return userServicesLocal.GetFirstAndLast(id);
	}
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
