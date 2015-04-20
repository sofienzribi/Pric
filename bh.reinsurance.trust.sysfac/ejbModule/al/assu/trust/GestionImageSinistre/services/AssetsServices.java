package al.assu.trust.GestionImageSinistre.services;

import java.util.List;

import al.assu.trust.GestionImageSinistre.domain.Assets;
import al.assu.trust.GestionImageSinistre.impl.AssetsServicesLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class AssetsServices
 */
@Stateless
public class AssetsServices implements AssetsServicesLocal {

    /**
     * Default constructor. 
     */
    public AssetsServices() {
        // TODO Auto-generated constructor stub
    }

    	@PersistenceContext
    	EntityManager entityManager;

		@Override
		public List<Assets> GetAllAssets() {
			Query query = entityManager.createQuery("select a from Assets a");
			return query.getResultList();
		}

		@Override
		public void AddAsset(Assets assets) {
			entityManager.merge(assets);
			
		}

		@Override
		public void DeleteAsset(Assets assets) {
			entityManager.remove(entityManager.find(Assets.class, assets.getId()));
			
		}
}
