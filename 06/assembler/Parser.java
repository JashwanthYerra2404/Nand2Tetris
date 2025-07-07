package assembler;

public class Parser {
	
	private String command;
	
	private String addressPart = null;
	private String destPart = null;
	private String compPart = null;
	private String jumpPart = null;
	
	private String labelPart = null;
	
	public Parser(String command){
		this.command = command;
		
		int index = this.command.indexOf("@");
		if(index > -1){
			// A part
			addressPart = this.command.substring(index + 1);
		}else{
            // Lable part
			int startIndex = this.command.indexOf("(");
			int endIndex = this.command.indexOf(")");
			
			if(startIndex > -1 && endIndex > -1){
				labelPart = this.command.substring(startIndex + 1, endIndex);
				return;
			}
			//C part
			String[] equalSepParts = this.command.split("=");
			String[] semicolonSepParts = this.command.split(";");
			int indexOfEq = this.command.indexOf("=");
			int indexOfSemicolon = this.command.indexOf(";");
			
			if(indexOfEq > -1 && indexOfSemicolon > -1){
				destPart = equalSepParts[0].trim();
				compPart = semicolonSepParts[0].split("=")[1].replaceAll(" ","");
				jumpPart = semicolonSepParts[1].trim();
				
			}else if(indexOfEq > -1){
				destPart = equalSepParts[0].trim();
				compPart = equalSepParts[1].replaceAll(" ","");
				
			}else if(indexOfSemicolon > -1){
				compPart = semicolonSepParts[0].replaceAll(" ","");
				jumpPart = semicolonSepParts[1].trim();
			}
		}
	}
	
	public int getCommandType(){
		if(this.command.indexOf("@") > -1){
			return 0;
		}
		
		return 1;
		
	}
	
	public String getCommand() {
		return command;
	}

	public String getAddressPart() {
		return addressPart;
	}

	public String getDestPart() {
		return destPart;
	}

	public String getCompPart() {
		return compPart;
	}

	public String getJumpPart() {
		return jumpPart;
	}

	public String getLabelPart() {
		return labelPart;
	}

	public void setAddressPart(String addressPart) {
		this.addressPart = addressPart;
	}
	
}