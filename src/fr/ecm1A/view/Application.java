package fr.ecm1A.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;

import fr.ecm1A.model.Fait;
import fr.ecm1A.model.SystemeExpert;
import fr.ecm1A.model.TableModelBDF;
import fr.ecm1A.model.TableModelBDR;
import fr.ecm1A.observer.Observable;
import fr.ecm1A.observer.Observer;

public class Application implements Observer {

	private JFrame frmSystmeExpert;
	private SystemeExpert SE;
	private JTable tableBDF;
	private JTable tableBDR;
	private JTextField txtNouveaufait;
	private JTextField txtRechercher;
	private JTextField txtRechercher_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Application window = new Application();
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
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("/SourisMOPT.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
		frmSystmeExpert = new JFrame();
		frmSystmeExpert.setIconImage(img);
		frmSystmeExpert.setTitle("Syst\u00E8me Expert");
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Rectangle tailleEcran = graphicsEnvironment.getMaximumWindowBounds();
		int hauteur = (int) (tailleEcran.height * 0.8) ;
		int largeur = hauteur * 4/3;
		int posX = (int) tailleEcran.getWidth() / 2 - largeur / 2;
		int posY = (int) tailleEcran.getHeight() / 2 - hauteur / 2;
		frmSystmeExpert.setMinimumSize(new Dimension(largeur, hauteur));
		frmSystmeExpert.setLocation(posX, posY);
		frmSystmeExpert.setSize(largeur, hauteur);
		frmSystmeExpert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmSystmeExpert.setJMenuBar(menuBar);

		JMenu mnFichier = new JMenu("Fichier");
		mnFichier.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFichier);

		JMenu mnImporter = new JMenu("Ouvrir");
		mnImporter.setMnemonic(KeyEvent.VK_O);
		mnFichier.add(mnImporter);

