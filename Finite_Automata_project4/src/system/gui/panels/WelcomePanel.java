package system.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import components.Button;

public abstract class WelcomePanel extends JPanel{
	private static final long serialVersionUID = -8293291538929965386L;
	private Graphics2D g2d;
	private String title, subtitle;
	private Font f1,f2;
	private FontMetrics fm;
	private Rectangle rect;
	private Button btn_next;

	public WelcomePanel() {
		setOpaque(false);
		setLayout(null);
		
		title = "Contact Number";
		f1 = new Font("Century Gothic", Font.PLAIN, 24);
		
		subtitle = " NFA Model Simulator";
		f2 = new Font("Century Gothic", Font.PLAIN, 12);
		
		btn_next = new Button();
		btn_next.setText("next");
		btn_next.setBounds(400-50, 300-20, 100, 30);
		btn_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onNext();
			}
		});
		add(btn_next);
		
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		rect = getBounds();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.translate(rect.width/2, rect.height/2);
		
		g2d.setColor(Color.white);
		
		g2d.setFont(f1);
		fm = g2d.getFontMetrics(f1);
		g2d.drawString(title, -(fm.stringWidth(title)/2), -fm.getHeight());

		g2d.setFont(f2);
		fm = g2d.getFontMetrics(f2);
		g2d.drawString(subtitle, -(fm.stringWidth(subtitle)/2), 0);
		
		g2d.translate(-rect.width/2, -rect.height/2);
		
		btn_next.setBounds(
			(getWidth()/2)-(btn_next.getWidth()/2),
			(getHeight()/2)+(btn_next.getHeight()/2),
			100,
			30
		);
		
		super.paint(g2d);
	}
	public abstract void onNext();
}
