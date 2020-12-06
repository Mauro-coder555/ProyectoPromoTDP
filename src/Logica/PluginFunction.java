package Logica;

public interface PluginFunction {
	
	public void setParameters(int p1,int p2);
	
	public int getResult() throws DivideByZeroException, InvalidOperatorException;
	
	public String getPluginName();
	
	public boolean hasError();

}
