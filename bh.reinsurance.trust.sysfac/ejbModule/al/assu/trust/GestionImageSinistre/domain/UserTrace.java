package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: UserTrace
 * 
 */
@Entity
public class UserTrace implements Serializable {

	private int id;
	private String activity;
	private int userId;
	private String Description;
	private String Date;
	private String time;
	private static final long serialVersionUID = 1L;

	public UserTrace() {
		super();
	}

	public UserTrace(String activity, int userId, String description) {
		super();
		this.activity = activity;
		this.userId = userId;
		Description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