		JMenuItem mntmBaseDeFaits_1 = new JMenuItem("Base de Faits");
		mntmBaseDeFaits_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		mntmBaseDeFaits_1.setMnemonic(KeyEvent.VK_F);
		mntmBaseDeFaits_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier BDF", "bdf");
				jfc.setFileFilter(filter);
				jfc.setSelectedFile(new File("bdf.bdf"));
				jfc.setDialogTitle("Ouvrir la Base de Faits");
				int reponse = jfc.showOpenDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION) {
					SE.getBdf().open(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		mnImporter.add(mntmBaseDeFaits_1);

		JMenuItem mntmBaseDeRgles_1 = new JMenuItem("Base de R\u00E8gles");
		mntmBaseDeRgles_1.setMnemonic(KeyEvent.VK_R);
		mntmBaseDeRgles_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		mntmBaseDeRgles_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier BDR", "bdr");
				jfc.setFileFilter(filter);
				jfc.setSelectedFile(new File("bdr.bdr"));
				jfc.setDialogTitle("Ouvrir la Base de Règles");
				int reponse = jfc.showOpenDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION) {
					SE.getBdr().open(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		mnImporter.add(mntmBaseDeRgles_1);

		JMenu mnExporter = new JMenu("Enregistrer");
		mnExporter.setMnemonic(KeyEvent.VK_E);
		mnFichier.add(mnExporter);

		JMenuItem mntmBaseDeFaits = new JMenuItem("Base de Faits");
		mntmBaseDeFaits.setMnemonic(KeyEvent.VK_F);
		mntmBaseDeFaits.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		mntmBaseDeFaits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier BDF", "bdf");
				jfc.setFileFilter(filter);
				jfc.setSelectedFile(new File("bdf.bdf"));
				jfc.setDialogTitle("Enregistrer la Base de Faits");
				int reponse = jfc.showSaveDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION) {
					SE.getBdf().save(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		mnExporter.add(mntmBaseDeFaits);

		JMenuItem mntmBaseDeRgles = new JMenuItem("Base de R\u00E8gles");
		mntmBaseDeRgles.setMnemonic(KeyEvent.VK_R);
		mntmBaseDeRgles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		mntmBaseDeRgles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier BDR", "bdr");
				jfc.setFileFilter(filter);
				jfc.setSelectedFile(new File("bdr.bdr"));
				jfc.setDialogTitle("Enregistrer la Base de Règles");
				int reponse = jfc.showSaveDialog(null);
				if (reponse == JFileChooser.APPROVE_OPTION) {
					SE.getBdr().save(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		mnExporter.add(mntmBaseDeRgles);

		JMenu mnReset = new JMenu("R\u00E9initialiser");
		mnReset.setMnemonic(KeyEvent.VK_R);
		menuBar.add(mnReset);
		
		JMenuItem mntmEffacerBdf = new JMenuItem("Base de Faits");
		mntmEffacerBdf.setMnemonic(KeyEvent.VK_F);
		mntmEffacerBdf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		mntmEffacerBdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((JOptionPane.showConfirmDialog(frmSystmeExpert,
						"Tout le travail non sauvegardé sera perdu!",
						"Attention", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION)) {
					SE.getBdf().clear();
				}
			}
		});
		mnReset.add(mntmEffacerBdf);

		JMenuItem mntmEffacerBdr = new JMenuItem("Base de R\u00E8gles");
		mntmEffacerBdr.setMnemonic(KeyEvent.VK_R);
		mntmEffacerBdr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		mntmEffacerBdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((JOptionPane.showConfirmDialog(frmSystmeExpert,
						"Tout le travail non sauvegardé sera perdu!",
						"Attention", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION)) {
					SE.getBdr().clear();
				}
			}
		});
		mnReset.add(mntmEffacerBdr);

		JMenu mnExecuter = new JMenu("Ex\u00E9cuter");
		mnExecuter.setMnemonic(KeyEvent.VK_E);
		menuBar.add(mnExecuter);

		JMenuItem mntmChainageAvant = new JMenuItem("Cha\u00EEnage Avant");
		mntmChainageAvant.setMnemonic(KeyEvent.VK_V);
		mntmChainageAvant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		mntmChainageAvant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SE.chainageAvant();
				ExplicationDialog.showDialog(frmSystmeExpert,SE.getLog());
			}
		});
		mnExecuter.add(mntmChainageAvant);

		JMenuItem mntmChanageArriere = new JMenuItem(
				"Cha\u00EEnage Arri\u00E8re");
		mntmChanageArriere.setMnemonic(KeyEvent.VK_R);
		mntmChanageArriere.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		mntmChanageArriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SE.chainageArriere(JOptionPane.showInputDialog(
						frmSystmeExpert, "Conclusion visée",
						"nom_de_la_conclusion"))) {
					ExplicationDialog.showDialog(frmSystmeExpert,SE.getLog());
				} else {
					JOptionPane.showMessageDialog(frmSystmeExpert, "Echec du chaînage arrière.");
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

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblBaseDeFaits = new JLabel("Base de Faits");
		lblBaseDeFaits.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblBaseDeFaits);

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		TableModelBDF tableModelBDF = new TableModelBDF();
		tableBDF = new JTable(tableModelBDF);
		tableBDF.setToolTipText("<html>Etat courant de la base de fait. <br> <br>\r\n(Double-clic pour modifier le nom) <br>\r\n(Clic simple pour modifier la valeur de v\u00E9rit\u00E9) <br>\r\n(Clic simple pour supprimer)");
		tableBDF.getColumnModel().getColumn(2).setPreferredWidth(1);
		tableBDF.getColumnModel().getColumn(1).setPreferredWidth(1);
		TableRowSorter<TableModelBDF> sorter = new TableRowSorter<TableModelBDF>(
				tableModelBDF);
		tableBDF.setRowSorter(sorter);
		sorter.toggleSortOrder(0);
		scrollPane.setViewportView(tableBDF);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(1, 1, 0, 0));

		txtNouveaufait = new JTextField("Nouveau fait");
		txtNouveaufait.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtNouveaufait.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txtNouveaufait.setText("Nouveau fait");
			}
		});
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
		panel_3.add(txtNouveaufait);
		txtNouveaufait.setColumns(10);
		
		JButton btnRemiseAZero = new JButton("Remise à zéro");
		btnRemiseAZero.setToolTipText("Passe tous les faits à la valeur \"faux\"");
		btnRemiseAZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Fait f : SE.getBdf()){
					f.setVal(false);
				}
				SE.getBdf().notifyObservers();
			}
		});
		btnRemiseAZero.setPreferredSize(txtNouveaufait.getPreferredSize());
		panel_3.add(btnRemiseAZero);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblBaseDeRgles = new JLabel("Base de R\u00E8gles");
		panel_5.add(lblBaseDeRgles);
		lblBaseDeRgles.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		TableModelBDR tableModelBdr = new TableModelBDR();
		tableModelBdr.addObserver(this);
		tableBDR = new JTable(tableModelBdr);
		tableBDR.setToolTipText("<html>Inventaire des r\u00E8gles cr\u00E9\u00E9es.<br><br>\r\n(Clic simple pour modifier)<br>\r\n(Clic simple pour supprimer)</html>\r\n");
		tableBDR.getColumnModel().getColumn(3).setPreferredWidth(1);
		tableBDR.getColumnModel().getColumn(2).setPreferredWidth(1);
		TableRowSorter<TableModelBDR> sorter2 = new TableRowSorter<TableModelBDR>(
				tableModelBdr);
		tableBDR.setRowSorter(sorter2);
		sorter2.toggleSortOrder(1);
		tableBDR.setDefaultRenderer(JPanel.class, new JPanelCellRenderer());
		tableBDR.setRowHeight(40);
		scrollPane_1.setViewportView(tableBDR);

		JButton btnCrerRgle = new JButton("Cr\u00E9er R\u00E8gle");
		btnCrerRgle.setToolTipText("Appuyez pour ouvrir l'\u00E9diteur de r\u00E8gle");
		btnCrerRgle.setPreferredSize(new Dimension(txtNouveaufait
				.getPreferredSize()));
		btnCrerRgle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegleDialog.showDialog(frmSystmeExpert);
			}
		});
		panel_2.add(btnCrerRgle, BorderLayout.SOUTH);

		txtRechercher = new JTextField("Rechercher");
		txtRechercher.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtRechercher.getText().equals("Rechercher")){
					txtRechercher.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (txtRechercher.getText().equals("")){
					txtRechercher.setText("Rechercher");
					sorter.setRowFilter(null);
				}
			}
		});
		txtRechercher.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				RowFilter<TableModelBDF, Integer> filter = new RowFilter<TableModelBDF,Integer>(){

					@Override
					public boolean include(Entry<? extends TableModelBDF, ? extends Integer> entry) {
						TableModelBDF model = entry.getModel();
						String s = (String) model.getValueAt(entry.getIdentifier(), 0);
						if (s.toLowerCase().startsWith(txtRechercher.getText().toLowerCase())){
							return true;
						} else {
							return false;
						}
					}
					
				};
				sorter.setRowFilter(filter);
			}
		});
		panel_4.add(txtRechercher);
		txtRechercher.setColumns(10);

		txtRechercher_1 = new JTextField("Rechercher");
		txtRechercher_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtRechercher_1.getText().equals("Rechercher")){
					txtRechercher_1.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (txtRechercher_1.getText().equals("")){
					txtRechercher_1.setText("Rechercher");
					sorter2.setRowFilter(null);
				}
			}
		});
		txtRechercher_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				RowFilter<TableModelBDR, Integer> filter2 = new RowFilter<TableModelBDR,Integer>(){

					@Override
					public boolean include(Entry<? extends TableModelBDR, ? extends Integer> entry) {
						TableModelBDR model = entry.getModel();
						String s = (String) model.getValueAt(entry.getIdentifier(), 1);
						if (s.toLowerCase().startsWith(txtRechercher_1.getText().toLowerCase())){
							return true;
						} else {
							return false;
						}
					}
				};
				sorter2.setRowFilter(filter2);
			}
		});
		panel_5.add(txtRechercher_1);
		txtRechercher_1.setColumns(10);

		frmSystmeExpert.setVisible(true);
		btnCrerRgle.requestFocusInWindow();
		
	}

	@Override
	public void update(Observable obs) {
		if (obs instanceof TableModelBDR) {
			RegleDialog.showDialog(frmSystmeExpert,
					((TableModelBDR) obs).getIndexRegleAModif());
		}
	}

}