package solutions;

import java.util.ArrayList;
import graph.Graph;


public class SimulatedAnnealing extends TSP{

    public SimulatedAnnealing (Graph graph){
        super(graph);
    }

    public ArrayList<Integer> solveSimulatedAnnealing(ArrayList<Integer> finalSolution) {
        ArrayList<Integer> newSolution = new ArrayList<> ();

        int i = 0;
        while (i < 1000) {
            newSolution = twoOPT (finalSolution);

            Double costFinalSolution = calculateCostTour(finalSolution);
            Double costNewSolution = calculateCostTour(newSolution);

            if (costFinalSolution < costNewSolution) {
                finalSolution = newSolution;
            } else {

                // Basic algorithm
                if(Math.random() > 0.5){

				/* // Greater importance to the worst solutions
				 if(1 - (costNewSolution / costFinalSolution) > Math.random()){
				*/

				/* // Less emphasis on the worst solutions
				 if((costNewSolution / costFinalSolution) > Math.random()){
				*/
                    finalSolution = newSolution;
                }
            }

            i++;
        }

        return finalSolution;
    }

    private ArrayList<Integer> twoOPT(ArrayList<Integer> bT) {
        boolean go_start = false;
        boolean improvement = true;

        double tempValue = calculateCostTour(bT);
        double bestValue = calculateCostTour(bT);

        ArrayList<Integer> tempTour = bT;
        ArrayList<Integer> bestTour = bT;

        while(improvement){
            improvement = false;

            for(int i = 0; i < getTSP().getN();i++){
                if(go_start){
                    break;
                }

                for(int k = i + 1; k < getTSP().getN(); k++){

                    tempTour = twoOptSwap(tempTour, i, k);
                    tempValue = calculateCostTour(tempTour);

                    if(tempValue < bestValue){
                        bestValue = tempValue;
                        bestTour = tempTour;
                        improvement = true;
                        go_start = true;

                        break;

                    }
                }
            }
        }

        return bestTour;
    }

    private ArrayList<Integer> twoOptSwap(ArrayList<Integer> bestTour, int i, int k) {
        ArrayList<Integer> newRoute = new ArrayList<Integer> ();

        for(int j = 0; j < i; j++){
            newRoute.add(bestTour.get(j));
        }

        for(int j = k; j >= i; j--){
            newRoute.add(bestTour.get(j));
        }

        for(int j = k+1; j < bestTour.size(); j++){
            newRoute.add(bestTour.get(j));
        }

        return newRoute;
    }


    public ArrayList<Integer> solveVariableNeighborhoodSearch(ArrayList<Integer> finalSolution, int maxNeighbour){
        int neighbour = 0;

        while (neighbour < maxNeighbour){
            ArrayList<Integer> shakedSolution = randomSolution();
            ArrayList<Integer> simulatedAnnealingSolution = solveSimulatedAnnealing(shakedSolution);

            if(calculateCostTour(simulatedAnnealingSolution) < calculateCostTour(finalSolution)){
                finalSolution = simulatedAnnealingSolution;
                neighbour = 0;
            } else {
                neighbour++;
            }
        }

        return finalSolution;
    }
}
