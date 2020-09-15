package fr.isep.ii2415.tp2;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjList extends Graph {

	
	/**
	 * Cause Java can't create arrays of generics
	 * Each entry in this list is a list of neighbors for the corresponding node
	 */
	public final List<List<Integer>> neighbors;
	
	public GraphAdjList(int nbNodes) {
		super(nbNodes,0);
		this.neighbors = new ArrayList<>();	
		for(int i=0;i<n; i++)
			this.neighbors.add(new ArrayList<>());
	}
	
	public GraphAdjList() {
		super();
		this.neighbors = new ArrayList<>();
	}
	
	/**
	 * Adds the corresponding vertex if needed
	 * @param u
	 * @param v
	 */
	public void addEdge(int u, int v) {
		int mx = Integer.max(u, v);
		this.n =  mx > n ? mx : n;
		this.m++;
		while(mx > neighbors.size()) {
			neighbors.add(new ArrayList<>());
		}
		
		List<Integer> uNeighbors = neighbors.get(u-1); 
		
		uNeighbors.add(v);
		List<Integer> vNeighbors = neighbors.get(v-1);
		vNeighbors.add(u);
		
	}
	public void neighbors(int v) {
		System.out.println(v + " : " + neighbors.get(v-1).toString());
	}
	public int degree(int v) {
		return neighbors.get(v-1).size();
	}
	
	public void printGraph() {
		for(int i = 1; i <= n; i++)
			neighbors(i);
	}
	
	
	
}