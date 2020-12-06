package Logica;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class PluginClassLoader extends ClassLoader {
	
	// Atributos
	
	private File directory;
	
	// Constructor
	
	public PluginClassLoader(File dir){
		directory = dir;
	}
	
	
	// Comandos y Consultas
	
	public Class loadClass(String name) throws ClassNotFoundException{	
		return load(name, true);
	}


	private Class load(String classname, boolean resolve) throws ClassNotFoundException {
		try {
	        // first check the cache.
	        Class c = findLoadedClass(classname);
	       

	        // try to load the class as a system class
	       
	        if (c == null) {
	          try { c = findSystemClass(classname); }
	          catch (Exception ex) {}
	        }

	       
	        // try to load it from a file in (or beneath) the directory
	        // specified when this ClassLoader object was created. 
	        if (c == null) {
	          
	          String filename = classname.replace('.',File.separatorChar)+".class";
	          
	          filename = "Logica."+filename;
	          
	          
	          File f = new File(directory, filename);
	          
	          
	          int length = (int) f.length();
	          byte[] classbytes = new byte[length];
	          
	
	          DataInputStream in = new DataInputStream(new FileInputStream(f));
	      
	          in.readFully(classbytes);
	          in.close();
	          
	          //System.out.println(classname);
	          c = defineClass(classname,classbytes, 0, length);
	        }

	        if (resolve) resolveClass(c);

	        
	        return c;
	      }
	     
	      catch (Exception ex) { throw new ClassNotFoundException(ex.toString()); }
	    }
		
}
