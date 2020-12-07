package Gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.*;

import Logica.DivideByZeroException;
import Logica.InvalidOperatorException;
import Logica.Logica;
import Logica.PluginErrorException;

public class Ventana extends JFrame implements ItemListener {

	private Logica miLogica;
	private JComboBox<String> menuDesplegable;
	private Container miContenedor;
	
	
	public Ventana(String nombre){
		super(nombre);
		miContenedor = this.getContentPane();	
		inicializar();
		
		crearLogica(); 
		
		
		
		
	
		
		cargarComponentes();
		
	}
	
	
	public static void main(String args[]){
		
		Ventana miVentana = new Ventana("Calucladora");
		miVentana.setVisible(true);
		
	}
	
	private void crearLogica(){
		try {
			try {
				miLogica = new Logica();
			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
	}
	
	private void operar(String operacion){
		int resultado;
		resultado = 0;
		
		String op1 = JOptionPane.showInputDialog("Introduzca un número para el Operando 1");
		String op2 = JOptionPane.showInputDialog("Introducción  número para el Operando 2");
		
		if(isNumeric(op1) && isNumeric(op2) && !operacion.equals("")){
			try {
								
				resultado = miLogica.operate(operacion, Integer.parseInt(op1),Integer.parseInt(op2));
				
			} catch (NumberFormatException | PluginErrorException | DivideByZeroException | InvalidOperatorException e)
		       {e.printStackTrace();  JOptionPane.showMessageDialog(null, "Ha ocurrido un error :(");}
		}
		
		JLabel res = new JLabel();
		res.setText("El resultado es :" + resultado);
		res.setLocation(150,250);
		miContenedor.add(res);
		
		//menuDesplegable.setEnabled(true);
	}
	
	private void actualizar(){
		
		
		
	}
	
	private void cargarComponentes(){
		
		
		menuDesplegable = getMenu();		
		menuDesplegable.setVisible(true);
		menuDesplegable.setLocation(100,150);
		miContenedor.add(menuDesplegable);
		
		
		
		
	}
	
	private void limpiarComponentes(){
		miContenedor.remove(menuDesplegable);
		
		
	}
	
	
	private void inicializar(){
		setSize(400,400);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	 private JComboBox<String> getMenu() {
		 
		    JComboBox<String> menuBotones = new JComboBox<String>();
		 	cargarMenuBotones(menuBotones); 
		 	menuBotones.addItemListener(this);
		 	menuBotones.setBounds(10,10,200,20);
		 	
	        return menuBotones;
	    }


	private void cargarMenuBotones(JComboBox<String> menuBotones) {
		List<String> operaciones;
		
		operaciones = miLogica.getOperations();
		menuBotones.addItem("Operaciones Disponibles");
		
		for (String nombre : operaciones){
		
			menuBotones.addItem(nombre);			
		}
		
	}

	

	
	public void itemStateChanged(ItemEvent itemEvent) {
		String operacion;	
		
		if(itemEvent.getSource() == menuDesplegable){
			operacion = "";
			operacion = (String) menuDesplegable.getSelectedItem();				
		    operar(operacion);
		}
		
	
	}
	
	private boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
}
