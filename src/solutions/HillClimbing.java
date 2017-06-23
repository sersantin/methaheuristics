package solutions;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import graph.Graph;

public class HillClimbing extends TSP {

    private ArrayList<Integer> list;

    public HillClimbing(Graph graph) {
        super(graph);
        list = new ArrayList<>();
    }

    public ArrayList<Integer> solve() {

        int n = getTSP().getN();

        int startNode = ThreadLocalRandom.current().nextInt(0, n); // Random start node
        int actualNode = startNode;
        list.add(actualNode); // Add start node as first node in list
        boolean end = false;

        while(!end) {

            if (list.size() == n) {
                end = true;
            } else {
                actualNode = getSuccessor(actualNode, list);
                // actualNode = getSuccessorRoulette(actualNode, list);
                list.add(actualNode);
            }

        }

        return list;

    }

    /**
     * Get a roulette successor
     * @param actualNode
     * @param list
     * @return
     */
    private int getSuccessorRoulette(int actualNode,  ArrayList<Integer> actualRoute) {

        Double totalCost = getTotalCost(actualNode, actualRoute);

        ArrayList<Double> probabilityCost = new ArrayList<Double> ();

        for(int i = 0; i < getTSP().getN(); i++){
            if(isACandidate(i, actualNode, actualRoute)) {

                probabilityCost.add(getTSP().getMatrixItem(actualNode, i) / totalCost);
            }
        }

        int numberRoulette = getResultOfRoulette(probabilityCost);
        int numberPosition = 0;

        for(int i = 0; i < getTSP().getN(); i++){
            if(isACandidate(i, actualNode, actualRoute)) {

                if(numberRoulette == numberPosition){
                    return i;

                } else {
                    numberPosition++;
                }
            }
        }

        return 0;
    }

    /**
     * Get the result of the roulette
     * @return
     */
    private int getResultOfRoulette(ArrayList<Double> probabilityCost) {
        Double rouletteValue = Math.random();
        Double values = 0.0;

        for(int i = 0; i < probabilityCost.size(); i++){
            values += probabilityCost.get(i);

            if(values >  probabilityCost.get(i)) {
                return i - 1;
            }
        }

        return 0;
    }

    /**
     * Function that calculate the cost to normalize the
     * possible successor
     *
     * @param actualNode
     * @return
     */
    private Double getTotalCost(int actualNode, ArrayList<Integer> actualRoute) {
        Double totalCost = 0.0;

        for(int i = 0; i < getTSP().getN(); i++){

            if(isACandidate(i, actualNode, actualRoute)) {
                totalCost += getTSP().getMatrixItem(actualNode, i);
            }
        }

        return totalCost;
    }

    /**
     * Check if a node is visited
     * @return
     */
    public boolean checkIsVisit(int actualNode, ArrayList<Integer> actualRoute){
        for(int i = 0; i < actualRoute.size(); i++){
            if(actualNode == actualRoute.get(i)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Check if a node is a candidate
     *
     * @param node
     * @param actualNode
     * @param actualRoute
     * @return
     */
    public boolean isACandidate(int node, int actualNode, ArrayList<Integer> actualRoute){

        if((node != actualNode) && (!checkIsVisit(node, actualRoute)) &&
                (getTSP().getMatrixItem(node, actualNode) != Double.MAX_VALUE)) {

            return true;
        }
        return false;
    }


    public int getSuccessor(int actualNode, ArrayList<Integer> list) {

        ArrayList<Integer> listSuccessors = new ArrayList<Integer>();
        ArrayList<Double> listSuccessorsValue = new ArrayList<Double>();

        double infinite = Double.MAX_VALUE;
        int n = getTSP().getN();

		/* Add all successors from actual node */
        for (int i = 0; i < n; i++) { // Loop for actualNode column
            if (getTSP().getMatrixItem(i, actualNode) != infinite) { // If there is a connection between nodes
                if (actualNode !=  i) { // If actual node is not itself (there isn't distance)
                    listSuccessors.add(i);
                    listSuccessorsValue.add(getTSP().getMatrixItem(i, actualNode));
                }
            }
        }

		/* Exclude visited nodes */
        for (int v = 0; v < list.size(); v++) { // Loop for check visited nodes
            for (int i = 0; i < listSuccessorsValue.size(); i++) {
                if (list.get(v) == listSuccessors.get(i)) {
                    listSuccessors.remove(i);
                    listSuccessorsValue.remove(i);
                }
            }
        }

		/* Get best successor comparing distances */
        int successor = listSuccessors.get(0);
        double bestDistance = listSuccessorsValue.get(0); // Get first node distance
        for (int i = 0; i < listSuccessors.size(); i++) {
            if (listSuccessorsValue.get(i) < bestDistance) {
                successor = listSuccessors.get(i);
                bestDistance = listSuccessorsValue.get(i);
            }
        }

        return successor;
    }

    /* Print list of nodes with the final tour  */
    public void printResult() {

        System.out.print("Hill Climbing result: ");
        for (int i = 0; i  < list.size(); i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();

    }
}

