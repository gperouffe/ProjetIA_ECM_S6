package fr.ecm1A.model;

public class SystemeExpert {

	private BdFaits bdf;
	private BdRegles bdr;

	public SystemeExpert() {
		bdf = new BdFaits();
		bdr = new BdRegles();
	}

	public SystemeExpert(BdFaits bdf, BdRegles bdr) {
		this.bdf = bdf;
		this.bdr = bdr;
	}

	public BdFaits getBdf() {
		return bdf;
	}

	public void setBdf(BdFaits bdf) {
		this.bdf = bdf;
	}

	public BdRegles getBdr() {
		return bdr;
	}

	public void setBdr(BdRegles bdr) {
		this.bdr = bdr;
	}

	public void chainageAvant() {
		Boolean saturation = new Boolean(false);
		while (!saturation) {
			for (Regle r : this.bdr) {
				Boolean declenchement = new Boolean(true);
				for (String cond : r.getConditions()) {
					Fait resultatRech = bdf.find(cond);
					if (resultatRech == null || !resultatRech.getVal()) {
						declenchement = false;
					}
				}
				Fait resultatRech = bdf.find(r.getConclusion());
				if (declenchement) {
					if (resultatRech == null) {
						resultatRech = new Fait(r.getConclusion());
						bdf.add(resultatRech);
					}
					resultatRech.valider();
					saturation = true;
				} else {
					if (resultatRech == null) {
						resultatRech = new Fait(r.getConclusion());
						bdf.add(resultatRech);
					}
				}
			}
		}
	}
}
