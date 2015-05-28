package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.*;

@Local
public interface PlaccandAuditServicesLocal {

		public void add(PIaccandAudit audit);
		public void update(PIaccandAudit audit);
		public PIaccandAudit searchById(int id);
		public void delete(PIaccandAudit audit);
		public PIaccandAudit GetByIdProject(int id);
		public List<PIaccandAudit> Getall();
	
}
