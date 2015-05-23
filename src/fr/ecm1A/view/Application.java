package fr.ecm1A.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Application {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenu mnImporter = new JMenu("Importer");
		mnFichier.add(mnImporter);
		
		JMenuItem mntmBaseDeFaits_1 = new JMenuItem("Base de Faits");
		mntmBaseDeFaits_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		mnImporter.add(mntmBaseDeFaits_1);
		
		JMenuItem mntmBaseDeRgles_1 = new JMenuItem("Base de R\u00E8gles");
		mnImporter.add(mntmBaseDeRgles_1);
		
		JMenu mnExporter = new JMenu("Exporter");
		mnFichier.add(mnExporter);
		
		JMenuItem mntmBaseDeFaits = new JMenuItem("Base de Faits");
		mnExporter.add(mntmBaseDeFaits);
		
		JMenuItem mntmBaseDeRgles = new JMenuItem("Base de R\u00E8gles");
		mnExporter.add(mntmBaseDeRgles);
		
		JMenu mnEdition = new JMenu("Edition");
		menuBar.add(mnEdition);
		
		JMenuItem mntmAjouterFait = new JMenuItem("Ajouter Fait");
		mnEdition.add(mntmAjouterFait);
		
		JMenuItem mntmAjouterRgle = new JMenuItem("Ajouter R\u00E8gle");
		mnEdition.add(mntmAjouterRgle);
		
		JSeparator separator = new JSeparator();
		mnEdition.add(separator);
		
		JMenuItem mntmEffacerBdf = new JMenuItem("Effacer B.D.F.");
		mnEdition.add(mntmEffacerBdf);
		
		JMenuItem mntmEffacerBdr = new JMenuItem("Effacer B.D.R.");
		mnEdition.add(mntmEffacerBdr);
		
		JMenu mnLancer = new JMenu("Lancer");
		menuBar.add(mnLancer);
		
		JMenuItem mntmChainageAvant = new JMenuItem("Cha\u00EEnage Avant");
		mnLancer.add(mntmChainageAvant);
		
		JMenuItem mntmChanageArriere = new JMenuItem("Cha\u00EEnage Arri\u00E8re");
		mnLancer.add(mntmChanageArriere);
	}

}
