package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean(name = "MapBean", eager = true)
@SessionScoped
public class MapBean implements Serializable {

	/**
	 * 
	 */

	
	private MapModel simpleModel;
	private Marker marker;
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MapBean() {
		user2 = new User();

		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		simpleModel = new DefaultMapModel();

		// Shared coordinates
		LatLng coord1 = new LatLng(26.0275, 50.5500);
		LatLng coord2 = new LatLng(36.883707, 30.689216);
		LatLng coord3 = new LatLng(40.7127, 74.0059);
		LatLng coord4 = new LatLng(34.0000, 9.0000);

		// Basic marker
		simpleModel.addOverlay(new Marker(coord1, "Risk1"));
		simpleModel.addOverlay(new Marker(coord2, " Risk2"));
		simpleModel.addOverlay(new Marker(coord3, " Risk 3"));
		simpleModel.addOverlay(new Marker(coord4, "Risk 4"));
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	// tests
	
	
	public void tryGrowl() {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful",
				"Your message: azerty "));
	}

	@EJB
	private UserServicesLocal local;

	private User user2;

	public List<User> completeTheme(String query) {
		List<User> allThemes = local.GetAllUsers();
		List<User> filteredThemes = new ArrayList<User>();

		for (int i = 0; i < allThemes.size(); i++) {
			User skin = allThemes.get(i);
			if (skin.getFirst_Name().toLowerCase().startsWith(query)
					|| skin.getLast_Name().toLowerCase().startsWith(query)) {
				filteredThemes.add(skin);
			}
		}

		return filteredThemes;
	}

	public User Finduserbyname(int id) {
		return local.GetUserByid(id);
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	public void addField(){
		
	}

}
