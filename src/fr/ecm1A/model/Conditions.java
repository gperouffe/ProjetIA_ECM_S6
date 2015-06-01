package fr.ecm1A.model;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Conditions extends ArrayList<String> {

	public Conditions() {
	}

	public Conditions(Conditions conditions) {
		super(conditions);
	}
	

	@Override
	public boolean add(String s) {
		Boolean bool = false;
		if (!this.contains(s)) {
			bool = super.add(s);
		}
		return bool;
	}

	public JPanel toJPanel() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(0, 1));
		for (String s : this) {
			pan.add(new JTextField(s));
		}
		return pan;
	}
}
