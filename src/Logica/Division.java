package Logica;

public class Division implements PluginFunction {
	
	private int op1;
	private int op2;
	
	
	public void setParameters(int p1, int p2) {
		this.op1 = p1;
		this.op2 = p2;		
	}

	
	public int getResult() throws DivideByZeroException {
		int result;
		
		result = 0;
		
		if(op2 != 0)
			result = op1/op2;
		else throw new DivideByZeroException("");
		
		return result;
	}

	
	public String getPluginName() {		
		return "Division";
	}

	
	public boolean hasError() {
		boolean error;
		
		error = op2 == 0;
		
		return error;
	}

}
