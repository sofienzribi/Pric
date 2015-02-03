package bh.reinsurance.trust.sysfacWeb.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.FlowEvent;

@ManagedBean
@RequestScoped
public class AdminBean {

	public AdminBean() {
		// TODO Auto-generated constructor stub
	}

	public boolean isSkip() {
		return Skip;
	}

	public void setSkip(boolean skip) {
		Skip = skip;
	}

	private boolean Skip;

	public String OnFlowProcess(FlowEvent event) {
		if (Skip) {
			Skip = false;
			return "confirm";
		}
		return event.getNewStep();
	}

}
