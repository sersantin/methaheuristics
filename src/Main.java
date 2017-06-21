import graph.Graph;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	/**
	 * Main function of the program
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if(args.length == 1) {
			Graph graph = new Graph(args[0]);
			System.out.println(graph.getName());
			//System.out.println(graph.getMatrixItem(1,2));
		}
	}
}