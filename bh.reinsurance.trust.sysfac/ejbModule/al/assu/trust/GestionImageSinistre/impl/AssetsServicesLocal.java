package al.assu.trust.GestionImageSinistre.impl;

import java.util.List;

import javax.ejb.Local;

import al.assu.trust.GestionImageSinistre.domain.Assets;

@Local
public interface AssetsServicesLocal {
		public List<Assets> GetAllAssets();
		public void AddAsset(Assets assets);
		public void DeleteAsset(Assets assets);
		public List<Assets> GetAssetsByIdProject(int id);
}
