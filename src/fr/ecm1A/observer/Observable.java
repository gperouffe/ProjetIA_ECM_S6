package fr.ecm1A.observer;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver(Observer obs);
	public void notifyObservers();
}
