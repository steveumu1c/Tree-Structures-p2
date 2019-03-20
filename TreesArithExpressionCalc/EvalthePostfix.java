package treetry1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

class EvalthePostfix {
	private TNode root;
	private Stack<TNode> nodeStack =new Stack<>();
	private int r1counter=0,r2counter=0;
	private String instructionExpression = "";
	public void evalPostfix(String s) throws IOException {

		int n = s.length();

		for (int i = 0; i < n; i++) {

			char ch = s.charAt(i);

			if (ch >= '0' && ch <= '9'){
				nodeStack.push(new OperandNode(Integer.parseInt(String.valueOf(ch))));

			} else if (ch == ' '){
				//ignore spaces

			} else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				TNode right = nodeStack.pop();
				TNode left = nodeStack.pop();
				nodeStack.push(new OperatorNode(String.valueOf(ch),left,right));
				createRegisterProc((OperatorNode) nodeStack.peek(), left, right);
				//System.out.println("called create");
				
			} else {
				throw new IOException("invalid Expression");
			}
		}//End of for loop
		root = nodeStack.lastElement();
		
	}

	public String infixExpression() throws IOException {
		if (root == null) {
			throw new IOException("infix expression not formated right?");
		}
		return root.inOrderTransversal();
	}

	private void writeFile(String s) throws IOException {
		File file = new File("Registers2.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(s);
	}

	private void createRegisterProc(OperatorNode operatorInst, TNode left, TNode right) throws IOException {
		//TODO
//		Add R0 5 9
//		Sub R1 3 R0
//		Mul R2 2 3
//		Div R3 R1 R2
		
		String operatorInstruction;
		switch (operatorInst.getOperator()){
			case "+":
				operatorInstruction = "ADD";
				break;
			case "-":
				operatorInstruction = "SUB";
				break;
			case "*":
				operatorInstruction = "MUL";
				break;
			default:
				operatorInstruction = "DIV";
				break;
		}
		instructionExpression += operatorInstruction+" R"+r1counter+" ";
		r1counter++;
		System.out.println("r1c ="+ r1counter);

		if (left.getClass() == OperandNode.class){
			System.out.println("block1");
			instructionExpression += left.inOrderTransversal()+" ";
			if (right.getClass() == OperandNode.class) {
				instructionExpression += right.inOrderTransversal()+" \n";
			} else {
			instructionExpression += "R"+r2counter+"\n";
			r2counter++;
			}
			
		} else if (left.getClass() == OperatorNode.class){
			System.out.println("block2");
			instructionExpression += "R"+r2counter+" ";
			r2counter++;
			if (right.getClass() == OperatorNode.class) {
				instructionExpression += "R" + r2counter+" \n";
				r2counter++;
			} else {
				instructionExpression += right.inOrderTransversal()+" \n";
			}
		}
		System.out.println(instructionExpression);
		writeFile(instructionExpression);
	}

}