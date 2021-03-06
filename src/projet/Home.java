package projet;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
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

import projet.FrameDragListener;
import projet.OpenFile;
import projet.Parser;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.DAGLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import graphe.Arete;
import graphe.Graphe;
import graphe.Sommet;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Home {

	private JFrame frame;
	DefaultModalGraphMouse<String, Number> graphMouse3=null;
	Layout<String, String> layout3 = null;
	Graphe grapheInvocation = null;
	Dimension preferredGraphSize=new Dimension(1360,798);
	VisualizationViewer<String, String> vv3 = null;
	private JTable table;

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
		frame.setBounds(100, 100, 800, 600);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);

        FrameDragListener frameDragListener = new FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);

		JLabel lblHihhh = new JLabel("HMIN306 WorkFlow Extraction");
		panel_1.add(lblHihhh);
		lblHihhh.setLabelFor(frame);
		lblHihhh.setBackground(new Color(255, 204, 153));
		lblHihhh.setForeground(new Color(0, 102, 102));
		lblHihhh.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		lblHihhh.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);

		JPanel input_panel = new JPanel();
		tabbedPane.addTab("Input", null, input_panel, null);
		input_panel.setLayout(new BoxLayout(input_panel, BoxLayout.X_AXIS));

		TextField textField = new TextField();
		input_panel.add(textField);


		JPanel output_graph_panel = new JPanel();
		tabbedPane.addTab("Invocation Graph", null, output_graph_panel, null);
		output_graph_panel.setLayout(new BorderLayout(0, 0));
		
		
		JPanel output_tache = new JPanel();
		tabbedPane.addTab("Tâches", null, output_tache, null);


		//Tableau
		
		
		String[] columnNames = {"Tâche",
                "Type",
                "args_in",
                "args_out"};

		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5)},
			    {"John", "Doe",
			     "Rowing", new Integer(3)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10)}
			};
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
				output_tache.setLayout(new BorderLayout(0, 0));
		
				table = new JTable(model);
				output_tache.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		output_tache.add(scrollPane);


		//Selected Graph Mode
		String[] petStrings = { "ANNOTATING", "EDITING", "PICKING", "TRANSFORMING"};

		JPanel menu_graphInv = new JPanel();
		menu_graphInv.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt)
			{

			}
		});
		output_graph_panel.add(menu_graphInv, BorderLayout.NORTH);
		menu_graphInv.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JComboBox graphSelectedMode = new JComboBox(petStrings);
		menu_graphInv.add(graphSelectedMode);



		String[] layouts = {"CircleLayout",
				"DAGLayout",
				"FRLayout2",
				"KKLayout",
				"SpringLayout2"};

		JComboBox graphLayout = new JComboBox(layouts);
		menu_graphInv.add(graphLayout);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				OpenFile of = new OpenFile();
				of.chooseFolder();
				textField.setText(of.sb.toString());
				String projectSourcePath  = of.sb.toString();
				// read java files
				final File folder = new File(projectSourcePath);


			     // Create the buffered image
	if (grapheInvocation != null)
	{
		VisualizationImageServer<String, String> vs = null;

		Dimension dim =new Dimension(200+10*grapheInvocation.getSommets().size(), 200+10*grapheInvocation.getSommets().size());

			if (((String) graphLayout.getSelectedItem()).equalsIgnoreCase("CircleLayout"))
			{
				vs = new VisualizationImageServer<String, String>(new CircleLayout<String, String>(generateGraph()),
									dim);
			}
			else if (((String) graphLayout.getSelectedItem()).equalsIgnoreCase("DAGLayout"))
			{

				vs = new VisualizationImageServer<String, String>(new DAGLayout<String, String>(generateGraph()),
									dim);
			}
			else if (((String) graphLayout.getSelectedItem()).equalsIgnoreCase("FRLayout2"))
			{

				vs = new VisualizationImageServer<String, String>(new FRLayout2<String, String>(generateGraph()),
									dim);
			}
			else if (((String) graphLayout.getSelectedItem()).equalsIgnoreCase("KKLayout"))
			{

				vs = new VisualizationImageServer<String, String>(new KKLayout<String, String>(generateGraph()),
									dim);
			}
			else if (((String) graphLayout.getSelectedItem()).equalsIgnoreCase("SpringLayout2"))
			{
				vs = new VisualizationImageServer<String, String>(new SpringLayout2<String, String>(generateGraph()),
									dim);
			}

		    //Noeuds
			vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		    //Noeuds Position
			vs.getRenderer().getVertexLabelRenderer().setPosition(edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position.CNTR);


			      BufferedImage image = (BufferedImage) vs.getImage(


			          new Point2D.Double(vs.getGraphLayout().getSize().getWidth(),


			        		  vs.getGraphLayout().getSize().getHeight()),


			          new Dimension(vs.getGraphLayout().getSize()));

			      // Write image to a png file

			      File outputfile = new File(projectSourcePath+"/graph.png");
			      System.out.println(outputfile.getAbsolutePath());

			      System.out.println(outputfile.getAbsolutePath());
			      try {


			        System.out.println("Image Write");


			          ImageIO.write(image, "png", outputfile);

			      } catch (IOException e1) {


			          // Exception handling


			      }

			        JOptionPane.showMessageDialog(null, outputfile.getAbsolutePath()+" save with success", "InfoBox: " + "", JOptionPane.INFORMATION_MESSAGE);


	}
			}
		});
		menu_graphInv.add(btnSave);

		JPanel graphInv_panel = new JPanel();
		output_graph_panel.add(graphInv_panel, BorderLayout.CENTER);

		graphLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {




				JComboBox cb = (JComboBox)e.getSource();
		        String petName = (String)cb.getSelectedItem();
		        System.out.println(petName);
		        DirectedGraph<String, String> g = generateGraph() ;
				if (petName.equalsIgnoreCase("CircleLayout"))
				{
					layout3 = new CircleLayout<String, String> (g);
				}
				else if (petName.equalsIgnoreCase("DAGLayout"))
				{

					layout3 = new DAGLayout<String, String> (g);
				}
				else if (petName.equalsIgnoreCase("FRLayout2"))
				{

					layout3 = new FRLayout2<String, String> (g);
				}
				else if (petName.equalsIgnoreCase("KKLayout"))
				{

					layout3 = new KKLayout<String, String> (g);
				}
				else if (petName.equalsIgnoreCase("SpringLayout2"))
				{
					layout3 = new SpringLayout2<String, String> (g);
				}
				vv3 = new VisualizationViewer<>(layout3 ,preferredGraphSize);


			    //Noeuds
			    vv3.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
			    //Noeuds Position
			    vv3.getRenderer().getVertexLabelRenderer().setPosition(edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position.CNTR);

			    //Arc
			    //vv3.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());



			    graphMouse3 = new DefaultModalGraphMouse<>();
			    vv3.setGraphMouse(graphMouse3);
			    graphMouse3.setMode(ModalGraphMouse.Mode.TRANSFORMING);
			  //  graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);


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
			    graphInv_panel.removeAll();
			    graphInv_panel.add(vv3, BorderLayout.CENTER);
					/*
					*/
					/*
				JScrollPane scrollPane = new JScrollPane(vv3);
				panel_3.add(scrollPane, BorderLayout.CENTER);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				*/
			    vv3.setPreferredSize(output_graph_panel.getPreferredSize());
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.pack();
			    frame.setVisible(true);
			    tabbedPane.setSelectedIndex(1);

			}
		});
		graphSelectedMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("ComboBox");
				JComboBox cb = (JComboBox)e.getSource();
		        String petName = (String)cb.getSelectedItem();
				if (petName.equalsIgnoreCase("ANNOTATING"))
				{
					graphMouse3.setMode(ModalGraphMouse.Mode.ANNOTATING);
				}
				else if (petName.equalsIgnoreCase("EDITING"))
				{
					graphMouse3.setMode(ModalGraphMouse.Mode.EDITING);
				}
				else if (petName.equalsIgnoreCase("PICKING"))
				{
					graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);
				}
				else if (petName.equalsIgnoreCase("TRANSFORMING"))
				{
					graphMouse3.setMode(ModalGraphMouse.Mode.TRANSFORMING);
				}
			}
		});



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
				grapheInvocation = new Graphe();

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

				System.out.println("Sommets "+grapheInvocation.getSommets());
			    DefaultTableModel model = (DefaultTableModel) table.getModel();
			    
			    //Suprression lignes tableau
			    int rowCount = model.getRowCount();
			    //Remove rows one by one from the end of the table
			    for (int i = rowCount - 1; i >= 0; i--) {
			    	model.removeRow(i);
			    }

				System.out.println("Aretes "+grapheInvocation.getAretes());
				System.out.println("Taches :"+grapheInvocation.getTaches());
				System.out.println("Liste des taches primitives");
				
				 //Insertion lignes tableau
				for (Map.Entry<String, Sommet> entry : grapheInvocation.getTaches().entrySet())
				{

					System.out.println(" Tache :"+entry.getKey()+" Entr�es"+entry.getValue().getArgsIn()+" Sorties "+entry.getValue().getArgsOutTache());
				    model.addRow(new Object[]{entry.getKey(), "Primitive", entry.getValue().getArgsIn(), entry.getValue().getArgOut()});
				}
				System.out.println("Liste des taches composites");
				for (Map.Entry<String, Sommet> entry : grapheInvocation.getTachesComposites().entrySet())
				{

					System.out.println(" Tache composite:"+entry.getKey()+" Entr�es"+entry.getValue().getArgsIn()+" Sorties "+entry.getValue().getArgsOutTache());
				    model.addRow(new Object[]{entry.getKey(), "Composite", entry.getValue().getArgsIn(), entry.getValue().getArgOut()});

				}
				//System.out.println("Points d'entrées :"+grapheInvocation.getPointEntree());
				System.out.println("Points d'entr�es");
				for (Map.Entry<String, Sommet> entry : grapheInvocation.getPointEntree().entrySet())
				{
					System.out.println(" PE :"+entry.getKey());
				    model.addRow(new Object[]{entry.getKey(), "Point Entrée", entry.getValue().getArgsIn(), entry.getValue().getArgOut()});
					//System.out.println("Parents : "+entry.getValue().getParents());
					//System.out.println("Childs : "+entry.getValue().getChilds());
				}

			   DirectedGraph<String, String> g = generateGraph();


				layout3 = new SpringLayout<String, String> (g);
			    VisualizationViewer<String, String> vv3 = new VisualizationViewer<>(layout3 ,preferredGraphSize);

			    //Noeuds
			    vv3.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
			    //Noeuds Position
			    vv3.getRenderer().getVertexLabelRenderer().setPosition(edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position.AUTO);

			    //Arc
			    //vv3.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());



			    graphMouse3 = new DefaultModalGraphMouse<>();
			    vv3.setGraphMouse(graphMouse3);
			    graphMouse3.setMode(ModalGraphMouse.Mode.TRANSFORMING);
			  //  graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);


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
			    graphInv_panel.removeAll();
			    graphInv_panel.add(vv3, BorderLayout.CENTER);
					/*
					*/
					/*
				JScrollPane scrollPane = new JScrollPane(vv3);
				panel_3.add(scrollPane, BorderLayout.CENTER);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				*/
			    vv3.setPreferredSize(output_graph_panel.getPreferredSize());
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.pack();
			    frame.setVisible(true);
			    tabbedPane.setSelectedIndex(1);
			    

			    
			

						
			}


		});
		input_panel.add(btnNewButton);

		



	}

	private DirectedGraph<String, String> generateGraph()
	{
		   DirectedGraph<String, String> g = new DirectedSparseMultigraph<String, String>();
			//Graph<String, String> g = new DelegateForest<>();

			for (Map.Entry<String, Sommet> entry : grapheInvocation.getSommets().entrySet())
			{
				if(!g.containsVertex(entry.getValue().getSignature())){
					g.addVertex(entry.getValue().getSignature());
				}

			}
			for (Arete a : grapheInvocation.getAretes())
			{
				g.addEdge(a.getLabelArret(), a.getSommetBegin().getSignature(), a.getSommetEnd().getSignature());
			}
			return g;
	}


}
