package fr.isep.ii2415.tp2;

public class GraphAdjMatrix extends Graph {
	
	private final boolean [][] matrix;
	
	public GraphAdjMatrix(int n) {
		super();
		this.n = n;
		this.matrix = new boolean[n][n];
		
	}

	@Override
	public void addEdge(int u, int v) {
		m++;
		matrix[u-1][v-1] = true;
		matrix[v-1][u-1] = true;
	}

	@Override
	public void neighbors(int v) {
		System.out.print(v + " : ");
		for(int i = 0; i<matrix[v-1].length; i++) {
			if(matrix[v-1][i])
				System.out.print((i+1) + ",");
		}
		System.out.println();
	}

	@Override
	public int degree(int v) {
		int d = 0;
		for(int i = 0; i<matrix[v-1].length; i++)
			if(matrix[v][i]) d++;
		return d;
	}

	@Override
	public void printGraph() {
		for(int v=0; v<matrix.length;v++) {
			neighbors(v+1);
		}

	}

}
