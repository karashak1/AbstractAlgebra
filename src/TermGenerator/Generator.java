package TermGenerator;

import java.util.*;

import tree.*;
import util.*;

public class Generator {
	
	private ArrayList<Identity> identities;
	private ArrayList<Node> terms;
	private ArrayList<Function> funcs;
	private ArrayList<Character> vars;
	private ArrayList<Integer> constants;
	
	public Generator(ArrayList<Function> symbols, ArrayList<Character> vars, ArrayList<Integer> constants){
		identities = new ArrayList<Identity>();
		terms = new ArrayList<Node>();
		funcs = symbols;
		this.vars = vars;
		this.constants = constants;
	}
	
	public void generate(){
		//ArrayList<Node> terms = new ArrayList<Node>();
		for(Character i : vars)
			terms.add(new Node(i));
		for(Integer i : this.constants)
			terms.add(new Node(i));
		while(terms.size() < 200){
			ArrayList<Node> temp = new ArrayList<Node>();
			for(Function i : funcs){
				switch(i.getArity()){
				case 1:
					for(int x = 0; (x < terms.size() && temp.size() < 1000) ; x++){
						temp.add(new OneNode(i.getSymbol(),terms.get(x)));
					}
					break;
				case 2:
					for(int x = 0; (x < terms.size() && temp.size() < 1000) ; x++)
						for(int y = 0; y < terms.size() && temp.size() < 1000; y++){
							temp.add(new TwoNode(i.getSymbol(), terms.get(x),terms.get(y)));
						}
					break;
				case 3:
					for(int x = 0; x < terms.size() && temp.size() < 1000; x++)
						for(int y = 0; y < terms.size() && temp.size() < 1000; y++)
							for(int z = 0; z < terms.size() && temp.size() < 1000; z++){
								temp.add(new ThreeNode(i.getSymbol(),i.getSecondarySymbol(),terms.get(x),terms.get(y),terms.get(z)));
							}
					break;
				default:
					System.err.println("That should not have happened!");
					System.exit(1);
				}
			}
			//System.out.println("out of loop");
			for(Node i: temp){
				if(!terms.contains(i))
					terms.add(i);
			}
			//System.out.println(terms.size());
			
		}
		//ArrayList<String> stringTerms = new ArrayList<String>();
		for(Node i: terms)
			for(Node j : terms)
				if(!i.toString().equalsIgnoreCase(j.toString()))
					this.identities.add(new Identity(i,j));
		return;
	}
	
	public ArrayList<Node> getTerms(){
		return terms;
	}
	
	public ArrayList<Identity> getIdentities(){
		return this.identities;
	}
	
	public static void main(String args[]){
		ArrayList<Node> terms = new ArrayList<Node>();
		terms.add(new Node(new Character('x')));
		ArrayList<Character> vars = new ArrayList<Character>();
		vars.add('x');
		//terms.add(new Node(new Character('y')));
		ArrayList<Function> funcs = new ArrayList<Function>();
		funcs.add(new Function('+',2));
		//funcs.add(new Function('-',2));
		//funcs.add(new Function('!',1));
		while(terms.size() < 100){
			ArrayList<Node> temp = new ArrayList<Node>();
			for(Function i : funcs){
				switch(i.getArity()){
				case 1:
					for(int x = 0; x < terms.size(); x++){
						temp.add(new OneNode(i.getSymbol(),terms.get(x)));
					}
					break;
				case 2:
					for(int x = 0; x < terms.size(); x++)
						for(int y = 0; y < terms.size(); y++){
							temp.add(new TwoNode(i.getSymbol(), terms.get(x),terms.get(y)));
						}
					break;
				case 3:
					for(int x = 0; x < terms.size(); x++)
						for(int y = 0; y < terms.size(); y++)
							for(int z = 0; z < terms.size(); z++){
								
							}
					break;
				default:
					System.err.println("That should not have happened!");
					System.exit(1);
				}
			}
			for(Node i: temp){
				if(!terms.contains(i))
					terms.add(i);
			}
			
		}
		System.out.println(terms.size());
		for(Node i: terms)
			System.out.println(i);
		Generator gen = new Generator(funcs,vars, new ArrayList<Integer>());
		gen.generate();
		ArrayList<Identity> stringTerms = gen.getIdentities();
		System.out.println(stringTerms.size());
		for(Identity i: stringTerms)
			System.out.println(i);
		
	}
	

}
