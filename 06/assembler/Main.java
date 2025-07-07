package assembler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) throws IOException {
		//File file = new File(args[0]);
		File file = new File("/Users/jashwanthyerra/Desktop/06/RectL.asm");
		String fileName = file.getName();
		System.out.println(fileName);
		int indexOfDot = fileName.indexOf(".");
		if (indexOfDot > -1) {
			fileName = fileName.substring(0, indexOfDot);
		}
		Scanner sc = new Scanner(file);

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".hack"));

		int lineNumber = 0, indexOfComment;
		String str;
		Parser parser;
		Code code;
		SymbolTable symbolTable = new SymbolTable();
		String bits;
		
		int variableMemory = 16;

		while (sc.hasNextLine()) {
			str = sc.nextLine();
			str = str.trim();
			indexOfComment = str.indexOf("//");

			if (!(indexOfComment == 0 || str.equals(""))) {
				if (indexOfComment > 0) {
					parser = new Parser(str.substring(0, indexOfComment).trim());
				} else {
					parser = new Parser(str);
				}

				if (parser.getLabelPart() != null) {
					symbolTable.setSymbol(parser.getLabelPart(), lineNumber);
					continue;
				}

				lineNumber++;
			}
		}
		
		sc.close();
		sc = new Scanner(file);

		while (sc.hasNextLine()) {
			str = sc.nextLine();
			str = str.trim();
			indexOfComment = str.indexOf("//");

			if (!(indexOfComment == 0 || str.equals(""))) {
				if (indexOfComment > 0) {
					parser = new Parser(str.substring(0, indexOfComment).trim());
				} else {
					parser = new Parser(str);
				}

				code = new Code();

				bits = "";

				if (parser.getLabelPart() != null) {
					continue;
				}
				
				if (parser.getCommandType() == 0) { 
                    // A instruction
					try {
						Integer.parseInt(parser.getAddressPart(), 10);
					} catch (NumberFormatException e) {
						Integer addressBit = symbolTable.getSymbol(parser.getAddressPart());
						if(addressBit == null){
							symbolTable.setSymbol(parser.getAddressPart(), variableMemory);
							addressBit = variableMemory;
							System.out.printf("Variable: %s , value: %d", parser.getAddressPart(), variableMemory);
							variableMemory++;
						}
						
						parser.setAddressPart(Integer.toString(addressBit));
						
					}
					bits += "0";
					bits += code.getAddressBits(parser.getAddressPart());
				} else if (parser.getCommandType() == 1) { 
                    // C instruction
					// comp dest jump
					bits += "111";
					bits += code.getCompBits(parser.getCompPart());
					bits += code.getDestBits(parser.getDestPart());
					bits += code.getJumpBits(parser.getJumpPart());
				}

				System.out.printf("Command: %s - machine code: %s\n", parser.getCommand(), bits);

				writer.write(bits + "\n");
			}
		}

		sc.close();
		writer.close();
	}
}