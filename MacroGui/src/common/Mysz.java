package common;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mysz {

	/**
	 * 
	 */
	private int klik;

	public Mysz() {
		super();
		klik = 0;
	}
	
	
	public void changeKlik() {
		if(klik==1) klik = 0;
		else klik = 1;
	}
	
	public int getKlik() {
		return this.klik;
	}

}
