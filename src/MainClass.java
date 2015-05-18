import fr.ecm1A.model.BdFaits;
import fr.ecm1A.model.Fait;


public class MainClass {

	public static void main(String[] args) {
		
		BdFaits BDF =new BdFaits();
		BDF.add(new Fait("lol"));
		BDF.add(new Fait("ptdr"));
		BDF.save("ressources/bdf.csv");
	}

}
