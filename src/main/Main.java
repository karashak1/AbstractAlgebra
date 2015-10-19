package main;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import TermGenerator.Generator;
import util.*;

public class Main implements SharedValues{
	/*
	 * TODO list
	 * [x] add a screen to see the terms or to add an algebra
	 * [x] break out the first menu into 3  separate  menus
	 * 		[x] arity of 1
	 * 		[x] arity of 2
	 * 		[x] arity of 0
	 */
	

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
			//arity
			int count1,count2,count0,i;
			for(i = count1 = count2 = count0 = 0; i < SharedValues.symbolValues.length; i++){
				if(SharedValues.symbolArity[i] == 1)
					count1++;
				else if(SharedValues.symbolArity[i] == 2)
					count2++;
				else
					count0++;
			}
			Character[] arity1 = new Character[count1];
			Character[] arity2 = new Character[count2];
			Character[] arity0 = new Character[count0];
			//now must fill array
			for(i = count1 = count2= count0 = 0; i < SharedValues.symbolValues.length;i++)
				if(SharedValues.symbolArity[i] == 1)
					arity1[count1++] = SharedValues.symbolValues[i];
				else if(SharedValues.symbolArity[i] == 2)
					arity2[count2++] = SharedValues.symbolValues[i];
				else
					arity0[count0++] = SharedValues.symbolValues[i];
			//arity 1
			//need to place while here
			int loop = 0;
			Iterator<Function> iter;
			while(loop < 4){
				switch(loop){
					case 0:
						symbols = new SymbolMenu(arity1,"Unary Symbols",returnValues,false);
						symbols.setModal(true);
						symbols.setVisible(true);
						iter = funcs.iterator();
						while (iter.hasNext()) {
						    Function str = iter.next();
						    if (str.getArity() == 1)
						        iter.remove();
						}
						//get arity of each symbol from the airty table
						for(int x = 0; x < symbols.getValues().size(); x++){
							char temp = symbols.getValues().get(x).charAt(0);
							for(int y = 0; y < SharedValues.symbolValues.length; y++){
								if(temp == SharedValues.symbolValues[y]){
									funcs.add(new Function(temp,1));
								}
							}
						}
						returnValues.clear();
						if(!symbols.checkBack() == true)
							loop++;
						else
							loop--;
						break;
					
					//arity 2
					case 1:
						symbols = new SymbolMenu(arity2,"Binary Symbols",returnValues,true);
						symbols.setModal(true);
						symbols.setVisible(true);
						
						iter = funcs.iterator();
						while (iter.hasNext()) {
						    Function str = iter.next();
						    if (str.getArity() == 2)
						        iter.remove();
						}
						//get arity of each symbol from the airty table
						for(int x = 0; x < symbols.getValues().size(); x++){
							char temp = symbols.getValues().get(x).charAt(0);
							for(int y = 0; y < SharedValues.symbolValues.length; y++){
								if(temp == SharedValues.symbolValues[y]){
									funcs.add(new Function(temp,2));
								}
							}
						}
						returnValues.clear();
						if(!symbols.checkBack() == true)
							loop++;
						else
							loop--;
						break;
					//Constants
					case 2:
						symbols = new SymbolMenu(arity0,"Constant Symbols",returnValues,true);
						symbols.setModal(true);
						symbols.setVisible(true);
						constants.clear();
						//get arity of each symbol from the airty table
						for(int x = 0; x < symbols.getValues().size(); x++){
							constants.add(Integer.parseInt(symbols.getValues().get(x)));
						}
						returnValues.clear();
						if(!symbols.checkBack() == true)
							loop++;
						else
							loop--;
						break;
					case 3:
						vars = new SymbolMenu(SharedValues.variableValues,"Variables",true);
						vars.setModal(true);
						vars.setVisible(true);
						variables.clear();
						for(int x = 0; x < vars.getValues().size(); x++){
							variables.add(vars.getValues().get(x).charAt(0));
						}
						if(!vars.checkBack() == true)
							loop++;
						else
							loop--;
						break;
					default:
						System.err.println("we had a problem with the symbol menues");
						
				}
			}
			symbols = null;
			vars = null;
			Generator gen = new Generator(funcs,variables, constants);
			gen.generate();
			//ArrayList<Node> terms = gen.getTerms();
			//ArrayList<Identity> ids = gen.getIdentities();
			/*
			 * Need to ask user if they want to see terms before
			 * adding algebras
			 */
			/*for(int x = 0; x < 100; x++){
				System.out.println(terms.get(x));
			}
			for(int x = 0; x < 100; x++){
				System.out.println(ids.get(x));
			}
			*/
			Object[] options = {"Yes, please",
                    "No, create Algebra"};
			int n = JOptionPane.showOptionDialog(null,
							"Would you like to see the terms?",
							"Terms",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[1]);
			//show terms
			if(n == 0){
				TermMenu terms = new TermMenu(gen.getTerms(),funcs,variables);
				terms.setModal(true);
				terms.setVisible(true);
			}
			/*
			 * Need to add algebra request...done
			 */
			algebra = new AlgebraTablesMenu(funcs,constants);
			algebra.setModal(true);
			algebra.setVisible(true);
			
			
			
			//System.out.println(terms.size());
			algs[0] = new Algebra(variables,funcs,algebra.getConsts(),algebra.getTableValues(),gen.getIdentities());
			//System.out.println(algs[0]);
			main = new MainMenu(algs);
			main.setVisible(true);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
