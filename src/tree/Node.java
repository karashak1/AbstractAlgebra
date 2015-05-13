package tree;

public class Node {
	
	protected Object data;
	
	public Node(Object data){
		this.data = data;
	}
	
	public String toString(){
		return data.toString();
	}
	
	public Object getData(){
		return data;
	}
	
	
	public int getHeight(){
		return 1;
	}
	public boolean equals(Object o){
		if(!(o instanceof Node))
			return false;
		Node temp = (Node)o;
		//System.out.println("In the equals method");
		//System.out.println(this);
		//System.out.println(temp);
		return this.toString().equals(temp.toString());
	}

}
