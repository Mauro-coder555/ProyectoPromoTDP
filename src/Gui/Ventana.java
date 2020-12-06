package Gui;

import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.*;

import Logica.Logica;

public class Ventana extends JFrame {

	private Logica miLogica;
	
	public Ventana(String nombre){
		super(nombre);
		inicializar();
		
		JToolBar menuDesplegable;  int i;
		
		
		try {
			try {
				miLogica = new Logica();
			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		menuDesplegable = getToolBar();
		
		menuDesplegable.setVisible(true);
		
	
		
		
		
		this.getContentPane().add(menuDesplegable, BorderLayout.NORTH);
		
	}
	
	
	public static void main(String args[]){
		
		Ventana miVentana = new Ventana("Calucladora");
		miVentana.setVisible(true);
		
	}
	
	
	private void inicializar(){
		setSize(400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	 private JToolBar getToolBar() {
		 
		 	JToolBar barraBotones = new JToolBar();
		 	cargarBarra(barraBotones);   
		 	
	        return barraBotones;
	    }


	private void cargarBarra(JToolBar barraBotones) {
		List<String> operaciones;
		
		operaciones = miLogica.getOperations();
		
		for (String nombre : operaciones){
		
			barraBotones.add(new JButton(nombre));			
		}
		
	}
}
