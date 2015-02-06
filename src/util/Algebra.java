package util;

import java.util.*;

public class Algebra {
	
	private ArrayList<Character> variables;
	private ArrayList<Function> functions;
	private ArrayList<Integer> constants;
	private ArrayList<Identity> identities;
	private ArrayList<Identity> potentialIdentities;
	
	public Algebra(ArrayList<Character> variables,ArrayList<Function> funcs,ArrayList<Integer> constants, ArrayList<Identity> identities){
		functions = funcs;
		this.constants = constants;
		this.potentialIdentities = identities;
		this.identities = new ArrayList<Identity>();
		this.variables = variables;
		eval();
	}
	
	private void eval(){
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
			int x,y,z,w;
			boolean forAll = true;
			switch(variables.size()){
			case 1:
				for(x = 0; x < constants.size(); x++){
					forAll = !temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(x), this.constants.get(x), functions);
					if(!forAll)
						break;
				}
				break;
			case 2:
				for(x = 0; x < constants.size(); x++){
					for(y = 0; y < constants.size(); y++){
						if(constants.contains('x')){
							if(constants.contains('z')){// x z
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(y), this.constants.get(x), functions);
							}
							else if(constants.contains('y')){// x y
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(y), this.constants.get(x), this.constants.get(x), functions);
							}
							else{ //x w
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(x), this.constants.get(y), functions);
							}
						}
						else if(constants.contains('y')){
							if(constants.contains('z')){// y z
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(y), this.constants.get(x), functions);
							}
							else{ //y w
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(x), this.constants.get(y), functions);
							}
						}
						else{ //z w
							forAll = !temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(x), this.constants.get(y), functions);
						}
						if(!forAll)
							break;
					}
				}
				break;
			case 3:
				for(x = 0; x < constants.size(); x++){
					for(y = 0; y < constants.size(); y++){
						for(z = 0; z < constants.size(); z++){
							if(constants.contains('x') && constants.contains('y') && constants.contains('z')){ // x y z
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(y), this.constants.get(z), this.constants.get(x), functions);
							}
							else if(constants.contains('x') && constants.contains('z') && constants.contains('w')){// x z w
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(y), this.constants.get(z), functions);
							}
							else if(constants.contains('x') && constants.contains('y') && constants.contains('w')){//x y w
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(y), this.constants.get(x), this.constants.get(z), functions);
							}
							else{//y z w
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(x), this.constants.get(y), this.constants.get(z), functions);
							}
							if(!forAll)
								break;
						}
					}
				}
				break;
			case 4:
				for(x = 0; x < constants.size(); x++){
					for(y = 0; y < constants.size(); y++){
						for(z = 0; z < constants.size(); z++){
							for(w = 0; w < constants.size(); w++){
								forAll = !temp.evaluate(this.constants.get(x), this.constants.get(y), this.constants.get(z), this.constants.get(w), functions);
								if(!forAll)
									break;
						
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
		if(this.potentialIdentities.size() < 10)
			identSize = this.potentialIdentities.size()-1;
		else
			identSize = 10;
		for(x = 0; x < identSize; x++)
			temp+= this.potentialIdentities.get(x)+"\n";
		temp += this.potentialIdentities.get(x)+"\n";
		return temp;
	}

}
