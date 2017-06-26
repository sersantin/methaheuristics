package solutions;

import java.util.ArrayList;
import graph.Graph;


public class TabooSearch extends TSP {

    private ArrayList<Integer> mFinalSolution;
    private ArrayList<Integer> mTabu;

    public TabooSearch(Graph graph) {
        super(graph);
        mFinalSolution = new ArrayList<Integer>();
        mTabu = new ArrayList<Integer>();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> solve() {

        HillClimbing hillclimbing = new HillClimbing(getTSP());
        ArrayList<Integer> currentSolution = new ArrayList<Integer>();
        boolean end = false;
        int n = 100;
        int i = 0;

        currentSolution = hillclimbing.solve();
        setmFinalSolution((ArrayList<Integer>) currentSolution.clone());

        do {

            if (i == n) {
                end = true;
            } else {
                currentSolution = twoOptTabu(currentSolution);
                if (calculateCostTour(currentSolution) < calculateCostTour(mFinalSolution)){
                    mFinalSolution = (ArrayList<Integer>) currentSolution.clone();
                }
                i++;
            }

        } while(end != true);

        return mFinalSolution;
    }

    private ArrayList<Integer> twoOptTabu (ArrayList<Integer> currentSolution){

        boolean go_start = false;
        boolean improvement = true;

        double tempValue = calculateCostTour(currentSolution);
        double bestValue = calculateCostTour(currentSolution);

        ArrayList<Integer> tempTour = currentSolution;
        ArrayList<Integer> bestTour = currentSolution;

        while(improvement){

            improvement = false;

            for(int i = 0; i < getTSP().getN(); i++){

                boolean isTabu = false; /* If "i" element is in Tabu list */
                for (int j = 0; j < getmTabu().size(); j++){

                    if (getmTabu().get(j) == i) {
                        isTabu = true;
                        continue;
                    }

                }

                if (!isTabu){

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
                            updateTabu(i);

                            break;

                        }
                    }

                }
            }
        }

        return bestTour;
    }

    private void updateTabu(int memNode){
        getmTabu().add(memNode);
    }

    public void printResult() {
        System.out.println("Tabu Search size: "+ getmFinalSolution().size());
        System.out.print("Tabu Search result: ");
        for (int i = 0; i  < mFinalSolution.size(); i++){
            System.out.print(mFinalSolution.get(i)+" ");
        }
        System.out.println();

    }

    public ArrayList<Integer> getmTabu() {
        return mTabu;
    }

    public void setmTabu(ArrayList<Integer> mTabu) {
        this.mTabu = mTabu;
    }

    public ArrayList<Integer> getmFinalSolution() {
        return mFinalSolution;
    }

    public void setmFinalSolution(ArrayList<Integer> mFinalSolution) {
        this.mFinalSolution = mFinalSolution;
    }


}
