package projet;

import javax.swing.JFileChooser;

public class OpenFile {
	
	public JFileChooser fileChooser = new JFileChooser();
	public StringBuilder sb = new StringBuilder();
	
	public void chooseFolder() {
		
	    fileChooser.setCurrentDirectory(new java.io.File("."));

	    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    //
	    // disable the "All files" option.
	    //
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    //    
	    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
	      System.out.println("getCurrentDirectory(): " 
	         +  fileChooser.getCurrentDirectory());
	      System.out.println("getSelectedFile() : " 
	         +  fileChooser.getSelectedFile());
	      sb.append(fileChooser.getSelectedFile().getAbsolutePath());
	      }
	    else {
	      System.out.println("No Selection ");
			sb.append("No path selected");

	      }
		
	}

}
