import fr.ecm1A.model.BdFaits;
import fr.ecm1A.model.BdRegles;
import fr.ecm1A.model.Conditions;
import fr.ecm1A.model.Fait;
import fr.ecm1A.model.Regle;
import fr.ecm1A.model.SystemeExpert;


public class MainClass {

	public static void main(String[] args) {
		
		BdFaits BDF =new BdFaits();
		Fait f1= new Fait("lol");
		Fait f2=new Fait("ptdr");
		Fait f3=new Fait("XD");
		BDF.add(f1);
		BDF.add(f2);
		Conditions C= new Conditions();
		C.add(f1.getNom());
		C.add(f2.getNom()); 
		Regle R = new Regle (C,f3.getNom());
		BdRegles BDR = new BdRegles();
		BDR.add(R);
		BDR.save("ressources/bdr.csv");
		f1.valider();
		f2.valider();
		SystemeExpert SE = new SystemeExpert(BDF,BDR);
		SE.chainageAvant();
		BDF.save("ressources/bdf.csv");
	}

}
