package tp3.main;
import java.util.Scanner;

public class CFEMain {
	
	public static void main(String[] args) throws Exception   {
		
		 Scanner sc= new Scanner(System.in);
		 System.out.println("Would you please the path of the project to analyze");
		 //String path=sc.nextLine();
		 final String dir = System.getProperty("user.dir");
		 System.out.println("current dir = " + dir);
		 String path=dir+"/src/tp3/test";
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
