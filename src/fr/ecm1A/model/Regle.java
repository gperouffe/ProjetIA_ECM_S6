package fr.ecm1A.model;


public class Regle {
	private Conditions conditions;
	private Fait conclusion;
	
	public Regle(Conditions conditions, Fait conclusion){
		this.conditions = conditions;
		this.conclusion = conclusion;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public Fait getConclusion() {
		return conclusion;
	}

	public void setConclusion(Fait conclusion) {
		this.conclusion = conclusion;
	}
	
	
}
