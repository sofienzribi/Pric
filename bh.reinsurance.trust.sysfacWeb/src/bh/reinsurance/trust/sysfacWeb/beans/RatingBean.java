package bh.reinsurance.trust.sysfacWeb.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class RatingBean implements Serializable {

	/**
	 * 
	 */
	private Map<String, String> Currency;
	private static final long serialVersionUID = 1L;

	public RatingBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		Currency=new HashMap<String, String>();
		Currency.put("USD", "usd");
		Currency.put("BHD", "bhd");
		Currency.put("EUR", "eur");
	}

	public Map<String, String> getCurrency() {
		return Currency;
	}

	public void setCurrency(Map<String, String> currency) {
		Currency = currency;
	}
}
