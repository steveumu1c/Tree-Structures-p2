package treetry1;

public class OperatorNode implements TNode {
    private String operator;
    private TNode left, right;

    public OperatorNode(String operator, TNode left, TNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }


    @Override
    public String inOrderTransversal() {
        //1. trav. left subtree (call Inorder leftsub)
        //2. visit root
        //3. trav right subtree (call Inorder right)
        return String.format("(%s %s %s)",left.inOrderTransversal(),getOperator(),right.inOrderTransversal());

    }

    @Override
    public void preOrderTransversal() {

    }

    @Override
    public int eval(){
        int leftValue = left.getValue();
        int rightValue = right.getValue();
        return eval(leftValue, rightValue);
    }

    public int eval(int left, int right) {
        int result;


        switch (operator) {
            case "+":
                result = left + right;
                break;

            case "-":
                result = left - right;
                break;

            case "*":
                result = left * right;
                break;

            default:
                //if right = 0 then throw divzero
                result = left / right;
                break;
        }
        return result;
    }

    @Override
    public int getValue() {
        return 0;
    }

    public String getOperator() {
        return operator;
    }
}
