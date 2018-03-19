import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JEditorPane;
import java.awt.Canvas;

public class Calculator extends JFrame {

	protected static JFrame frame;
	protected static JPanel contentPane;
	protected static JPanel panel_graph ;
	protected static JTextField textField;
	protected static JTextField textField_calculator;
	protected static JList<String> history = new JList(Listener.dim);
	protected static String[] color = { "Black", "Red", "Green", "Blue" };
	protected static JComboBox comboBox = new JComboBox(color);
	protected static Stack<String> stack = new Stack<String>();
	protected static int width = 1500;
	protected static int height =700;
	protected static JTextField textField_range_x;
	protected static JTextField textField_range_y;

	//	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public Calculator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1223, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_graph = new JPanel();
		panel_graph.setBounds(12, 0, 650, 704);
		contentPane.add(panel_graph);

		JPanel panel_calculator = new JPanel();
		panel_calculator.setBounds(674, 34, 519, 670);
		contentPane.add(panel_calculator);
		panel_calculator.setLayout(new GroupLayout(panel_calculator));
		panel_calculator.setBackground(Color.gray);

		JLabel lblNewLabel = new JLabel("Equation :-");
		lblNewLabel.setBounds(67, 6, 71, 14);
		panel_calculator.add(lblNewLabel);

		textField = new JTextField();
		textField.setText("Enter Equation to plot");
		textField.setBounds(119, 31, 227, 30);
		panel_calculator.add(textField);
		textField.setColumns(10);

		JButton btnPlot = new JButton("Plot");
		btnPlot.setBounds(358, 36, 60, 20);
		panel_calculator.add(btnPlot);
		

		JLabel lblNewLabel_1 = new JLabel("y = f(x) = ");
		lblNewLabel_1.setBounds(10, 39, 85, 14);
		panel_calculator.add(lblNewLabel_1);


		JLabel lblNewLabel_2 = new JLabel("Select Color :-");
		lblNewLabel_2.setBounds(67, 166, 129, 14);
		panel_calculator.add(lblNewLabel_2);


		comboBox.setBounds(191, 163, 71, 20);
		panel_calculator.add(comboBox);


		JLabel lblNewLabel_3 = new JLabel("History :-");
		lblNewLabel_3.setBounds(67, 201, 71, 14);
		panel_calculator.add(lblNewLabel_3);


