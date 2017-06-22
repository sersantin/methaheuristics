import Solutions.*;
import graph.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /**
     * Main function of the program
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //if(args.length == 1) {
        //Graph graph = new Graph(args[0]);
        //System.out.println(graph.getName());
        //System.out.println(graph.getMatrixItem(1,2));

			/*TSP solution = new TSP(graph);
			solution.printData();
			System.out.println();
			System.out.println("COST");
			System.out.println();
			System.out.println("One Candidate Solution:		"
					+solution.calculateCostTour(solution.randomSolution()));
			System.out.println("Other Candidate Solution:	"
					+solution.calculateCostTour(solution.randomSolution()));
		*/
        int opc1, opc2;
        Graph graph = new Graph();

        Scanner sc = new Scanner(System.in);
        System.out.print("1. burma14\n2. ft70\nSelect Problem: ");
        opc1 = sc.nextInt();

        if (opc1 == 1) {
            graph = new Graph("burma14");
        } else if (opc1 == 2) {
            graph = new Graph("ft70");
        } else {
            System.err.println("Option not found");
            System.exit(-1);
        }

        System.out.println();
        System.out.print("1. HillClimbing\nSelect Solution: ");
        opc2 = sc.nextInt();

        if (opc2 == 1) {
            for (int i = 0; i < 20; i++) {
                ArrayList<Integer> solution = new ArrayList<>();
                ArrayList<Double> result = new ArrayList<> ();
                for (int j = 0; j < 100; j++) {
                    HillClimbing problem = new HillClimbing(graph);
                    solution = problem.solve();
                    problem.printResult();
                }

            }

        }

    }
}