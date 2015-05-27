package fr.ecm1A.model;

public class Fait {

	private String nom;
	private Boolean val;

	public Fait(String nom) {
		this.nom = nom;
		this.val = false;

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = new String(nom);
	}

	public Boolean getVal() {
		return val;
	}

	public void setVal(Boolean val) {
		this.val = val;
	}

	public void valider() {
		this.val = true;
	}

	public String toString() {
		return this.nom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fait other = (Fait) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
