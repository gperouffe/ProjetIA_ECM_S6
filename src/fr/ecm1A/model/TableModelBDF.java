package fr.ecm1A.model;

import javax.swing.table.AbstractTableModel;

import fr.ecm1A.observer.Observable;
import fr.ecm1A.observer.Observer;

@SuppressWarnings("serial")
public class TableModelBDF extends AbstractTableModel implements Observer {

	private BdFaits bdf;
	private String[] entetes = { "Fait", "Valeur de vérité", "Supprimer" };

	public TableModelBDF() {
		super();
		this.bdf = SystemeExpert.getInstance().getBdf();
		bdf.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return bdf.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return bdf.get(rowIndex).getNom();
		case 1:
			return bdf.get(rowIndex).getVal();
		case 2:
			return false;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			bdf.get(rowIndex).setNom((String) o);
			fireTableCellUpdated(rowIndex,columnIndex);
		} else if (columnIndex == 1) {
			bdf.get(rowIndex).setVal((Boolean) o);
			fireTableCellUpdated(rowIndex,columnIndex);
		} else if (columnIndex == 2) {
			bdf.remove(rowIndex);
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Boolean.class;
		case 2:
			return Boolean.class;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void update(Observable obs) {
		if (obs==bdf) {
			fireTableDataChanged();
		}
	}

}
