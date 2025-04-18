package system.gui.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import drawables.NFAContactNumberVisualizer;
import drawables.StateGraph;
import oop.NFAContactNumberModel;

public abstract class VisualPanel extends JPanel{
	private static final long serialVersionUID = -906747761141499416L;
	private Graphics2D g2d;
	private NFAContactNumberVisualizer nfa_visualizer;
	private NFAContactNumberModel nfa_model;
	private boolean isAccepted=false;
	private int prev_state=0, next_state;
	private static int timing=1;
	
	public int running_threads=0;
	
	public VisualPanel() {
		setOpaque(false);
		
		nfa_visualizer = new NFAContactNumberVisualizer();
		nfa_model = new NFAContactNumberModel() {
			@Override
			public void onNextState(char input, int state) {
				next_state = state;

				running_threads++;
				new Thread() {
					final int sleep = timing*500, s1=prev_state, s2=next_state;
					public void run() {
						try {
							Thread.sleep(sleep);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						nfa_visualizer.states[s1].bg_color = StateGraph.white;
						nfa_visualizer.states[s1].fg_color = StateGraph.darkblue;
						
						int t;
						boolean pass=false;
						for(t=0; t<nfa_visualizer.states[s1].transitions.length; t++) {
							for(char in : nfa_visualizer.states[s1].transitions[t].input) {
								if(in == input) {
									nfa_visualizer.states[s1].transitions[t].line_color = StateGraph.white;
									nfa_visualizer.states[s1].transitions[t].fill_color = StateGraph.blue;
									pass=true;
									break;
								}
							}
							if(pass) break;
						}

						nfa_visualizer.states[s2].bg_color = StateGraph.white;
						nfa_visualizer.states[s2].fg_color = StateGraph.darkblue;
						
						if(s2==15) {
							if(isAccepted) {
								nfa_visualizer.states[15].bg_color = Color.green;
							}
							else {
								nfa_visualizer.states[15].bg_color = Color.red;
							}
						}
						
						onSimmulationRunning(s1, input, s2);
						
						running_threads--;
					};
				}.start();
				timing++;
				
				prev_state = state;
			}
		};
		
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		nfa_visualizer.draw(g2d);
		
		super.paint(g);
	}
	public void simulateInput(String input) {
		if(running_threads!=0) {
			getToolkit().beep();
			return;
		}
		onSimulationStarted();
		
		timing = 1;
		prev_state = 0;
		for(int s=0; s<nfa_visualizer.states.length; s++) {
			nfa_visualizer.states[s].bg_color = StateGraph.darkblue;
			nfa_visualizer.states[s].fg_color = StateGraph.white;
			
			for(int t=0; t<nfa_visualizer.states[s].transitions.length; t++) {
				nfa_visualizer.states[s].transitions[t].line_color = StateGraph.darkblue;
				nfa_visualizer.states[s].transitions[t].fill_color = StateGraph.blue;
			}
		}
		
		isAccepted = nfa_model.isContactNumber(input);
		new Thread() {
			public void run() {
				while(running_threads!=0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
				onSimulationEnded(isAccepted);
			};
		}.start();
	}
	public void zoom(float zoom) {
		nfa_visualizer.scale = zoom;
		nfa_visualizer.rescale = true;
	}
	public void showInputTrasitions(boolean show) {
		nfa_visualizer.showInputTransitions = show;
	}
	public abstract void onSimulationStarted();
	public abstract void onSimmulationRunning(int prev_state, char input, int next_state);
	public abstract void onSimulationEnded(boolean isAccepted);

}
