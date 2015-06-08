package fr.ecm1A.model;

public class Regle {

	private Conditions conditions;
	private String conclusion;

	public Regle() {
		this.conditions = new Conditions();
		this.conclusion = "";
	}

	public Regle(Conditions conditions, String conclusion) {
		this.conditions = conditions;
		this.conclusion = conclusion;
	}

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
