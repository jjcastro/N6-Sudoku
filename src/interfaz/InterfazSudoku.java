package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mundo.Sudoku;

public class InterfazSudoku extends JFrame {

	private Sudoku mundo;
	private PanelCasillas panelCasillas;
	private PanelAcciones panelBotones;

	public InterfazSudoku() {

		String nombre = JOptionPane.showInputDialog(this, "Como se llama?",
				"Nombre", JOptionPane.QUESTION_MESSAGE);

		nombre = ((nombre == null) ? "sin nombre" : nombre);

		
		
		mundo = new Sudoku(nombre);

		panelCasillas = new PanelCasillas(this);
		panelBotones = new PanelAcciones(this);
		
		
		
		setLayout(new BorderLayout());

		add(panelCasillas, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
	
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();

		setVisible(true);
	}

	public void cargar() {
		JFileChooser fc = new JFileChooser("./datos");
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {

		}
	}

	public static void main(String[] args) {
		new InterfazSudoku();
	}

	private void actualizar() {
		panelCasillas.actualizar(mundo.darCasillas());

	}

	public void cargar(File archivo) {
		try {
			mundo.cargar(archivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actualizar();
	}

	public void salvar(File archivo) {
		// TODO Auto-generated method stub
		try {
			mundo.salvar2(archivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reiniciarJuego() {
		// TODO Auto-generated method stub
		mundo.reiniciarJuego();
		actualizar();
	}
}
