package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.User;

@Local
public interface UserServicesLocal {
	public User login(String login, String password);

	public User GetUserByid(int id);

	public String GetFirstAndLast(int id);

	public void AddUser(User user);

	public List<User> GetAllUsers();

	public void UpdateUser(User user);

	public User GetUserByLogin(String Login);

}
