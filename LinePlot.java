package myMath;

import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;


public class LinePlot extends JFrame{	
	/**
	 * The class print Polynom graph in the range received from the user.
	 * In addition, she paints the extrema points of Polynom in blue.
	 * The graph code taken from 
	 * I add the code of extrema points.
	 * @author Renana
	 */
	private static final long serialVersionUID = 1L;

	public LinePlot(String Polynom, double x0, double x1, double eps) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,400);
		@SuppressWarnings("unchecked")
		DataTable data= new DataTable(Double.class, Double.class);
		@SuppressWarnings("unchecked")
		DataTable dataK= new DataTable(Double.class, Double.class);

		Polynom_able p= new Polynom (Polynom);
		
		XYPlot plot= new XYPlot(data, dataK);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines= new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);
		for(double x=x0; x<=x1; x+=eps) {
			double y= p.f(x);
			if (p.f(x)>p.f(x+eps) && p.f(x)>p.f(x-eps)) {
				dataK.add(x,y);
				System.out.println("Point ("+x+","+y+") Max");
			}
			else if (p.f(x)<p.f(x+eps) && p.f(x)<p.f(x-eps)) {
				dataK.add(x,y);
				System.out.println("Point ("+x+","+y+") Min");
			}
			else
				data.add(x,y);
		}
		Color color= new Color (0.0f, 0.3f, 1.0f);
		plot.getPointRenderers(dataK).get(0).setColor(color);
		plot.getLineRenderers(data).get(0).setColor(color);
		PointRenderer pointRenderer = plot.getPointRenderers(dataK).get(0);
		Area circle = new Area(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
		circle.subtract(new Area(new Ellipse2D.Double(-2.0, -2.0, 4.0, 4.0)));
		pointRenderer.setShape(circle);	
	}
	public static void main(String[] args) {
		LinePlot frame= new LinePlot("0.2x^4+-1.5x^3+3x^2+-1x^1+-5", -2, 6, 0.01);
		frame.setVisible(true);
	}
}
