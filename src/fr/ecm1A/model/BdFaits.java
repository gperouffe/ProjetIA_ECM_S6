package fr.ecm1A.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class BdFaits extends ArrayList<Fait>{
	
	public BdFaits(){}
	
	public BdFaits(BdFaits bdf){
		super(bdf);
	}
	
	public BdFaits(String chemin){
		File save = new File(chemin);
		BufferedReader bfr = null;
		
		try {
			bfr = new BufferedReader(new FileReader(save));
			String[] lecture = new String[2];
			do{
				lecture = (new String(bfr.readLine())).split(";");
				if(lecture != null){
					Fait x = new Fait(lecture[0]);
					if(lecture[1]=="true"){
						x.setVal(true);
					}
					this.add(x);
				}
			}while(lecture != null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bfr!=null){
					bfr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void save(String chemin){
		File save = new File(chemin);
		BufferedWriter bfw = null;
		try { 
			bfw = new BufferedWriter(new FileWriter(save));
		for (Fait x : this){
			bfw.write(x.getNom() + ";" + x.getVal()+"\n");
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bfw!=null){
					bfw.flush();
					bfw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
