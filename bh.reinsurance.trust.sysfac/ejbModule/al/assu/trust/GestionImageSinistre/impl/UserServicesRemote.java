package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Remote;

import al.assu.trust.GestionImageSinistre.domain.User;

@Remote
public interface UserServicesRemote {
	public User login(String login, String password);

	public User GetUserByid(int id);

	public String GetFirstAndLast(int id);

	public void AddUser(User user);

	public List<User> GetAllUsers();

	public void UpdateUser(User user);

}
