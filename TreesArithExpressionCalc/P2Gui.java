package treetry1;


import java.awt.*;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

//import java.util.Stack;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JTextField;

import javax.swing.SwingConstants;

//Declare and define the gui class

public class P2Gui extends JFrame implements ActionListener {

private JTextField userText,resultText;

private JLabel inputDescLbl, resultLbl;

private JPanel inputPanel, resultPanel;

JButton evlBtn;
Integer paramInt;

Stack<Object> stk;

//set up the panels

P2Gui() {

super("Postfix to Infix Generator");

inputPanel = new JPanel(new FlowLayout());

resultPanel = new JPanel(new FlowLayout());

setLayout(new GridLayout(2, 1));

userText = new JTextField(20);
resultText = new JTextField(20);

inputDescLbl = new JLabel("Enter Postfix Expression:");

evlBtn = new JButton("Construct Tree");

evlBtn.addActionListener(this);

resultLbl = new JLabel("Infix Expression:", SwingConstants.LEFT);

add(inputPanel);

add(resultPanel);

inputPanel.add(inputDescLbl);

inputPanel.add(userText);

inputPanel.add(evlBtn);

resultPanel.add(resultLbl);
resultPanel.add(resultText);
setResizable(false);
stk = new Stack<Object>();

}

//Main function at each button push
// do need this?

//can't get error handling working
public void actionPerformed(ActionEvent arg0)

	{

//Instantiate the object of evaluation class

		EvalthePostfix eval = new EvalthePostfix();

//copy the user entered string in expression

		String str = userText.getText();
		try {
			eval.evalPostfix(str);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,"");
		 }catch (RuntimeException ex ) {
	            JOptionPane.showMessageDialog(this,ex.getMessage());
		}
{

	resultLbl.setText("Result ");

//Copy the text in the text field

	try {
		resultText.setText(eval.infixExpression());
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}

}
}

//Program starts from main() Instantiate controller

	public static void main(String[] args) {
		P2Gui startgui = new P2Gui();
		startgui.setVisible(true);
		startgui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		startgui.setSize(390, 290);
	}

}