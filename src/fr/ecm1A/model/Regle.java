package fr.ecm1A.model;

/**
 * Classe repr�sentant une r�gle.
 */
public class Regle {

	/**
	 * Conditions de la r�gle
	 * 
	 * @see Conditions
	 */
	private Conditions conditions;
	
	/**
	 * Conclusion de la r�gle
	 */
	private String conclusion;

	/**
	 * Constructeur par d�faut (ni conditions, ni conlcusion).
	 */
	public Regle() {
		this.conditions = new Conditions();
		this.conclusion = "";
	}

	/**
	 * Constructeur de Regle
	 * 
	 * @param conditions
	 * @param conclusion
	 */
	public Regle(Conditions conditions, String conclusion) {
		this.conditions = conditions;
		this.conclusion = conclusion;
	}

	/**
	 * Constructeur par copie
	 */
	public Regle(Regle r) {
		this.conditions = new Conditions(r.conditions);
		this.conclusion = new String(r.conclusion);
	}

	public Conditions getConditions() {
		return conditions;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	@Override
	public String toString() {
		String str = new String();
		str = "SI :\r\n";
		for (String s : conditions) {
			str += s + "\r\n";
		}
		str += "\nALORS :\r\n" + conclusion;
		return str;
	}

}
