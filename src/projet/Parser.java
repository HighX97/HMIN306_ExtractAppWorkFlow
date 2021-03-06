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
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import astvisitor.IfStatementVisitor;
import astvisitor.MethodDeclarationVisitor;
import astvisitor.MethodInvocationVisitor;
import astvisitor.VariableDeclarationFragmentVisitor;
import graphe.*;

public class Parser {

	public final static String dir = System.getProperty("user.dir");
	public static final String projectPath = dir+"/../HMIN306_ExtractAppWorkFlow/";
	public static final String projectSourcePath = projectPath + "/src/";
	//public static final String jrePath = "/usr/share/java";
	public static final String jrePath = "";


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
	public static CompilationUnit parse(char[] classSource) {
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
			/*System.out.println("Method name: " + method.getName()
			+ " Return type: " + method.getReturnType2());*/
		}

	}

	// navigate method information
	public static Graphe nodeMethodInfo(CompilationUnit parse,Graphe g, String className) {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);

		for (MethodDeclaration method : visitor.getMethods())
		{
			Sommet s = new Sommet(className+":"+(method.getName()),g);
			String entree ="";
			
			for (Object param : method.parameters())
			{
				SingleVariableDeclaration e = (SingleVariableDeclaration)param;
					//System.out.println("DEBUG");
					//System.out.println(e);
					//System.out.println(e.resolveBinding());
					if (e.getName() != null)
					{
						//System.out.println("Arg type : "+e.getName());
						String arg_in = e.resolveBinding().getType().getName();
						s.getArgsIn().add(arg_in);
						s.getArgsOutTache().add(arg_in);
					}
					
			}
			
			
			if (method.getReturnType2() != null)
			{
				s.setArgOut(method.getReturnType2().toString());
				if(!s.getArgsOutTache().contains(method.getReturnType2().toString())){
					s.getArgsOutTache().add(method.getReturnType2().toString());
				}
				
			}
			else
			{
				s.setArgOut("void");
			}
			
			

			
			g.addSommet(s);
			/*
			System.out.println("Method name: " + method.getName()
			+ " Return type: " + method.getReturnType2());
			*/
		}
		return g;

	}
	
	public static Graphe nodeMethodInvocationInfo(CompilationUnit parse,Graphe g, String className) {
		MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
		parse.accept(visitor1);
		Map<String ,Sommet> sommets = g.getSommets();
		for (MethodDeclaration method : visitor1.getMethods()) {

			MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
			method.accept(visitor2);

			/*
			 * Issue to get class name from method
			 */
			for (MethodInvocation methodInvocation : visitor2.getMethods()) 
			{
				Sommet s = new Sommet(toStringMethdIn(methodInvocation,className),g);
				
				for (Object param : methodInvocation.arguments())
				{
						Expression e = (Expression)param;
						//System.out.println("DEBUG");
						//System.out.println(e);
						if (e.resolveTypeBinding() != null)
						{
							//System.out.println("Arg type : "+e.resolveTypeBinding().getName());
							s.getArgsIn().add(e.resolveTypeBinding().getName());
							s.getArgsOutTache().add(e.resolveTypeBinding().getName());
						}
						
				}
				
				if (methodInvocation.resolveTypeBinding() != null)
				{
					//System.out.println("Arg type : "+methodInvocation.resolveMethodBinding().getName());
					s.setArgOut(methodInvocation.resolveTypeBinding().getName());
					if(!s.getArgsOutTache().contains(methodInvocation.resolveTypeBinding().getName())){
						s.getArgsOutTache().add(methodInvocation.resolveTypeBinding().getName());
					}
				}
				else
				{
					s.setArgOut("void");
				}
				
				
				g.addSommet(s);
			}

		}
		return g;
	}
	
	//navigate IfStatement
	public static void nodeIfStatementInfo(CompilationUnit parse,Graphe g, String className) {
		IfStatementVisitor visitor = new IfStatementVisitor();
		parse.accept(visitor);

		for (IfStatement ifStatment : visitor.getifStatements())
		{
			
			System.out.println("ifStatment start: " + ifStatment.getStartPosition()
			+ " ifStatment " + ifStatment);
			
		}

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
				/*System.out.println("variable name: "
						+ variableDeclarationFragment.getName()
						+ " variable Initializer: "
						+ variableDeclarationFragment.getInitializer());*/
			}

		}
	}

	// navigate method invocations inside method
		public static void printMethodInvocationInfo(CompilationUnit parse , String className) {

			MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
			parse.accept(visitor1);
			for (MethodDeclaration method : visitor1.getMethods()) {

				MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
				method.accept(visitor2);

				for (MethodInvocation methodInvocation : visitor2.getMethods())
				{
					/*System.out.println("method " + method.getName() + " invoc method "
							+ toStringMethdIn(methodInvocation,className));
					System.out.println(toStringMethdIn(methodInvocation,className));*/

				}

			}
		}

		public static String toStringMethdIn(MethodInvocation method , String className)
		{
			//System.out.println(method);
			//System.out.println(method.getExpression());
			String result = "";
			
			System.out.println(method.getName()+" ARGS "+method.arguments());
			for(Object n : method.arguments()){
				Expression e = (Expression)n;
				//System.out.println("DEBUG");
				//System.out.println(e);
				if (e.resolveTypeBinding() != null)
				{
					//System.out.println("Arg type "+e.resolveTypeBinding().getName());

				}
			}
			
			
			if ( method.getExpression() == null || method.getExpression().toString().equalsIgnoreCase("this"))
			{
				//System.out.println("this");
				result = className +":"+method.getName();
			}
			else
			{
				ITypeBinding typeBinding = method.getExpression().resolveTypeBinding();
                if (typeBinding != null) {
                    //System.out.println("Type: " + typeBinding.getName());
                	result = typeBinding.getName()+":"+method.getName();
                }
                else
                {
        			result = method.getExpression()+":"+method.getName();
                }
				//System.out.println("blabla ="+method.getExpression().resolveTypeBinding().getDeclaringMethod().getName());
	
			}
			//System.out.println(result);
			return result;

		}

		public static String toStringMethdIn(MethodDeclaration method , String className)
		{
			//System.out.println(method);
			String result = "";
			result = className +":"+method.getName();
			//System.out.println(result);
			return result;

		}

		public static Graphe areteMethodInvocationInfo(CompilationUnit parse,Graphe g , String className) {

			MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
			parse.accept(visitor1);
			Map<String ,Sommet> sommets = g.getSommets();
			for (MethodDeclaration method : visitor1.getMethods()) {
				
				//System.out.println(method.getName()+" ARGS "+method.parameters());
				//System.out.println(method.getName()+" RETURN "+method.getReturnType2());

				MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
				method.accept(visitor2);

				/*
				 * Issue to get class name from method
				 */
				for (MethodInvocation methodInvocation : visitor2.getMethods()) {

					//System.out.println("method " + toStringMethdIn(method,className) + " invoc method "
					//		+ toStringMethdIn(methodInvocation,className));

					Sommet sommetBegin = sommets.get(toStringMethdIn(method,className));
					Sommet sommetEnd = sommets.get(toStringMethdIn(methodInvocation,className));
					g.addArete(new Arete(sommetBegin,sommetEnd));
				}

			}

			return g;
		}

}
