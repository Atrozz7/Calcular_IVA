package es.tentudia.iva;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class iva implements WindowListener, ActionListener {

	Frame ventana = new Frame("Calcular IVA");
	Label lblPrecioSinIva = new Label("Escriba aquí el precio");
	TextField txtPrecioSinIva = new TextField(20);
	Label lblIva = new Label("Escriba aquí el IVA que quiera aplicar (%)");
	TextField txtIva = new TextField(20);
	Button btnAgregarIva = new Button("Agregar IVA");
	Button btnQuitarIva = new Button("Quitar IVA");
	Button btnLimpiar = new Button("Limpiar");
	

	Dialog feedback = new Dialog(ventana, "Aviso", true);
	Label mensaje = new Label("");

	public iva() {

		ventana.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		ventana.addWindowListener(this);
		ventana.setVisible(true);
		ventana.setSize(460, 150);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		feedback.setLayout(new FlowLayout());
		feedback.setSize(900, 100);
		feedback.setLocationRelativeTo(null);
		feedback.setResizable(false);
		feedback.add(mensaje);
		feedback.addWindowListener(this);

		ventana.add(lblPrecioSinIva);
		ventana.add(txtPrecioSinIva);
		ventana.add(lblIva);
		ventana.add(txtIva);
		ventana.add(btnAgregarIva);
		ventana.add(btnQuitarIva);
		ventana.add(btnLimpiar);

		btnLimpiar.addActionListener(this);
		btnQuitarIva.addActionListener(this);
		btnAgregarIva.addActionListener(this);

	}

	public static void main(String[] args) {

		new iva();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnLimpiar)) {

			txtPrecioSinIva.setText("");
			txtIva.setText("");
			txtPrecioSinIva.requestFocus();
		}

		if (e.getSource().equals(btnAgregarIva) || e.getSource().equals(btnQuitarIva)) {

			if (txtPrecioSinIva.getText().isBlank() || txtIva.getText().isBlank()) {
				mensaje.setText("Hay algún campo de texto vacío");
				feedback.setVisible(true);
				txtPrecioSinIva.setText("");
				txtIva.setText("");
				txtPrecioSinIva.requestFocus();
			} else {

				try {

					double precioSinIva = Double.parseDouble(txtPrecioSinIva.getText());
					double iva = Double.parseDouble(txtIva.getText());
					double sumadeliva = (iva + 100) / 100; // Variable para que el IVA que haya agregado el usuario sea
															// sumado a 100 para que el cálculo sea correcto, tras esto
															// se divide entre 100 y se saca el numero real por el que
															// se va a realizar el cálculo

					if (e.getSource().equals(btnAgregarIva)) {

						double ivaAgregado = precioSinIva * sumadeliva;
						mensaje.setText("El precio con IVA agregado es " + ivaAgregado);

					} else {
						double ivaQuitado = precioSinIva / sumadeliva;
						mensaje.setText("El precio con IVA restado es " + ivaQuitado);
					}

					txtPrecioSinIva.setText("");
					txtIva.setText("");
					txtPrecioSinIva.requestFocus();
					feedback.setVisible(true);

				} catch (NumberFormatException ex) {

					mensaje.setText("Error: Debes ingresar solo números válidos");
					feedback.setVisible(true);
					txtPrecioSinIva.setText("");
					txtIva.setText("");
					txtPrecioSinIva.requestFocus();
				}
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {

		if (e.getSource().equals(feedback)) {
			feedback.setVisible(false);
		} else {
			System.exit(0);
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
