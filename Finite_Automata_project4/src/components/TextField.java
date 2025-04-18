package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class TextField extends JTextField{
	private static final long serialVersionUID = 5030767401767931571L;
	private Graphics2D g2d;
	
	public TextField() {
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		setHorizontalAlignment(CENTER);
		
		setFont(new Font("Century Gothic", Font.PLAIN, 11));
		
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.white);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
		
		super.paint(g);
	}
		
}
