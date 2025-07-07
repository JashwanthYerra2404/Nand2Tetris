package assembler;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
	
	private Map<String, Integer> symbol;
	
	public SymbolTable(){
		symbol = new HashMap<>();
		// for Registers R0 to R15
		for(int i=0; i < 16; i++){
			symbol.put("R" + i, i);
		}
		
		symbol.put("SCREEN", 16384);
		symbol.put("KBD", 24576);
		
		symbol.put("SP", 0);
		symbol.put("LCL", 1);
		symbol.put("ARG", 2);
		symbol.put("THIS", 3);
		symbol.put("THAT", 4);	
	}
	
	public void setSymbol(String symbol, int value){
		this.symbol.put(symbol, value);
	}
	
	public Integer getSymbol(String symbol){
		
		return this.symbol.get(symbol);
	}

}