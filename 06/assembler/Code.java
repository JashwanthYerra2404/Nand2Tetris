package assembler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Code {
	
	private Map<String, String> jumpCode;
	private Map<Integer, String> compCode;
	
	private String [] a0;
	private String [] a1;
	
	
	public Code(){
		jumpCode = new HashMap<>();
		jumpCode.put("JGT", "001");
		jumpCode.put("JEQ", "010");
		jumpCode.put("JGE", "011");
		
		jumpCode.put("JLT", "100");
		jumpCode.put("JNE", "101");
		jumpCode.put("JLE", "110");
		jumpCode.put("JMP", "111");
		
		compCode = new HashMap<>();
		compCode.put(0, "101010");
		compCode.put(1, "111111");
		compCode.put(2, "111010");
		compCode.put(3, "001100");
		compCode.put(4, "110000");
		compCode.put(5, "001101");
		compCode.put(6, "110001");
		compCode.put(7, "001111");
		compCode.put(8, "110011");
		compCode.put(9, "011111");
		compCode.put(10, "110111");
		compCode.put(11, "001110");
		compCode.put(12, "110010");
		compCode.put(13, "000010");
		compCode.put(14, "010011");
		compCode.put(15, "000111");
		compCode.put(16, "000000");
		compCode.put(17, "010101");
		
		
		a0 = new String[]{"0", "1", "-1", "D", "A", "!D", "!A", "-D", "-A", 
						"D+1", "A+1", "D-1", "A-1", "D+A", "D-A", "A-D", "D&A", "D|A"};
		
		a1 = new String[]{null, null, null, null, "M", null,"!M", null, "-M", null, "M+1", null, "M-1",
					"D+M", "D-M", "M-D", "D&M", "D|M"};
	}
		
	public String getAddressBits(String address) {
		int intAddress = Integer.parseInt(address);
		String decimal = Integer.toBinaryString(intAddress);
		int addressLen = decimal.length();
		String leftPad = "";
		for(int i = addressLen; i < 15; i++){
			leftPad += "0";
		}
		return leftPad + decimal;
	}

	public String getDestBits(String dest) {
		if(dest != null){
			int bits = 0;
			String[] parts = dest.split("");
			
			for(String s: parts){
				if(s.equals("M")){
					bits += 1;
				}else if(s.equals("D")){
					bits += 10;
				}else if(s.equals("A")){
					bits += 100;
				}
			}
			
			String sBits = Integer.toString(bits);
			String leftPad = "";
			for(int i = sBits.length(); i < 3; i++){
				leftPad += "0";
			}
			return leftPad + sBits;
		}
		
		return "000";
	}

	public String getCompBits(String dest) {
		if(dest != null){
			int indexOfA0 = Arrays.asList(a0).indexOf(dest);
			int indexOfA1 = Arrays.asList(a1).indexOf(dest);
			
			if(indexOfA0 > -1){
				return "0" + compCode.get(indexOfA0);
			}else if(indexOfA1 > -1){
				return "1" + compCode.get(indexOfA1);
			}
			
			return "";
		}
		
		return "";
	}

	public String getJumpBits(String dest) {
		if(dest != null){
			return jumpCode.get(dest);
		}
		
		return "000";
	}
	
}