package al.assu.trust.GestionImageSinistre.impl;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.User;

@Local
public interface UserServicesLocal {
	public User login(String login, String password);

	public User GetUserByid(int id);

	public void AddUser(User user);
}
