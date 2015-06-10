package fr.ecm1A.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class SystemeExpert {

	private BdFaits bdf;
	private BdRegles bdr;
	private static SystemeExpert instance;
	private DefaultTreeModel log;

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

	public TreeModel getLog() {
		return log;
	}

	public void chainageAvant() {
		Boolean saturation = new Boolean(false);
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(
				"Chaînage avant");
		log = new DefaultTreeModel(parent);
		int i = 1;
		while (!saturation) {
			saturation = true;
			DefaultMutableTreeNode parcours = new DefaultMutableTreeNode(
					"Parcours n°" + i);
			for (Regle r : this.bdr) {
				Boolean declenchement = new Boolean(true);
				Fait conclFait = bdf.find(r.getConclusion());
				if (conclFait == null) {
					conclFait = new Fait(r.getConclusion());
					bdf.add(conclFait);
				}
				if (!conclFait.getVal()) {
					for (String cond : r.getConditions()) {
						Fait condFait = bdf.find(cond);
						if (condFait == null || !condFait.getVal()) {
							declenchement = false;
						}
					}
					if (declenchement) {
						if (!conclFait.getVal()) {
							conclFait.valider();
							bdf.notifyObservers();
							saturation = false;
						}
						DefaultMutableTreeNode noeud = new DefaultMutableTreeNode(
								r.getConclusion());
						parcours.add(noeud);
						for (String s : r.getConditions()) {
							noeud.add(new DefaultMutableTreeNode(s));
						}
					}
				}
			}
			if (!saturation) {
				parent.add(parcours);
			}
			i++;
		}
	}

	private Boolean chainageArriereRec(String but, DefaultMutableTreeNode parent) {
		Boolean succes = false;
		Fait faitBut = bdf.find(but);
		DefaultMutableTreeNode noeudBut = new DefaultMutableTreeNode(but);
		if (but == null || but.equals("")) {
			return false;
		}
		if (faitBut == null) {
			faitBut = new Fait(but);
			bdf.add(faitBut);
		} else if (but.equals("")) {
			return false;
		}
		if (faitBut.getVal()) {
			parent.add(noeudBut);
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
						if (!chainageArriereRec(condi, noeudBut))
							possible = false;
						indc++;
					}
					if (possible) {
						succes = true;
					} else {
						noeudBut.removeAllChildren();
					}
				}
				indr++;
			}
			if (succes) {
				faitBut.valider();
				parent.add(noeudBut);
				bdf.notifyObservers();
				return true;
			} else
				return false;
		}

	}

	public Boolean chainageArriere(String but) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(
				"Chaînage avant");
		log = new DefaultTreeModel(parent);
		Boolean reussite = chainageArriereRec(but, parent);
		return reussite;
	}
}
