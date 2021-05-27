package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.controllers.ControladorProvincia;
import model.entities.Municipio;
import model.entities.Provincia;

public class PanelProvincias extends JFrame {

	private JPanel contentPane;
	
	private JTextField jtfSelectMunicipio;
	private JTextField jtfMunicipio;
	Municipio mActual;
	JComboBox<Provincia> jcbProvincia;
	JComboBox<Municipio> jcbMunicipios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelProvincias frame = new PanelProvincias();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Método utilizado para cargar todos sexos en una lista para trabajarla en esta clase
	 */
	private void cargarProvincias() {
		List<Provincia> provincias = ControladorProvincia.getInstance().findAllProvincias();
		
		for (Provincia t: provincias) {
			this.jcbProvincia.addItem(t);
		}
	}

	/**
	 * Create the frame.
	 */
	public PanelProvincias() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		this.contentPane.setLayout(gridBagLayout);
		
		jtfSelectMunicipio = new JTextField();
		GridBagConstraints gbc_jtfSelectMunicipio = new GridBagConstraints();
		gbc_jtfSelectMunicipio.gridwidth = 11;
		gbc_jtfSelectMunicipio.insets = new Insets(0, 0, 5, 5);
		gbc_jtfSelectMunicipio.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfSelectMunicipio.gridx = 0;
		gbc_jtfSelectMunicipio.gridy = 0;
		add(jtfSelectMunicipio, gbc_jtfSelectMunicipio);
		jtfSelectMunicipio.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcbMunicipios.removeAllItems();
				filtrar();
			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnFiltrar.gridx = 11;
		gbc_btnFiltrar.gridy = 0;
		add(btnFiltrar, gbc_btnFiltrar);
		
		jcbMunicipios = new JComboBox();
		GridBagConstraints gbc_jcbMunicipios = new GridBagConstraints();
		gbc_jcbMunicipios.gridwidth = 11;
		gbc_jcbMunicipios.insets = new Insets(0, 0, 5, 5);
		gbc_jcbMunicipios.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbMunicipios.gridx = 0;
		gbc_jcbMunicipios.gridy = 1;
		add(jcbMunicipios, gbc_jcbMunicipios);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionar();
			}
		});
		GridBagConstraints gbc_btnSeleccionar = new GridBagConstraints();
		gbc_btnSeleccionar.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeleccionar.gridx = 11;
		gbc_btnSeleccionar.gridy = 1;
		add(btnSeleccionar, gbc_btnSeleccionar);
		
		JLabel lblNewLabel = new JLabel("Municipio seleccionado:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 13;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre del municipio:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfMunicipio = new JTextField();
		GridBagConstraints gbc_jtfMunicipio = new GridBagConstraints();
		gbc_jtfMunicipio.insets = new Insets(0, 0, 5, 0);
		gbc_jtfMunicipio.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfMunicipio.gridx = 1;
		gbc_jtfMunicipio.gridy = 0;
		panel.add(jtfMunicipio, gbc_jtfMunicipio);
		jtfMunicipio.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Provincia del municipio:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jcbProvincia = new JComboBox();
		GridBagConstraints gbc_jcbProvincia = new GridBagConstraints();
		gbc_jcbProvincia.insets = new Insets(0, 0, 5, 0);
		gbc_jcbProvincia.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbProvincia.gridx = 1;
		gbc_jcbProvincia.gridy = 1;
		panel.add(jcbProvincia, gbc_jcbProvincia);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.gridx = 1;
		gbc_btnGuardar.gridy = 2;
		panel.add(btnGuardar, gbc_btnGuardar);
		
		cargarProvincias();

	}
	
	/**
	 * 
	 */
	private void filtrar() {		
		List<Municipio> municipios = ControladorProvincia.getInstance().filtrarMunicipio(jtfSelectMunicipio.getText());
		for (Municipio m: municipios) {
			this.jcbMunicipios.addItem(m);
		}
	}
	
	/**
	 * 
	 */
	private void seleccionar() {
		mActual = (Municipio) this.jcbMunicipios.getSelectedItem();
		this.jtfMunicipio.setText(mActual.getNombre());
//		this.jcbProvincia.getSelectedItem(mActual.getProvincia());
		
		for (int i = 0; i < this.jcbMunicipios.getItemCount(); i++) {		
			if (this.mActual.getProvincia().getId() == jcbMunicipios.getItemAt(i).getId()) {
				jcbProvincia.setSelectedIndex(i);
			}
		}
	}
	
	/**
	 * Método utilizado para dos situaciones, guardar un registro modificado o guardar uno nuevo
	 */
	private void guardar() {
		this.mActual.setProvincia((Provincia) jcbProvincia.getSelectedItem());
		this.mActual.setNombre(jtfMunicipio.getText());
		//Comprobar que el registro se ha guardado correctamente
		boolean resultado = ControladorProvincia.getInstance().guardar(mActual);
		if (resultado == true && this.mActual != null && this.mActual.getId() > 0) {
			JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
		}
		else {
			JOptionPane.showMessageDialog(null, "Error al guardar");
		}
	}
	
}
