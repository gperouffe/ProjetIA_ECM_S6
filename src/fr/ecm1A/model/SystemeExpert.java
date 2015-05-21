package fr.ecm1A.model;

public class SystemeExpert {

	private BdFaits bdf;
	private BdRegles bdr;

	public SystemeExpert() {
		bdf = new BdFaits();
		bdr = new BdRegles();
	}

	public SystemeExpert(BdFaits bdf, BdRegles bdr) {
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

	public boolean chainageArriere(Fait but) {
		boolean succes = false;
		boolean possible = false;
		int ind = bdf.indexOf(but);
		if (ind==-1)
			return false; //à changer, un but ui n'existe pas est à rejouter dans bdf
		else
			{ Fait fait = bdf.get(ind);
			if (fait.getVal())
				return true;
			else 
				{int indr=0;
				
				while (indr<bdr.size() & !succes)
					{ Regle R=bdr.get(indr);
					if (bdf.find(R.getConclusion())==but)
						{possible = true;
						int indc =0;
						Conditions C =R.getConditions();
						while (possible==true & indc<C.size())
							{String condi = C.get(indc);
							Fait condiva = bdf.find(condi);
							if (condiva.getVal()==false)
								{ if (!chainageArriere(condiva))
									possible = false;
								}
							if (possible)
							succes=true;
							indc ++;
							}
						}
						
						indr ++;
					}
				if (succes)
					{bdf.add(but);
					return true;
					}
				else
					return false;
				}
			
			}
		
	}

}
