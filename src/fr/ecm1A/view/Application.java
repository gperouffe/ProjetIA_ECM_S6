package fr.ecm1A.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import fr.ecm1A.model.Fait;
import fr.ecm1A.model.SystemeExpert;
import fr.ecm1A.model.TableModelBDF;
import fr.ecm1A.model.TableModelBDR;

public class Application {

	private JFrame frmSystmeExpert;
	private SystemeExpert SE;
	private JTable tableBDF;
	private JTable tableBDR;
	private JTextField txtNouveaufait;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmSystmeExpert.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		SE = SystemeExpert.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSystmeExpert = new JFrame();
		frmSystmeExpert.setTitle("Syst\u00E8me Expert");
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Rectangle tailleEcran = graphicsEnvironment.getMaximumWindowBounds();
		int hauteur = 640;
		int largeur = hauteur * 4 / 3;
		int posX = (int) tailleEcran.getWidth() / 2 - largeur / 2;
		int posY = (int) tailleEcran.getHeight() / 2 - hauteur / 2;
		frmSystmeExpert.setMinimumSize(new Dimension(largeur, hauteur));
		frmSystmeExpert.setLocation(posX, posY);
		frmSystmeExpert.setSize(largeur, hauteur);
		frmSystmeExpert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmSystmeExpert.setJMenuBar(menuBar);

		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);

		JMenu mnImporter = new JMenu("Ouvrir");
		mnFichier.add(mnImporter);

		JMenuItem mntmBaseDeFaits_1 = new JMenuItem("Base de Faits");
		mntmBaseDeFaits_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier CSV", "csv");
				jfc.setFileFilter(filter);
				jfc.setSelectedFile(new File("bdf.csv"));
				jfc.setDialogTitle("Ouvrir la Base de Faits");
				int reponse = jfc.showOpenDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION) {
					SE.getBdf().open(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		mnImporter.add(mntmBaseDeFaits_1);

		JMenuItem mntmBaseDeRgles_1 = new JMenuItem("Base de R\u00E8gles");
		mntmBaseDeRgles_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier CSV", "csv");
				jfc.setFileFilter(filter);
				jfc.setSelectedFile(new File("bdr.csv"));
				jfc.setDialogTitle("Ouvrir la Base de Règles");
				int reponse = jfc.showOpenDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION) {
					SE.getBdr().open(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		mnImporter.add(mntmBaseDeRgles_1);

		JMenu mnExporter = new JMenu("Enregistrer");
		mnFichier.add(mnExporter);

		JMenuItem mntmBaseDeFaits = new JMenuItem("Base de Faits");
		mntmBaseDeFaits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier CSV", "csv");
				jfc.setFileFilter(filter);
				jfc.setSelectedFile(new File("bdf.csv"));
				jfc.setDialogTitle("Enregistrer la Base de Faits");
				int reponse = jfc.showSaveDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION) {
					SE.getBdf().save(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		mnExporter.add(mntmBaseDeFaits);

		JMenuItem mntmBaseDeRgles = new JMenuItem("Base de R\u00E8gles");
		mntmBaseDeRgles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier CSV", "csv");
				jfc.setFileFilter(filter);
				jfc.setSelectedFile(new File("bdr.csv"));
				jfc.setDialogTitle("Enregistrer la Base de Règles");
				int reponse = jfc.showSaveDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION) {
					SE.getBdr().save(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		mnExporter.add(mntmBaseDeRgles);

		JMenu mnNouveau = new JMenu("R\u00E9initialiser");
		menuBar.add(mnNouveau);

		JMenuItem mntmEffacerBdf = new JMenuItem("Base de Faits");
		mntmEffacerBdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(JOptionPane.showConfirmDialog(frmSystmeExpert,
						"Tout le travail non-sauvegardé sera perdu!",
						"Attention", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE) == 1)) {
					SE.getBdf().clear();
				}
			}
		});
		mnNouveau.add(mntmEffacerBdf);

		JMenuItem mntmEffacerBdr = new JMenuItem("Base de R\u00E8gles");
		mntmEffacerBdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(JOptionPane.showConfirmDialog(frmSystmeExpert,
						"Tout le travail non-sauvegardé sera perdu!",
						"Attention", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION)) {
					SE.getBdr().clear();
				}
			}
		});
		mnNouveau.add(mntmEffacerBdr);

		JMenu mnExecuter = new JMenu("Ex\u00E9cuter");
		menuBar.add(mnExecuter);

		JMenuItem mntmChainageAvant = new JMenuItem("Cha\u00EEnage Avant");
		mntmChainageAvant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SE.chainageAvant();
			}
		});
		mnExecuter.add(mntmChainageAvant);

		JMenuItem mntmChanageArriere = new JMenuItem(
				"Cha\u00EEnage Arri\u00E8re");
		mntmChanageArriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SE.chainageArriere(JOptionPane.showInputDialog(
						frmSystmeExpert, "Conclusion visée",
						"nom_de_la_conclusion"))) {
					JOptionPane.showMessageDialog(frmSystmeExpert,
							"Chaînage arrière réussi");
				} else {
					JOptionPane.showMessageDialog(frmSystmeExpert,
							"Chaînage arrière échoué");
				}
			}
		});
		mnExecuter.add(mntmChanageArriere);
		frmSystmeExpert.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 15, 15, 15));
		frmSystmeExpert.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 1, 15, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblBaseDeFaits = new JLabel("Base de Faits");
		lblBaseDeFaits.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblBaseDeFaits, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		TableModelBDF tableModelBDF = new TableModelBDF();
		tableBDF = new JTable(tableModelBDF);
		tableBDF.getColumnModel().getColumn(2).setPreferredWidth(1);
		tableBDF.getColumnModel().getColumn(1).setPreferredWidth(1);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				tableModelBDF);
		tableBDF.setRowSorter(sorter);
		sorter.toggleSortOrder(0);
		scrollPane.setViewportView(tableBDF);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		txtNouveaufait = new JTextField();
		txtNouveaufait
				.setToolTipText("Indiquer le nom du fait puis appuyer sur Entrée pour l'ajouter");
		txtNouveaufait.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					if (!txtNouveaufait.getText().equals("")) {
						SE.getBdf().add(new Fait(txtNouveaufait.getText()));
						txtNouveaufait.setText("");
					}
			}
		});
		txtNouveaufait.setText("nouveau_fait");
		panel_3.add(txtNouveaufait);
		txtNouveaufait.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblBaseDeRgles = new JLabel("Base de R\u00E8gles");
		lblBaseDeRgles.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblBaseDeRgles, BorderLayout.NORTH);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		TableModelBDR tableModelBdr = new TableModelBDR();
		tableBDR = new JTable(tableModelBdr);
		tableBDR.getColumnModel().getColumn(3).setPreferredWidth(1);
		tableBDR.getColumnModel().getColumn(2).setPreferredWidth(1);
		RowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(
				tableModelBdr);
		tableBDR.setRowSorter(sorter2);
		sorter2.toggleSortOrder(1);
		tableBDR.setDefaultRenderer(JPanel.class, new JPanelCellRenderer());
		tableBDR.setRowHeight(40);
		scrollPane_1.setViewportView(tableBDR);

		JButton btnCrerRgle = new JButton("Cr\u00E9er R\u00E8gle");
		btnCrerRgle.setPreferredSize(new Dimension(txtNouveaufait
				.getPreferredSize()));
		btnCrerRgle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegleDialog.showDialog(frmSystmeExpert);
			}
		});
		panel_2.add(btnCrerRgle, BorderLayout.SOUTH);
	}

}
