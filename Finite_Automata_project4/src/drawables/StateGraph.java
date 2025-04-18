package drawables;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class StateGraph implements Drawable{
	private Random r;
	
	public static Color 
		blue = new Color(48, 126, 150),
		darkblue = new Color(17, 45, 53),
		white = Color.white;
	public static 
		Font font = new Font("Century Gothic", Font.PLAIN, 9);
	
	public State states[], curent_state;
	public Rectangle rect;
	public int x_origin, y_origin;
	public boolean showInputTransitions;
	
	public StateGraph() {
		r = new Random();
		
		x_origin = 0;
		y_origin = 0;
		showInputTransitions = true;
	}
	@Override
	public void onDraw(Graphics2D g2d) {
		rect = g2d.getClipBounds();
		
		g2d.translate((rect.width/2) + x_origin, (rect.getHeight()/2) + y_origin);
		
		g2d.setColor(Color.white);
		g2d.drawPolygon(
			new int[] {
				states[0].point.x-(states[0].size),
				states[0].point.x-(states[0].size/2),
				states[0].point.x-(states[0].size)}, 
			new int[] {
				states[0].point.y-(states[0].size/3),
				states[0].point.y,
				states[0].point.y+(states[0].size/3)
			},
			3
		);
		for(int s=0; s<states.length; s++) {
			states[s].draw(g2d);
		}
		
		g2d.translate(-((rect.width/2) + x_origin), -((rect.getHeight()/2) + y_origin));
	}
	public void setStates(State states[]) {
		this.states = states;
		curent_state = states[0];
	}
	public void nextInput(char next_input) {
		
	}
	
	public class State implements Drawable{
		private float alpha;
		private int timming;
		
		public Point point;
		public String label;
		public Color bg_color, fg_color;
		public Transition transitions[];
		public int size = 30;
		public boolean isFinalState=false;
		
		public State(String label) {
			point = new Point();
			bg_color = white;
			fg_color = darkblue;
			
			alpha = 0.0f;
			timming = r.nextInt(64);
			
			this.label = label;
			this.transitions = new Transition[0];
		}
		@Override
		public void onDraw(Graphics2D g2d) {
			if(alpha!=1f) {
				if(timming!=0) {
					timming--;
				}
				if(timming==0 && alpha<1f) {
					alpha += 0.05f;
					if(alpha>=1f) {
						alpha=1f;
					}
				}
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			}
			
			if(timming==0) {
				for(int t=0; t<transitions.length; t++) {
					transitions[t].draw(g2d);
				}
			}
			
			g2d.setColor(bg_color);
			g2d.fillOval(point.x-(size/2), point.y-(size/2), size, size);
			
			if(isFinalState) g2d.drawOval(point.x-(size/2)-4, point.y-(size/2)-4, size+8, size+8);

			g2d.setColor(fg_color);
			g2d.setFont(font);
			g2d.drawString(
				label,
				point.x-(g2d.getFontMetrics(font).stringWidth(label)/2),
				point.y+(g2d.getFontMetrics(font).getAscent()/2)
			);

			if(alpha != 1f) g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		public void setTransitions(Transition transitions[]) {
			if(transitions == null) {
				this.transitions = new Transition[0];
			}
			else {
				this.transitions = transitions;
				for(int t=0; t<transitions.length; t++) {
					transitions[t].point = point;
				}
			}
		}
	}
	
	public class Transition implements Drawable{
		private float line_iterator, line_speed;
		
		public Point point;
		public String label;
		public Color line_color, fill_color;;
		public State next_state;
		public char input[];
		public double angle, length;
		public int x_center, y_center, size=20;
		
		public Transition(String label, char input[], State next_state) {
			line_iterator = 0.1f;
			line_speed = 0.01f * (2+r.nextInt(3));
			
			point = new Point(50,50);
			line_color = white;
			fill_color = blue;
			
			this.label = label;
			this.input = input;
			this.next_state = next_state;
		}
		private int x_head, y_head;
		@Override
		public void onDraw(Graphics2D g2d) {
			if(line_iterator<1f) line_iterator += line_speed;

			angle = Math.atan2(
				next_state.point.y-point.y,
				next_state.point.x-point.x
			);
			
			length = Math.sqrt(
				Math.pow(Math.abs(next_state.point.x-point.x), 2)
				+
				Math.pow(Math.abs(next_state.point.y-point.y), 2)
			) * line_iterator;

			x_head = (int)Math.round(point.x + (Math.cos(angle) * (length-(next_state.size/2))));
			y_head = (int)Math.round(point.y + (Math.sin(angle) * (length-(next_state.size/2))));
			
			g2d.setColor(line_color);
			g2d.drawLine(point.x, point.y, x_head, y_head); 
			g2d.fillPolygon(
				new int[] {
					x_head + (int)Math.round(Math.cos(angle - Math.toRadians(160))*10),
					x_head,
					x_head + (int)Math.round(Math.cos(angle + Math.toRadians(160))*10)
				},
				new int[] {
					y_head + (int)Math.round(Math.sin(angle - Math.toRadians(160))*10),
					y_head,
					y_head + (int)Math.round(Math.sin(angle + Math.toRadians(160))*10)
				},
				3
			);
			
			if(showInputTransitions && line_iterator>=1f) {
				x_center = (int)Math.round(point.x+(Math.cos(angle)*(length/2)));
				y_center = (int)Math.round(point.y+(Math.sin(angle)*(length/2)));
				
				g2d.setColor(fill_color);
				g2d.fillOval(
					x_center-(g2d.getFontMetrics(font).stringWidth(label)/2)-4, 
					y_center-(g2d.getFontMetrics(font).getAscent()/2),
					g2d.getFontMetrics(font).stringWidth(label)+7,
					g2d.getFontMetrics(font).getHeight()+4
				);
				
				g2d.setColor(line_color);
				g2d.drawOval(
					x_center-(g2d.getFontMetrics(font).stringWidth(label)/2)-4, 
					y_center-(g2d.getFontMetrics(font).getAscent()/2),
					g2d.getFontMetrics(font).stringWidth(label)+7,
					g2d.getFontMetrics(font).getHeight()+4
				);
				
				g2d.setFont(font);
				g2d.drawString(
					label,
					x_center - (g2d.getFontMetrics(font).stringWidth(label)/2),
					y_center + (g2d.getFontMetrics(font).getAscent()/2)
				);
			}
			
		}
	}

}
