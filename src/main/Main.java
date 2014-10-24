package main;

import java.util.ArrayList;

import javax.swing.*;

import TermGenerator.Generator;
import util.*;

public class Main implements SharedValues{
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainMenu main;
		SymbolMenu symbols,vars;
		ArrayList<Character> variables = new ArrayList<Character>();
		ArrayList<Function> funcs = new ArrayList<Function>();
		ArrayList<String> returnValues = new ArrayList<String>();
		try{
			symbols = new SymbolMenu(SharedValues.symbolValues,"Symbols",returnValues);
			symbols.setModal(true);
			symbols.setVisible(true);
			//get arity of each symbol from the airty table
			for(int x = 0; x < symbols.getValues().size(); x++){
				char temp = symbols.getValues().get(x).charAt(0);
				for(int y = 0; y < SharedValues.symbolValues.length; y++){
					if(temp == SharedValues.symbolValues[y]){
						funcs.add(new Function(temp,SharedValues.symbolArity[y]));
						break;
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
			for(Function x : funcs){
				System.out.println(x);
			}
			symbols = null;
			vars = new SymbolMenu(SharedValues.variableValues,"Variables");
			vars.setModal(true);
			vars.setVisible(true);
			for(int x = 0; x < vars.getValues().size(); x++){
				variables.add(vars.getValues().get(x).charAt(0));
			}
			vars = null;
			/*
			 * Need to add algebra request
			 */
			Generator gen = new Generator(funcs,variables);
			gen.generate();
			ArrayList<Identity> terms = gen.getTerms();
			//System.out.println(terms.size());
			main = new MainMenu(terms,funcs);
			main.setVisible(true);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
