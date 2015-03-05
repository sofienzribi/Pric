package al.assu.trust.GestionImageSinistre.domain;

public class PersistObject {
	public PersistObject() {
		// TODO Auto-generated constructor stub
	}

	private int id;
	private int idFactor;
	private Object object;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdFactor() {
		return idFactor;
	}

	public void setIdFactor(int idFactor) {
		this.idFactor = idFactor;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return getClass().toString();
	}

}
