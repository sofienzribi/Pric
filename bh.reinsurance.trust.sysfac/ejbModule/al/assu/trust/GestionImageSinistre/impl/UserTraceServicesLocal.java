package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.User;
import al.assu.trust.GestionImageSinistre.domain.UserTrace;

@Local
public interface UserTraceServicesLocal {
	public List<UserTrace> GetAllTraces();
	public void AddTrace(UserTrace trace);
	public List<UserTrace> FindTracesByuser(User user);
	public UserTrace FindTrace(int id); 
}
