package tp3.main;


import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import spoon.*;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.CtTypeParameter;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.factory.TypeFactory;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtTypeReference;

public class ControlFlowExtractor extends AbstractProcessor<CtClass>{
	
	private static Collection<CtMethod> ColnotExposedMethods = new ArrayList<>();
	
	
	
	public void process(CtClass t)
	{  
			if(!t.isInterface())
			{  
				
				System.out.println("Current class: "+t.getSimpleName());
		
				//now we pass to methods
				//if we want to take inherited methods into account, it is possible to use getAllMethods()
				Collection<CtMethod<?>> methods = t.getMethods();
		
				for (CtMethod<?> meth : methods)
				{ 
					
					List<CtParameter<?>> par = meth.getParameters();
					
					for(CtParameter<?> p : par)
					{
						System.out.println(p.getSimpleName());
					}
					
					String str = "\"" + t.getQualifiedName() +" : "+ meth.getSimpleName() + "\"";
		
					CtStatement s = getFactory().Code().createCodeSnippetStatement("System.out.println(" + str + ")");
				
					meth.getBody().addStatement(s);

					//CtStatement statment = meth.getFactory().Code().createCodeSnippetStatement("System.out.println();")	;
					System.out.println(meth.getSimpleName());
				}
			}
		
	}
	
	
	public static void main(String[] args) throws Exception   {
		
		 Scanner sc= new Scanner(System.in);
		 System.out.println("Would you please the path of the project to analyze");
		 //String path=sc.nextLine();
		 String path="/home/lowx/Documents/Professionnel/Etude/Master_AIGLE/M2/workspace_eclipse/HMIN306_TP3/src/test";
		 System.out.println(path);
		 
		 
		 
		 System.out.println("Would you please enter the path to your jar files");
		 System.out.println("They must be separated by a : expect the last one");
		 System.out.println("The jar files of the four application are in Command file");
		 System.out.println("If you want to analyse your own project, you must begin the jar Files");
		 System.out.println("by the path of spoon core with dependencies which exist also in this project");
		 //String jarFiles= sc.nextLine();
		 String jarFiles= "/home/lowx/Documents/Professionnel/Etude/Master_AIGLE/M2/HMIN306_Evolution_Restructuration/03_AnalyseDynamique_Spoon/spoon-core-5.3.0-jar-with-dependencies.jar";
		 System.out.println(jarFiles);
	     spoon.Launcher.main(new String[]
	    		 {
	        "-p", "ControlFlowExtractor",
	        "-i", path,
	        "--source-classpath", jarFiles
	    		 });
	     
}
	
}
