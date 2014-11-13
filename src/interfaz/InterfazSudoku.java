package interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mundo.Sudoku;

public class InterfazSudoku extends JFrame
{
	private Sudoku sudoku;
	
	private PanelCasillas pnlCasillas;
	private PanelAcciones pnlAcciones;

	public InterfazSudoku()
	{
		String nombre = JOptionPane.showInputDialog(this, "Como se llama?",
				"Nombre", JOptionPane.QUESTION_MESSAGE);

		nombre = ((nombre == null) ? "sin nombre" : nombre);
		
		sudoku = new Sudoku(nombre);

		pnlCasillas = new PanelCasillas(this);
		pnlAcciones = new PanelAcciones(this);
		
		setLayout(new BorderLayout());

		add(pnlCasillas, BorderLayout.CENTER);
		add(pnlAcciones, BorderLayout.SOUTH);
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();

		setVisible(true);
	}

	public void cargar()
	{
		JFileChooser fc = new JFileChooser("./datos");
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION)
		{

		}
	}

	public static void main(String[] args)
	{
		new InterfazSudoku();
	}

	private void actualizar()
	{
		pnlCasillas.actualizar(sudoku.darCasillas());
	}

	public void cargar(File archivo)
	{
		try
		{
			sudoku.cargar(archivo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		actualizar();
	}

	public void salvar(File archivo)
	{
		try
		{
			sudoku.salvar(archivo);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void reiniciarJuego()
	{
		sudoku.reiniciarJuego();
		actualizar();
	}
}
