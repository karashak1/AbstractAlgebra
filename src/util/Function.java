package util;

public class Function {
	
	private Character symbol;
	private Character secondarySymbol;
	private Integer arity;
	private Integer table[][];
	
	
	public Function(Character symbol, Integer arity){
		this.symbol = symbol;
		this.arity = arity;
	}
	
	public Function(Character symbol, Character support){
		this.symbol = symbol;
		this.arity = 3;
		this.secondarySymbol = support;
	}
	
	public Character getSymbol(){
		return this.symbol;
	}
	
	public void setTable(Integer[][] table){
		this.table = table;
	}
	
	public Integer[][] getTable(){
		return table;
	}
	
	public Integer solve(Integer x){
		if(arity == 1){
			for(int i = 1; x < table[0].length; i++){
				if(table[0][i] == x){
					return table[1][i];
				}
			}
			return null;
		}
		else{
			return null;
		}
	}
	
	public Integer solve(Integer x, Integer y){
		if(arity == 2){
			int i, j;
			for(i = 1; i < table[0].length; i++){
				if(table[0][i] == x){
					for(j = 1; j < table.length; j++){
						if(table[j][0] == y){
							return table[j][i];
						}
					}
				}
			}
			return null;
		}
		else{
			return null;
		}
	}
	
	public Integer getArity(){
		return this.arity;
	}
	
	public Character getSecondarySymbol(){
		return this.secondarySymbol;
	}
	
	public String toString(){
		return Character.toString(symbol)+ "  "+ arity;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Function))
			return false;
		Function temp = (Function)o;
		if(this.arity == temp.arity){
			if(this.arity == 3){
				if((this.symbol == temp.symbol) && (this.secondarySymbol == temp.secondarySymbol))
					return true;
			}
			else{
				if(this.symbol == temp.symbol)
					return true;
			}
		}
		return false;
	}
	

}
