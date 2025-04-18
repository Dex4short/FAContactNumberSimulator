package system;

import javax.swing.JFrame;

import system.gui.Window;

public class FAContactNumberSimulator {

	public static void main(String[]args) {
		Window w = new Window();
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setExtendedState(JFrame.MAXIMIZED_BOTH);
		w.setVisible(true);
		
	}
	
}
