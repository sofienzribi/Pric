package al.assu.trust.GestionImageSinistre.domain.ReportingClasses;

public class AccountantAndAuditorsReporting {

	private String nameOfTheProject;
	private String user;
	private String broker;
	private String insured;

	public String getNameOfTheProject() {
		return nameOfTheProject;
	}

	public void setNameOfTheProject(String nameOfTheProject) {
		this.nameOfTheProject = nameOfTheProject;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getInsured() {
		return insured;
	}

	public void setInsured(String insured) {
		this.insured = insured;
	}

}
