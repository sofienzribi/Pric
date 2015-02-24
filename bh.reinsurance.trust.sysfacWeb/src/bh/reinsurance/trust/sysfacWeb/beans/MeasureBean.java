package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.Measure;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.MeasureServicesLocal;

@ManagedBean
@SessionScoped
public class MeasureBean implements Serializable {

	/**
	 * 
	 */
	// Models
	@ManagedProperty("#{login.getUser()}")
	private User user;
	private Measure measure;
	private Measure Newmeasure;
	private Measure WorkingMeasure;
	private boolean DisplayButtons;
	private List<Measure> measures;
	@EJB
	MeasureServicesLocal measureServicesLocal;
	private static final long serialVersionUID = 1L;

	// const
	public MeasureBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		DisplayButtons = false;
		Newmeasure = new Measure();
		WorkingMeasure = new Measure();
		measure = new Measure();
		measures = measureServicesLocal.GetAllMeasures();
	}

	// methods
	public void CreateMeasure() {

		if (measureServicesLocal.NameExist(Newmeasure.getName())) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Name already exists!!", "Name Exist"));

		} else {
			Newmeasure.setUserId(user.getId());
			measureServicesLocal.NewMeasure(Newmeasure);
			measures = measureServicesLocal.GetAllMeasures();
			RequestContext context = RequestContext.getCurrentInstance();
			WorkingMeasure = Newmeasure;
			Newmeasure = new Measure();
			context.execute("popup.hide();");
		}
	}

	public void OnRowSelected() {
		DisplayButtons = true;
	}

	public void DeleteMeasure() {
		measureServicesLocal.DeleteMeasure(measure);
		measure = new Measure();
		measures = measureServicesLocal.GetAllMeasures();
		DisplayButtons = false;
	}

	// getters and setters
	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public List<Measure> getMeasures() {
		return measures;
	}

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}

	public Measure getNewmeasure() {
		return Newmeasure;
	}

	public void setNewmeasure(Measure newmeasure) {
		Newmeasure = newmeasure;
	}

	public Measure getWorkingMeasure() {
		return WorkingMeasure;
	}

	public void setWorkingMeasure(Measure workingMeasure) {
		WorkingMeasure = workingMeasure;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDisplayButtons() {
		return DisplayButtons;
	}

	public void setDisplayButtons(boolean displayButtons) {
		DisplayButtons = displayButtons;
	}
}
