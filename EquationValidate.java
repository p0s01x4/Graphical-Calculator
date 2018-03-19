import java.io.*;  
import java.lang.*;

import javax.swing.JOptionPane;
public class EquationValidate  
{  
	private static EquationValidate validation;
	 
	private EquationValidate() {
		
	}
	public static EquationValidate getInstance() {
		if(validation == null){
			validation = new EquationValidate();
        }
        return validation;
	}
	public boolean isEquationValid(String expression){
	char[] tokens = expression.toCharArray();
	 // DataInputStream get=new DataInputStream(System.in);  
	  //int n,i,top=0,f=0;  
	  char a[];  
	  String str = Calculator.textField.getText(); 
	  int count = 0;
	  int flag = 0;
	  Character current;
	  Character next;
	  try {
	  for (int i = 0; i < tokens.length-1; i++) {
			current = tokens[i];
			next = tokens[i+1];
			//System.out.println(tokens.length);
			if(i==tokens.length-2 && tokens[tokens.length-2]!=')' &&( next ==')')) 
			{
			count--;
			continue;
			}
			else if(i==tokens.length-2 &&(tokens[tokens.length-2]==')')&&(tokens[tokens.length-1]==')')) {
					count-=2;
					continue;
				}
				
			
			
			if(i==tokens.length-1 && (next == '+' || next == '-' || next == '*' || next == '/'|| next == '^')) {
				flag=1;
				continue;
			}
			if ((current >= '0' && current <= '9') && (next == 'x'||next =='X'))
			{
				flag=1;
				continue;
			}
			if(tokens[i] == ' ') continue;
			else if((current >= '0' && current <= '9') && ((next >='0' && next <= '9')||
					(next == '+' || next == '-' || next == '*' || next == '/'|| next == '^')))
				 continue;
			
			else if(current == 'x'||current =='X')
				continue;
			
			else if ((current == '.' )&& (next >='0' && next <= '9'))
				continue;
			
			else if((tokens[i] >= '0' && tokens[i] <= '9')||
					(tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'|| tokens[i] == '^'))
			{
				continue;
			}
			else if((current=='s'&&next=='i'&&tokens[i+2]=='n')||(current=='c'&& next=='o'&& tokens[i+2]=='s')||
					(current=='t' && next=='a' && tokens[i+2]=='n')) {
				i=i+2;
				continue;
				
				
			}
			else if(current=='(')
			{
				count++;
				continue;
			}
			else if(current==')')
			{
				count --;
				continue;
			}
			else if((current == '+' || current == '-' || current == '*' || current == '/'|| current == '^')&&
					(next == '+' || next == '-' || next == '*' || next == '/'|| next == '^'))
					{
				
				flag=1;
				continue;
			}
			else if ((i == tokens.length-1) && (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'|| tokens[i] == '^' )) {
				
				flag=1;
				continue;
			}
			
			
			else flag=1;
	  }
	  if(tokens.length==1 && tokens[0]!='x') {
		  flag=1;
	  }
	  if(count>0) {
		  JOptionPane.showMessageDialog(null, "Error occured. Please Investigate!!!");
		  Calculator.textField.setText("");
		  GraphListener.fl=1;
		  return false;
	  }
	  if(flag!=0) {
		  JOptionPane.showMessageDialog(null, "Error occured. Please Investigate!!!");
		  Calculator.textField.setText("");
		  GraphListener.fl=1;
		  return false;
	  }
	  else return true;
	  }
	  catch(Exception e) {
		  flag = 1;
		  JOptionPane.showMessageDialog(null, "Error occured. Please Investigate!!!");
		  Calculator.textField.setText(" ");
		  GraphListener.fl=1;
		  return false;
	  }
	
	} 
	public boolean israngeValid(String expression) {
		char[] tokens = expression.toCharArray();
		int flag = 0,count =0;
		try {
		for (int i = 0; i < tokens.length; i++) {
			
			if(tokens[i] == ' ') continue;
			else if((i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9')) || tokens[i] == '.' || tokens[i] == '-'|| tokens[i] == ',' )
					{
					flag=0;
					continue;
					}
			else if (tokens[i]=='['||tokens[i]=='('||tokens[i]=='{') {
				count++;
				continue;
			}
			else if(tokens[i]==']'||tokens[i]==')'||tokens[i]=='}') {
				count--;
				continue;
			}
			else flag=1;
		}
		if(count > 0 || flag!=0) {
			JOptionPane.showMessageDialog(null, "Please select a valid range!!!");
			Calculator.textField_range_x.setText("");
			Calculator.textField_range_y.setText("");
			GraphListener.fl=1;
			return false;
		}
		else return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			flag = 1;
			JOptionPane.showMessageDialog(null, "Please select a valid range!!!");
			GraphListener.fl=1;
			Calculator.textField_range_x.setText("");
			Calculator.textField_range_y.setText("");
			return false;
		}
		
		
	}
}
