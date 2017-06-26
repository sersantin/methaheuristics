package solutions;

import java.util.ArrayList;
import graph.Graph;

public class TSP {

    private Graph mTsp;				// Graph

    /**
     * Default builder
     */
    public TSP(){}

    /**
     * Builder II
     * @param tsp
     */
    public TSP(Graph tsp){
        load(tsp);
    }

    /**
     * Function to load the file
     * @param tsp
     */
    public void load(Graph tsp){
        mTsp = tsp;
    }

    /**
     * Function to print the graph data.
     */
    public void printData(){
        getTSP().printDataGraph();
    }

    /**
     * Function to generate a aleatory solution
     * @return
     */
    public ArrayList<Integer> randomSolution (){

        ArrayList<Integer> nodes = new ArrayList<>();
        for(int i = 0; i < getTSP().getN(); i++) {
            nodes.add(i);
        }

        ArrayList<Integer> randomTour = new ArrayList<> ();
        do {
            int indexNode = (int) Math.floor(Math.random() * (getTSP().getN() - randomTour.size()));
            if(randomTour.size() == 0) {
                randomTour.add(nodes.get(indexNode));
            } else {
                Double cost = getTSP().getMatrixItem(randomTour.get(randomTour.size() - 1), nodes.get(indexNode));
                if(cost != Double.POSITIVE_INFINITY){
                    randomTour.add(nodes.get(indexNode));
                }
            }
            nodes.remove(indexNode);
        } while (randomTour.size() != getTSP().getN());
        return randomTour;
    }

    /**
     * Function to calculate the cost tour
     * @param tour
     * @return
     */
    public Double calculateCostTour(ArrayList<Integer> tour) {
        Double cost = 0.0;
        for(int i = 0; i < tour.size() - 1; i++) {
            cost += getTSP().getMatrixItem(tour.get(i), tour.get(i + 1));
        }
        cost += getTSP().getMatrixItem(tour.get(tour.size() - 1),
                tour.get(0));
        return cost;
    }

    /**
     * Getter TSP graph
     * @return
     */
    public Graph getTSP() {
        return mTsp;
    }

    /**
     * Setter TSP graph
     * @param tsp
     */
    public void setTSP(Graph tsp) {
        mTsp = tsp;
    }

    protected ArrayList<Integer> twoOptSwap(ArrayList<Integer> bestTour, int i, int k) {
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
}
