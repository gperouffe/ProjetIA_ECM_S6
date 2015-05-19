package fr.ecm1A.model;


public class Regle {
	private Conditions conditions;
	private String conclusion;
	
	public Regle(Conditions conditions, String conclusion){
		this.conditions = conditions;
		this.conclusion = conclusion;
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
	
	
}
