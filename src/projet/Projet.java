package projet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ConstantTransformer;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.w3c.dom.Node;

import com.google.common.base.Function;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.text.Position;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.DAGLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.LayoutDecorator;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.decorators.VertexIconShapeTransformer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.VertexLabelAsShapeRenderer;
import edu.uci.ics.jung.visualization.transform.*;
import edu.uci.ics.jung.visualization.util.VertexShapeFactory;
import graphe.*;

public class Projet
{

	public final static String dir = System.getProperty("user.dir");
	public static final String projectPath = dir+"/../HMIN306_ExtractAppWorkFlow/";
	public static final String projectSourcePath = projectPath + "/src/tp3/test";
	//public static final String jrePath = "/usr/share/java";
	public static final String jrePath = "";
	
	

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = Parser.listJavaFilesForFolder(folder);
		Graphe grapheInvocation = new Graphe();

		//
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
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
			String content = FileUtils.readFileToString(fileEntry);
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
		
	   Graph<String, String> g = new SparseMultigraph<String, String>();
		//Graph<String, String> g = new DelegateForest<>();
		
		for (Map.Entry<String, Sommet> entry : grapheInvocation.getSommets().entrySet())
		{
			
				g.addVertex(entry.getValue().getNomSommet());
				
		}
		for (Arete a : grapheInvocation.getAretes())
		{
			g.addEdge(a.getLabelArret(), a.getSommetBegin().getNomSommet(), a.getSommetEnd().getNomSommet(), EdgeType.DIRECTED);
		}
		 
		
		Layout<String, String> layout3 = new FRLayout<String, String> (g);
		layout3.setSize(new Dimension(500,500));
		
	    VisualizationViewer<String, String> vv3 = new VisualizationViewer<>(layout3);


	    /*Transformer<String, Shape> vertexShape = 
	    	    new Transformer<String, Shape>() {
	    	 
	    	        @Override
	    	        public Shape transform(String i) {
	    	            return new Ellipse2D.Double(-25, -10, 50, 20);
	    	        }
	    	    };*/
/*
	     new VisualizationImageServer<String, String>(new FRLayout<String, String>(g), new Dimension(600, 600));
	    
	    vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	    vs.getRenderContext().setVertexLabelRenderer(new DefaultVertexLabelRenderer(Color.green));
	    */

        //vs.getRenderContext().setVertexDrawPaintTransformer( new ConstantTransformer(Color.white));
       // vs.getRenderContext().setEdgeStrokeTransformer(new ConstantTransformer(new BasicStroke(2.5f)));

        //vs.getRenderContext().setVertexFillPaintTransformer(new VertexPaintTransformer(vs.getPickedVertexState()));

        DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();
        graphMouse.setMode(edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode.PICKING);
	    vv3.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	    
	    vv3.getRenderContext().setVertexLabelRenderer(new DefaultVertexLabelRenderer(Color.pink));
	    
	    //vv3.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	    vv3.getRenderer().getVertexLabelRenderer().setPosition(edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position.CNTR);
	  
	    
       // vs.setGraphMouse(graphMouse);
       
        /*new VisualizationImageServer<String, String>(new TreeLayout<String,String> (tree), 
	        		new Dimension(2000, 2000));
	    // CircleLayout<String, String>(g)
	    
	    VertexShapeFactory<String> shap = new VertexShapeFactory<String>();
        vv3.getRenderContext().setVertexShapeTransformer(new VertexShapeFactory());


	    vv3.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	    
	    vv3.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	    
	    vv3.getRenderContext().setVertexLabelRenderer(new DefaultVertexLabelRenderer(Color.pink));
	    
	    vv3.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	    vv3.getRenderer().getVertexLabelRenderer().setPosition(edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position.CNTR);
	  
	    

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
	    frame.getContentPane().add(vv3);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);


	}
	
	

}
