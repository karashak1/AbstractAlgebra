package util;

import java.util.ArrayList;

import tree.*;

public class Identity {
	
	private Node leftSide;
	private Node rightSide;
	private ArrayList<Function> funcs;
	
	
	public Identity(Node right, Node left){
		this.leftSide = left;
		this.rightSide = right;
	}
	
	public boolean evaluate(int x, int y, int z, int w,ArrayList<Function> funcs){
		this.funcs = funcs;
		int rightValue = eval(rightSide,x,y,z,w);
		int leftValue = eval(leftSide,x,y,z,w);
		if(rightValue == leftValue)
			return true;
		else
			return false;
	}
	
	public boolean same(){
		String left = leftSide.toString();
		String right = rightSide.toString();
		if(left.equals(right))
			return true;
		else
			return false;
	}
	
	private int eval(Node node,int x, int y, int z, int w){
		/*
		 * [x] Find if a function or a variable 
		 * [x]if function find its arity cast node and get the left and right sides
		 * [x] once values obtained pass to functiont to get result return
		 * [x]if var return value
		 * [x] if a constant return
		 */
		if(node.getData() instanceof Integer){
			return (Integer)node.getData();
		}
		Character data = (Character)node.getData();
		switch(data){
			case 'x':
				return x;
			case 'y':
				return y;
			case 'z':
				return z;
			case 'w':
				return w;
		}
		int i;
		for(i = 0; i < funcs.size(); i++){
			if(data == funcs.get(i).getSymbol())
				break;
		}
		Function temp = funcs.get(i);
		switch(temp.getArity()){
			case 1://arity of 1 function
			{
				OneNode oneNode = (OneNode)node;
				int result = eval(oneNode.getChild(),x,y,z,w);
				return temp.solve(result);
			}
			case 2: //arity of 2 function
			{
				TwoNode twoNode = (TwoNode)node;
				int leftResult = eval(twoNode.getLeftChild(),x,y,z,w);
				int rightResult = eval(twoNode.getRightChild(),x,y,z,w);
				return temp.solve(leftResult,rightResult);
			}
			case 3: //arity of 3 function
			{
				ThreeNode threeNode = (ThreeNode)node;
				int leftResult = eval(threeNode.getLeftChild(),x,y,z,w);
				int centerResult = eval(threeNode.getCenterChild(),x,y,z,w);
				int rightResult = eval(threeNode.getRightChild(),x,y,z,w);
				return temp.solve(leftResult, centerResult);
			}
			default:
				System.err.println("Something bad happened in Identiy Eval");
				return -1;
		}
		
	}
	
	public String toString(){
		return rightSide+""+Character.toString((char) 0x2261)+""+leftSide;
	}
	

}
