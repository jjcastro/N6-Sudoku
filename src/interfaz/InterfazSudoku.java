package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mundo.Sudoku;

public class InterfazSudoku extends JFrame implements ActionListener
{
	private static final String LLENO = "LLENO";

	private static final String HAYREPETIDOSR = "HAYREPETIDOSR";

	private static final String HAYREPETIDOSF = "HAYREPETIDOSF";

	private static final String HAYREPETIDOSC = "HAYREPETIDOSC";

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
		
		pnlAcciones.actualizar("", nombre);
		
		JPanel pnlRepetidos = new JPanel();
		pnlRepetidos.setLayout(new GridLayout(1,4));
		
		JButton btnRepetidosC = new JButton("Columnas");
		btnRepetidosC.setActionCommand(HAYREPETIDOSC);
		btnRepetidosC.addActionListener(this);
		pnlRepetidos.add(btnRepetidosC);
		
		JButton btnRepetidosF = new JButton("Filas");
		btnRepetidosF.setActionCommand(HAYREPETIDOSF);
		btnRepetidosF.addActionListener(this);
		pnlRepetidos.add(btnRepetidosF);
		
		JButton btnRepetidosR = new JButton("Recuadros");
		btnRepetidosR.setActionCommand(HAYREPETIDOSR);
		btnRepetidosR.addActionListener(this);
		pnlRepetidos.add(btnRepetidosR);
		
		JButton btnLleno = new JButton("Lleno");
		btnLleno.setActionCommand(LLENO);
		btnLleno.addActionListener(this);
		pnlRepetidos.add(btnLleno);
		
		add(pnlRepetidos, BorderLayout.NORTH);
		
		setTitle("Sudoku");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new InterfazSudoku();
	}

	private void actualizar()
	{
		pnlCasillas.actualizar(sudoku.darCasillas());
		pnlAcciones.actualizar(sudoku.darNombreArchivo(), sudoku.darNombreJugador());
	}
	
	private void borrarCasillas()
	{
		Sudoku temp = new Sudoku(null);
		pnlCasillas.actualizar(temp.darCasillas());
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
		try
		{
			sudoku.reiniciarJuego();
			actualizar();
		}
		catch(Exception e)
		{
			int reiniciar = JOptionPane.showConfirmDialog(this, e.getMessage() + "\n¿Desea empezar desde cero?", "Error", JOptionPane.YES_NO_OPTION);
			
			if(reiniciar == 0)
			{
				borrarCasillas();
			}	
		}
	}
	
	public void jugar(int i, int j, String num) throws Exception
	{
		try
		{
			if(num.equals(""))
			{
				sudoku.jugar(i, j, -1);
			}
			else if(num.equals("-1"))
			{
				throw new Exception();
			}
			else
			{
				sudoku.jugar(i, j, Integer.parseInt(num));
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();
		
		if(comando.equals(HAYREPETIDOSC))
		{
			System.out.println(sudoku.hayRepetidosColumnas());
		}
		else if(comando.equals(HAYREPETIDOSF)) 
		{
			System.out.println(sudoku.hayRepetidosFilas());
		}
		else if(comando.equals(HAYREPETIDOSR))
		{
			System.out.println(sudoku.hayRepetidosRecuadros());
		}
		else if(comando.equals(LLENO))
		{
			System.out.println(sudoku.estaLleno());
		}
	}
}
