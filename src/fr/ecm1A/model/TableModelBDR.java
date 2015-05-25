package fr.ecm1A.model;

import javax.swing.table.AbstractTableModel;

import fr.ecm1A.observer.Observable;
import fr.ecm1A.observer.Observer;

@SuppressWarnings("serial")
public class TableModelBDR extends AbstractTableModel implements Observer{
	
	private BdRegles bdr;
	private String[] entetes = {"Conditions","Conclusion","Modifier","Supprimer"}; 
	
	public TableModelBDR(){
		super();
		this.bdr=SystemeExpert.getInstance().getBdr();
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
		switch(columnIndex){
		case 0:
			return bdr.get(rowIndex).getConditions();
		case 1:
			return bdr.get(rowIndex).getConclusion();
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
			return Conditions.class;
		case 1:
			return String.class;
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
		if(obs instanceof BdRegles){
			fireTableDataChanged();
		}
	}
	
	
}

