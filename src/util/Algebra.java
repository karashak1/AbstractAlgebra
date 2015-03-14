package util;

import java.util.*;

public class Algebra {
	
	private ArrayList<Character> variables;
	private ArrayList<Function> functions;
	private ArrayList<Integer> constants;
	private ArrayList<Identity> identities;
	private ArrayList<Identity> potentialIdentities;
	private ArrayList<Identity> rejectedIdentites;
	
	public Algebra(ArrayList<Character> variables,ArrayList<Function> funcs,ArrayList<Integer> constants, ArrayList<Identity> identities){
		functions = funcs;
		this.constants = constants;
		this.potentialIdentities = identities;
		this.identities = new ArrayList<Identity>();
		this.rejectedIdentites = new ArrayList<Identity>();
		this.variables = variables;
		eval();
	}
	
	
	public ArrayList<Identity> getIdenitites(){
		return identities;
	}
	
	public ArrayList<Function> getFunctions(){
		return functions;
	}
	
	public ArrayList<Character> getVariables(){
		return this.variables;
	}
	
	public ArrayList<Identity> getRejectIdenities(){
		return this.rejectedIdentites;
	}
	
	private void eval(){
		//System.out.println("in eval");
		/*
		 * TODO
		 * [x] loop over all the potential identites
		 * [x] cases for each number of variables
		 *    [x] find the variables the user is using
		 *    [x] increment them in the loop
		 *    [x] loop over the constants * 
		 * [x] if it goes through every case true then add to identities
		 */
		for(int i = 0; i < this.potentialIdentities.size(); i++){
			Identity temp = this.potentialIdentities.get(i);
			if(temp == null)
				continue;
			int x,y,z,w;
			boolean forAll = true;
			switch(variables.size()){
			case 1:
				for(x = 0; x < constants.size() && forAll == true; x++){
					forAll = temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(x), this.constants.get(x), functions);
				}
				break;
			case 2:
				for(x = 0; x < constants.size() && forAll == true; x++){
					for(y = 0; y < constants.size() && forAll == true; y++){
						if(this.variables.contains('x')){
							if(this.variables.contains('z')){// x z
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(y), this.constants.get(x), functions);
							}
							else if(this.variables.contains('y')){// x y
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(y), this.constants.get(x), this.constants.get(x), functions);
							}
							else{ //x w
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(x), this.constants.get(y), functions);
							}
						}
						else if(this.variables.contains('y')){
							if(constants.contains('z')){// y z
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(y), this.constants.get(x), functions);
							}
							else{ //y w
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(x), this.constants.get(y), functions);
							}
						}
						else{ //z w
							//System.out.println("In this one for some odd reason");
							forAll = temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(x), this.constants.get(y), functions);
						}
					}
				}
				break;
			case 3:
				for(x = 0; x < constants.size() && forAll == true; x++){
					for(y = 0; y < constants.size() && forAll == true; y++){
						for(z = 0; z < constants.size() && forAll == true; z++){
							if(this.variables.contains('x') && this.variables.contains('y') && this.variables.contains('z')){ // x y z
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(y), this.constants.get(z), this.constants.get(x), functions);
							}
							else if(this.variables.contains('x') && this.variables.contains('z') && this.variables.contains('w')){// x z w
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(y), this.constants.get(z), functions);
							}
							else if(this.variables.contains('x') && this.variables.contains('y') && this.variables.contains('w')){//x y w
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(y), this.constants.get(x), this.constants.get(z), functions);
							}
							else{//y z w
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(y), this.constants.get(z), functions);
							}
						}
					}
				}
				break;
			case 4:
				for(x = 0; x < constants.size() && forAll == true; x++){
					for(y = 0; y < constants.size() && forAll == true; y++){
						for(z = 0; z < constants.size() && forAll == true; z++){
							for(w = 0; w < constants.size() && forAll == true; w++){
								forAll = temp.evaluate(this.constants.get(x), this.constants.get(y), this.constants.get(z), this.constants.get(w), functions);
						
							}
						}
					}
				}
				break;
			default:
				forAll = false;
				System.err.println("Error in the algebra eval function");
			}
			if(forAll)
				this.identities.add(temp);
			else
				this.rejectedIdentites.add(temp);
		}
	}
	
	
	public String toString(){
		String temp = "";
		int x;
		temp += "variables:";
		for(x = 0; x < variables.size()-1; x++)
			temp += variables.get(x)+",";
		temp += variables.get(x)+"\n";
		temp += "functions:";
		for(x = 0; x < functions.size()-1; x++)
			temp+= functions.get(x)+",";
		temp += functions.get(x)+"\n";
		temp += "constants:";
		for(x = 0; x < constants.size()-1; x++)
			temp+= constants.get(x)+",";
		temp += constants.get(x)+"\n";
		temp += "identities:";
		int identSize;
		/*
		temp += "Number of potential identites"+this.potentialIdentities.size()+"\n";
		//if(this.potentialIdentities.size() < 10)
		//	identSize = this.potentialIdentities.size()-1;
		else
			identSize = 10;
		for(x = 0; x < identSize; x++)
			temp+= this.potentialIdentities.get(x)+"\n";
		//temp += this.potentialIdentities.get(x)+"\n";
		 */
		temp += "Number or real identites:"+this.identities.size()+"\n";
		if(this.identities.size() < 10000)
			identSize = this.identities.size()-1;
		else
			identSize = 10000;
		for(x = 0; x < identSize; x++)
			temp+= this.identities.get(x)+"\n";
		
		return temp;
	}
	
	public String toString(int start, int end){
		String temp = "";
		return temp;
	}

}
