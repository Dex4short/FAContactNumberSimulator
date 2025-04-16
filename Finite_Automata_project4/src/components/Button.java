package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Button extends JButton{
	private static final long serialVersionUID = 2614415174807150540L;
	private Graphics2D g2d;
	private Color bg_color,fg_color;
	private Stroke stroke;

	public Button() {
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		
		setFont(new Font("Century Gothic", Font.PLAIN, 12));
		setForeground(Color.white);
		
		fg_color = Color.white;
		bg_color = new Color(48, 126, 150);
		stroke = new BasicStroke(1);
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				stroke = new BasicStroke(2);
				fg_color = Color.white;
				bg_color = new Color(48, 126, 150);
				
				setFont(new Font("Century Gothic", Font.BOLD, 12));
				setForeground(fg_color);
				repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				stroke = new BasicStroke(2);
				fg_color = new Color(48, 126, 150);
				bg_color = Color.white;
				
				setFont(new Font("Century Gothic", Font.BOLD, 12));
				setForeground(fg_color);
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				stroke = new BasicStroke(1);
				
				setFont(new Font("Century Gothic", Font.PLAIN, 12));
				repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				stroke = new BasicStroke(2);
				
				setFont(new Font("Century Gothic", Font.BOLD, 12));
				repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(bg_color);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
		
		g2d.setColor(fg_color);
		g2d.setStroke(stroke);
		g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, getHeight()-2, getHeight()-2);
		
		super.paint(g);
	}
}
