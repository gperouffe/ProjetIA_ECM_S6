package fr.ecm1A.view;

import javax.swing.JTextPane;

import fr.ecm1A.model.TableModelBDFCreerRegle;
import fr.ecm1A.observer.Observable;
import fr.ecm1A.observer.Observer;

@SuppressWarnings("serial")
public class PrevisuRegle extends JTextPane implements Observer {

	public PrevisuRegle(TableModelBDFCreerRegle tableModel) {
		super();
		this.setEditable(false);
		this.setText(tableModel.getRegle().toString());
		tableModel.addObserver(this);
	}

	@Override
	public void update(Observable obs) {
		if (obs instanceof TableModelBDFCreerRegle) {
			this.setText(((TableModelBDFCreerRegle) obs).getRegle().toString());
		}
	}

}
