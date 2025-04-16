package system.gui.panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import drawables.NFAContactNumberVisualizer;

public class VisualPanel extends JPanel{
	private static final long serialVersionUID = -906747761141499416L;
	private Graphics2D g2d;
	private NFAContactNumberVisualizer nfa_visualizer;
	
	public VisualPanel() {
		setOpaque(false);
		
		nfa_visualizer = new NFAContactNumberVisualizer();
		
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		nfa_visualizer.draw(g2d);
		
		super.paint(g);
	}

}
