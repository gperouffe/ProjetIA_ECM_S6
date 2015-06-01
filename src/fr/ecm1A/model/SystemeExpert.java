package fr.ecm1A.model;

public class SystemeExpert {

	private BdFaits bdf;
	private BdRegles bdr;
	private static SystemeExpert instance;

	private SystemeExpert() {
		bdf = new BdFaits();
		bdr = new BdRegles();
	}

	public static SystemeExpert getInstance() {
		if (instance == null) {
			instance = new SystemeExpert();
		}
		return instance;
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
			saturation = true;
			for (Regle r : this.bdr) {
				Boolean declenchement = new Boolean(true);
				for (String cond : r.getConditions()) {
					Fait condFait = bdf.find(cond);
					if (condFait == null || !condFait.getVal()) {
						declenchement = false;
					}
				}
				Fait conclFait = bdf.find(r.getConclusion());
				if (declenchement) {
					if (conclFait == null) {
						conclFait = new Fait(r.getConclusion());
						conclFait.valider();
						conclFait.setModified(true);
						bdf.add(conclFait);
					}
					if (!conclFait.getVal()) {
						conclFait.valider();
						conclFait.setModified(true);
						bdf.notifyObservers();
						saturation = false;
					}
				} else {
					if (conclFait == null) {
						conclFait = new Fait(r.getConclusion());
						bdf.add(conclFait);
					}
				}
			}
		}
	}

	public Boolean chainageArriere(String but) {
		Boolean succes = false;
		Fait faitBut = bdf.find(but);
		if (but == null || but.equals("")){
			return false;
		}
		if (faitBut == null) {
			faitBut = new Fait(but);
			bdf.add(faitBut);
		} else if (but.equals("")) {
			return false;
		}
		if (faitBut.getVal()) {
			return true;
		} else {
			int indr = 0;
			int tailleBdr = bdr.size();
			while (indr < tailleBdr & !succes) {
				Regle R = bdr.get(indr);
				if (but.equals(R.getConclusion())) {
					Boolean possible = true;
					int indc = 0;
					Conditions C = R.getConditions();
					int tailleCond = C.size();
					while (indc < tailleCond & possible) {
						String condi = C.get(indc);
						Fait condiFait = bdf.find(condi);
						if (condiFait == null) {
							if (!chainageArriere(condi))
								possible = false;
						} else if (!condiFait.getVal()) {
							if (!chainageArriere(condi))
								possible = false;
						}
						indc++;
					}
					if (possible) {
						succes = true;
					}
				}
				indr++;
			}
			if (succes) {
				faitBut.valider();
				faitBut.setModified(true);
				bdf.notifyObservers();
				return true;
			} else
				return false;
		}

	}
}
