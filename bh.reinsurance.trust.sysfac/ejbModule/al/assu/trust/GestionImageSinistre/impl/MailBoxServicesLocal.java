package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.MailBox;

@Local
public interface MailBoxServicesLocal {
	public void CreateMailBox(MailBox box);
	public List<MailBox> GetMailBoxByUserId(int User_Id);
	public MailBox GetMailBox(int id);
	public void UpdateMailBox(MailBox box);
}
