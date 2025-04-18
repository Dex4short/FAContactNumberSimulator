package system.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import components.Button;
import components.TextField;
import drawables.NFAContactNumberVisualizer;

public abstract class FooterPanel extends JPanel{
	private static final long serialVersionUID = -1435969491146130109L;
	private TextField input_field;
	private Button btn_simulate;
	private Graphics2D g2d;
	private Font font;
	private Color message_color;
	private String message, input_passed;
	
	{
		font = new Font("Century Gothic", Font.PLAIN, 12);
		message_color = Color.white;
		message = "Please input contact number.";
		input_passed = "";
	}
	public FooterPanel() {
		setOpaque(false);
		setLayout(null);
		
		input_field = new TextField();
		input_field.setText("+63 901 234-5678");
		add(input_field);
		
		btn_simulate = new Button();
		btn_simulate.setText("Simulate");
		btn_simulate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				simulate();
			}
		});
		add(btn_simulate);
		
	}
	private int x_center, y_center, c, circles, circle_size=30, cx, cy;
	private String c_lbl;
	private Color fill_color, txt_color;
	
	public int step=0,error_at=-1;
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setFont(font);
		g2d.setColor(message_color);
		g2d.drawString(
			message,
			(x_center)-(g2d.getFontMetrics(font).stringWidth(message)/2),
			(y_center)-((btn_simulate.getHeight()/2) + 15)
		);
		
		g2d.translate(x_center, 0);
		circles = input_passed.length();
		for(c=0; c<circles; c++) {
			cx = - (((circle_size+10)*(circles-1))/2) + ((circle_size+10)*c);
			cy = circle_size;
			c_lbl = input_passed.substring(c, c+1);

			if(c == error_at) {
				fill_color = Color.red;
				txt_color = Color.white;
			}
			else {
				if(c<step) {
					fill_color = NFAContactNumberVisualizer.white;
					txt_color = NFAContactNumberVisualizer.darkblue;
				}
				else {
					fill_color = NFAContactNumberVisualizer.darkblue;
					txt_color = NFAContactNumberVisualizer.white;
				}
			}
			
			g2d.setColor(fill_color);
			g2d.fillOval(cx-(circle_size/2), cy-(circle_size/2), circle_size, circle_size);
			
			g2d.setColor(txt_color);
			g2d.drawString(
				c_lbl,
				cx - (g2d.getFontMetrics(font).stringWidth(c_lbl)/2),
				cy + (g2d.getFontMetrics(font).getAscent()/2)
			);
		}
		g2d.translate(-x_center, 0);
		
		super.paint(g2d);
	}
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		x_center = width/2;
		y_center = height/2;
		
		input_field.setBounds(x_center - 100, y_center-15, 200, 30);
		btn_simulate.setBounds(x_center - 100, y_center+25, 200, 30);
	}
	public void setMessage(String message, Color message_color) {
		this.message = message;
		this.message_color = message_color;
	}
	public void simulate() {
		step=0;
		error_at=-1;
		input_passed = input_field.getText();
		onSimulate(input_passed);
	}
	public abstract void onSimulate(String input);
}
