package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@javax.faces.bean.SessionScoped
public class RedirectionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2248985821074026073L;
	private String testaff = "Summary";

	public RedirectionBean() {
	}

	public void GoToSummary() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Summary2.jsf");
		testaff = "Summary";
	}
	
	public void GoToProjectScreen() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Project_Screen.jsf");
		testaff = "Summary";
	}

	public void GoToManageProject() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ManageProject.jsf");
	}

	public void GoToReceived() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ProjectBox.jsf");
	}

	public void GoToFacInfo() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Fac_info.jsf");
	}

	public void GoToMap() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ShowRisksOnMap.jsf");
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
		testaff = "Rating";
	}

	public void GoToProject() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Project_Screen.jsf");

	}

	public void GoToSendProject() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("Send_Project.jsf");
	}

	public String getTestaff() {
		return testaff;
	}

	public void setTestaff(String testaff) {
		this.testaff = testaff;
	}

}
