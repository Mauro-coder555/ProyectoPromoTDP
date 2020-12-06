package Logica;

public class Resta implements PluginFunction {
	
	private int op1;
	private int op2;
	
	
	public void setParameters(int p1, int p2) {
		this.op1 = p1;
		this.op2 = p2;		
	}

	
	public int getResult() {
		int result;
		
		result = op1-op2;
		return result;
	}

	
	public String getPluginName() {		
		return "Resta";
	}

	
	public boolean hasError() {		
		return false;
	}

}
