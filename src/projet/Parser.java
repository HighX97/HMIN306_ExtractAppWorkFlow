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

public class Parser {

	public final static String dir = System.getProperty("user.dir");
	public static final String projectPath = dir+"/../HMIN306_ExtractAppWorkFlow/";
	public static final String projectSourcePath = projectPath + "/src/tp3/test";
	//public static final String jrePath = "/usr/share/java";
	public static final String jrePath = "";

	public static void main(String[] args) throws IOException {

		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);
		Graphe grapheInvocation = new Graphe();

		//
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			// System.out.println(content);
			String  fileName = fileEntry.getName();
			String  className = fileName.substring(0, fileName.length() - 5);


			CompilationUnit parse = parse(content.toCharArray());

			System.out.println("----------------------------"+className+"Methodes--------------------------");
			// print methods info
			//printMethodInfo(parse);
			grapheInvocation = nodeMethodInfo(parse, grapheInvocation);

			System.out.println("----------------------------"+className+"Variables--------------------------");
			// print variables info
			printVariableInfo(parse);

		}

		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			// System.out.println(content);
			String  fileName = fileEntry.getName();
			String  className = fileName.substring(0, fileName.length() - 5);


			CompilationUnit parse = parse(content.toCharArray());

			System.out.println("----------------------------"+className+"MethodInvocations--------------------------");
			//print method invocations
			printMethodInvocationInfo(parse);
			grapheInvocation = areteMethodInvocationInfo(parse, grapheInvocation);

		}
	}

	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				// System.out.println(fileEntry.getName());
				javaFiles.add(fileEntry);
			}
		}

		return javaFiles;
	}

	// create AST
	private static CompilationUnit parse(char[] classSource) {
		ASTParser parser = ASTParser.newParser(AST.JLS4); // java +1.6
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);

		parser.setBindingsRecovery(true);

		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);

		parser.setUnitName("");

		String[] sources = { projectSourcePath };
		String[] classpath = {jrePath};

		parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
		parser.setSource(classSource);

		return (CompilationUnit) parser.createAST(null); // create and parse
	}

	// navigate method information
	public static void printMethodInfo(CompilationUnit parse) {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);

		for (MethodDeclaration method : visitor.getMethods()) {
			System.out.println("Method name: " + method.getName()
			+ " Return type: " + method.getReturnType2());
		}

	}

	// navigate method information
	public static Graphe nodeMethodInfo(CompilationUnit parse,Graphe g) {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);

		for (MethodDeclaration method : visitor.getMethods())
		{
			g.addSommet(new Sommet("methodeClass"+":"+(method.getName())));
			System.out.println("Method name: " + method.getName()
			+ " Return type: " + method.getReturnType2());
		}
		return g;

	}

	// navigate variables inside method
	public static void printVariableInfo(CompilationUnit parse) {

		MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
		parse.accept(visitor1);
		for (MethodDeclaration method : visitor1.getMethods()) {

			VariableDeclarationFragmentVisitor visitor2 = new VariableDeclarationFragmentVisitor();
			method.accept(visitor2);

			for (VariableDeclarationFragment variableDeclarationFragment : visitor2
					.getVariables()) {
				System.out.println("variable name: "
						+ variableDeclarationFragment.getName()
						+ " variable Initializer: "
						+ variableDeclarationFragment.getInitializer());
			}

		}
	}

	// navigate method invocations inside method
		public static void printMethodInvocationInfo(CompilationUnit parse) {

			MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
			parse.accept(visitor1);
			for (MethodDeclaration method : visitor1.getMethods()) {

				MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
				method.accept(visitor2);

				for (MethodInvocation methodInvocation : visitor2.getMethods())
				{
					System.out.println("method " + method.getName() + " invoc method "
							+ methodInvocation.getName());
					System.out.println("&&&&&&&&&&");
					System.out.println(method);
					System.out.println(methodInvocation);
					System.out.println("&&&&&&&&&&");

				}

			}
		}

		public static Graphe areteMethodInvocationInfo(CompilationUnit parse,Graphe g) {

			MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
			parse.accept(visitor1);
			Map<String ,Sommet> sommets = g.getSommets();
			for (MethodDeclaration method : visitor1.getMethods()) {

				MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
				method.accept(visitor2);

				/*
				 * Issue to get class name from method
				 */
				for (MethodInvocation methodInvocation : visitor2.getMethods()) {
					System.out.println("method " + method.getName()+ " invoc method "
							+ methodInvocation.getName());
					Sommet sommetBegin = sommets.get("methodeClass"+":"+(method.getName()));
					Sommet sommetEnd = sommets.get("methodeClass"+":"+(methodInvocation.getName()));
					g.addArete(new Arete(sommetBegin,sommetEnd));
				}

			}

			return g;
		}

}
