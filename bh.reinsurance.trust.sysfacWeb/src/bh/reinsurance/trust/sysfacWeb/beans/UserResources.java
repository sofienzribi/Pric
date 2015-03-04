package bh.reinsurance.trust.sysfacWeb.beans;

import javax.faces.application.FacesMessage;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint(value ="/rr")
public class UserResources {

	@OnMessage(encoders = { JSONEncoder.class })
	public FacesMessage onMessage(FacesMessage message) {
		System.out.println("this works too");
		return message;

	}

}