		history.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.GRAY, null, Color.LIGHT_GRAY, null));
		history.setBounds(67, 217, 191, 73);
		panel_calculator.add(history);

		Listener listen = new Listener();
		CalcListener calclistener = new CalcListener();
		GraphListener graphlistener = new GraphListener();
		btnPlot.addActionListener(listen);
		btnPlot.addActionListener(graphlistener);
		
		
		comboBox.addActionListener(listen);
		comboBox.addActionListener(graphlistener);

	

		JButton button_7 = new JButton("7");
		button_7.setBounds(67, 378, 52, 30);
		panel_calculator.add(button_7);
		button_7.addActionListener(calclistener);

		JButton button_8 = new JButton("8");
		button_8.setBounds(129, 378, 52, 30);
		panel_calculator.add(button_8);
		button_8.addActionListener(calclistener);

		JButton button_9 = new JButton("9");
		button_9.setBounds(191, 378, 52, 30);
		panel_calculator.add(button_9);
		button_9.addActionListener(calclistener);

		JButton button_Add = new JButton("+");
		button_Add.setBounds(253, 378, 52, 30);
		panel_calculator.add(button_Add);
		button_Add.addActionListener(calclistener);

		JButton button_pow = new JButton("^");
		button_pow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_pow.setBounds(315, 378, 52, 30);
		panel_calculator.add(button_pow);
		button_pow.addActionListener(calclistener);

		JButton btnSin = new JButton("sin");
		btnSin.setBounds(377, 378, 71, 30);
		panel_calculator.add(btnSin);
		btnSin.addActionListener(calclistener);

		JButton button_4 = new JButton("4");
		button_4.setBounds(67, 419, 52, 30);
		panel_calculator.add(button_4);
		button_4.addActionListener(calclistener);

		JButton button_5 = new JButton("5");
		button_5.setBounds(129, 419, 52, 30);
		panel_calculator.add(button_5);
		button_5.addActionListener(calclistener);

		JButton button_6 = new JButton("6");
		button_6.setBounds(191, 419, 52, 30);
		panel_calculator.add(button_6);
		button_6.addActionListener(calclistener);

		JButton button_Sub = new JButton("-");
		button_Sub.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_Sub.setBounds(253, 419, 52, 30);
		panel_calculator.add(button_Sub);
		button_Sub.addActionListener(calclistener);

		JButton button_sqrt = new JButton("\u221A");
		button_sqrt.setBounds(315, 419, 52, 30);
		panel_calculator.add(button_sqrt);
		button_sqrt.addActionListener(calclistener);

		JButton btnCos = new JButton("cos");
		btnCos.setBounds(377, 419, 71, 30);
		panel_calculator.add(btnCos);
		btnCos.addActionListener(calclistener);

		JButton button_1 = new JButton("1");
		button_1.setBounds(67, 460, 52, 30);
		panel_calculator.add(button_1);
		button_1.addActionListener(calclistener);

		JButton button_2 = new JButton("2");
		button_2.setBounds(129, 460, 52, 30);
		panel_calculator.add(button_2);
		button_2.addActionListener(calclistener);

		JButton button_3 = new JButton("3");
		button_3.setBounds(191, 460, 52, 30);
		panel_calculator.add(button_3);
		button_3.addActionListener(calclistener);

		JButton button_Div = new JButton("/");
		button_Div.setBounds(253, 460, 52, 30);
		panel_calculator.add(button_Div);
		button_Div.addActionListener(calclistener);

		JButton btnE = new JButton("e");
		btnE.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnE.setBounds(315, 460, 52, 30);
		panel_calculator.add(btnE);
		btnE.addActionListener(calclistener);

		JButton btnTan = new JButton("tan");
		btnTan.setBounds(377, 460, 71, 30);
		panel_calculator.add(btnTan);
		btnTan.addActionListener(calclistener);

		JButton button_Dot = new JButton(".");
		button_Dot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_Dot.setBounds(67, 501, 52, 30);
		panel_calculator.add(button_Dot);
		button_Dot.addActionListener(calclistener);

		JButton button_0 = new JButton("0");
		button_0.setBounds(129, 501, 52, 30);
		panel_calculator.add(button_0);
		button_0.addActionListener(calclistener);

		JButton btnC = new JButton("C");
		btnC.setBounds(191, 501, 52, 30);
		panel_calculator.add(btnC);
		btnC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField_calculator.setText("");
			}
		});

		JButton button_Mul = new JButton("*");
		button_Mul.setBounds(253, 501, 52, 30);
		panel_calculator.add(button_Mul);
		button_Mul.addActionListener(calclistener);

		JButton button_pi = new JButton("\u03C0");
		button_pi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_pi.setBounds(315, 501, 52, 30);
		panel_calculator.add(button_pi);
		button_pi.addActionListener(calclistener);

		JButton btnLn = new JButton("ln");
		btnLn.setBounds(377, 501, 71, 30);
		panel_calculator.add(btnLn);
		btnLn.addActionListener(calclistener);

		JButton button_answer = new JButton("Answer");
		button_answer.setBounds(268, 542, 81, 36);
		panel_calculator.add(button_answer);
		button_answer.addActionListener(calclistener);

		JButton button_enter = new JButton("Enter");
		button_enter.setBounds(363, 542, 85, 36);
		panel_calculator.add(button_enter);
		button_enter.addActionListener(calclistener);

		JButton button_Back = new JButton("\u2190");
		button_Back.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_Back.setBounds(187, 542, 71, 36);
		panel_calculator.add(button_Back);
		button_Back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int length = textField_calculator.getText().length();
				String remaining;
				if(length>0) {
					StringBuilder back = new StringBuilder(textField_calculator.getText());					
					remaining = back.deleteCharAt(length-1).toString();
					textField_calculator.setText(remaining);
				}
				else{
					JOptionPane.showMessageDialog(null, "Nothing to Delete");
				}

			}
		});

		JButton button_close = new JButton(")");
		button_close.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_close.setBounds(129, 542, 52, 36);
		panel_calculator.add(button_close);
		button_close.addActionListener(calclistener);


		JButton btnDel = new JButton("Del");		
		btnDel.setBounds(430, 36, 60, 20);
		panel_calculator.add(btnDel);
		btnDel.addActionListener(listen);
		btnDel.addActionListener(graphlistener);

		textField_calculator = new JTextField();
		textField_calculator.setBounds(67, 326, 381, 30);
		panel_calculator.add(textField_calculator);
		textField_calculator.setColumns(10);

		JButton button_open = new JButton("(");
		button_open.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_open.setBounds(67, 542, 52, 36);
		button_open.addActionListener(calclistener);
		panel_calculator.add(button_open);

		JButton button_Load = new JButton("Load");
		button_Load.setBounds(67, 289, 71, 23);
		panel_calculator.add(button_Load);
		
		JLabel lblNewLabel_4 = new JLabel("X Axis Range");
		lblNewLabel_4.setBounds(10, 77, 85, 16);
		panel_calculator.add(lblNewLabel_4);
		
		textField_range_x = new JTextField();
		textField_range_x.setText("Enter Range of X");
		textField_range_x.setBounds(142, 74, 116, 22);
		panel_calculator.add(textField_range_x);
		textField_range_x.setColumns(10);
		
		JLabel lblYAxisRange = new JLabel("Y Axis Range");
		lblYAxisRange.setBounds(10, 116, 85, 16);
		panel_calculator.add(lblYAxisRange);
		
		textField_range_y = new JTextField();
		textField_range_y.setText("Enter Range of Y");
		textField_range_y.setColumns(10);
		textField_range_y.setBounds(142, 113, 116, 22);
		panel_calculator.add(textField_range_y);
		button_Load.addActionListener(listen);
		//setVisible(true);
		//frame.add(contentPane);
		//frame.add(GraphPlotter);
		
		


	}
//	public void paint(Graphics g) {
//	//g.setColor(getBackground());
//	
//	
//	g.fillRect(27, 34, 650, 670);
//	g.setColor(Color.blue);
//	g.drawLine(325, 34, 325, 700);
//	g.drawLine(27, 358, 670, 358);
////	//g.setColor(getForeground()
////
//	
//}

	
}
