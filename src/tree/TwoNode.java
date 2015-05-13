package tree;

public class TwoNode extends Node {
	
	protected Node leftChild, rightChild;
	
	
	public TwoNode(Object data, Node left, Node right){
		super(data);
		leftChild = left;
		rightChild = right;
	}
	
	/*
	public String toString(){
		return leftChild.toString()+" "+rightChild.toString()+" "+super.toString();
	}
	*/
	public Node getLeftChild(){
		return leftChild;
	}
	
	public Node getRightChild(){
		return rightChild;
	}
	
	public String toString(){
		return "("+leftChild.toString()+""+super.toString()+""+rightChild.toString()+")";
	}
	
	
	public int getHeight(){
		int left = leftChild.getHeight();
		int right = rightChild.getHeight();
		if(left > right)
			return 1 + leftChild.getHeight();
		else
			return 1 + rightChild.getHeight();
	}
	public boolean equals(Object o){
		return super.equals(o);
	}
	
	

}
