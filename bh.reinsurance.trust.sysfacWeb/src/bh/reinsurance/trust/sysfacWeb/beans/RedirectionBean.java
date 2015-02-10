package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class RedirectionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2248985821074026073L;

	public RedirectionBean() {
		// TODO Auto-generated constructor stub
	}

	public void GoToSummary() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Summary.jsf");
	}

	public void GoToAssets() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Assets.jsf");
	}

	public void GoToRiskAssesment() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("RiskAssesment.jsf");
	}

	public void GoToRating() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Rating.jsf");
	}

	public void GoToProject() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Project_Screen.jsf");
	}

	public void GoToComment() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Comment_Project.jsf");
	}

}