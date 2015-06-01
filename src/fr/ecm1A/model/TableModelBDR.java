package fr.ecm1A.model;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

import fr.ecm1A.observer.Observable;
import fr.ecm1A.observer.Observer;

@SuppressWarnings("serial")
public class TableModelBDR extends AbstractTableModel implements Observer, Observable{

	private BdRegles bdr;
	private String[] entetes = { "Conditions", "Conclusion", "Modifier",
			"Supprimer" };
	private ArrayList<Observer> listObs = new ArrayList<Observer>();
	private int indexRegleAModif;

	public TableModelBDR() {
		super();
		this.bdr = SystemeExpert.getInstance().getBdr();
		bdr.addObserver(this);

	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return bdr.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return bdr.get(rowIndex).getConditions().toJPanel();
		case 1:
			return bdr.get(rowIndex).getConclusion();
		case 2:
			return false;
		case 3:
			return false;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		if (columnIndex == 2) {
			indexRegleAModif = rowIndex;
			notifyObservers();
			indexRegleAModif = -1;
		} else if (columnIndex == 3) {
			bdr.remove(rowIndex);
		}
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return JPanel.class;
		case 1:
			return String.class;
		case 2:
			return Boolean.class;
		case 3:
			return Boolean.class;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 2 || columnIndex == 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public int getIndexRegleAModif() {
		return indexRegleAModif;
	}

	@Override
	public void update(Observable obs) {
		if (obs == bdr) {
			fireTableDataChanged();
		}
	}

	@Override
	public void addObserver(Observer obs) {
		if (!listObs.contains(obs)) {
			listObs.add(obs);
		}
	}

	@Override
	public void removeObserver(Observer obs) {
		listObs.remove(obs);
	}

	@Override
	public void notifyObservers() {
		for (Observer obs : listObs) {
			obs.update(this);
		}
	}
}
