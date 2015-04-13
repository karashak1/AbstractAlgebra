package main;

import java.util.ArrayList;



import TermGenerator.Generator;
import tree.Node;
import util.*;

public class Main implements SharedValues{
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainMenu main;
		SymbolMenu symbols,vars;
		AlgebraTablesMenu algebra;
		Algebra[] algs = new Algebra[8];
		ArrayList<Character> variables = new ArrayList<Character>();
		ArrayList<Function> funcs = new ArrayList<Function>();
		ArrayList<String> returnValues = new ArrayList<String>();
		ArrayList<Integer> constants = new ArrayList<Integer>();
		try{
			symbols = new SymbolMenu(SharedValues.symbolValues,"Symbols",returnValues);
			symbols.setModal(true);
			symbols.setVisible(true);
			//get arity of each symbol from the airty table
			for(int x = 0; x < symbols.getValues().size(); x++){
				char temp = symbols.getValues().get(x).charAt(0);
				for(int y = 0; y < SharedValues.symbolValues.length; y++){
					if(temp == SharedValues.symbolValues[y]){
						if(SharedValues.symbolArity[y] != 0){
							funcs.add(new Function(temp,SharedValues.symbolArity[y]));
							break;
						}
						else{
							constants.add(Integer.parseInt(temp+""));
						}
					}
				}
			}
			//new version we already know arity based on symbol
			/*
			Object[] arity = {1,2,3};
			for(int x = 0; x < symbols.getValues().size(); x++){
				Integer ans = (Integer)JOptionPane.showInputDialog(null,
						"what arity would you like for symbol "+symbols.getValues().get(x),
						"Symbol Arity",
						JOptionPane.PLAIN_MESSAGE,
						null,
						arity,
						2);
				if(ans != 3)
					funcs.add(new Function(symbols.getValues().get(x).charAt(0),ans));
				else{
					Character symbol = (Character)JOptionPane.showInputDialog(null,
							"Choose second symbol",
							"Second Symbol",
							JOptionPane.PLAIN_MESSAGE,
							null,
							SharedValues.symbolValues,
							SharedValues.symbolValues[0]);
					funcs.add(new Function(symbols.getValues().get(x).charAt(0),symbol));
				}
				
			}
			*/
			symbols = null;
			vars = new SymbolMenu(SharedValues.variableValues,"Variables");
			vars.setModal(true);
			vars.setVisible(true);
			for(int x = 0; x < vars.getValues().size(); x++){
				variables.add(vars.getValues().get(x).charAt(0));
			}
			vars = null;
			Generator gen = new Generator(funcs,variables, constants);
			gen.generate();
			ArrayList<Node> terms = gen.getTerms();
			/*
			 * Need to ask user if they want to see terms before
			 * adding algebras
			 */
			//for(int x = 0; x < 100; x++){
			//	System.out.println(terms.get(x));
			//}
			/*
			 * Need to add algebra request...done
			 */
			algebra = new AlgebraTablesMenu(funcs,constants);
			algebra.setModal(true);
			algebra.setVisible(true);
			
			
			
			//System.out.println(terms.size());
			algs[0] = new Algebra(variables,funcs,algebra.getConsts(),gen.getIdentities());
			//System.out.println(algs[0]);
			main = new MainMenu(algs);
			main.setVisible(true);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
