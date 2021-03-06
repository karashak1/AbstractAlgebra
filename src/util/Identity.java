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
				//int rightResult = eval(threeNode.getRightChild(),x,y,z,w);
				return temp.solve(leftResult, centerResult);
			}
			default:
				System.err.println("Something bad happened in Identiy Eval");
				return -1;
		}
		
	}
	
	public static int evaluate(Node node,int x, int y, int z, int w,ArrayList<Function> funcs){
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
				int result = evaluate(oneNode.getChild(),x,y,z,w,funcs);
				return temp.solve(result);
			}
			case 2: //arity of 2 function
			{
				TwoNode twoNode = (TwoNode)node;
				int leftResult = evaluate(twoNode.getLeftChild(),x,y,z,w,funcs);
				int rightResult = evaluate(twoNode.getRightChild(),x,y,z,w,funcs);
				return temp.solve(leftResult,rightResult);
			}
			case 3: //arity of 3 function
			{
				ThreeNode threeNode = (ThreeNode)node;
				int leftResult = evaluate(threeNode.getLeftChild(),x,y,z,w,funcs);
				int centerResult = evaluate(threeNode.getCenterChild(),x,y,z,w,funcs);
				//int rightResult = evaluate(threeNode.getRightChild(),x,y,z,w,funcs);
				return temp.solve(leftResult, centerResult);
			}
			default:
				System.err.println("Something bad happened in Identiy Eval");
				return -1;
		}
	}
	
	public String toString(){
		/*
		String right,left;
		if(rightSide.toString().charAt(0) == '(' && rightSide.toString().charAt(rightSide.toString().length()-1) == ')')
			right = rightSide.toString().substring(1, rightSide.toString().length()-1);
		else
			right = rightSide.toString();
		if(leftSide.toString().charAt(0) == '(' && leftSide.toString().charAt(leftSide.toString().length()-1) == ')')
			left = leftSide.toString().substring(1, leftSide.toString().length()-1);
		else
			left = leftSide.toString();
			*/
		return rightSide.toString()+""+Character.toString((char) 0x2261)+""+leftSide.toString();
	}
	

}
