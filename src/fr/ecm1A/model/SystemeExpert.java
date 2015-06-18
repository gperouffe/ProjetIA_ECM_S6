package fr.ecm1A.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 * Classe Singleton représentant le système expert, c'est à dire l'ensemble
 * composé des bases de faits et de règles et du moteur d'inférences.
 */
public class SystemeExpert {

	/**
	 * Base de Faits
	 * 
	 * @see BdFaits
	 */
	private BdFaits bdf;

	/**
	 * Base de Règles
	 * 
	 * @see BdRegles
	 */
	private BdRegles bdr;

	/**
	 * Instance du Systeme Expert
	 */
	private static SystemeExpert instance;

	/**
	 * Arbre destiné à garder une trace du fonctionnement du moteur
	 * d'inférences.
	 */
	private DefaultTreeModel log;

	/**
	 * Constructeur par défaut de SystemeExpert
	 */
	private SystemeExpert() {
		bdf = new BdFaits();
		bdr = new BdRegles();
	}

	/**
	 * Instanciation du SystemeExpert
	 * 
	 * @return Instance du SystemeExpert
	 */
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

	/**
	 * Execute l'algorithme du chainage avant sur les attributs bdf et bdr.
	 */
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
		bdf.notifyObservers();
	}

	/**
	 * Execute le chainage arriere à partir d'un fait 'but' sur les attributs
	 * bdf et bdr. Garde la trace du fonctionnement sous le noeud 'parent'.
	 * 
	 * @param but
	 * @param parent
	 * @return true si le chainage arriere a réussi, false sinon.
	 */
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

	/**
	 * Execute l'algorithme du chainage arrière à partir du fait 'but'. Garde la
	 * trace du fonctionnement sous la racine de l'arbre log
	 * 
	 * @param but
	 * @return true si le chainage arriere a réussi, false sinon.
	 */
	public Boolean chainageArriere(String but) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(
				"Chaînage arrière");
		log = new DefaultTreeModel(parent);
		return chainageArriereRec(but, parent);

	}
}
