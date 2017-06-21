/**
 * Project of metaheuristics
 * 
 * Máster Universitario en Ingeniería Informática
 * E.S.I.T.– INFORMÁ́TICA
 * Departamento de Ingeniería Informática y de Sistemas
 * Sistemas Inteligentes e Interacción Persona Computador
 * 
 * Project to study of metaheuristics, it is a higher-level procedure or heuristic 
 * designed to find, generate, or select a heuristic (partial search algorithm) that
 * may provide a sufficiently good solution to an optimization problem, especially 
 * with incomplete or imperfect information or limited computation capacity.
 *
 * Class to represent the nodes of the graph.
 */
package graph;

public class Instance {
	
	protected int n;				// Number of nodes.
	protected double graph[];		// Array with the cost of each edge.
	
	/**
	 * Default builder.
	 */
	public Instance() {
		
	}
	
	/**
	 * Function to print the cost of each edge.
	 */
	public void  printMatrix(){
		System.out.println("Costs matrix: ");
		for(int i = 0;i < getN(); i++){
			for(int j = 0; j < getN(); j++){
				System.out.print(" " + getMatrixItem(i,j) + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * Function to return a specific cost (of the node i to j)
	 * @param i Node number i.
	 * @param j Node number j.
	 * @return Value of the cost.
	 */
	public double getMatrixItem(int i,int j){
		if((i < 0) || (j < 0)){
			return 0;
		}else{
			return graph[getPosition(i,j)];
		}
	}
	
	/**
	 * Function to change a specific cost (of the node i to j)
	 * @param i Node number i.
	 * @param j Node number j.
	 * @param New value of the cost.
	 */
	public void setMatrixItem(int i, int j, double it){
		graph[getPosition(i, j)] = it;
	}
	
	/**
	 * Return the position in the matrix.
	 * @param i Node i
	 * @param j Node j
	 * @return Position in the array
	 */
	private int getPosition(int i, int j){
		if ((i < 0) || (i > getN()) || (j < 0) || (j > getN())){
			System.out.println("Error accediendo a matriz");
			return 0;
		}else{
			return i * getN() + j;
		}
	}
	
	/**
	 * Function to build the matrix.
	 * @param n
	 */
	protected void buildMatrix(int n){
		this.n = n; 
		graph = new double[n * n];
	}
	
	/**
	 * Getter number of nodes.
	 * @return n
	 */
	public int getN(){ return n; }
	
	/**
	 * Setter number of nodes
	 * @param n
	 */
	public void setN(int n) { this.n = n; }
	
	/**
	 * Getter graph
	 * @return graph
	 */
	public double[] getGraph() { return graph; }

	/**
	 * Setter graph.
	 * @param graph
	 */
	public void setGraph(double[] graph) { this.graph = graph; }
}
