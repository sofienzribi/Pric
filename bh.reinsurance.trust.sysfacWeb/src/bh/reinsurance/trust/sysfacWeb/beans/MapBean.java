package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.impl.UserServicesLocal;

@ManagedBean(name = "MapBean", eager = true)
@ViewScoped
public class MapBean implements Serializable {
	// models
	private MapModel simpleModel;
	private Marker marker;
	private static final long serialVersionUID = 1L;
	private String MapRegion;
	private int MapZoom;
	private List<String> Regions;
	private String SelectedRegion = "all";
	@EJB
	private UserServicesLocal local;

	// const

	public MapBean() {

		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		Regions = new ArrayList<String>();
		FillCountryList();
		RegionChange();

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

	// methods

	public void FillCountryList() {
		Regions.add("Europe");
		Regions.add("Asia");
		Regions.add("North America");
		Regions.add("South Amercia");
		Regions.add("Africa");

	}

	public String RegionChange() {

		if (SelectedRegion.equals("all")) {
			MapRegion = "26.0275, 50.5500";
			MapZoom = 2;
			return null;

		}
		if (SelectedRegion.equals("Europe")) {
			MapRegion = "49.5,22";
			MapZoom = 3;
			return null;
		}
		if (SelectedRegion.equals("Asia")) {
			MapRegion = "29,100";
			MapZoom = 3;
			return null;
		}

		if (SelectedRegion.equals("North America")) {
			MapRegion = "37.5,-110";
			MapZoom = 3;
			return null;
		}
		if (SelectedRegion.equals("South Amercia")) {
			MapRegion = "-21.7351043,-63.28125";
			MapZoom = 3;
			return null;
		}
		if (SelectedRegion.equals("Africa")) {
			MapRegion = "0.2136714,16.98485";
			MapZoom = 3;
			return null;
		}

		return null;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	// get set
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

	public List<String> getRegions() {
		return Regions;
	}

	public void setRegions(List<String> regions) {
		Regions = regions;
	}

	public String getSelectedRegion() {
		return SelectedRegion;
	}

	public void setSelectedRegion(String selectedRegion) {
		SelectedRegion = selectedRegion;
	}

	public String getMapRegion() {
		return MapRegion;
	}

	public void setMapRegion(String mapRegion) {
		MapRegion = mapRegion;
	}

	public int getMapZoom() {
		return MapZoom;
	}

	public void setMapZoom(int mapZoom) {
		MapZoom = mapZoom;
	}

	public User Finduserbyname(int id) {
		return local.GetUserByid(id);
	}

}
