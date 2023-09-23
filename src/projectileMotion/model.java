package projectileMotion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.geom.Path2D;
import java.lang.Math.*;

public class model extends JPanel implements ActionListener {
	final int PANEL_HEIGHT = 500;
	final Image theta;
	private Timer timer;
	double initialVel = 0;
	private double height;
	private double angle;
	private double time = 0;
	private double x = 25;
	private double y;
	private double prevXPosition;
	private double prevYPosition;
	private double maxX;
	double initX;
	double initY;


	model(double vel, double ang, double hgt) {
		initialVel = vel;
		angle = ang;
		height = 525 - hgt;
		y = height;
		maxX = maxDisplacement(vel, angle, height);
		int PANEL_WIDTH = 600;
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setBackground(Color.WHITE);
		theta = new ImageIcon("blackdot.png").getImage();
		timer = new Timer(30, this);
		timer.start();


	}





	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(theta, (int)x, (int)y, 50, 50, null);

		// draw quadratic curve
		if(y > 525){
			Path2D p = new Path2D.Double();
			p.moveTo(initX,600 - initY);
			int midPointX = (int) ((initX + maxX)/2);
			int midPointY = (int) (height -(initialVel * (midPointX/(initialVel * Math.cos((angle * Math.PI)/180)))) + (0.5 * 9.81 * (midPointX/(initialVel * Math.cos((angle * Math.PI)/180))) * (midPointX/(initialVel * Math.cos((angle * Math.PI)/180)))));
			int yControl = (int) ((midPointY - (initY - 0.75))/0.75);
			p.quadTo(20, 1100 - yControl, (float) x, 550);
			System.out.println(yControl);

			g2D.draw(p);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e){
		prevXPosition = x;
		prevYPosition = y;
		x = initialVel * Math.cos((angle * Math.PI)/180) * time;
		y = height -(initialVel * time) + (0.5 * 9.81 * time * time);

		time = time + 0.1;

		if(y < 525){
			repaint();
		}

	}

	public double maxDisplacement(double vel, double ang, double hgt){
		double t = timeTaken(vel, ang, hgt);

		double maxDis = vel * Math.cos((ang * Math.PI)/180) * t;

		return maxDis;
	}

	public double maxHeight(double vel, double ang, double hgt){
		double g = 9.81;
		double hmax = vel * vel;
		hmax = hmax * Math.sin((ang * Math.PI)/180) * Math.sin((ang * Math.PI)/180);
		hmax = hmax / (2 * g);
		hmax = hmax + hgt;

		return hmax;
	}

	public double timeTaken(double vel, double ang, double hgt){
		double vy = vel * Math.sin((ang * Math.PI)/180);
		double g = 9.81;
		double ti = Math.sqrt(vy * vy + 2 * g * hgt);
		ti = ti + vy;
		ti = ti/g;
		return ti;
	}

	/*
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawRect(30, 290, 100, 300);
		drawCenteredCircle(g2D, 80, 255,70);
		g2D.drawLine(80,255, 150, 180);
		g2D.drawLine(80, 255, 180, 255);
	}
	public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  g.fillOval(x,y,r,r);
	}
	 */
}
