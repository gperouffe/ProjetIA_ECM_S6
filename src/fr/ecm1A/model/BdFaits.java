package fr.ecm1A.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("serial")
public class BdFaits extends ALObservable<Fait> {

	public BdFaits() {
	}

	public BdFaits(BdFaits bdf) {
		super(bdf);
	}

	public BdFaits(String chemin) {
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
				lecture[0] = bfr.readLine();
				if (lecture[0] != null) {
					lecture = lecture[0].split(";");
					Fait x = new Fait(lecture[0]);
					if (lecture[1].equals("true")) {
						x.setVal(true);
					}
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
			for (Fait x : this) {
				bfw.write(x.getNom() + ";" + x.getVal() + "\n");
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

	public Fait find(String nom) {
		Fait resultat = null;
		int i = 0;
		while (i < this.size() && resultat == null) {
			if (this.get(i).getNom().equals(nom)) {
				resultat = this.get(i);
			}
			i++;
		}
		return resultat;
	}

}
