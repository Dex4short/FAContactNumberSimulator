package system.gui.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import components.Button;

public abstract class HeaderPanel extends JPanel{
	private static final long serialVersionUID = -5329778712227498722L;
	private Graphics2D g2d;
	private Font font;
	private boolean showInputTransitions = true;
	private float zoom=0.95f;

	{
		font = new Font("Century Gothic", Font.PLAIN, 15);
	}
	public HeaderPanel() {
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		Button 
		show_inputs = new Button(),
		zoom_in = new Button(),
		zoom_out = new Button();

		show_inputs.setText("Show Input Transitions");
		show_inputs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showInputTransitions = !showInputTransitions;
				onShowInputTransitionss(showInputTransitions);
			}
		});
		add(show_inputs);
		
		zoom_in.setText("Zoom +");
		zoom_in.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				zoom += 0.1f;
				onZoom(zoom);
			}
		});
		add(zoom_in);
		
		zoom_out.setText("Zoom -");
		zoom_out.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				zoom -= 0.1f;
				onZoom(zoom);
			}
		});
		add(zoom_out);
		
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.white);
		g2d.setFont(font);
		g2d.drawString("NFA Model: Contact Number Format", 10, 10 + g2d.getFontMetrics(font).getAscent());
		
		super.paint(g);
	}
	public abstract void onZoom(float zoom);
	public abstract void onShowInputTransitionss(boolean show);
}
