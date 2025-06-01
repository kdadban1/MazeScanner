import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p1 {

	public static void main(String[] args) throws IllegalCommandLineInputsException { 
	
		
		//command line - the content of args
		
		//example - not real/actual logic
//		if (args.length <= 1) {
//			//throw the appropriate exception if you 
//			//encounter an error from the list
//			throw new IllegalCommandLineInputsException();
//		}
		
		
		
			File file = new File("easyMap1.txt");
            Maze m = new Maze(file);

            
            m.findPath();
            
            

       

	}

}
