package Logica;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Logica {

	private List <PluginFunction> plugins;
	
	// - - - - - - - - - -
	
	public Logica() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{		
				
		plugins = new ArrayList<PluginFunction>();
		
		getPlugins();
		
	}
	
	// Comandos y Consultas
	
	
	
	public void loadPlugin(){
		
		
		
	}
	
	public int operate(String name, int p1, int p2) throws PluginErrorException, DivideByZeroException, InvalidOperatorException{
		
		PluginFunction op; int result;
		
		op = getOperation(name);
		
		op.setParameters(p1, p2);
		
		if(op.hasError())throw new PluginErrorException("Inizialization error");
		
		result = op.getResult();
		
		if(op.hasError())throw new PluginErrorException("Plugin Execution Error");
		
		return result;
		
	}
	
	public List<String> getOperations(){
		List<String> list;
		
		list = new ArrayList<String>();
		
		for (PluginFunction p : plugins ){
			list.add(p.getPluginName());			

		}
		
		
		return list;
		
	}
	
	public int cantidadDeOperaciones(){
		return plugins.size();
	}
	
	private PluginFunction getOperation(String name) throws PluginErrorException {
		Iterator<PluginFunction> it; boolean stop; PluginFunction op;
		
		stop = false;
		it = plugins.iterator();
		op = null;
		
		while (it.hasNext() && !stop) {
			op = (PluginFunction) it.next();
			if(op.getPluginName().equals(name))
				stop = true;
			
		}
		
		if(op == null) throw new PluginErrorException("Plugin not found");
		
		return op;
	}

	private void getPlugins() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		
		
		File directory = new File(System.getProperty("user.dir")+File.separator+"Plugins");
		
		
		PluginClassLoader myClassLoader = new PluginClassLoader(directory);
		
		
		
		
		if (true) {
		
			String[] files = directory.list();
			
			
			
			for (int i=0; i<files.length; i++) {
				
				    
					if (files[i].endsWith(".class")){
						
				
					Class c = myClassLoader.loadClass("Logica."+files[i].substring(0, files[i].indexOf(".")));
					
					Class[] intf = c.getInterfaces();
					for (int j=0; j<intf.length; j++) {
						if (intf[j].getName().equals("Logica.PluginFunction")) {
							
							@SuppressWarnings("unchecked")
							PluginFunction pf = (PluginFunction) c.getDeclaredConstructor().newInstance();
							plugins.add(pf);
							
						
						}
					}
			}
			}
		}
	}
	
	
	
	
	
}
