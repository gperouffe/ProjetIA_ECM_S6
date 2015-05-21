package fr.ecm1A.model;

public class SystemeExpert {
	
	private BdFaits bdf;
	private BdRegles bdr;
	
	public SystemeExpert(){
		bdf = new BdFaits();
		bdr = new BdRegles();
	}
	
	public SystemeExpert(BdFaits bdf, BdRegles bdr){
		this.bdf = bdf;
		this.bdr = bdr;
	}

	public BdFaits getBdf() {
		return bdf;
	}

	public void setBdf(BdFaits bdf) {
		this.bdf = bdf;
	}

	public BdRegles getBdr() {
		return bdr;
	}

	public void setBdr(BdRegles bdr) {
		this.bdr = bdr;
	}
}
