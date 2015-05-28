package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;

import org.primefaces.context.RequestContext;

import al.assu.trust.GestionImageSinistre.domain.PlaccountantandauditorsMeasure;
import al.assu.trust.GestionImageSinistre.domain.Project;
import al.assu.trust.GestionImageSinistre.impl.CrudBasicLocal;

@javax.faces.bean.ManagedBean(name = "applicationbean")
@ApplicationScoped
public class ApplicationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	public ApplicationBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

	}

	



}
