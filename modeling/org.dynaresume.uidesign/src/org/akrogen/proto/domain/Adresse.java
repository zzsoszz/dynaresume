package org.akrogen.proto.domain;

public class Adresse extends AbstractModelObject {

	private String ville;

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		Object oldValue = this.ville;
	
		this.ville = ville;
	
		firePropertyChange("ville", oldValue, ville);
	}
	
	
}
