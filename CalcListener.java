

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JOptionPane;

public class CalcListener implements ActionListener {
	static Double result;
	static Double answer;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String choice = e.getActionCommand();
		String text = " " + choice + " ";
		String edit = choice + " ( ";

		switch (choice) {
		case "7":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "8":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "9":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "4":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "5":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "6":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "1":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "2":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "3":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "0":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case ".":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);

			break;
		case "+":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + text);
			break;
		case "-":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + text);
			break;

		case "*":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + text);
			break;
		case "/":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + text);
			break;
		case "(":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + text);
			break;
		case ")":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + text);
			break;
		case "^":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + text);
			break;
		case "\u221A":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + edit);
			break;

		case "sin":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + edit);
			break;

		case "cos":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + edit);
			break;

		case "tan":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + edit);
			break;

		case "ln":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + edit);
			break;

		case "e":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);
			break;

		case "\u03C0":
			Calculator.textField_calculator.setText(Calculator.textField_calculator.getText() + choice);
			break;

		case "Enter":
			result = calculations(Calculator.textField_calculator.getText().toString());
			answer = result;
			Calculator.textField_calculator.setText(result.toString());
			break;

		case "Answer":

			Calculator.textField_calculator
					.setText(Calculator.textField_calculator.getText() + " " + answer.toString());
			break;

		default:
			break;
		}
	}

	public double calculations(String expression) {

		char[] tokens = expression.toCharArray();
		Stack<Double> values = new Stack<Double>();
		Stack<Character> ops = new Stack<Character>();
		int negativeFlag =0;
		Character operator;
		Character current;
		Character next;
		try {
			for (int i = 0; i < tokens.length; i++) {
				current = tokens[i];
				if (current == '(' && i < tokens.length - 6) {
					next = tokens[i + 3];
					int j = i + 5;

					if (current == '(' && next == '-') {
						StringBuffer negative = new StringBuffer();
						while (j < tokens.length && tokens[j] >= '0' && tokens[j] <= '9') {
							negative.append(tokens[j++]);
						}
						String negativeValue = "-" + negative.toString();
						negativeFlag =1;
						values.push(Double.parseDouble(negativeValue));
						i = j;
					}
				}
				else if (tokens[i] == ' ')
					continue;
				else if (tokens[i] == '\u221A')
					ops.push(tokens[i]);
				else if (tokens[i] == 's' || tokens[i] == 'c' || tokens[i] == 't' || tokens[i] == 'l') {
					StringBuffer sb = new StringBuffer();
					while (i < tokens.length && tokens[i] != ' ') {
						sb.append(tokens[i++]);

					}
					ops.push(sb.charAt(0));

				}

				// pushing the number to the stack
				else if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {
					// taking care of more than one digits in a number
					StringBuffer buffer = new StringBuffer();
					while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
						buffer.append(tokens[i++]);
					}
					values.push(Double.parseDouble(buffer.toString()));
				} else if (tokens[i] == 'e') {
					values.push(Math.E);

				} else if (tokens[i] == '\u03C0') {
					values.push(Math.PI);
				}
				// if the token is ( , pushing it to the char stack
				else if (tokens[i] == '(')
					ops.push(tokens[i]);
				// if the token is ) , popping two operands until ) is encountered and calling
				// operation method and pushing the result in values stack
				else if (tokens[i] == ')') {
					if (ops.peek() == '(')
						ops.pop();
					else {
						while (ops.peek() != '(') {
							operator = ops.peek();
							if (operator == '\u221a') {
								if (!values.isEmpty()) {
									values.push(Math.sqrt(values.pop()));
								}
								ops.pop();
							} else if (operator == 's') {
								if (!values.isEmpty()) {
									values.push(Math.sin(values.pop()));
								}
								ops.pop();
							} else if (operator == 'c') {
								if (!values.isEmpty()) {
									values.push(Math.cos(values.pop()));
								}
								ops.pop();
							} else if (operator == 't') {
								if (!values.isEmpty()) {
									values.push(Math.tan(values.pop()));
								}
								ops.pop();
							} else if (operator == 'l') {
								if (!values.isEmpty()) {
									values.push(Math.log(values.pop()));
								}
								ops.pop();
							} 
//							else if( i<tokens.length-1 && negativeFlag==1 ) {
//								break;
//							}
							else {
								values.push(operation(operator, values.pop(), values.pop()));
								ops.pop();
							}

						}
					}

				} else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'
						|| tokens[i] == '^') {
					while (!ops.empty() && Precedence(tokens[i], ops.peek())) {

						operator = ops.pop();

						if (operator == '\u221a') {
							if (!values.isEmpty()) {
								values.push(Math.sqrt(values.pop()));
							}
						} else if (operator == 's') {
							if (!values.isEmpty()) {
								values.push(Math.sin(values.pop()));
							}
						} else if (operator == 'c') {
							if (!values.isEmpty()) {
								values.push(Math.cos(values.pop()));
							}
						} else if (operator == 't') {
							if (!values.isEmpty()) {
								values.push(Math.tan(values.pop()));
							}
						} else if (operator == 'l') {
							if (!values.isEmpty()) {
								values.push(Math.log(values.pop()));
							}
						} else
							values.push(operation(operator, values.pop(), values.pop()));
					}
					ops.push(tokens[i]);
				}

				else {
					operator = ops.pop();
					if (operator == '\u221a') {
						if (!values.isEmpty()) {
							values.push(Math.sqrt(values.pop()));
						}
					} else if (operator == 's') {
						if (!values.isEmpty()) {
							values.push(Math.sin(values.pop()));
						}
					} else if (operator == 'c') {
						if (!values.isEmpty()) {
							values.push(Math.cos(values.pop()));
						}
					} else if (operator == 't') {
						if (!values.isEmpty()) {
							values.push(Math.tan(values.pop()));
						}
					} 
					else if (operator == 'l') {
						if (!values.isEmpty()) {
							values.push(Math.log(values.pop()));
						}
					}

				}

			}
			// after parsing entire expression applying remaining operator to remaining
			// values
			
			while (!ops.empty()) {
				if (ops.peek() == '(')
					ops.pop();
				operator = ops.pop();
				if (operator == '\u221a') {
					if (!values.isEmpty()) {
						values.push(Math.sqrt(values.pop()));
					}
				} else if (operator == 's') {
					if (!values.isEmpty()) {
						values.push(Math.sin(values.pop()));

					}
				}

				else if (operator == 'c') {
					if (!values.isEmpty()) {
						values.push(Math.cos(values.pop()));
					}

				} else if (operator == 't') {
					if (!values.isEmpty()) {
						values.push(Math.tan(values.pop()));
					}

				} else if (operator == 'l') {
					if (!values.isEmpty()) {
						values.push(Math.log(values.pop()));
					}

				} else if (operator == 'e') {
					if (!values.isEmpty()) {
						values.push(Math.exp(values.pop()));
					}
				}
				
				else {
					values.push(operation(operator, values.pop(), values.pop()));
				}

//				 while (!ops.empty())
//				 values.push(operation(ops.pop(), values.pop(), values.pop()));
				
			}
		} catch (Exception e) {

		}
		if (!values.isEmpty())
			return values.pop();
		
		else {
			JOptionPane.showMessageDialog(null, "Error occured. Please Investigate!!!");
			GraphListener.fl=1;
			return 0;
		}

	}

	private boolean Precedence(char c, Character peek) {

		if (peek == '(' || peek == ')')
			return false;
		if ((c == '*' || c == '/') && (peek == '+' || peek == '-'))
			return false;
		if ((c == '^' || c == '\u221A') && (peek == '+' || peek == '-' || peek == '*' || peek == '/'))
			return false;
		if ((c == 'l' || c == 's' || c == 'c' || c == 't')
				&& (peek == '+' || peek == '-' || peek == '*' || peek == '/' || c == '^' || c == '\u221A'))
			return false;
		else
			return true;
	}

	public Double operation(Character op, Double a, Double b) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return b - a;
		case '*':
			return a * b;
		case '/':
			if (a == 0.0) {

				JOptionPane.showMessageDialog(null, "Cannot Divide by 0");
				break;

			} else
				return b / a;
		case '^':
			return Math.pow(b, a);
		case '\u221A':
			if (a < 0) {
				JOptionPane.showMessageDialog(null, "Square root operation cannot be performed on a negative number");
				GraphListener.fl=1;
				throw new UnsupportedOperationException(
						"Square root operation cannot be performed on a negative number");
			}
			return Math.pow(a, b);

		}

		return 0.0;
	}

}
