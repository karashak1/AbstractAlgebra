package tree;

public class OneNode extends Node{
	
	private Node child;
	
	public OneNode(Character data, Node child){
		super(data);
		this.child = child;
	}
	
	public Node getChild(){
		return child;
	}
	
	public String toString(){
		if(child != null){
			//return child.toString()+" "+ super.toString();
			return super.toString()+" "+child.toString();
		}
		else
			return super.toString();
	}
	
	public int getHeight(){
		return 1+child.getHeight();
	}
	
	public boolean equals(Object o){
		return super.equals(o);
	}

}
