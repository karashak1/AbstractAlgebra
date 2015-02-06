package tree;

public class TwoNode extends Node {
	
	protected Node leftChild, rightChild;
	
	
	public TwoNode(Character data, Node left, Node right){
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
	
	public boolean equals(Object o){
		return super.equals(o);
	}
	
	

}
