package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import al.assu.trust.GestionImageSinistre.domain.Assets;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.AssetsServicesLocal;

@ManagedBean
@RequestScoped
public class AssetsBean {
	/**
	 * 
	 */
	@ManagedProperty("#{login.getUser()}")
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	private static final long serialVersionUID = 1L;

	private Assets assets;
	private List<Assets> assetsList;
	private boolean forrmDisplayed;
	@EJB
	private AssetsServicesLocal assetsServicesLocal;

	LoginBean bean;
	ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();

	public AssetsBean() throws IOException {
		
		forrmDisplayed = false;
		setAssets(new Assets());

	}

	@PostConstruct
	public void init() throws IOException {
		if (user.getLogin() == null) {
			
			context.redirect("login.jsf");
			return;
		}
		System.out.println(user.login);
	}

	public List<Assets> GetAssets() {
		return assetsServicesLocal.GetAllAssets();
	}

	public void AddAsset() {
		assetsServicesLocal.AddAsset(assets);
		forrmDisplayed = false;
		assetsList = assetsServicesLocal.GetAllAssets();

	}

	public String Testconnection() {
		if (user.getLogin() == null) {
			return "login?faces-redirect=true";

		}
		return null;
	}

	public void DoDelete() {
		forrmDisplayed = false;
		assetsServicesLocal.DeleteAsset(assets);
		assetsList = assetsServicesLocal.GetAllAssets();
	}

	public void DoNew() {
		assets = new Assets();
		forrmDisplayed = true;
	}

	public void DoCancel() {
		assets = new Assets();
		forrmDisplayed = false;
	}

	public void OnRowSelected() {
		forrmDisplayed = true;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public List<Assets> getAssetsList() {
		return assetsList;
	}

	public void setAssetsList(List<Assets> assetsList) {
		this.assetsList = assetsList;
	}

	public boolean isForrmDisplayed() {
		return forrmDisplayed;
	}

	public void setForrmDisplayed(boolean forrmDisplayed) {
		this.forrmDisplayed = forrmDisplayed;
	}

}
