package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MailBox
 *
 */
@Entity

public class MailBox implements Serializable {

	
	private int id;
	private int user_id;
	private int user_sending_id;
	private String state;
	private String message;
	private int id_project;
	private static final long serialVersionUID = 1L;

	public MailBox() {
		super();
	}   
	@Id    
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}   
	public int getUser_sending_id() {
		return this.user_sending_id;
	}

	public void setUser_sending_id(int user_sending_id) {
		this.user_sending_id = user_sending_id;
	}   
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}   
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}   
	public int getId_project() {
		return this.id_project;
	}

	public void setId_project(int id_project) {
		this.id_project = id_project;
	}
   
}
