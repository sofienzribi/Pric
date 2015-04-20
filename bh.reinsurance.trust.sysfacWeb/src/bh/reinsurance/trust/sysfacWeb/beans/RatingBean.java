package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import al.assu.trust.GestionImageSinistre.domain.PIaccandAudit;
import al.assu.trust.GestionImageSinistre.impl.PlaccandAuditServicesLocal;

@ManagedBean
@SessionScoped
public class RatingBean implements Serializable {

	/**
	 * 
	 */
	// models

	private Map<String, String> Currency;
	private Map<Double, String> practise1;

	private static final long serialVersionUID = 1L;
	private PIaccandAudit iaccandAudit;
	@EJB
	private PlaccandAuditServicesLocal auditServicesLocal;

	// const
	public RatingBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		iaccandAudit = new PIaccandAudit();
		Currency = new HashMap<String, String>();
		practise1 = new HashMap<Double, String>();
		Currency.put("USD", "usd");
		Currency.put("BHD", "bhd");
		Currency.put("EUR", "eur");
		practise1.put( 0.0,"0-59%");
		practise1.put( 0.1,"60-79%");
		practise1.put( 0.15,"80% or more");
	}

	// meth
	public void add() {
		System.out.println(iaccandAudit.getAggregatelimit());

	}

	// getters setters
	public Map<String, String> getCurrency() {
		return Currency;
	}

	public void setCurrency(Map<String, String> currency) {
		Currency = currency;
	}

	public PIaccandAudit getIaccandAudit() {
		return iaccandAudit;
	}

	public void setIaccandAudit(PIaccandAudit iaccandAudit) {
		this.iaccandAudit = iaccandAudit;
	}

	public Map<Double,String> getPractise1() {
		return practise1;
	}

	public void setPractise1(Map< Double,String> practise1) {
		this.practise1 = practise1;
	}
}
