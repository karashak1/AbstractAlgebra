package tree;

public class Node {
	
	protected Character data;
	
	public Node(Character data){
		this.data = data;
	}
	
	public String toString(){
		return data.toString();
	}
	
	public Character getData(){
		return data;
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
