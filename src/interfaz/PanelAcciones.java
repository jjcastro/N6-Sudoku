package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelAcciones extends JPanel implements ActionListener {

	private static final String SALVAR = "SALVAR";
	private static final String CARGAR = "CARGAR";
	private static final String REINICIAR = "REINICIAR";

	private JButton botonSalvar;
	private JButton botonCargar;
	private JButton botonReiniciar;

	private InterfazSudoku principal;

	public PanelAcciones(InterfazSudoku padre) {
		principal = padre;

		botonSalvar = new JButton("Salvar");
		botonSalvar.setActionCommand(SALVAR);
		botonSalvar.addActionListener(this);
		add(botonSalvar);

		botonCargar = new JButton("Cargar");
		botonCargar.setActionCommand(CARGAR);
		botonCargar.addActionListener(this);
		add(botonCargar);

		botonReiniciar = new JButton("Reiniciar");
		botonReiniciar.setActionCommand(REINICIAR);
		botonReiniciar.addActionListener(this);
		add(botonReiniciar);
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (SALVAR.equals(comando)) {

			JFileChooser fc = new JFileChooser("./datos");
			int result = fc.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File archivo = fc.getSelectedFile();
				try {
					principal.salvar(archivo);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this,
							"HEY!!!!!" + e1.getMessage());
				}
			}
		} else if (CARGAR.equals(comando)) {

			JFileChooser fc = new JFileChooser("./datos");
			fc.setMultiSelectionEnabled(false);
			int result = fc.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION) {
				File archivo = fc.getSelectedFile();
				try {
					principal.cargar(archivo);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this,
							"HEY!!!!!" + e1.getMessage());
				}
			}
		} else if (REINICIAR.equals(comando)) {
			principal.reiniciarJuego();

		}

	}
}
