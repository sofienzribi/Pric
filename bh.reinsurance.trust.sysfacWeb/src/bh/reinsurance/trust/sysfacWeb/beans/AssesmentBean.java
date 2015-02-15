package bh.reinsurance.trust.sysfacWeb.beans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AssesmentBean {
	
	private Map<String, String> Quality;
	private Map<String, String> LossRatio;
	private Map<String, String> ConsClass;
	private Map<String, String> ResponseTime;

	public AssesmentBean() {

	}
	
	@PostConstruct
	public void init() {
		
		Quality = new HashMap<String, String>();
		LossRatio = new HashMap<String, String>();
		ConsClass = new HashMap<String, String>();
		ResponseTime = new HashMap<String, String>();
		ResponseTime.put("<=5 min", "5-");
		ResponseTime.put("<=10 min", "10-");
		ResponseTime.put("<=15 min", "15-");
		ResponseTime.put(">15 min", "15+");

		ConsClass.put("A", "A");
		ConsClass.put("B", "B");
		ConsClass.put("C", "C");

		LossRatio.put("<20%", "<20%");
		LossRatio.put("<50%", "<50%");
		LossRatio.put("<100%", "<100%");
		LossRatio.put(">100%", ">100%");
		LossRatio.put("New Risk", "new");

		Quality.put("Excellent", "excellent");
		Quality.put("Good", "good");
		Quality.put("Standard", "standard");
		Quality.put("Substandard", "substandard");
	}

	public Map<String, String> getQuality() {
		return Quality;
	}

	public void setQuality(Map<String, String> quality) {
		Quality = quality;
	}

	public Map<String, String> getLossRatio() {
		return LossRatio;
	}

	public void setLossRatio(Map<String, String> lossRatio) {
		LossRatio = lossRatio;
	}

	public Map<String, String> getConsClass() {
		return ConsClass;
	}

	public void setConsClass(Map<String, String> consClass) {
		ConsClass = consClass;
	}

	public Map<String, String> getResponseTime() {
		return ResponseTime;
	}

	public void setResponseTime(Map<String, String> responseTime) {
		ResponseTime = responseTime;
	}
}
