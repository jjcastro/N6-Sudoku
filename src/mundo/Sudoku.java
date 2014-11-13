package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Properties;

public class Sudoku
{
	private int[][] casillas;

	private String nombreJugador;

	private Properties propiedadesIniciales;
	private String nombreArchivoCargado;

	public Sudoku(String nombre)
	{
		nombreJugador = nombre;

		casillas = new int[9][9];

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				casillas[i][j] = -1;
			}
		}
	}
	
	public String darNombreJugador()
	{
		return nombreJugador;
	}

	public String darNombreArchivo()
	{
		return nombreArchivoCargado;
	}
	
	public int[][] darCasillas()
	{
		return casillas;
	}

	public void salvar(File archivo) throws FileNotFoundException
	{
		PrintWriter pw = new PrintWriter(archivo);

		pw.println("jugador=" + nombreJugador);

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				int num = casillas[i][j];
				pw.println("casilla." + i + "." + j + "=" + num);
			}
		}

		pw.close();
	}

	/*public void salvar2(File archivo) throws FileNotFoundException
	{
		PrintWriter pw = new PrintWriter(archivo);

		pw.println("jugador=" + nombreJugador);

		for (int i = 0; i < 9; i++)
		{
			pw.print("fila" + i + "=");
			
			for (int j = 0; j < 9; j++)
			{
				int num = casillas[i][j];
				pw.print(num + " ");
			}
			
			pw.println();
		}

		pw.close();
	}*/

	public void cargar(File archivo) throws Exception
	{
		propiedadesIniciales = loadProperties(archivo);
		nombreArchivoCargado = archivo.getName();

		nombreJugador = propiedadesIniciales.getProperty("jugador");

		reiniciarJuego();
	}

	public void reiniciarJuego() throws Exception
	{
		if(propiedadesIniciales != null)
		{
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					String num = propiedadesIniciales.getProperty("casilla." + i + "." + j);
					casillas[i][j] = Integer.parseInt(num);
				}
			}
		}
		else
		{
			throw new Exception("No se ha cargado ningún archivo.");
		}
	}

	public Properties loadProperties(File archivo) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(archivo);
		try
		{
			prop.load(fis);
		}
		catch (Exception e)
		{
			throw new Exception("Formato inválido");
		}
		fis.close();
		return prop;
	}
	
	public void jugar(int i, int j, int num) throws Exception
	{
		Exception error = new Exception("El número debe estar entre 1-9");
		
		if(0 < num && num < 10)
		{
			casillas[i][j] = num;
		}
		
		else throw error;
		
	}
	
	public boolean hayRepetidosColumnas()
	{
		boolean hayRepetidos = false;
		
		for(int i = 0; i < 9 && !hayRepetidos; i++)
		{
			boolean[] numeros = new boolean[9];
			
			for(int j = 0; j < 9 && !hayRepetidos; j++)
			{
				int num = casillas[j][i];
				
				if(num != -1)
				{
					if(!numeros[num+1]) numeros[num+1] = true;
					else hayRepetidos = true;
				}
			}
		}
		
		return hayRepetidos;
	}
	
	public boolean hayRepetidosFilas()
	{
		boolean hayRepetidos = false;
		
		for(int i = 0; i < 9 && !hayRepetidos; i++)
		{
			boolean[] numeros = new boolean[9];
			
			for(int j = 0; j < 9 && !hayRepetidos; j++)
			{
				int num = casillas[i][j];
				
				if(num != -1)
				{
					if(!numeros[num+1]) numeros[num+1] = true;
					else hayRepetidos = true;
				}
			}
		}
		
		return hayRepetidos;
	}
	
	public boolean hayRepetidosRecuadros()
	{
		return false;
	}
}
