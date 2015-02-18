package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
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
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(26.0275, 50.5500);
        LatLng coord2 = new LatLng(36.883707, 30.689216);
        LatLng coord3 = new LatLng(40.7127, 74.0059);
        LatLng coord4 = new LatLng(34.0000, 9.0000);
          
        //Basic marker
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

}
