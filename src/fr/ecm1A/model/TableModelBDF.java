package fr.ecm1A.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TableModelBDF implements TableModel {
	
	private BdFaits bdf;
	private String[] entetes = {"Nom du fait","Valeur de vérité","Modifier","Supprimer"}; 
	
	public TableModelBDF(BdFaits bdf){
		super();
		this.bdf=bdf;
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
		switch(entetes[columnIndex]){
		case "Nom de Fait":
			return bdf.get(rowIndex).getNom();
		case "Valeur de vérité":
			return bdf.get(rowIndex).getVal();
		case "Modifier":
			return null;
		case "Supprimer":
			return null;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(entetes[columnIndex]){
		case "Nom de Fait":
			return String.class;
		case "Valeur de vérité":
			return Boolean.class;
		case "Modifier":
			return null;
		case "Supprimer":
			return null;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
	case 0:
		return entetes[0];
	case 1:
		return entetes[1];
	case 2:
		return entetes[2];
	case 3:
		return entetes[3];
	default:
		throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

