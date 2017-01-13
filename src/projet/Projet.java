package projet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.internal.utils.FileUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import graphe.*;

public class Projet
{

	public final static String dir = System.getProperty("user.dir");
	public static final String projectPath = dir+"/../HMIN306_ExtractAppWorkFlow/";
	public static final String projectSourcePath = projectPath + "/src/tp3/test";
	//public static final String jrePath = "/usr/share/java";
	public static final String jrePath = "";

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
		
	    DirectedSparseGraph<String, String> g = new DirectedSparseGraph<String, String>();
		for (Map.Entry<String, Sommet> entry : grapheInvocation.getSommets().entrySet())
		{
			g.addVertex(entry.getValue().getNomSommet());
		}
		for (Arete a : grapheInvocation.getAretes())
		{
			g.addEdge(a.getLabelArret(), a.getSommetBegin().getNomSommet(), a.getSommetEnd().getNomSommet());
		}
	    VisualizationImageServer<String, String> vs =
	        new VisualizationImageServer<String, String>(new CircleLayout<String, String>(g), new Dimension(600, 600));

	    JFrame frame = new JFrame();
	    frame.getContentPane().add(vs);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}

}
