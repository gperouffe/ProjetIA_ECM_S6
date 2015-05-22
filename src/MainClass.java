import fr.ecm1A.model.BdFaits;
import fr.ecm1A.model.BdRegles;
import fr.ecm1A.model.Conditions;
import fr.ecm1A.model.Fait;
import fr.ecm1A.model.Regle;
import fr.ecm1A.model.SystemeExpert;


public class MainClass {

	public static void main(String[] args) {
		
		BdFaits BDF =new BdFaits();
		Fait f1= new Fait("oeuf");
		Fait f2=new Fait("concombre");
		Fait f3=new Fait("p�tes");
		Fait f4=new Fait("tomates");
		Fait f5=new Fait("lait");
		Fait f6=new Fait("lardon");
		Fait f7=new Fait("cr�me");
		Fait f8=new Fait("viande hach�e");
		Fait f9=new Fait("p�tes");
		Fait f10=new Fait("p�tes");
		Fait f11=new Fait("p�tes");
		Fait f12=new Fait("p�tes");
		Fait f13=new Fait("p�tes");
		
		
		
		
		
		BDF.add(f1);
		BDF.add(f2);
		BDF.add(f3);
		BDF.add(f4);
		BDF.add(f5);
		BDF.add(f6);
		BDF.add(f7);
		BDF.add(f8);
		
		
		
		
		Conditions C= new Conditions();
		C.add(f1.getNom());
		C.add(f3.getNom());
		C.add(f6.getNom());
		C.add(f7.getNom());
		Conditions C2 = new Conditions();
		C.add(f8.getNom());
		C.add("tomates");
		Regle R1 = new Regle (C,"carbonara");
		Regle R2 = new Regle (C2,"tomate farcie");
		BdRegles BDR = new BdRegles();
		BDR.add(R1);
		BDR.add(R2);
		BDR.save("ressources/bdr.csv");
		f8.valider();
		f4.valider();
		f1.valider();
		f7.valider();
		f6.valider();
		f3.valider();
		SystemeExpert SE = new SystemeExpert(BDF,BDR);
		SE.chainageAvant();
		BDF.save("ressources/bdf.csv");
	}

}
