package fr.ecm1A.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
			String lecture = new String();
			do{
				lecture = new String(bfr.readLine());
				if(lecture != null){
					this.add(new Fait(lecture));
				}
			}while(lecture != null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
