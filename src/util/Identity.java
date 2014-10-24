package util;

public class Identity {
	
	private String leftSide;
	private String rightSide;
	
	
	public Identity(String right, String left){
		this.leftSide = left;
		this.rightSide = right;
	}
	
	public String toString(){
		return rightSide+""+Character.toString((char) 0x2261)+""+leftSide;
	}

}
