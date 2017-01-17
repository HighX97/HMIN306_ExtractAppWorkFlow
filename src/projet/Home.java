package projet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFileChooser;
import javax.swing.JButton;

import java.awt.Canvas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import graphe.Arete;
import graphe.Graphe;
import graphe.Sommet;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.awt.TextField;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.ScrollPane;
import java.awt.Point;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setBounds(100, 100, 450, 300);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblHihhh = new JLabel("HMIN306 WorkFlow Extraction");
		lblHihhh.setLabelFor(frame);
		lblHihhh.setBackground(new Color(255, 204, 153));
		lblHihhh.setForeground(new Color(0, 102, 102));
		lblHihhh.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		lblHihhh.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblHihhh, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Input", null, panel_1, null);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		TextField textField = new TextField();
		panel_1.add(textField);


		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Invocation Graph", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));

		/*
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, BorderLayout.SOUTH);
		*/


		JButton btnNewButton = new JButton("project path");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				OpenFile of = new OpenFile();
				of.chooseFolder();
				textField.setText(of.sb.toString());
				String projectSourcePath  = of.sb.toString();
				// read java files
				final File folder = new File(projectSourcePath);
				ArrayList<File> javaFiles = Parser.listJavaFilesForFolder(folder);
				Graphe grapheInvocation = new Graphe();

				//
				for (File fileEntry : javaFiles) {
					String content = null;
					try {
						content = FileUtils.readFileToString(fileEntry);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// System.out.println(content);
					String  fileName = fileEntry.getName();
					String  className = fileName.substring(0, fileName.length() - 5);


					CompilationUnit parse = Parser.parse(content.toCharArray());

					System.out.println("----------------------------"+className+"Methodes--------------------------");
					// print methods info
					//printMethodInfo(parse);
					grapheInvocation = Parser.nodeMethodInfo(parse, grapheInvocation,className);
					grapheInvocation = Parser.nodeMethodInvocationInfo(parse, grapheInvocation,className);


					System.out.println("----------------------------"+className+"Variables--------------------------");
					// print variables info
					Parser.printVariableInfo(parse);

				}

				for (File fileEntry : javaFiles) {
					String content = null;
					try {
						content = FileUtils.readFileToString(fileEntry);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// System.out.println(content);
					String  fileName = fileEntry.getName();
					String  className = fileName.substring(0, fileName.length() - 5);


					CompilationUnit parse = Parser.parse(content.toCharArray());


					System.out.println("----------------------------"+className+"MethodInvocations--------------------------");
					//print method invocations
					//Parser.printMethodInvocationInfo(parse);
					grapheInvocation = Parser.areteMethodInvocationInfo(parse, grapheInvocation , className);
				}

				System.out.println(grapheInvocation.getSommets());
				System.out.println(grapheInvocation.getAretes());
				System.out.println("Taches :"+grapheInvocation.getTaches());
				for (Map.Entry<String, Sommet> entry : grapheInvocation.getTaches().entrySet())
				{
					System.out.println(entry.getKey()+" Tache :");
					System.out.println(entry.getKey()+" Tache :");
					System.out.println("Parents : "+entry.getValue().getParents());
					System.out.println("Childs : "+entry.getValue().getChilds());
				}
				//System.out.println(grapheInvocation.getTachesComposites());
				System.out.println("Points d'entrées :"+grapheInvocation.getPointEntree());
				for (Map.Entry<String, Sommet> entry : grapheInvocation.getPointEntree().entrySet())
				{
					System.out.println(entry.getKey()+" PE :");
					System.out.println("Parents : "+entry.getValue().getParents());
					System.out.println("Childs : "+entry.getValue().getChilds());
				}

			   // DirectedGraph<String, String> g = new DirectedSparseMultigraph<String, String>();
				Graph<String, String> g = new DelegateForest<>();

				for (Map.Entry<String, Sommet> entry : grapheInvocation.getSommets().entrySet())
				{
					if(!g.containsVertex(entry.getValue().getNomSommet())){
						g.addVertex(entry.getValue().getNomSommet());
					}

				}
				for (Arete a : grapheInvocation.getAretes())
				{
					g.addEdge(a.getLabelArret(), a.getSommetBegin().getNomSommet(), a.getSommetEnd().getNomSommet());
				}

				Dimension preferredGraphSize=new Dimension(1360,798);
				Layout<String, String> layout3 = new SpringLayout<String, String> (g);
			    VisualizationViewer<String, String> vv3 = new VisualizationViewer<>(layout3 ,preferredGraphSize);


			    vv3.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
			    //vv3.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());


			    final DefaultModalGraphMouse<String, Number> graphMouse3 = new DefaultModalGraphMouse<>();
			    vv3.setGraphMouse(graphMouse3);
			    graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);


			    // Write image to a png file
			    File outputfile = new File("graph.png");
			    System.out.println(outputfile.getAbsolutePath());

			    /*try {
			    	System.out.println("Image Write");
			        ImageIO.write(image, "png", outputfile);
			    } catch (IOException e) {
			        // Exception handling
			    }*/



			    JFrame frame = new JFrame();
			    panel_3.removeAll();
			    panel_3.add(vv3, BorderLayout.CENTER);
					/*
					*/
					/*
				JScrollPane scrollPane = new JScrollPane(vv3);
				panel_3.add(scrollPane, BorderLayout.CENTER);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				*/
			    vv3.setPreferredSize(panel_3.getPreferredSize());
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.pack();
			    frame.setVisible(true);
			    tabbedPane.setSelectedIndex(1);
			}
		});
		panel_1.add(btnNewButton);



	}
}
