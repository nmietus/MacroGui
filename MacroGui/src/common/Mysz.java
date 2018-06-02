package common;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mysz implements MouseListener {

	/**
	 * 
	 */
	private int klik;

	public Mysz() {
		super();
		klik = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//this.klik = 1;
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		klik = 1;
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		klik = 0;
		
	}
	
	public int getKlik() {
		return this.klik;
	}

}
