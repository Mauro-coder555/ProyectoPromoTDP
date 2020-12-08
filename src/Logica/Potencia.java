package Logica;

public class Potencia implements PluginFunction {
	
	private int op1;
	private int op2;
	
	
	public void setParameters(int p1, int p2) {
		this.op1 = p1;
		this.op2 = p2;		
	}

	
	public int getResult() throws InvalidOperatorException {
		int result; int i;
		
		if(op1 == 0 && op2 == 0) throw new InvalidOperatorException("Exponent and Base are ZERO");
		
		if(op2 < 0) throw new InvalidOperatorException("Negative exponent is not valid for this version");
		
		System.out.println("Hola");
		
		i = 0;
		result = 1;
		
		while(i < op2){
			result = result * op1;
			i++;
		}	
		
		return result;
	}

	
	public String getPluginName() {		
		return "Potencia";
	}

	
	public boolean hasError() {		
		boolean error;
		
		error = false;
		
		if(op1 == 0 && op2 == 0)
			error = true;
		
		return error;
	}

}
