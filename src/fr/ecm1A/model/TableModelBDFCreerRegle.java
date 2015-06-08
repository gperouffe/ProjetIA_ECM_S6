package fr.ecm1A.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fr.ecm1A.observer.Observable;
import fr.ecm1A.observer.Observer;

@SuppressWarnings("serial")
public class TableModelBDFCreerRegle extends AbstractTableModel implements
		Observable, Observer {

	private ArrayList<Observer> listObs = new ArrayList<Observer>();
	private BdFaits bdf;
	private ArrayList<Integer> selectionCond;
	private String[] entetes = { "Fait", "Condition", "Conclusion" };
	private Regle regle;
	private int indexCcl;

	public TableModelBDFCreerRegle() {
		super();
		this.regle = new Regle();
		this.bdf = SystemeExpert.getInstance().getBdf();
		this.bdf.addObserver(this);
		selectionCond = new ArrayList<Integer>();
		indexCcl = -1;
	}
	
	public void setRegle(Regle regle){
		reset();
		this.regle = regle;
		for(String x : regle.getConditions()){
			Fait rech = bdf.find(x);
			if(!x.equals("")&&rech==null){
				Fait nvFait = new Fait(x);
				bdf.add(nvFait);
			}
		}
		for(String x : regle.getConditions()){
			Fait rech = bdf.find(x);
			if(!x.equals("")&&rech!=null){ 
				selectionCond.add((Integer)bdf.indexOf(rech));	
			}
			
		}
		String ccl = regle.getConclusion();
		Fait rech = bdf.find(ccl);
		if(rech!=null&&ccl!=""){
			indexCcl = bdf.indexOf(rech);
		} else if (ccl!="") {
			Fait nvFait = new Fait(ccl);
			bdf.add(nvFait);
			indexCcl = bdf.indexOf(nvFait);
		}
		fireTableDataChanged();
		notifyObservers();
	}

	public void reset() {
		selectionCond.clear();
		indexCcl = -1;
		regle = new Regle();
		fireTableDataChanged();
		notifyObservers();
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
			return selectionCond.contains(rowIndex);
		case 2:
			return rowIndex == indexCcl;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
			if (rowIndex != indexCcl) {
				if ((Boolean) o) {
					selectionCond.add((Integer) rowIndex);
					regle.getConditions().add(bdf.get(rowIndex).getNom());
				} else {
					selectionCond.remove((Integer) rowIndex);
					regle.getConditions().remove(bdf.get(rowIndex).getNom());
				}
			} else {
				if ((Boolean) o) {
					indexCcl = -1;
					regle.setConclusion("");
					selectionCond.add((Integer) rowIndex);
					regle.getConditions().add(bdf.get(rowIndex).getNom());
				}
			}
			fireTableCellUpdated(rowIndex,columnIndex);
		} else if (columnIndex == 2) {
			int tempCcl = indexCcl;
			if (!selectionCond.contains(rowIndex) && ((Boolean) o)) {
				regle.setConclusion(bdf.get(rowIndex).getNom());
				indexCcl = rowIndex;
			} else if (selectionCond.contains(rowIndex) && ((Boolean) o)) {
				setValueAt(false, rowIndex, 1);
				regle.setConclusion(bdf.get(rowIndex).getNom());
				indexCcl = rowIndex;
				setValueAt(false, rowIndex, 1);
			} else {
				regle.setConclusion("");
				indexCcl = -1;
			}
			if ( tempCcl != -1) {
				fireTableCellUpdated(tempCcl,2);
			}
		} else {
			throw new IllegalArgumentException();
		}
		fireTableRowsUpdated(rowIndex,rowIndex);
		notifyObservers();
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
		return columnIndex > 0;
	}

	@Override
	public void update(Observable obs) {
		if (obs==bdf) {
			selectionCond.clear();
			indexCcl = -1;
			updateRegle();
			fireTableDataChanged();
			notifyObservers();
		}
	}

	private void updateRegle() {
		selectionCond.clear();
		indexCcl = -1;
		ArrayList<String> aSupprimer = new ArrayList<String>();
		for(String x : regle.getConditions()){
			Fait rech = bdf.find(x);
			if(!x.equals("")&&rech!=null){ 
				selectionCond.add((Integer)bdf.indexOf(rech));	
			} else {
				aSupprimer.add(x);
			}
		}
		for(String s : aSupprimer){
			regle.getConditions().remove(s);
		}
		String ccl = regle.getConclusion();
		Fait rech = bdf.find(ccl);
		if(rech!=null&&ccl!=""){
			indexCcl = bdf.indexOf(rech);
		} else {
			regle.setConclusion("");
		}
		fireTableDataChanged();
		notifyObservers();
	}

	public Regle getRegle() {
		return regle;
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
