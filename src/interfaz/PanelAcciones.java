package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelAcciones extends JPanel implements ActionListener
{
	private static final String SALVAR = "SALVAR";
	private static final String CARGAR = "CARGAR";
	private static final String REINICIAR = "REINICIAR";

	private JButton botonSalvar;
	private JButton botonCargar;
	private JButton botonReiniciar;
	
	private JTextField txtNombreArchivo;

	private InterfazSudoku interfaz;

	public PanelAcciones(InterfazSudoku pInterfaz)
	{
		interfaz = pInterfaz;
		
		setLayout(new GridLayout(2,1));
		
		JPanel pnlBotones = new JPanel();
		add(pnlBotones);
		
		botonSalvar = new JButton("Salvar");
		botonSalvar.setActionCommand(SALVAR);
		botonSalvar.addActionListener(this);
		pnlBotones.add(botonSalvar);

		botonCargar = new JButton("Cargar");
		botonCargar.setActionCommand(CARGAR);
		botonCargar.addActionListener(this);
		pnlBotones.add(botonCargar);

		botonReiniciar = new JButton("Reiniciar");
		botonReiniciar.setActionCommand(REINICIAR);
		botonReiniciar.addActionListener(this);
		pnlBotones.add(botonReiniciar);
		
		JPanel pnlArchivo = new JPanel();
		pnlArchivo.setLayout(new GridLayout(1, 2));
		add(pnlArchivo);
		
		JLabel lblArchivo = new JLabel("Archivo cargado:", SwingConstants.CENTER);
		pnlArchivo.add(lblArchivo);
		
		txtNombreArchivo = new JTextField();
		pnlArchivo.add(txtNombreArchivo);
	}
	
	public void actualizar(String nombreArchivo)
	{
		txtNombreArchivo.setText(nombreArchivo);
	}

	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();

		if (SALVAR.equals(comando))
		{
			JFileChooser fc = new JFileChooser("./datos");
			int result = fc.showSaveDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION)
			{
				File archivo = fc.getSelectedFile();
				interfaz.salvar(archivo);
			}
		}
		else if (CARGAR.equals(comando))
		{
			JFileChooser fc = new JFileChooser("./datos");
			fc.setMultiSelectionEnabled(false);
			int result = fc.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION)
			{
				File archivo = fc.getSelectedFile();
				interfaz.cargar(archivo);
			}
		}
		else if (REINICIAR.equals(comando))
		{
			interfaz.reiniciarJuego();
		}
	}
}
