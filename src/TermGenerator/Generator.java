package TermGenerator;

import java.util.*;

import tree.*;
import util.*;

public class Generator {
	
	private ArrayList<Identity> terms;
	private ArrayList<Function> funcs;
	private ArrayList<Character> vars;
	
	public Generator(ArrayList<Function> symbols, ArrayList<Character> vars){
		terms = new ArrayList<Identity>();
		funcs = symbols;
		this.vars = vars;
	}
	
	public void generate(){
		ArrayList<Node> terms = new ArrayList<Node>();
		for(Character i : vars)
			terms.add(new Node(i));
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
								temp.add(new ThreeNode(i.getSymbol(),i.getSecondarySymbol(),terms.get(x),terms.get(y),terms.get(z)));
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
		//ArrayList<String> stringTerms = new ArrayList<String>();
		for(Node i: terms)
			for(Node j : terms)
			this.terms.add(new Identity(i,j));
		//System.out.println(terms.size());
		return;
	}
	
	public ArrayList<Identity> getTerms(){
		return terms;
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
		Generator gen = new Generator(funcs,vars);
		gen.generate();
		ArrayList<Identity> stringTerms = gen.getTerms();
		System.out.println(stringTerms.size());
		for(Identity i: stringTerms)
			System.out.println(i);
		
	}
	

}
