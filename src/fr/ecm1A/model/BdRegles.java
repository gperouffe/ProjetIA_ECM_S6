package fr.ecm1A.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("serial")
public class BdRegles extends ALObservable<Regle> {

	public BdRegles() {
	}

	public BdRegles(BdRegles bdr) {
		super(bdr);
	}

	public BdRegles(String chemin) {
		this.open(chemin);
	}

	public void open(String chemin) {
		File save = new File(chemin);
		BufferedReader bfr = null;
		this.clear();

		try {
			bfr = new BufferedReader(new FileReader(save));
			String[] lecture = new String[2];
			do {
				lecture[0] = (bfr.readLine());
				if (lecture[0] != null) {
					lecture = lecture[0].split(";");
					Conditions cond = new Conditions();
					for (String x : lecture[0].split(",")) {
						cond.add(x);
					}
					Regle x = new Regle(cond, new String(lecture[1]));
					this.add(x);
				}
			} while (lecture[0] != null);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bfr != null) {
					bfr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void save(String chemin) {
		File save = new File(chemin);
		BufferedWriter bfw = null;
		try {
			bfw = new BufferedWriter(new FileWriter(save));
			for (Regle x : this) {
				String temp = new String();
				for (String y : x.getConditions()) {
					temp += y + ",";
				}
				temp += ";" + x.getConclusion() + "\n";
				bfw.write(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bfw != null) {
					bfw.flush();
					bfw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
