import fr.ecm1A.model.BdFaits;
import fr.ecm1A.model.BdRegles;
import fr.ecm1A.model.Conditions;
import fr.ecm1A.model.Fait;
import fr.ecm1A.model.Regle;


public class MainClass {

	public static void main(String[] args) {
		
		BdFaits BDF =new BdFaits();
		Fait f1= new Fait("lol");
		Fait f2=new Fait("ptdr");
		Fait f3=new Fait("XD");
		BDF.add(f1);
		BDF.add(f2);
		BDF.save("ressources/bdf.csv");
		Conditions C= new Conditions();
		C.add(f1);
		C.add(f2); 
		Regle R = new Regle (C,f3);
		BdRegles BDR = new BdRegles();
		BDR.add(R);
		BDR.save("ressources/bdr.csv");
		
		
		
	}

}
