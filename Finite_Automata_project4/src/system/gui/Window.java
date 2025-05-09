package system.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import drawables.ConstelationBackground;
import system.gui.panels.SimulationPanel;
import system.gui.panels.WelcomePanel;

public class Window extends JFrame{
	private static final long serialVersionUID = -7980518265562393464L;
	private WelcomePanel welcome_panel;
	private SimulationPanel simulation_panel;
	
	public Window() {
		setTitle("Contact Number NFA Model Simulator");
		Dimension screen_size = getToolkit().getScreenSize();
		setSize(screen_size);
		
		setContentPane(new JPanel() {
			private static final long serialVersionUID = 5951915581713596733L;
			private Graphics2D g2d;
			private Color bg_color;
			private ConstelationBackground background;
			private Timer timer;
			{
				setOpaque(false);
				setLayout(new GridLayout(1,1));
				
				bg_color = new Color(48, 126, 150);
				background = new ConstelationBackground(32, new Rectangle(0,0,screen_size.width,screen_size.height));
				
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						repaint();
					}
				}, 100, 20);
				
			}
			@Override
			public void paint(Graphics g) {				
				g2d = (Graphics2D)g;				
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2d.setColor(bg_color);
				g2d.fill(g2d.getClipBounds());
				
				background.updateBoundary(getBounds());
				background.draw(g2d);
				
				super.paint(g);
			}
		});
		
		welcome_panel = new WelcomePanel() {
			private static final long serialVersionUID = -5467905869857145563L;
			@Override
			public void onNext() {
				Window.this.remove(welcome_panel);

				simulation_panel = new SimulationPanel();	
				Window.this.add(simulation_panel);
				
				Window.this.revalidate();
			}
		};
		add(welcome_panel);
			
		
	}

}
