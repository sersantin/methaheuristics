import solutions.*;
import graph.*;
import Statistics.*;
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

//			TSP solution = new TSP(graph);
//			solution.printData();
//			System.out.println();
//			System.out.println("COST");
//			System.out.println();
//			System.out.println("One Candidate Solution:		"
//					+solution.calculateCostTour(solution.randomSolution()));
//			System.out.println("Other Candidate Solution:	"
//					+solution.calculateCostTour(solution.randomSolution()));

        int opc1, opc2;
        String name = "";
        Graph graph = new Graph();

        Scanner sc = new Scanner(System.in);
        System.out.print("1. burma14\n2. ft70\nSelect Problem: ");
        opc1 = sc.nextInt();

        if (opc1 == 1) {
            graph = new Graph("burma14");
            name = "burma14";
        } else if (opc1 == 2) {
            graph = new Graph("ft70");
            name = "ft70";
        } else {
            System.err.println("Option not found");
            System.exit(-1);
        }

        System.out.println();
        System.out.print("1. Hill Climbing\n2. Simulated Annealing\n3. Variable Neighborhood Search\n4. Taboo Search\nSelect Solution: ");
        opc2 = sc.nextInt();

        ArrayList<Integer> solutions = new ArrayList<>();
        ArrayList<Double> results = new ArrayList<> ();

        if (opc2 == 1) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 100; j++) {
                    HillClimbing problem = new HillClimbing(graph);
                    solutions = problem.solve();
                    results.add(problem.calculateCostTour(solutions));
                    //problem.printResult();
                }


                Statistics statistics = new Statistics(results);
                statistics.saveStatistics(name + "_sa_output.csv", ";");
                statistics.showStatistics();
            }
        }
        else if (opc2 == 2) {

            SimulatedAnnealing problem = new SimulatedAnnealing(graph);

            for(int i = 0; i < 20; i++) {

                for(int j = 0; j < 100; j++) {

                    solutions = problem.solveSimulatedAnnealing(problem.randomSolution());
                    results.add(problem.calculateCostTour(solutions));
                }

                Statistics statistics = new Statistics(results);
                statistics.saveStatistics(name+"_sa_output.csv", ";");
                statistics.showStatistics();
            }

        }
        else if (opc2 == 3) {
            System.out.println();
            System.out.print("Select max neighborhoods: ");
            opc1 = sc.nextInt();
            SimulatedAnnealing problem = new SimulatedAnnealing(graph);

            for(int i = 0; i < 20; i++) {

                for(int j = 0; j < 100; j++) {

                    solutions = problem.solveVariableNeighborhoodSearch(problem.randomSolution(),opc1);
                    results.add(problem.calculateCostTour(solutions));
                }

                Statistics statistics = new Statistics(results);
                statistics.saveStatistics(name+"_vns_sa_output.csv", ";");
                statistics.showStatistics();
            }

        }
        else if (opc2 == 4) {

            TabooSearch problem = new TabooSearch(graph);

            for(int i = 0; i < 20; i++) {

                for(int j = 0; j < 100; j++) {
                    solutions = problem.solve();
                    //problem.printResult();
                    results.add(problem.calculateCostTour(solutions));
                }

                Statistics statistics = new Statistics(results);
                statistics.saveStatistics(name+"_vns_sa_output.csv", ";");
                statistics.showStatistics();
            }

        }

    }
}