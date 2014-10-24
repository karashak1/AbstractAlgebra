package util;

import java.util.*;

public class Algebra {
	
	private String set;
	private ArrayList<Function> functions;
	private ArrayList<Object> constants;
	private ArrayList<Identity> identities;
	
	public Algebra(String set,ArrayList<Function> funcs,ArrayList<Object> constants, ArrayList<Identity> identities){
		this.set = set;
		functions = funcs;
		this.constants = constants;
		this.identities = identities;
	}
	
	public String toString(){
		String temp = "";
		temp += "set:"+set+"\n";
		temp += "functions:";
		int x;
		for(x = 0; x < functions.size()-1; x++)
			temp+= functions.get(x)+",";
		temp += functions.get(x)+"\n";
		temp += "constants:";
		for(x = 0; x < constants.size()-1; x++)
			temp+= constants.get(x)+",";
		temp += constants.get(x)+"\n";
		temp += "identities:";
		for(x = 0; x < identities.size()-1; x++)
			temp+= identities.get(x)+"\n";
		temp += identities.get(x)+"\n";
		return temp;
	}

}
