package al.assu.trust.GestionImageSinistre.impl;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Offer;

@Local
public interface OfferServicesLocal {
		public void AddOffer(Offer offer);
		public Offer GetOffer(int id_proj);
}
