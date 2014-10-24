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
	
	public Integer getArity(){
		return this.arity;
	}
	
	public Character getSecondarySymbol(){
		return this.secondarySymbol;
	}
	
	public String toString(){
		return Character.toString(symbol);
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
