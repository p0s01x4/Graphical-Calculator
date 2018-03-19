import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GraphListener extends JFrame implements ActionListener {

	public static int fl = 0;
	private boolean draw = false;
	int xc = Calculator.panel_graph.getWidth() / 2, yc = Calculator.panel_graph.getHeight() / 2;
	int i;
	int erase =0;
	Color c;
	ArrayList<Integer> x = new ArrayList<Integer>();
	ArrayList<Integer> y = new ArrayList<Integer>();
	EvaluateExpression evalExpr;
	boolean isEquationVariable = false;
	int comboBoxIndex;
	String expression;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Plot") {
			// x1 = 50; y1 = 50; x1 = 200; y2 = 300;
			draw = true;
			comboBoxIndex = Calculator.comboBox.getSelectedIndex();
			paint(Calculator.panel_graph.getGraphics());

		}
		if (e.getSource() == Calculator.comboBox) {
			i = Calculator.comboBox.getSelectedIndex();

		}
		if(e.getActionCommand()=="Del") {
			draw =false;
			erase =1;
			final Graphics g = Calculator.panel_graph.getGraphics();
			paint(Calculator.panel_graph.getGraphics());
			g.clearRect(0, 0, Calculator.panel_graph.getWidth(), Calculator.panel_graph.getHeight());
			g.drawLine(xc, 0, xc, Calculator.panel_graph.getHeight());
			g.drawLine(12, yc, Calculator.panel_graph.getWidth(), yc);
			g.drawString("+X " + "\u2192", Calculator.panel_graph.getWidth() - 40, yc);
			g.drawString("+Y " + "\u2191", xc, 20);
			g.drawString("(0,0)", xc + 5, xc + 15);
			}

	}

	
	
	
	
	
	@Override
	public void paint(Graphics g) {
		// super.paintComponent(g);
		 
		if (EquationValidate.getInstance().isEquationValid(Calculator.textField.getText())
				&& (EquationValidate.getInstance().israngeValid(Calculator.textField_range_x.getText()))
				&& (EquationValidate.getInstance().israngeValid(Calculator.textField_range_y.getText()))) {

			expression=Calculator.textField.getText();
			if (draw && erase ==0) {
				isEquationVariable = false;
				if(expression.indexOf('x')!=-1) {
					isEquationVariable = true;
				}
				Color color = Color.black;
				switch (i) {
				case 0:
					color = color.black;
					break;
				case 1:
					
					c = Color.red;
					g.setColor(c);
					break;
				case 2:
					
					c=Color.green;
					g.setColor(c);
					break;
				case 3:
					
					c = Color.blue;
					g.setColor(c);
					break;
				default:
					break;
				}

				g.drawLine(xc, 0, xc, Calculator.panel_graph.getHeight());
				g.drawLine(12, yc, Calculator.panel_graph.getWidth(), yc);
				g.drawString("+X " + "\u2192", Calculator.panel_graph.getWidth() - 40, yc);
				g.drawString("+Y " + "\u2191", xc, 20);
				g.drawString("(0,0)", xc + 5, xc + 15);
				
				
				getCoordinates(Calculator.textField_range_x.getText(), Calculator.textField_range_y.getText(),c,Calculator.panel_graph.getGraphics());

			}

		}
		
		
	}

	public void getCoordinates(String xrange,String yrange, Color c, Graphics g) {
		final Graphics2D g2 = (Graphics2D) g;
		char[] tokens = xrange.toCharArray();
		char[] yvalues = yrange.toCharArray();
		
		CalcListener value = new CalcListener();
		g2.setColor(c);
		
		try {
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i] == ' '||tokens[i]==',')
				continue;
			if(tokens[i]=='['||tokens[i]=='('||tokens[i]=='{') continue;
			StringBuffer buffer = new StringBuffer();
			while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.' || tokens[i] == '-')) {
				buffer.append(tokens[i++]);
			}
			x.add(Integer.parseInt(buffer.toString()));
			
		}
		for (int i = 0; i < yvalues.length; i++) {
			if (yvalues[i] == ' ' || yvalues[i]==',')
				continue;
			if(yvalues[i]=='['||yvalues[i]=='('||yvalues[i]=='{') continue;
			StringBuffer buffer = new StringBuffer();
			while (i < yvalues.length && ((yvalues[i] >= '0' && yvalues[i] <= '9') || yvalues[i] == '.' || yvalues[i] == '-' )) {
				buffer.append(yvalues[i++]);
			}
			y.add(Integer.parseInt(buffer.toString()));
			
		}
		
		
		int lo = x.get(0);
		int hi = x.get(1);
		int lo_y = y.get(0);
		int hi_y = y.get(1);
		int[] xpoints = new int[hi-lo+1];
		int[] ypoints = new int[hi_y-lo_y+1];
		double maxAns = Double.MIN_VALUE;
		String result = new String();

		final DecimalFormat df = new DecimalFormat("#.##");
		
		for (double i = (double)lo; i <= (double)hi; i += 0.01) {
			i = Double.valueOf(df.format(i));
			if (i < 0) {
				result = expression.replace("x", "(" + Double.toString(i) + ")");
			} else {
				result = expression.replace("x", Double.toString(i));
			}
			evalExpr = new EvaluateExpression(result);
			double ans = evalExpr.evaluateExpression();
			ans = Double.valueOf(df.format(ans));
			if (ans > maxAns) {
				maxAns = ans;
			}
		}
		for (double i = (double)lo; i <= (double)hi; i += 0.01) {
			i = Double.valueOf(df.format(i));
			if (i < 0) {
				result = expression.replace("x", "(" + Double.toString(i) + ")");
			} else {
				result = expression.replace("x", Double.toString(i));
			}
			evalExpr = new EvaluateExpression(result);
			double ans = evalExpr.evaluateExpression();
			ans = Double.valueOf(df.format(ans));
			final double ratio = (Calculator.panel_graph.getHeight()/(2* maxAns));
			if (ratio > 1 && isEquationVariable == true) {
				ans = ans * ratio;
			}
			ans = Double.valueOf(df.format(ans));
			final double x = Double.valueOf(df.format(Calculator.panel_graph.getWidth()/2 + i));
			final double y = Double.valueOf(df.format(Calculator.panel_graph.getHeight()/2 - ans));
			final Shape l = new Line2D.Double(x, y, x, y);
			g2.draw(l);
		}
		
		
	
	
	}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error occured. Please Investigate!!!");
			
			e.printStackTrace();
			  Calculator.textField.setText(" ");
		}
	}
	
	public  String quickFix(String s) {
		char[] c = {'+','*','/','^','(',')','-'};
		char[] temp = {'p','m','d','r','q','y','x'};
		StringBuilder s1 = new StringBuilder(s);
		for(int i=0; i<c.length;i++) {
			for(int j=0; j<s1.length();j++) {
				//s.replace(c[i],temp[i]);
				if(s1.charAt(j) == c[i]) {
					s1.setCharAt(j,temp[i]); 
				}
			}
		}
		s=s1.toString();
		s=s.replaceAll("p", " + ");
		s=s.replaceAll("m", " * ");
		s=s.replaceAll("d", " / ");
		s=s.replaceAll("r", " ^ ");
		s=s.replaceAll("q", " ( ");
		s=s.replaceAll("y", " ) ");
		s=s.replaceAll("x", " - ");
		return s;
		
		
	}

}
