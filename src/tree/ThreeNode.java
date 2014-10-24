package tree;

public class ThreeNode extends TwoNode{
	
	private Node centerChild;
	private Character secondData;

	public ThreeNode(Character data,Character secondData, Node left, Node center, Node right) {
		super(data,left,right);
		this.secondData = secondData;
		this.centerChild = center;
	}
	/*
	public String toString(){
		return leftChild.toString()+" "+centerChild.toString()+" "+rightChild.toString()+" "+this.data;
	}
	*/
	
	public String toString(){
		return "("+leftChild.toString()+this.data+centerChild.toString()+this.secondData+rightChild.toString()+")";
	}
	
	

}
