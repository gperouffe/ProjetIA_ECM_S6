package fr.ecm1A.model;

/**
 * Classe représentant un fait
 */
public class Fait {

	/**
	 * Nom du fait
	 */
	private String nom;
	
	/**
	 * Valeur de vérité du fait
	 */
	private Boolean val;

	/**
	 * Constructeur de Fait
	 * Par défaut, la valeur de vérité d'un fait est toujours false.
	 * 
	 * @param nom
	 * 			Nom du nouveau fait.
	 */
	public Fait(String nom) {
		this.nom = nom;
		this.val = false;
	}
	
	/**
	 * Constructeur par copie
	 */
	public Fait(Fait f){
		this.nom = new String(f.nom);
		this.val = new Boolean(f.val);
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

	/**
	 * Passe la valeur de vérité du fait à true.
	 */
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
