package GUI;
import java.awt.Color;
/**
 * Code taken from: https://javatutorial.net/display-text-and-graphics-java-jframe
 * 
 */
import java.awt.Graphics;
import javax.swing.JPanel;
public class JFrmaeGraphics extends JPanel{

	private static final long serialVersionUID = 1L;

	public void paint(Graphics g){
			int w = this.getWidth();
			int h = this.getHeight();
			 g.setColor(Color.red);
			 g.fillOval(w/3, h/3, w/3, h/3);
			g.setColor(Color.blue);
			String s = " ["+w+","+h+"]";
		    g.drawString(s, w/3, h/2);
	}
}