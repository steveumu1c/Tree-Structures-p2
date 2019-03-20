package treetry1;

public class OperandNode implements TNode{
private int operand;

    public OperandNode(int operand) {
        this.operand = operand;
    }

    @Override
    public String inOrderTransversal() {
        return String.valueOf(operand);
    }

    @Override
    public void preOrderTransversal() {

    }
    public int getValue(){
        return operand;
    }

    public int eval() {
        return operand;
    }
}
