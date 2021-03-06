package Gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Logica.DivideByZeroException;
import Logica.InvalidOperatorException;
import Logica.Logica;
import Logica.PluginErrorException;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Ventana1 {

	private JFrame frame;
	private Container miContenedor;
	private Logica miLogica;
	private JComboBox<String> menu;
	private JPanel panelMenu;
	private JPanel panelResultado;
	private JLabel etiquetaResultado;
	private boolean resultadoValido;
	private List<String> operaciones;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana1 window = new Ventana1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana1() {
		crearLogica();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	
		resultadoValido = true;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		miContenedor = frame.getContentPane();
		panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 434, 119);
		panelMenu.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panelMenu);
		panelMenu.setLayout(null);
	
		
		getMenu();
	
		menu.setBounds(212, 12, 200, 20);
		
		
		JButton btnNewButton = new JButton("Actualizar");
		OyenteActualizar oyenteAct = new OyenteActualizar();
		btnNewButton.addActionListener(oyenteAct);
		
		btnNewButton.setBounds(28, 11, 100, 23);
		panelMenu.add(btnNewButton);
		
		panelResultado = new JPanel();
		panelResultado.setBounds(10, 130, 424, 120);
		frame.getContentPane().add(panelResultado);
		panelResultado.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("El resultado es :");
		lblNewLabel_3.setBounds(31, 11, 96, 14);
		panelResultado.add(lblNewLabel_3);
		
		etiquetaResultado = new JLabel("");
		etiquetaResultado.setBounds(147, 11, 61, 14);
		panelResultado.add(etiquetaResultado);
	}

	 protected void borrar() {
		operaciones.removeAll(operaciones);
		menu.removeAllItems();		
		panelMenu.remove(menu);
				
	}

	private void getMenu() {
		 	Oyente oyente;
		 	
		 	
		 	
		    menu = new JComboBox<String>();
		    
		    
		    oyente = new Oyente();
		 	cargarMenuBotones(); 
		 	menu.addActionListener(oyente);
		 	panelMenu.add(menu);
		 	
	        
	    }


	private void cargarMenuBotones() {
		
		
		operaciones = miLogica.getOperations();
		System.out.println("El largo de la lista de plugins es:"+operaciones.size());
		
		 
		
		for (String nombre : operaciones){
			System.out.println(nombre);
			menu.addItem(nombre);			
		}
		
	}
	
	private int operar(String operacion){
		int resultado;
		resultado = 0;
		resultadoValido = true;
		
		String op1 = JOptionPane.showInputDialog("Introduzca un n�mero entero para el Operando 1");
		String op2 = JOptionPane.showInputDialog("Introduzca un n�mero entero para el Operando 2");
		
		if(isNumeric(op1) && isNumeric(op2)){
			try {
								
				resultado = miLogica.operate(operacion, Integer.parseInt(op1),Integer.parseInt(op2));
				
			} catch (NumberFormatException | PluginErrorException | DivideByZeroException | InvalidOperatorException e)
		       { JOptionPane.showMessageDialog(null, "Ha ocurrido un error :(");resultadoValido = false;}
		}else {JOptionPane.showMessageDialog(null, "Ha ocurrido un error :("); resultadoValido = false;}
		
		return resultado;
		
		
	}
	
	private class Oyente implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
				String operacion; int resultado;
			
			
				operacion = "";
				operacion = (String) menu.getSelectedItem();				
			    resultado = operar(operacion);
			
			    accionarEtiquetaResultado(resultado);			   
			
		}
		
	}
	
	private class OyenteActualizar implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {	
			    menu.removeAllItems();		
			    crearLogica();
				initialize();	
				
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

	public void accionarEtiquetaResultado(int resultado) {
		
		if(resultadoValido)
			etiquetaResultado.setText(resultado+"");
		else etiquetaResultado.setText("Invalido");
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
	
}
