package fr.ecm1A.model;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe représentant un lot de conditions d'une regle.
 * 
 * @see Regle
 */
@SuppressWarnings("serial")
public class Conditions extends ArrayList<String> {

	/**
	 * Constructeur par défaut
	 */
	public Conditions() {}

	/**
	 * Constructeur par copie
	 */
	public Conditions(Conditions conditions) {
		super(conditions);
	}
	
	/**
	 * Ajoute une condition au lot.
	 * 
	 * @param s
	 * 			Nom de la condition à ajouter.
	 * @return true si l'ajout a réussi, false sinon.
	 */
	@Override
	public boolean add(String s) {
		Boolean bool = false;
		if (!this.contains(s)) {
			bool = super.add(s);
		}
		return bool;
	}

	/**
	 * Crée un JPanel représentant le lot de conditions en colonnes. 
	 * 
	 * @return JPanel
	 */
	public JPanel toJPanel() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(0, 1));
		for (String s : this) {
			pan.add(new JTextField(s));
		}
		return pan;
	}
}
