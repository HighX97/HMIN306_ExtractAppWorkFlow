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

import graphe.*;

public class Projet{
	
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
			grapheInvocation = Parser.nodeMethodInfo(parse, grapheInvocation);

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
			Parser.printMethodInvocationInfo(parse);
			grapheInvocation = Parser.areteMethodInvocationInfo(parse, grapheInvocation);

		}
	}

}
