package fr.ecm1A.model;

import javax.swing.table.AbstractTableModel;

import fr.ecm1A.observer.Observable;
import fr.ecm1A.observer.Observer;

@SuppressWarnings("serial")
public class TableModelBDF extends AbstractTableModel implements Observer{
	
	private BdFaits bdf;
	private String[] entetes = {"Nom du fait","Valeur de v�rit�","Modifier","Supprimer"}; 
	
	public TableModelBDF(){
		super();
		this.bdf=SystemeExpert.getInstance().getBdf();
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
		switch(columnIndex){
		case 0:
			return bdf.get(rowIndex).getNom();
		case 1:
			return bdf.get(rowIndex).getVal();
		case 2:
			return null;
		case 3:
			return null;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0:
			return String.class;
		case 1:
			return Boolean.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}


	@Override
	public void update(Observable obs) {
		if(obs instanceof BdFaits){
			fireTableDataChanged();
		}
	}
	
	
}

