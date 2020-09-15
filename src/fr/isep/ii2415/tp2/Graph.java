package fr.isep.ii2415.tp2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Scanner;

public abstract class Graph {
	
	
	private static Graph createGraphFomKeyboard(Graph g) {
		try(Scanner scan = new Scanner(System.in)) {
			System.out.print("Number of vertices ? ");
			int nbV = scan.nextInt();
			scan.nextLine();
			
			System.out.print("Number of edges ? ");
			int nbE = scan.nextInt();
			scan.nextLine();
			
			for(int i=0; i < nbE; i++) {
				System.out.print("Edge " + (i+1)+ " ? ");
				int u = scan.nextInt(),
						v = scan.nextInt();
				g.addEdge(u, v);
				scan.nextLine();
			} 
					
		} catch(Exception e) {
			e.printStackTrace();
		}
		return g;
	}
	
	
	private static Graph createGraphFromTextFile(String path, Graph g) {
		try(Scanner scan = new Scanner(FileSystems.getDefault().getPath(path))){
			while(scan.hasNextInt()) {
				int u = scan.nextInt(),
					v = scan.nextInt();
				g.addEdge(u, v);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	public static Graph createAdjListFromTextFile(String path) {
		return createGraphFromTextFile(path, new GraphAdjList());
	}
	
	public static Graph createAdjListFromKB() {
		return createGraphFomKeyboard(new GraphAdjList());
	}
	
	public static Graph createMatrixGraphFromTextFile(String path, int n) {
		return createGraphFromTextFile(path, new GraphAdjMatrix(n));
	}

	
	
	protected int n;
	protected int m;
	
	public Graph(int nbVertices, int nbEdges) {
		this.n= nbVertices;
		this.m = nbEdges;
	}
	
	public Graph() {
		this(0,0);
	}
	
	public int order() {
		return n;
	}
	
	public int size() {
		return m;
	}

	/**
	 * Adds the corresponding vertex if needed
	 * @param u
	 * @param v
	 */
	public abstract void addEdge(int u, int v);

	/** 
	 * Prints out the list of v's neighbors
	 * @param v
	 */
	public abstract void neighbors(int v);
		
	/**
	 * Returns the degree of node v
	 * @param v
	 * @return
	 */
	public abstract int degree(int v);
	
	/**
	 * Returns the average degree of the graph
	 * @return
	 */
	public float avgDegree() {
		if(order()==0) return 0.0f;
		
		return (2.0f * size()) / order();
	}
	
	public float density() {
		int o = order(); 
		if(o==0) return 0.0f;
		return (2.0f * size()) / (o*o);
	}
	
	public int minmax(boolean max) {
		if(size()==0) return 0;
		
		int m = degree(1);
		for(int v=2; v<=n;v++) {
			int d = degree(v);
			if(max)
				m = d > m ? d : m;
			else
				m = d < m ? d : m;	
		}
		return m;
	}
	
	
	public int minDegree() {
		return minmax(false);
	}
	public int maxDegree() {
		return minmax(true);
	}
	
	public abstract void printGraph();

	public static void main(String [] args) {
		System.out.println("Reading graph from file....");
		
		if(args.length < 1) {
			System.out.println("USAGE: java -cp ./bin/ fr.isep.ii2415.TP2.GraphAdjList /path/to/graph.txt");
			System.exit(0);
		}
			
		String fileName = args[0];
		Graph lg = Graph.createAdjListFromTextFile(fileName);
		
		lg.printGraph();
		
		System.out.println("QUESTION 1");
		System.out.println("===========");
		System.out.println("Avg: " + lg.avgDegree());
		System.out.println("Min: " + lg.minDegree());
		System.out.println("Max: " + lg.maxDegree());
		System.out.println("Dens: " + lg.density());
	}    
	
}
