package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class ConstelationBackground implements Drawable{
	private Rectangle rect;
	private Particle particles[];
	private Random r = new Random();

	public ConstelationBackground(int particle_count, Rectangle rect) {
		this.rect = rect;
		
		particles = new Particle[particle_count];
		for(int p=0; p<particle_count; p++) {
			particles[p] = new Particle(rect);
		}
	}
	public void updateBoundary(Rectangle rect) {
		this.rect = rect;
		for(int p=0; p<particles.length; p++) {
			particles[p].boundary = rect;
		}
	}
	@Override
	public void onDraw(Graphics2D g2d) {
		for(int p=0; p<particles.length; p++) {
			particles[p].draw(g2d);
			
			for(int pp=0; pp<particles.length; pp++) {
				if(p!=pp) {
					double distance = Math.sqrt(
						Math.pow(
							Math.abs(particles[pp].x-particles[p].x),
							2
						) 
						+ 
						Math.pow(
							Math.abs(particles[pp].y-particles[p].y),
							2
						)
					);
					if(distance<100) {
						g2d.drawLine(
							(int)Math.round(particles[p].x+(particles[p].size/2)),
							(int)Math.round(particles[p].y+(particles[p].size/2)),
							(int)Math.round(particles[pp].x+(particles[pp].size/2)),
							(int)Math.round(particles[pp].y+(particles[pp].size/2))
						);
					}
				}
			}
		}
	}
	
	public class Particle implements Drawable{
		float x,y,angle,speed,x_iterator,y_iterator;
		int size,alpha;
		Color color;
		Rectangle boundary;
		
		public Particle(Rectangle boundary) {
			size = 10;
			this.boundary = boundary;
			reset();
		}
		@Override
		public void onDraw(Graphics2D g2d) {
			if(alpha < 16) {
				color = new Color(0,0,0,alpha);
				alpha++;
			}
			
			g2d.setColor(color);
			g2d.fillOval(Math.round(x), Math.round(y), size, size);
			
			x += x_iterator;
			if(x+size<0 || x>boundary.width) {
				reset();
			}

			y += y_iterator;
			if(y+size<0 || y>boundary.height) {
				reset();
			}
			
		}
		private void reset() {
			x = r.nextInt(rect.width);
			y = r.nextInt(rect.height);
			angle = r.nextInt(360);
			speed = 0.05f * (1 + r.nextInt(20));
			x_iterator = (float)(Math.cos(Math.toDegrees(angle)) * speed);
			y_iterator = (float)(Math.sin(Math.toDegrees(angle)) * speed);

			alpha = 0;
			color = new Color(0,0,0,0);
		}
	}
	
}
