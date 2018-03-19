

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Listener implements ActionListener {

	protected static DefaultListModel dim = new DefaultListModel();

	@Override
	public void actionPerformed(ActionEvent e) {
		String choice = e.getActionCommand();
		switch (choice) {
		case "Plot":
			
			if (EquationValidate.getInstance().isEquationValid(Calculator.textField.getText())) {
			dim.addElement(Calculator.textField.getText());
			Calculator.history.setModel(dim);
			Calculator.textField.setText("");
			}
			break;
		case "Del":
			dim.removeElementAt(Calculator.history.getSelectedIndex());
			Calculator.textField.setText("");
			break;
		case "Load":
			Calculator.textField.setText(Calculator.history.getSelectedValue());
			break;

		default:
			break;
		}
		if (e.getSource()== Calculator.comboBox) {
			int i = Calculator.comboBox.getSelectedIndex();
			switch (i) {
			case 0:
				Calculator.textField.setForeground(Color.black);
				break;
			case 1:
				Calculator.textField.setForeground(Color.red);
				break;
			case 2:
				Calculator.textField.setForeground(Color.green);
				break;
			case 3:
				Calculator.textField.setForeground(Color.blue);
				break;
			default:
				break;
			}
		}

	}

}
