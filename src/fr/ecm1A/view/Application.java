package fr.ecm1A.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.ecm1A.model.Conditions;
import fr.ecm1A.model.Fait;
import fr.ecm1A.model.SystemeExpert;
import fr.ecm1A.model.TableModelBDF;
import fr.ecm1A.model.TableModelBDR;

public class Application {

	private JFrame frmSystmeExpert;
	private SystemeExpert SE;
	private JTable tableBDF;
	private JTable tableBDR;

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
		frmSystmeExpert.setBounds(100, 100, 826, 641);
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

		JMenu mnNouveau = new JMenu("Nouveau");
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

		JSeparator separator = new JSeparator();
		mnNouveau.add(separator);

		JMenuItem mntmFait = new JMenuItem("Fait");
		mntmFait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SE.getBdf().add(
						new Fait(JOptionPane.showInputDialog(frmSystmeExpert,
								"Nouveau Fait", "nom_du_fait")));
			}
		});
		mnNouveau.add(mntmFait);

		JMenuItem mntmRegle = new JMenuItem("R\u00E8gle");
		mntmRegle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnNouveau.add(mntmRegle);

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
				if(SE.chainageArriere(JOptionPane.showInputDialog(frmSystmeExpert,
						"Conclusion visée", "nom_de_la_conclusion"))){
					JOptionPane.showMessageDialog(frmSystmeExpert, "Chaînage arrière réussi");
				} else {
					JOptionPane.showMessageDialog(frmSystmeExpert, "Chaînage arrière échoué");
				}
			}
		});
		mnExecuter.add(mntmChanageArriere);
		frmSystmeExpert.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 20, 20, 20));
		frmSystmeExpert.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 1, 20, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblBaseDeFaits = new JLabel("Base de Faits");
		lblBaseDeFaits.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblBaseDeFaits, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		tableBDF = new JTable(new TableModelBDF());
		scrollPane.setViewportView(tableBDF);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblBaseDeRgles = new JLabel("Base de R\u00E8gles");
		lblBaseDeRgles.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblBaseDeRgles, BorderLayout.NORTH);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		tableBDR = new JTable(new TableModelBDR());
		scrollPane_1.setViewportView(tableBDR);
	}

}
