package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import al.assu.trust.GestionImageSinistre.domain.Assets;
import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.AssetsServicesLocal;

@ManagedBean
@RequestScoped
public class AssetsBean {
	// models

	@ManagedProperty("#{login.getUser()}")
	private User user;

	private static final long serialVersionUID = 1L;

	private Assets assets;
	private List<Assets> assetsList;
	private boolean forrmDisplayed;
	@EJB
	private AssetsServicesLocal assetsServicesLocal;

	LoginBean bean;
	ExternalContext context = FacesContext.getCurrentInstance()
			.getExternalContext();

	// const
	public AssetsBean() throws IOException {

		forrmDisplayed = false;
		setAssets(new Assets());

	}

	@PostConstruct
	public void init() {
		assetsList = assetsServicesLocal.GetAllAssets();
	}
	

	public List<Assets> GetAssets() {
		return assetsServicesLocal.GetAllAssets();
	}

	// methods
	public void AddAsset() {
		assetsServicesLocal.AddAsset(assets);
		forrmDisplayed = false;
		assetsList = assetsServicesLocal.GetAllAssets();

	}
	
	public void onRowEdit(RowEditEvent event) {
		
		assetsServicesLocal.AddAsset(assets);
		assetsList = assetsServicesLocal.GetAllAssets();
		FacesMessage msg = new FacesMessage( "Asset Modified","Asset id :"+assets.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	FacesMessage msg = new FacesMessage("Edit Cancelled", "ff");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	

	public void DoDelete() {
		FacesMessage msg = new FacesMessage( "Asset Deleted","Asset id :"+assets.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
		assetsServicesLocal.DeleteAsset(assets);
		assetsList = assetsServicesLocal.GetAllAssets();
	}

	

	public void DoCancel() {
		assets = new Assets();
		forrmDisplayed = false;
	}

	public void OnRowSelected() {
		forrmDisplayed = true;
	}

	// getters setters
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

	public void setUser(User user) {
		this.user = user;
	}

}
