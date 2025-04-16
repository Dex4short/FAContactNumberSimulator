package drawables;

import java.awt.Graphics2D;

public interface Drawable {

	public void onDraw(Graphics2D g2d);
	public default void draw(Graphics2D g2d) {
		onDraw(g2d);
	}
	
}
