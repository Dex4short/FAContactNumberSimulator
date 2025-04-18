package system.gui.panels;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class SimulationPanel extends JPanel{
	private static final long serialVersionUID = 3889425748801682006L;
	private VisualPanel visual_panel;
	private HeaderPanel header_panel;
	private FooterPanel footer_panel;
	private int width, height, raise;

	public SimulationPanel() {
		setOpaque(false);
		setLayout(null);
		
		header_panel = new HeaderPanel() {
			private static final long serialVersionUID = 4723164257406861396L;
			@Override
			public void onShowInputTransitionss(boolean show) {
				visual_panel.showInputTrasitions(show);
			}
			@Override
			public void onZoom(float zoom) {
				visual_panel.zoom(zoom);
			}
		};
		add(header_panel);
		
		visual_panel = new VisualPanel() {
			private static final long serialVersionUID = 8434515073577950188L;
			@Override
			public void onSimulationStarted() {
				footer_panel.setMessage("Running Simulation...", Color.white);
			}
			@Override
			public void onSimmulationRunning(int prev_state, char input, int next_state) {
				footer_panel.step++;
			}
			@Override
			public void onSimulationEnded(boolean isAccepted) {
				if(isAccepted) {
					footer_panel.setMessage("Accepted: Contact Number Valid!", Color.green);
				}
				else {
					footer_panel.setMessage("Not Accepted: Contact Number Invalid!", Color.red);
					footer_panel.error_at = footer_panel.step;
				}
			}
		};
		add(visual_panel);
		
		footer_panel = new FooterPanel() {
			private static final long serialVersionUID = -8884953780218868810L;
			@Override
			public void simulate() {
				if(visual_panel.running_threads == 0) {
					super.simulate();
				}
				else {
					getToolkit().beep();
				}
			}
			@Override
			public void onSimulate(String input) {
				visual_panel.simulateInput(input);
			}
		};
		add(footer_panel);
		
		new Timer().scheduleAtFixedRate(new TimerTask() {
			{
				raise=50;
			}
			@Override
			public void run() {
				raise--;
				if(raise==0) {
					cancel();
				}
				header_panel.setBounds(0, 0-raise, width, 50);
				visual_panel.setBounds(0, 50, width, height-250);
				footer_panel.setBounds(0, (height-200)+(4*raise), width, 200);
			}
		}, 2000, 20);
	}
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		this.width = width;
		this.height = height;
		
		header_panel.setBounds(0, 0-raise, width, 50);
		visual_panel.setBounds(0, 50, width, height-250);
		footer_panel.setBounds(0, (height-200)+(4*raise), width, 200);
	}
	

}
