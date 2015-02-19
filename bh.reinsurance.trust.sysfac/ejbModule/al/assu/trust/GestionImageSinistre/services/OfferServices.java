package al.assu.trust.GestionImageSinistre.services;

import al.assu.trust.GestionImageSinistre.domain.Offer;
import al.assu.trust.GestionImageSinistre.impl.OfferServicesLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class OfferServices
 */
@Stateless
public class OfferServices implements OfferServicesLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager entityManager;
    public OfferServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void AddOffer(Offer offer) {
	entityManager.merge(offer);
		
	}

	@Override
	public Offer GetOffer(int id_proj) {
		return (Offer) entityManager
				.createQuery("select p from Offer p where p.id_project=:c")
				.setParameter("c", id_proj).getSingleResult();
	}

}
