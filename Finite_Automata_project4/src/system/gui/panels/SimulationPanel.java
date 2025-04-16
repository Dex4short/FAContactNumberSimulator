package system.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class SimulationPanel extends JPanel{
	private static final long serialVersionUID = 3889425748801682006L;
	private VisualPanel visual_panel;

	public SimulationPanel() {
		setOpaque(false);
		setLayout(new BorderLayout());
		
		visual_panel = new VisualPanel();
		add(visual_panel, BorderLayout.CENTER);
		
	}
	

}
