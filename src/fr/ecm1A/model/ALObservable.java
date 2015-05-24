package fr.ecm1A.model;

import java.util.ArrayList;

import fr.ecm1A.observer.Observable;
import fr.ecm1A.observer.Observer;

@SuppressWarnings("serial")
public class ALObservable<T> extends ArrayList<T> implements Observable {

	private ArrayList<Observer> listObs = new ArrayList<Observer>();
	
	public ALObservable(){
		super();
	}
	
	public ALObservable(ALObservable<T> alo){
		super(alo);
	}

	@Override
	public boolean add(T obj){
		Boolean bool = false;
		if(!this.contains(obj)){
			bool = super.add(obj);
		}
		notifyObservers();
		return bool;
	}
	
	@Override
	public boolean remove(Object obj){
		Boolean bool = super.remove(obj);
		notifyObservers();
		return bool;
	}
	
	@Override
	public void addObserver(Observer obs) {
		if(!listObs.contains(obs)){
			listObs.add(obs);
		}
	}

	@Override
	public void removeObserver(Observer obs) {
		listObs.remove(obs);
	}

	@Override
	public void notifyObservers() {
		for(Observer obs : listObs){
			obs.update(this);
		}
	}

}
